package com.example.login.controller;

import com.example.login.model.request.UserRequest;
import com.example.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {

    private final UserService service;

    @PostMapping("/user")
    public void saveUser(@RequestBody @Valid UserRequest request) {
        service.addUser(request);
    }
}
