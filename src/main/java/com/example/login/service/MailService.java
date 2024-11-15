package com.example.login.service;

import com.example.login.exception.OtpEmailException;
import com.example.login.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${otp.expiration.minutes}")
    private int otpExpirationMinutes;

    @Value("${otp.email.subject}")
    private String otpEmailSubject;

    @Value("${otp.email.message}")
    private String otpEmailMessage;

    @Value("${otp.forget.password.message}")
    private String forgetPasswordSubject;

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${app.reset-password-endpoint}")
    private String resetPasswordEndpoint;

    @Value("${app.reset-password-message}")
    private String resetPasswordMessage;

    private final MailSender mailSender;
    private final MailServiceJunction mailServiceJunction;

    @Retryable(
            value = {MailException.class},
            maxAttempts = 11,
            backoff = @Backoff(delay = 7000)
    )
    public void sendOtpToEmail(String email, String otpCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(otpEmailSubject);
        message.setText(String.format(otpEmailMessage, otpCode, otpExpirationMinutes));

        try {
            mailSender.send(message);
        } catch (MailAuthenticationException e) {
            throw new OtpEmailException("Authentication failed while sending OTP email", e);
        } catch (MailSendException e) {
            throw new OtpEmailException("Failed to send OTP email", e);
        } catch (MailException e) {
            throw new OtpEmailException("An error occurred while sending OTP email", e);
        }
    }

    @Retryable(
            value = {MailException.class},
            maxAttempts = 7,
            backoff = @Backoff(delay = 2000)
    )
    public void createNewPassword(String email) {
        String newPassword = Utils.generateRandomPassword();
        String otpCode = mailServiceJunction.createNewOtpWithNewPassword(email, newPassword);
        String resetLink = generateResetLink(otpCode, newPassword);
        sendMailInternal(email, resetLink);
    }

    private void sendMailInternal(String email, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(forgetPasswordSubject);
        String messageText = generateMessage(resetLink);
        message.setText(messageText);

        try {
            mailSender.send(message);
        } catch (MailAuthenticationException e) {
            throw new OtpEmailException("Authentication failed while sending OTP email", e);
        } catch (MailSendException e) {
            throw new OtpEmailException("Failed to send OTP email", e);
        } catch (MailException e) {
            throw new OtpEmailException("An error occurred while sending OTP email", e);
        }
    }

    public String generateResetLink(String otpCode, String newPassword) {
        return baseUrl + resetPasswordEndpoint
                .replace("{otpCode}", otpCode)
                .replace("{newPassword}", newPassword);
    }

    public String generateMessage(String resetLink) {
        return resetPasswordMessage.replace("{resetLink}", resetLink);
    }
}
