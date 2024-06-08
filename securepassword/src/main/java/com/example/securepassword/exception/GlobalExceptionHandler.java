package com.example.securepassword.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleAllExceptions(Exception e) {
        return new ResponseEntity<>("Um erro inesperado ocorreu: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
