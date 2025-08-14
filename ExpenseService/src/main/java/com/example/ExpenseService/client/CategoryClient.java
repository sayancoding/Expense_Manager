package com.example.ExpenseService.client;

import com.example.ExpenseService.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category-service", url = "${service.category.url}/api/category")
public interface CategoryClient {
    @GetMapping("/{categoryId}")
    public CategoryDto getCategoryById(@PathVariable Long categoryId);
}

