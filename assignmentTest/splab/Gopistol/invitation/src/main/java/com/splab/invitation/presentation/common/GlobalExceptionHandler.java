package com.splab.invitation.presentation.common;

import jakarta.persistence.EntityNotFoundException;
import jakarta.xml.bind.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public ApiResponse<Void> handleIllegalArgumentException(IllegalArgumentException exception) {
    return ApiResponse.failure(ResponseType.BAD_REQUEST, exception.getMessage());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(EntityNotFoundException.class)
  public ApiResponse<Void> handleEntityNotFoundException(EntityNotFoundException exception) {
    return ApiResponse.failure(ResponseType.BAD_REQUEST, exception.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ApiResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    return ApiResponse.failure(ResponseType.BAD_REQUEST, exception.getMessage());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(RuntimeException.class)
  public ApiResponse<Void> handleRuntimeException(RuntimeException exception) {
    return ApiResponse.failure(ResponseType.INTERNAL_SERVER_ERROR, exception.getMessage());
  }
}
