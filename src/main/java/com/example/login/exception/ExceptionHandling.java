package com.example.login.exception;

import com.example.login.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
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
        return createErrorResponse(e);
    }

    @ExceptionHandler(Throwable.class)
    public Object handleUnhandled(Exception e) {
        return mapException(e);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(LogicalException e) {
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
}
