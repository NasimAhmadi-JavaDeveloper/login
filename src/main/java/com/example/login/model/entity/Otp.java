package com.example.login.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = true)
@Table(name = "otp")
public class Otp extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^\\d{4}|\\d{6}$", message = "OTP code must be 4 or 6 digits")
    @Column(nullable = false, unique = true, length = 6)
    private String otpCode;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDateTime expirationTime;

    @Column(nullable = false)
    private Integer failedOtpAttempts;

    private LocalDateTime lockTimeDuration;

    @Column(nullable = false)
    private Integer otpRequestCount;

    @Column(nullable = false)
    private String newPassword;
}