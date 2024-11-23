package com.example.login.utils;

import com.example.login.model.entity.Otp;
import com.example.login.security.CustomUserDetails;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;

@UtilityClass
public class Utils {

    private static final String CHARACTERS = "ABC12789";
    private static final int PASSWORD_LENGTH = 8;
    private static final SecureRandom random = new SecureRandom();

    public String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }

    public boolean isOtpExpired(Otp otpEntity) {
        return otpEntity.getExpirationTime().isBefore(LocalDateTime.now());
    }

    public int getCurrentUserId() {
        return getCurrentUser().getId().intValue();
    }

    public CustomUserDetails getCurrentUser() {
        final Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (Objects.isNull(authentication)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid_token");
        }

        Object userDetails = authentication.getPrincipal();

        if (userDetails instanceof CustomUserDetails) {
            return (CustomUserDetails) userDetails;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid_token");
        }
    }
}
