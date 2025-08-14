package com.example.CategoryService.entity;

import com.example.CategoryService.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private Long userId;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryType type;
}
