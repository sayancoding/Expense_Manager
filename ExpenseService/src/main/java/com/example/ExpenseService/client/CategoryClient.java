package com.example.ExpenseService.client;

import com.example.ExpenseService.component.CategoryClientFallback;
import com.example.ExpenseService.dto.CategoryDto;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${service.category.name}",
//        url = "${service.category.url}"
        path = "/api/category", fallback = CategoryClientFallback.class
)
public interface CategoryClient {
    @GetMapping("/{categoryId}")
    @LoadBalanced
    public CategoryDto getCategoryById(@PathVariable Long categoryId);
}

