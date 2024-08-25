package com.example.login.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.login.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration:86400000}")
    private Long expiration;

    public String generateEncodeAccessToken(User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("userName", user.getUserName())
                .withClaim("email", user.getEmail())
                .withClaim("phone", user.getPhone())
                .withArrayClaim("roles", new String[]{user.getRole().name()})
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(HMAC512(jwtSecret));
    }

    public UserDetails decode(String token) {
        DecodedJWT decodedJWT = JWT.require(HMAC512(jwtSecret))
                .build()
                .verify(token);
        long userId = Long.parseLong(decodedJWT.getSubject());
        String userName = decodedJWT.getClaim("userName").asString();
        String email = decodedJWT.getClaim("email").asString();
        String phone = decodedJWT.getClaim("phone").asString();
        Set<GrantedAuthority> roles = decodeRoles(decodedJWT);
        return new CustomUserDetails(userName, "", roles, userId, email, phone);
    }

    private Set<GrantedAuthority> decodeRoles(DecodedJWT decodedJWT) {
        return Arrays.stream(decodedJWT.getClaim("roles").asArray(String.class))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
