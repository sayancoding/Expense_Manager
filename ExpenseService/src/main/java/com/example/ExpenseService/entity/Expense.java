package com.example.ExpenseService.entity;

import com.example.ExpenseService.enmus.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_expense")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;
    private Long userId;
    private String name;
    private String description;
    private Long categoryId;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private LocalDateTime expense_at;
}
