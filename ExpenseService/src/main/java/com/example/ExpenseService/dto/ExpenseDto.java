package com.example.ExpenseService.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExpenseDto(
        Long expenseId,
        Long userId,
        String name,
        String description,
        Long categoryId,
        Double amount,
        String currency,
        LocalDateTime expenseAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
