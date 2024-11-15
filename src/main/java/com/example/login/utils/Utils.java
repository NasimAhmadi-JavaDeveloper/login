package com.example.login.utils;

import com.example.login.model.entity.Otp;
import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.time.LocalDateTime;

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
}
