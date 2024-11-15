package com.example.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final MailService emailService;

    public void createNewPassword(String email) {
        emailService.createNewPassword(email);
    }

}
