package com.example.login.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionSpec {

    UN_AUTHORIZED("unauthorized", HttpStatus.UNAUTHORIZED),
    CONSTRAINT("The entered value already exists.", HttpStatus.CONFLICT),
    CRYPTO_CONVERTER("crypto converter", HttpStatus.NO_CONTENT),
    USER_NOT_FOUND("user not found", HttpStatus.NOT_FOUND),
    SERVER_ERROR("Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_LOCKED("User account is locked. Please try again later", HttpStatus.LOCKED),
    VALIDATE("invalidate field", HttpStatus.BAD_REQUEST),
    USER_NOT_EMPTY("userName cannot be null or empty", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_EMPTY("Invalid email format or cannot be null or empty", HttpStatus.BAD_REQUEST),
    PHONE_NOT_EMPTY("Invalid phone number format", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_EMPTY("Invalid phone number format", HttpStatus.BAD_REQUEST),
    EXPIRE_OTP("OTP code has expired", HttpStatus.BAD_REQUEST),
    OTP_NOT_FOUND("OTP does not exist for this user", HttpStatus.BAD_REQUEST),
    INVALID_OTP("The OTP code has expired", HttpStatus.BAD_REQUEST),
    POST_NOT_FOUND("post not found", HttpStatus.NOT_FOUND),
    INVALID_ROLE("invalid role", HttpStatus.BAD_REQUEST),
    COMMENT_NOT_FOUND("comment not found", HttpStatus.BAD_REQUEST),
    NOT_YOUR_COMMENT("not_your_comment", HttpStatus.BAD_REQUEST),
    SELF_FOLLOW_NOT_ALLOWED("You cannot follow yourself", HttpStatus.BAD_REQUEST),
    ALREADY_FOLLOWING_USER("Already following this user",HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND("Category not found" ,HttpStatus.BAD_REQUEST),

    ;
    private final String message;
    private final HttpStatus httpStatus;
}
