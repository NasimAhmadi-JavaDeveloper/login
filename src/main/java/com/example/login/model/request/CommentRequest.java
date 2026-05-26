package com.example.login.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class CommentRequest {
    @NotEmpty(message = "Comment text is mandatory")
    private String comment;
}
