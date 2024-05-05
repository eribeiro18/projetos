package io.github.eribeiro.quarkussocial.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class FieldError {

    private String field;
    private String message;
}
