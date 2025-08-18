package com.example.ExpenseService.component;

import com.example.ExpenseService.client.CategoryClient;
import com.example.ExpenseService.dto.CategoryDto;
import com.example.ExpenseService.exception.ServiceDownException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CategoryClientFallback implements CategoryClient {
    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        log.error("Category service is down or not reachable. CategoryClientFallback method called for categoryId: {}", categoryId);
        throw new ServiceDownException("Category service is currently unavailable. Please try again later.");
    }
}
