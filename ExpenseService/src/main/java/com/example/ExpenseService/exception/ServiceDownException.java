package com.example.ExpenseService.exception;

public class ServiceDownException extends RuntimeException {
    public ServiceDownException(String message) {
        super(message);
    }

    public ServiceDownException(String message, Throwable cause) {
        super(message, cause);
    }
}
