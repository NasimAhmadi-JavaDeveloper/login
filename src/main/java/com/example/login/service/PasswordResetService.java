package com.example.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final OtpService otpService;
    private final MailService emailService;

    public void setNewPassword(String email) {
        String code = otpService.createNewOtp(email);
        String resetLink = "https://yourdomain.com/reset-password?token=" + code;
        emailService.sendOtpForgetPassword(email, code, resetLink);
    }
}
