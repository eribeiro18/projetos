package io.github.eribeiro.quarkussocial.rest;

import io.github.eribeiro.quarkussocial.domain.model.Post;
import io.github.eribeiro.quarkussocial.domain.model.User;
import io.github.eribeiro.quarkussocial.domain.repository.FollowerRepository;
import io.github.eribeiro.quarkussocial.domain.repository.PostRepository;
import io.github.eribeiro.quarkussocial.domain.repository.UserRepository;
import io.github.eribeiro.quarkussocial.rest.dto.CreatePostRequest;
import io.github.eribeiro.quarkussocial.rest.dto.PostResponse;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.stream.Collectors;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final FollowerRepository followerRepository;

    @Inject
    public PostResource(UserRepository userRepository,
                        PostRepository postRepository,
                        FollowerRepository followerRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.followerRepository = followerRepository;
    }

    @POST
    @Transactional
    public Response savePost(@PathParam("userId") Long userId, CreatePostRequest postRequest){
        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Post post = new Post();
        post.setText(postRequest.getText());
        post.setUser(user);
        postRepository.persist(post);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listPost(
            @PathParam("userId") Long userId,
            @HeaderParam("followerId") Long followerId){
        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if(followerId == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("You forgot the header followerId!")
                    .build();
        }
        User follower = userRepository.findById(followerId);
        if(follower == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Inexistent followerId!")
                    .build();
        }
        boolean follows = followerRepository.follows(follower, user);
        if(!follows){
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("You can't see these posts!")
                    .build();
        }
        PanacheQuery<Post> query = postRepository.find(
                "user", Sort.by("dateTime", Sort.Direction.Descending), user);
        var list = query.list();
        var postResponseList = list.stream()
                .map(PostResponse::fromEntity)
                .collect(Collectors.toList());
        return Response.ok(postResponseList).build();
    }
}
