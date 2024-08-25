package com.example.login.model.request;

import com.example.login.enumeration.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@Accessors(chain = true)
public class UserRequest {
    @NotEmpty(message = "user name is required")
    private String userName;
    @NotEmpty(message = "password is required")
    private String password;
    @Email(message = "provide a valid email address")
    private String email;
    @Pattern(regexp = "9\\d{10}", message = "invalid phone")
    private String phone;
    @NotEmpty(message = "role is required")
    private Role role;
}