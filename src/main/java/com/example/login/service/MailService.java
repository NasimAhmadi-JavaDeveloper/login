package com.example.login.service;

import com.example.login.exception.OtpEmailException;
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
    private final MailSender mailSender;
    @Value("${otp.forget.password.message}")
    private String forgetPasswordSubject;

    @Retryable(
            value = {MailException.class},
            backoff = @Backoff(delay = 2000)
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
            backoff = @Backoff(delay = 2000)
    )
    public void sendOtpForgetPassword(String email, String otpCode, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(forgetPasswordSubject);
        String messageText = String.format(otpEmailMessage, otpCode, otpExpirationMinutes) + "Click here to reset: " + resetLink;
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
}
