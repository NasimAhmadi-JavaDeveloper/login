package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.model.entity.Otp;
import com.example.login.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PasswordService {

    private final MailService emailService;
    private final OtpService otpService;
    private final UserService userService;

    public void createNewPassword(String email) {
        emailService.createNewPassword(email);
    }

    public void forgetPassword(String otp, String newPassword) {
        Otp entity = otpService.getOtp(otp);

        if (Utils.isOtpExpired(entity)) {
            throw new LogicalException(ExceptionSpec.INVALID_OTP);
        }
        userService.updatePassword(entity.getEmail(), newPassword);
    }
}
