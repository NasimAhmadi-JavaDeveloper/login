package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.exception.OtpEmailException;
import com.example.login.model.entity.Otp;
import com.example.login.model.entity.User;
import com.example.login.model.response.OtpResponse;
import com.example.login.model.response.VerifyResponse;
import com.example.login.repository.OtpRepository;
import com.example.login.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final JWTService jwtService;
    private final UserService userService;
    private final OtpRepository otpRepository;
    private final JavaMailSender mailSender;
    private static final int LOCK_TIME_MIN = 5;
    private static final int DEFAULT_REQUEST_COUNT = 1;
    private static final int OTP_EXPIRATION_MINUTES = 1;
    private static final int MAX_OTP_REQUESTS_PER_HOUR = 5;
    private static final int DEFAULT_FAILED_OTP_ATTEMPT = 0;

    public OtpResponse sendOtp(String email) {
        Optional<Otp> existOtp = otpRepository.findByEmail(email);

        if (existOtp.isPresent()) {
            return getActiveOtp(email)
                    .map(this::createResponseForActiveOtp)
                    .orElseGet(() -> updateOtpWithNewCode(existOtp.get()));
        } else {
            String otp = generateOtpAndSend(email);
            return new OtpResponse(otp);
        }
    }

    private String generateOtpAndSend(String email) {
        String otpCode = generateOtpCode();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES);

        Otp entity = Otp.builder()
                .otpCode(otpCode)
                .email(email)
                .expirationTime(expirationTime)
                .failedOtpAttempts(DEFAULT_FAILED_OTP_ATTEMPT)
                .otpRequestCount(DEFAULT_REQUEST_COUNT)
                .lockTimeDuration(null)
                .build();
        otpRepository.save(entity);
        sendOtpToEmail(email, otpCode);
        return otpCode;
    }

    public Optional<Otp> getActiveOtp(String email) {
        return otpRepository.findValidOtp(email, LocalDateTime.now());
    }

    private OtpResponse createResponseForActiveOtp(Otp activeOtp) {
        long secondsLeft = java.time.Duration.between(LocalDateTime.now(), activeOtp.getExpirationTime()).getSeconds();
        return new OtpResponse(String.format("Your current OTP is still valid. It will expire in %d seconds.", secondsLeft));
    }

    private OtpResponse updateOtpWithNewCode(Otp otp) {
        int newOtpRequestCount = otp.getOtpRequestCount() + 1;

        if (newOtpRequestCount >= MAX_OTP_REQUESTS_PER_HOUR) {
            otpCountLimitExceeded(otp, newOtpRequestCount);
        }

        Integer id = otp.getId();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES);
        String otpCode = generateOtpCode();

        otpRepository.updateOtp(id, expirationTime, otpCode, newOtpRequestCount);
        sendOtpToEmail(otp.getEmail(), otpCode);
        return new OtpResponse(otpCode);
    }

    private void otpCountLimitExceeded(Otp otp, int newOtpRequestCount) {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);

        if (otp.getUpdatedAt().isAfter(oneHourAgo)) {
            otp.setLockTimeDuration(LocalDateTime.now().plusMinutes(LOCK_TIME_MIN));
            otp.setOtpRequestCount(newOtpRequestCount);
            otpRepository.save(otp);
            throw new LogicalException(ExceptionSpec.USER_LOCKED);
        }
    }

    public String generateOtpCode() {
        int length = new Random().nextBoolean() ? 6 : 4;
        int max = (int) Math.pow(10, length) - 1;
        int min = (int) Math.pow(10, length - 1);
        return String.valueOf(min + new Random().nextInt(max - min + 1));
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

    private void sendOtpToEmail(String email, String otpCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText(String.format("Your OTP code is %s. It will expire in %d minutes.", otpCode, OTP_EXPIRATION_MINUTES));

        try {
            mailSender.send(message);
        } catch (MailAuthenticationException e) {
            throw new OtpEmailException("Authentication failed while sending OTP email.", e);
        } catch (MailSendException e) {
            throw new OtpEmailException("Failed to send OTP email.", e);
        } catch (MailException e) {
            throw new OtpEmailException("An error occurred while sending OTP email.", e);
        }
    }
}