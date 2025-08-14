package com.example.ExpenseService.dto;

import lombok.Builder;


@Builder
public record CategoryDto(String categoryId, String userId, String name, String type, String description) { }
