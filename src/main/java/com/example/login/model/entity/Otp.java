package com.example.login.model.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "otp")
public class Otp {
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

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Integer failedOtpAttempts;

    private LocalDateTime lockTimeDuration;

    @Column(nullable = false)
    private Integer otpRequestCount;

    @Version
    @ColumnDefault("0")
    private Integer version;
}