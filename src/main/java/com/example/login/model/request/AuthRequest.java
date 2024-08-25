package com.example.login.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class AuthRequest {
    @NotEmpty(message = "user name is required")
    private String userName;
    @NotEmpty(message = "password is required")
    private String password;
}
