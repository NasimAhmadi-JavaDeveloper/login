package com.example.login.controller;

import com.example.login.model.request.UserRequest;
import com.example.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {

    private final UserService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user")
    public void saveUser(@RequestBody @Valid UserRequest request) {
        service.addUser(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user/{userId}/unblock")
    public void unblockUser(@PathVariable int userId) {
        service.unblockUser(userId);
    }
}
