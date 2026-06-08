package com.example.login.exception;

import com.example.login.model.enums.ExceptionSpec;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LogicalException extends RuntimeException {

  private final ExceptionSpec specs;

  @Override
  public String getMessage() {
    return specs.getMessage();
  }
}
