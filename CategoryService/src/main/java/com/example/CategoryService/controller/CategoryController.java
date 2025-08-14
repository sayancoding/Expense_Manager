package com.example.CategoryService.controller;

import com.example.CategoryService.dto.CategoryDto;
import com.example.CategoryService.dto.CategoryRequest;
import com.example.CategoryService.entity.Category;
import com.example.CategoryService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public String createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/{categoryId}")
    public CategoryDto getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }
    @GetMapping("/type/{type}")
    public List<CategoryDto> getCategoriesByType(@PathVariable String type) {
        return categoryService.getCategoriesByType(type);
    }
    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
