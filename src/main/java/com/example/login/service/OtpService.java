package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.model.entity.Otp;
import com.example.login.model.entity.User;
import com.example.login.model.response.OtpResponse;
import com.example.login.model.response.VerifyResponse;
import com.example.login.repository.OtpRepository;
import com.example.login.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

    @Value("${lock.time.minutes:5}")
    private int lockTimeMinutes;
    @Value("${otp.expiration.minutes:1}")
    private int otpExpirationMinutes;
    @Value("${otp.max.requests.per.hour:5}")
    private int maxOtpRequestsPerHour;

    private final JWTService jwtService;
    private final UserService userService;
    private final OtpRepository otpRepository;
    private final MailService mailService;
    private static final int DEFAULT_REQUEST_COUNT = 1;
    private static final int DEFAULT_FAILED_OTP_ATTEMPT = 0;

    public OtpResponse sendOtp(String email) {
        return otpRepository.findByEmail(email)
                .map(this::handleExistingOtp)
                .orElseGet(() -> sendNewOtp(email));
    }

    public OtpResponse handleExistingOtp(Otp existingOtp) {
        if (isOtpExpired(existingOtp)) {
            deleteExpiredOtp(existingOtp.getEmail());
            return sendNewOtp(existingOtp.getEmail());
        }
        return getActiveOtp(existingOtp.getEmail())
                .map(this::createResponseForActiveOtp)
                .orElseGet(() -> updateOtp(existingOtp));
    }

    private OtpResponse sendNewOtp(String email) {
        String otpCode = generateOtpCode();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(otpExpirationMinutes);

        Otp entity = Otp.builder()
                .otpCode(otpCode)
                .email(email)
                .expirationTime(expirationTime)
                .failedOtpAttempts(DEFAULT_FAILED_OTP_ATTEMPT)
                .otpRequestCount(DEFAULT_REQUEST_COUNT)
                .lockTimeDuration(null)
                .build();
        otpRepository.save(entity);
        mailService.sendOtpToEmail(email, otpCode);
        return new OtpResponse(otpCode);
    }

    public Optional<Otp> getActiveOtp(String email) {
        return otpRepository.findValidOtp(email, LocalDateTime.now());
    }

    private OtpResponse createResponseForActiveOtp(Otp activeOtp) {
        long secondsLeft = java.time.Duration.between(LocalDateTime.now(), activeOtp.getExpirationTime()).getSeconds();
        return new OtpResponse(String.format("Your current OTP is still valid. It will expire in %d seconds.", secondsLeft));
    }

    private OtpResponse updateOtp(Otp otp) {
        int newOtpRequestCount = otp.getOtpRequestCount() + 1;

        if (checkAndHandleOtpRequestLimit(otp, newOtpRequestCount)) {
            throw new LogicalException(ExceptionSpec.USER_LOCKED);
        }

        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(otpExpirationMinutes);
        otp.setExpirationTime(expirationTime);

        String otpCode = generateOtpCode();
        otp.setOtpCode(otpCode);
        otp.setOtpRequestCount(newOtpRequestCount);
        otpRepository.save(otp);

        mailService.sendOtpToEmail(otp.getEmail(), otpCode);
        return new OtpResponse(otpCode);
    }

    private boolean checkAndHandleOtpRequestLimit(Otp otp, int newOtpRequestCount) {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);

        if (otp.getUpdatedAt().isAfter(oneHourAgo) && newOtpRequestCount >= maxOtpRequestsPerHour) {
            otp.setLockTimeDuration(LocalDateTime.now().plusMinutes(lockTimeMinutes));
            otp.setOtpRequestCount(newOtpRequestCount);
            otpRepository.save(otp);
            return true;
        }

        return false;
    }

    public String generateOtpCode() {
        int otpCode = new Random().nextInt(900000) + 100000;
        return String.valueOf(otpCode);
    }

    public VerifyResponse verifyAndSendToken(String otp) {
        Otp otpEntity = otpRepository.findByOtpCode(otp)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.INVALID_OTP));

        if (isOtpExpired(otpEntity)) {
            throw new LogicalException(ExceptionSpec.EXPIRE_OTP);
        }

        if (isUserLocked(otpEntity)) {
            throw new LogicalException(ExceptionSpec.USER_LOCKED);
        }

        resetFailedAttemptsIfLockExpired(otpEntity);

        return generateToken(otpEntity);
    }

    private VerifyResponse generateToken(Otp otpEntity) {
        String email = otpEntity.getEmail();
        User user = userService.getUserByEmail(email);
        String token = jwtService.generateEncodeAccessToken(user);
        return VerifyResponse.builder()
                .token(token)
                .build();
    }

    private boolean isOtpExpired(Otp otpEntity) {
        return otpEntity.getExpirationTime().isBefore(LocalDateTime.now());
    }

    private boolean isUserLocked(Otp otpEntity) {
        return Objects.nonNull(otpEntity.getLockTimeDuration()) &&
                LocalDateTime.now().isBefore(otpEntity.getLockTimeDuration());
    }

    private void resetFailedAttemptsIfLockExpired(Otp otpEntity) {
        if (Objects.nonNull(otpEntity.getLockTimeDuration()) &&
                LocalDateTime.now().isAfter(otpEntity.getLockTimeDuration())) {
            otpEntity.setFailedOtpAttempts(0);
            otpEntity.setLockTimeDuration(null);
            otpRepository.save(otpEntity);
        }
    }

    private void deleteExpiredOtp(String email) {
        otpRepository.deleteByEmail(email);
    }
}