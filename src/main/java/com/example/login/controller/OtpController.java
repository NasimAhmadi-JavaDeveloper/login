package com.example.login.controller;

import com.example.login.model.response.OtpResponse;
import com.example.login.model.response.VerifyResponse;
import com.example.login.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/otp")
public class OtpController {

    private final OtpService otpService;

    @GetMapping("send-otp")
    public OtpResponse sendOtp(@RequestParam String email) {
        return otpService.sendOtp(email);
    }

    @PostMapping("/verify-otp")
    public VerifyResponse verifyOtp(@RequestParam String otp) {
        return otpService.verifyAndSendToken(otp);
    }
}
