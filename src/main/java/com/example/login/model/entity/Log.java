package com.example.login.model.entity;

import com.example.login.enumeration.LogType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "logs")
@Accessors(chain = true)
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String requestBody;
    @Column(columnDefinition = "TEXT")
    private String requestQueryString;
    @Column(columnDefinition = "TEXT")
    private String responseBody;
    @Column(nullable = false)
    private long durationTimeInMs;
    @Column(columnDefinition = "TEXT")
    private String errorDesc;
    @Enumerated(EnumType.STRING)
    private LogType logType = LogType.APP_LOG;
    private String createdBy;
    private LocalDateTime createdOn;
    private String requestPath;
    private String requestMethod;
    @Column(nullable = false)
    private Boolean hasError;
    private String requestRemoteIp;
    @Column(columnDefinition = "TEXT")
    private String requestHeader;
    @Column(columnDefinition = "TEXT")
    private String responseHeader;
}
