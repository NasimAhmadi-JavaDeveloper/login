package com.example.login.controller;

import com.example.login.model.response.UserResponse;
import com.example.login.security.CustomUserDetails;
import com.example.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService service;

    @GetMapping("me")
    public UserResponse findUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return service.getCurrentUser(userDetails.getId().intValue());
    }
}
