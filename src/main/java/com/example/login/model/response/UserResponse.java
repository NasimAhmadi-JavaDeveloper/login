package com.example.login.model.response;

import com.example.login.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String userName;
    private String email;
    private String phone;
    private Role role;
}
