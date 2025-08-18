package com.example.ExpenseService.dto;

import lombok.Builder;


@Builder
public record CategoryDto(Long categoryId, Long userId, String name, String type, String description) { }
