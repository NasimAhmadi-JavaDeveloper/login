package com.example.login.exception;

import com.example.login.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred while sending OTP email: " + ex.getMessage());
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
}
