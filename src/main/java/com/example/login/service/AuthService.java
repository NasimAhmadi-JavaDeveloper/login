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

    public LoginResponse login(String userName, char[] password) {
        User user = userService.getUserByName(userName);

        try {
            if (!userService.unlockWhenTimeExpired(user)) {
                throw new LogicalException(ExceptionSpec.USER_LOCKED);
            }

            if (passwordEncoder.matches(String.valueOf(password), String.valueOf(user.getPassword()))) {
                userService.resetFailedAttempts(userName);
                String token = jwtService.generateEncodeAccessToken(user);
                return new LoginResponse(token);
            } else {
                userService.loginFailed(user);
                throw new LogicalException(ExceptionSpec.UN_AUTHORIZED);
            }
        } finally {
            clearPassword(password);
        }
    }

    private void clearPassword(char[] password) {
        if (password != null) {
            java.util.Arrays.fill(password, '\0');
        }
    }
}
