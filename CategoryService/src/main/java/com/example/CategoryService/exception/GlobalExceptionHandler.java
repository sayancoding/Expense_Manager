package com.example.CategoryService.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("Exception occurred: {}" , e.getMessage());
        return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        log.error("NotFoundException occurred: {}", e.getMessage());
        return ResponseEntity.status(404).body("Not found error occurred: " + e.getMessage());
    }
}
