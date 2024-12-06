package com.example.login.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Async
    public void sendNotificationEmail(String email, String message) {
        try {
            Thread.sleep(5000);
            System.out.println("Email sent to: " + email + " with message: " + message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
