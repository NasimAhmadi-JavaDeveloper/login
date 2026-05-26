package com.example.login.security;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Hidden
@Getter
public class CustomUserDetails extends User {

    private final Integer id;
    private final String email;
    private final String phone;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer id, String email, String phone) {
        super(username, password, authorities);
        this.id = id;
        this.email = email;
        this.phone = phone;
    }
}
