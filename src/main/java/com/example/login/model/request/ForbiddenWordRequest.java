package com.example.login.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class ForbiddenWordRequest {

    @NotEmpty(message = "word cannot be empty")
    private String word;
}