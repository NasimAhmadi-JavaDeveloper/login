package com.example.login.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionSpec {

    UN_AUTHORIZED("unauthorized", HttpStatus.UNAUTHORIZED),
    CONSTRAINT("already exist", HttpStatus.CONFLICT),
    CRYPTO_CONVERTER("crypto converter", HttpStatus.NO_CONTENT),
    USER_NOT_FOUND("user not found", HttpStatus.NOT_FOUND),
    SERVER_ERROR("Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_LOCKED("User account is locked. Please try again later", HttpStatus.LOCKED),
    ;
    private final String message;
    private final HttpStatus httpStatus;
}
