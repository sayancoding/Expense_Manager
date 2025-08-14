package com.example.userService.exception;

import com.example.userService.dto.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AlreadyExistException.class,NotFoundException.class})
    public ErrorResponse generalHandler(Exception exception){
        return ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .build();
    }
}
