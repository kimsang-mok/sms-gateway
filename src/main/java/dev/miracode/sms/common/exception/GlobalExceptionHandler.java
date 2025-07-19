package dev.miracode.sms.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ProblemDetail> handleValidation(MethodArgumentNotValidException ex,
                                                        HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    final Map<String, List<String>> errors = new HashMap<>();

    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.computeIfAbsent(error.getField(), key -> new ArrayList<>()).add(error.getDefaultMessage());
    }

    ProblemDetail problemDetail = ProblemDetailExt.forStatusDetailAndErrors(
        status,
        "Validation Failed",
        errors);

    return new ResponseEntity<>(problemDetail, status);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetail> handleExceptions(Exception ex, HttpServletRequest request) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    problemDetail.setTitle("Unexpected Error");
    problemDetail.setDetail(ex.getMessage());
    return ResponseEntity.internalServerError().body(problemDetail);
  }

}