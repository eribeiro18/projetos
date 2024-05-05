package io.github.eribeiro.quarkussocial.rest.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FollowerRequest {

    private Long followerId;
}

