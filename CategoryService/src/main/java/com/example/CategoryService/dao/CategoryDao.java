package com.example.CategoryService.dao;

import com.example.CategoryService.entity.Category;
import com.example.CategoryService.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
    List<Category> findByType(CategoryType type);
}
