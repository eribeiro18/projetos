package io.github.eribeiro.quarkussocial.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreatePostRequest {

    @NotBlank(message = "Name is required")
    private String text;
}
