package io.github.eribeiro.quarkussocial.rest;

import io.github.eribeiro.quarkussocial.domain.model.User;
import io.github.eribeiro.quarkussocial.domain.repository.UserRepository;
import io.github.eribeiro.quarkussocial.rest.dto.CreateUserRequest;
import io.github.eribeiro.quarkussocial.rest.dto.ResponseError;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserResourceTest {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserResource userResource;

    @Test
    @DisplayName("should create an user successfully")
    @Order(1)
    public void createUserTest(){
        CreateUserRequest userRequest = this.buildCreateUserRequest("Fulano", 30);
        Response response = userResource.createUser(userRequest);
        assertEquals(201, response.getStatus());
        User userToSave = JsonbBuilder.create().fromJson(String.valueOf(response.getEntity()), User.class);
        assertNotNull(userToSave.getName());
    }

    private CreateUserRequest buildCreateUserRequest(String name, Integer age){
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .name(name)
                .age(age)
                .build();
        return userRequest;
    }

    @Test
    @DisplayName("should return error when json is not valid")
    @Order(2)
    public void createUserValidationErrorTest(){
        CreateUserRequest userRequest = this.buildCreateUserRequest(null, null);
        Response response = userResource.createUser(userRequest);
        assertEquals(ResponseError.UNPROCESSABLE_ENTITY_STATUS, response.getStatus());
        ResponseError responseError = (ResponseError) response.getEntity();
        assertEquals("Validation Error", responseError.getMessage());
        assertEquals("Name is required", responseError.getErrors().get(0).getMessage());
        assertEquals("Age is required", responseError.getErrors().get(1).getMessage());
    }

    @Test
    @DisplayName("should list all users")
    @Order(3)
    public void listAllUsersTest(){
        Response response = userResource.listAllUsers();
        assertEquals(200, response.getStatus());
    }
}