package io.github.eribeiro.quarkussocial.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class FollowersPerUserResponse {

    private Integer followersCount;
    private List<FollowerResponse> content;
}
