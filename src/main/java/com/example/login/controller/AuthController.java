package com.example.login.controller;

import com.example.login.model.request.AuthRequest;
import com.example.login.model.response.LoginResponse;
import com.example.login.service.AuthService;
import lombok.RequiredArgsConstructor;
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

  @PostMapping("login")
  public LoginResponse loginUser(@RequestBody @Valid AuthRequest request) {
    return authService.login(request.getUserName(),request.getPassword());
  }
}
