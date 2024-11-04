package com.example.login.repository;

import com.example.login.model.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Integer> {

    @Query(value = "SELECT o FROM Otp o WHERE o.email = :email AND o.expirationTime > :currentDateTime")
    Optional<Otp> findValidOtp(@Param("email") String email, @Param("currentDateTime") LocalDateTime currentDateTime);

    Optional<Otp> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Otp o SET o.expirationTime = :expirationTime, o.otpCode = :otpCode, o.otpRequestCount = :newOtpRequestCount WHERE o.id = :id")
    void updateOtp(@Param("id") Integer id,
                               @Param("expirationTime") LocalDateTime expirationTime,
                               @Param("otpCode") String otpCode,
                               @Param("newOtpRequestCount") int newOtpRequestCount);

    Optional<Otp> findByOtpCode(String otpCode);

}