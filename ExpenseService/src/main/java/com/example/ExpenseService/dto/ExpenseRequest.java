package com.example.ExpenseService.dto;

public record ExpenseRequest(
        Long userId,
        Long categoryId,
        String name,
        String description,
        Double amount,
        String currency,
        String expenseAt
) {
}
