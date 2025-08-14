package com.example.CategoryService.dto;

import lombok.Builder;
import lombok.Data;


@Builder
public record CategoryDto(String categoryId, String userId, String name, String type, String description) { }
