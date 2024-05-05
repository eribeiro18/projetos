package io.github.eribeiro.quarkussocial.rest;

import io.github.eribeiro.quarkussocial.domain.model.Follower;
import io.github.eribeiro.quarkussocial.domain.model.User;
import io.github.eribeiro.quarkussocial.domain.repository.FollowerRepository;
import io.github.eribeiro.quarkussocial.domain.repository.PostRepository;
import io.github.eribeiro.quarkussocial.domain.repository.UserRepository;
import io.github.eribeiro.quarkussocial.rest.dto.CreatePostRequest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PostResourceTest {

    @Inject
    private FollowerRepository followerRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PostResource postResource;
    private Long userId;
    private Long userNotFollowerId;
    private Long userFollowerId;

    @BeforeEach
    @Transactional
    public void setUp(){
        //usuario seguido
        User user = this.buildUser("Fulano" , 30);
        this.userRepository.persist(user);
        userId = user.getId();

        //usuario não segue ninguem
        User userNotFollower = this.buildUser("Ciclano", 25);
        this.userRepository.persist(userNotFollower);
        userNotFollowerId = userNotFollower.getId();

        //usuario seguidor
        User userFollower = this.buildUser("Beltrano", 15);
        this.userRepository.persist(userFollower);
        userFollowerId = userFollower.getId();

        Follower follower = new Follower();
        follower.setFollower(userFollower);
        follower.setUser(user);
        followerRepository.persist(follower);
    }

    private CreatePostRequest postRquestBuild(){
        CreatePostRequest postRequest = CreatePostRequest.builder()
                .text("Dia de fazer churrasco!!!")
                .build();
        return postRequest;
    }

    private User buildUser(String name, Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @Test
    @DisplayName("should create a post for a user")
    public void createPostTest(){
        CreatePostRequest postRequest = this.postRquestBuild();
        Response response = postResource.savePost(userId, postRequest);
        assertEquals(201, response.getStatus());
    }

    @Test
    @DisplayName("should return 404 when trying to make a post for an inexistent user")
    public void inexistentUserTest(){
        CreatePostRequest postRequest = this.postRquestBuild();
        Response response = postResource.savePost(0L, postRequest);
        assertEquals(404, response.getStatus());
    }

    @Test
    @DisplayName("should return 404 when user doens't exist")
    public void listPostUserNotFoundTest(){
        Response response = postResource.listPost(0L, 0L);
        assertEquals(404, response.getStatus());
    }

    @Test
    @DisplayName("should return 400 when followerId header is not present")
    public void listPostFollowerHeaderNotSendTest(){
        CreatePostRequest postRequest = this.postRquestBuild();
        Response response = postResource.listPost(userId, null);
        assertEquals(400, response.getStatus());
        assertEquals("You forgot the header followerId!", String.valueOf(response.getEntity()));
    }

    @Test
    @DisplayName("should return 400 when follower doens't exist")
    public void listPostFollowerNotFoundTest(){
        CreatePostRequest postRequest = this.postRquestBuild();
        Response response = postResource.listPost(userId, 0L);
        assertEquals(400, response.getStatus());
        assertEquals("Inexistent followerId!", String.valueOf(response.getEntity()));
    }

    @Test
    @DisplayName("should return 403 when follower ins't a follower")
    public void listPostNotAFollowerTest(){
        CreatePostRequest postRequest = this.postRquestBuild();
        Response response = postResource.listPost(userId, userNotFollowerId);
        assertEquals(403, response.getStatus());
        assertEquals("You can't see these posts!", String.valueOf(response.getEntity()));
    }

    @Test
    @DisplayName("should list posts")
    public void listPostsTest(){
        CreatePostRequest postRequest = this.postRquestBuild();
        Response response = postResource.listPost(userId, userFollowerId);
        assertEquals(200, response.getStatus());
    }
}