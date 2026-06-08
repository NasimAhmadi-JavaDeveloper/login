package com.example.login.exception.handler;

import com.example.login.exception.LogicalException;
import com.example.login.exception.OtpEmailException;
import com.example.login.model.enums.ExceptionSpec;
import com.example.login.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.codec.DecodingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.TimeoutException;

@Slf4j
@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LogicalException.class)
    public Object handleBusinessExceptions(LogicalException e) {
        log.warn("Business Error Occurred! {}", e.getMessage());
        return mapBusinessException(e);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Object handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.warn("Uk exception Occurred! {}", e.getMessage());
        return mapDataIntegrityViolation(e);
    }

    @ExceptionHandler(Throwable.class)
    public Object handleUnhandled(Exception e) {
        return mapException(e);
    }

    @ExceptionHandler(OtpEmailException.class)
    public ResponseEntity<String> handleOtpEmailException(OtpEmailException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred while sending OTP email: " + ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Object handleAccessDeniedException(AccessDeniedException ex) {
        return mapAccessDeniedException(ex);
    }

    @ExceptionHandler(WebClientRequestException.class)
    public ResponseEntity<String> handleConnectionError(WebClientRequestException ex) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Connection Error");
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleResponseError(WebClientResponseException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body("Remote Service Error");
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<String> handleTimeout(TimeoutException ex) {
        return ResponseEntity
                .status(HttpStatus.GATEWAY_TIMEOUT)
                .body("Request Timeout");
    }

    @ExceptionHandler(DecodingException.class)
    public ResponseEntity<ErrorResponse> handleDecodingException(DecodingException ex) {
        log.error("Response Decoding Error", ex);

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(new ErrorResponse("Invalid response received from remote service"));
    }

    private ResponseEntity<ErrorResponse> mapBusinessException(LogicalException e) {
        return ResponseEntity
                .status(e.getSpecs().getHttpStatus())
                .body(new ErrorResponse(e.getSpecs().getMessage()));
    }

    private Object mapException(Exception e) {
        log.error("Exception Occurred!", e);
        return ResponseEntity
                .status(ExceptionSpec.SERVER_ERROR.getHttpStatus())
                .body(new ErrorResponse(ExceptionSpec.SERVER_ERROR.getMessage()));
    }

    private Object mapDataIntegrityViolation(Exception e) {
        log.error("Exception Occurred!", e);
        return ResponseEntity
                .status(ExceptionSpec.CONSTRAINT.getHttpStatus())
                .body(new ErrorResponse(ExceptionSpec.CONSTRAINT.getMessage()));
    }

    private Object mapAccessDeniedException(Exception e) {
        log.error("Exception Occurred!", e);
        return ResponseEntity
                .status(ExceptionSpec.INVALID_ROLE.getHttpStatus())
                .body(new ErrorResponse(ExceptionSpec.INVALID_ROLE.getMessage()));
    }
}
