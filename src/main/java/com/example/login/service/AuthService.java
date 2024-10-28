package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.model.entity.User;
import com.example.login.model.response.LoginResponse;
import com.example.login.security.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final RedisService redisService;

    public LoginResponse login(String userName, char[] password) {

        if (redisService.isUserLocked(userName)) {
            throw new LogicalException(ExceptionSpec.USER_LOCKED);
        }

        User user = userService.getUserByName(userName);

        if (passwordEncoder.matches(String.valueOf(password), String.valueOf(user.getPassword()))) {
            redisService.resetFailedAttempts(userName);
            String token = jwtService.generateEncodeAccessToken(user);
            return new LoginResponse(token);
        } else {
            //to use LOCKS, all methods needs to be transactional
            //but this one should not roll back on the error after
            redisService.increaseFailedAttempts(user.getUserName());
            throw new LogicalException(ExceptionSpec.UN_AUTHORIZED);
        }
    }
}
