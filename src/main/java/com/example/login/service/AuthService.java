package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.model.response.LoginResponse;
import com.example.login.repository.UserRepository;
import com.example.login.security.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public LoginResponse login(String userName, String password) {
        return userRepository.findByUserName(userName)
                .filter(entity -> passwordEncoder.matches(password, entity.getPassword()))
                .map(entity -> new LoginResponse(jwtService.generateEncodeAccessToken(entity)))
                .orElseThrow(() -> new LogicalException(ExceptionSpec.UN_AUTHORIZED));
    }
}
