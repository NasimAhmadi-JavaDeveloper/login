package com.example.login.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class PatchUserRequest {
    private String password;
    private String email;
}