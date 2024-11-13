package com.example.login.controller;

import com.example.login.model.request.AuthRequest;
import com.example.login.model.request.ForgetPasswordRequest;
import com.example.login.model.response.LoginResponse;
import com.example.login.service.AuthService;
import com.example.login.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final PasswordResetService passwordResetService;

    @PostMapping("login")
    public LoginResponse loginUser(@RequestBody @Valid AuthRequest request) {
        return authService.login(request.getUserName(), request.getPassword());
    }

    @PostMapping("/forget-password")
    public ResponseEntity<Void> forgetPassword(@RequestBody @Valid ForgetPasswordRequest request) {
        passwordResetService.setNewPassword(request.getEmail());
        return ResponseEntity.noContent().build();
    }
}
