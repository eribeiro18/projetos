package io.github.eribeiro.quarkussocial.rest;

import io.github.eribeiro.quarkussocial.domain.model.Follower;
import io.github.eribeiro.quarkussocial.domain.model.User;
import io.github.eribeiro.quarkussocial.domain.repository.FollowerRepository;
import io.github.eribeiro.quarkussocial.domain.repository.UserRepository;
import io.github.eribeiro.quarkussocial.rest.dto.FollowerRequest;
import io.github.eribeiro.quarkussocial.rest.dto.FollowersPerUserResponse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class FollowerResourceTest {

    @Inject
    private UserRepository userRepository;

    @Inject
    private FollowerRepository followerRepository;

    @Inject
    private FollowerResource followerResource;

    private Long userId;
    private Long followerId;

    @BeforeEach
    @Transactional
    public void setUp(){
        //usuario seguido
        User user = this.buildUser("Fulano" , 30);
        this.userRepository.persist(user);
        userId = user.getId();

        //usuario seguidor
        User userFl = this.buildUser("Ciclano" , 12);
        this.userRepository.persist(userFl);
        followerId = userFl.getId();

        //criar um follower
        var follower = new Follower();
        follower.setFollower(userFl);
        follower.setUser(user);
        followerRepository.persist(follower);
    }

    private User buildUser(String name, Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @Test
    @DisplayName("should return 409 when follower Id is equal to User id")
    public void sameUserAsFollowerTest(){
        FollowerRequest request = FollowerRequest.builder()
                .followerId(userId)
                .build();
        Response response = followerResource.followUser(userId, request);
        assertEquals(Response.Status.CONFLICT.getStatusCode(),  response.getStatus());
    }

    @Test
    @DisplayName("should return 404 on follow a user when User id doesn't exist")
    public void userNotFoundWhenTryingToFollowTest(){
        FollowerRequest request = FollowerRequest.builder()
                .followerId(userId)
                .build();
        Response response = followerResource.followUser(0L, request);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),  response.getStatus());
    }

    @Test
    @DisplayName("should follow a user")
    public void followUserTest(){
        FollowerRequest request = FollowerRequest.builder()
                .followerId(followerId)
                .build();
        Response response = followerResource.followUser(userId, request);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(),  response.getStatus());
    }

    @Test
    @DisplayName("should return 404 on list user followers and User id doesn't exist")
    public void userNotFoundWhenListFollowersTest(){
        Response response = followerResource.listFollowers(0L);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),  response.getStatus());
    }

    @Test
    @DisplayName("should list a user's followers")
    public void listFollowersTest(){
        Response response = followerResource.listFollowers(userId);
        assertEquals(Response.Status.OK.getStatusCode(),  response.getStatus());
        FollowersPerUserResponse responseFollowers = (FollowersPerUserResponse) response.getEntity();
        assertEquals(1,  responseFollowers.getFollowersCount());
        assertEquals(1,  responseFollowers.getContent().size());
    }

    @Test
    @DisplayName("should Unfolow an user not exist")
    public void unfollowUserNotFoundTest(){
        Response response = followerResource.unfollowUser(0L, 0L);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),  response.getStatus());
    }

    @Test
    @DisplayName("should unfolow an user")
    public void unfollowUserTest(){
        Response response = followerResource.unfollowUser(userId, followerId);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(),  response.getStatus());
    }
}