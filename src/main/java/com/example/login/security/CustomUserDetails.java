package com.example.login.security;

import io.swagger.v3.oas.annotations.Hidden;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Hidden
@Getter
public class CustomUserDetails extends User {

    private final Number id;
    private final String email;
    private final String phone;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, long id, String email, String phone) {
        super(username, password, authorities);
        this.id = id;
        this.email = email;
        this.phone = phone;
    }
}
