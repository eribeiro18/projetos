package io.github.eribeiro.quarkussocial.rest.dto;

import io.github.eribeiro.quarkussocial.domain.model.Follower;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FollowerResponse {

    private Long id;
    private String name;

    public FollowerResponse(Follower follower){
        this(follower.getId(), follower.getFollower().getName());
    }

    public FollowerResponse(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
