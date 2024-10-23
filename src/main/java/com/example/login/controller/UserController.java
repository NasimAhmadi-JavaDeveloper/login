package com.example.login.controller;

import com.example.login.model.response.UserResponse;
import com.example.login.security.CustomUserDetails;
import com.example.login.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService service;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("me")
    public UserResponse findUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return service.getCurrentUser(userDetails.getId().intValue());
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping(value = "/{userId}")
    public void updateUser(@PathVariable Integer userId, @RequestBody String json) throws JsonProcessingException {
        service.patchUser(userId, json);
    }

    @GetMapping("/id/{id}")
    public UserResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/email/{email}")
    public UserResponse findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        service.deleteUser(id);
    }
}
