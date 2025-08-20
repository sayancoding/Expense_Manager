package com.example.CategoryService.service;

import com.example.CategoryService.dao.CategoryDao;
import com.example.CategoryService.dto.CategoryDto;
import com.example.CategoryService.dto.CategoryRequest;
import com.example.CategoryService.entity.Category;
import com.example.CategoryService.enums.CategoryType;
import com.example.CategoryService.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public String createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());
        category.setDescription(categoryRequest.description());
        category.setType(CategoryType.INCOME.getType().equalsIgnoreCase(categoryRequest.type())
                ? CategoryType.INCOME : CategoryType.EXPENSE);
        category.setUserId(categoryRequest.userId());
        categoryDao.save(category);
        log.info("Created Category : {}", category);
        return "Category created successfully with id: " + category.getCategoryId();
    }

    public CategoryDto getCategoryById(Long categoryId) throws InterruptedException {
        Category category =  categoryDao.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + categoryId));
        log.info("Start fetching category with id: {}", categoryId);
        log.info("Retrying..");
        Thread.sleep(1000);
        log.info("End fetching category with id: {}", categoryId);
        return CategoryDto.builder()
                .name(category.getName())
                .categoryId(category.getCategoryId().toString())
                .userId(category.getUserId().toString())
                .type(category.getType().getType())
                .description(category.getDescription())
                .build();
    }

    public List<CategoryDto> getCategoriesByType(String type) {
        CategoryType categoryType = CategoryType.INCOME.getType().equalsIgnoreCase(type)
                ? CategoryType.INCOME : CategoryType.EXPENSE;
        return categoryDao.findByType(categoryType).stream().map(category ->
            CategoryDto.builder()
                .name(category.getName())
                .categoryId(category.getCategoryId().toString())
                .userId(category.getUserId().toString())
                .type(category.getType().getType())
                .description(category.getDescription())
                .build()
        ).toList();
    }
    public List<CategoryDto> getAllCategories() {
        return categoryDao.findAll().stream().map(category ->
                CategoryDto.builder()
                        .name(category.getName())
                        .categoryId(category.getCategoryId().toString())
                        .userId(category.getUserId().toString())
                        .type(category.getType().getType())
                        .description(category.getDescription())
                        .build()
        ).toList();
    }
    public String deleteCategory(Long categoryId) {
        Category category = categoryDao.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + categoryId));
        categoryDao.delete(category);
        log.info("Deleted Category with id: {}", categoryId);
        return "Category deleted successfully with id: " + categoryId;
    }

}
