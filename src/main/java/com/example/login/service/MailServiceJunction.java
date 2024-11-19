package com.example.login.service;

import com.example.login.model.entity.Otp;
import com.example.login.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceJunction {

    private final OtpRepository otpRepository;
    private static final int DEFAULT_REQUEST_COUNT = 1;
    private static final int DEFAULT_FAILED_OTP_ATTEMPT = 0;

    @Value("${otp.expiration.minutes:1}")
    private int otpExpirationMinutes;

    public String createNewOtpWithNewPassword(String email, String newPassword) {

        String otpCode = generateOtpCode();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(otpExpirationMinutes);

        Otp entity = Otp.builder()
                .otpCode(otpCode)
                .email(email)
                .newPassword(newPassword)
                .expirationTime(expirationTime)
                .failedOtpAttempts(DEFAULT_FAILED_OTP_ATTEMPT)
                .otpRequestCount(DEFAULT_REQUEST_COUNT)
                .lockTimeDuration(null)
                .build();
        otpRepository.save(entity);
        return otpCode;
    }

    public String generateOtpCode() {
        int otpCode = new Random().nextInt(900000) + 100000;
        return String.valueOf(otpCode);
    }
}
