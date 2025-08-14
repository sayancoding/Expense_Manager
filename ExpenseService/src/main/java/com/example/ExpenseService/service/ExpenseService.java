package com.example.ExpenseService.service;

import com.example.ExpenseService.client.CategoryClient;
import com.example.ExpenseService.dao.ExpenseDao;
import com.example.ExpenseService.dto.CategoryDto;
import com.example.ExpenseService.dto.ExpenseDto;
import com.example.ExpenseService.dto.ExpenseRequest;
import com.example.ExpenseService.enmus.Currency;
import com.example.ExpenseService.entity.Expense;
import com.example.ExpenseService.exception.NotFoundException;
import com.example.ExpenseService.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ExpenseService {
    @Autowired
    private ExpenseDao expenseDao;
    @Autowired
    private CategoryClient categoryClient;

    public String saveExpense(ExpenseRequest expenseRequest) {
        //Check category exists or not
        CategoryDto categoryDto = categoryClient.getCategoryById(expenseRequest.categoryId());
        if(categoryDto == null) {
            log.error("Category not found with id: {}", expenseRequest.categoryId());
            throw new NotFoundException("Category not found with id: " + expenseRequest.categoryId());
        }

        Expense expense = new Expense();
        expense.setUserId(expenseRequest.userId());
        expense.setCategoryId(expenseRequest.categoryId());
        expense.setName(expenseRequest.name());
        expense.setDescription(expenseRequest.description());
        expense.setAmount(expenseRequest.amount());
        expense.setCurrency(Currency.INR);
        expense.setExpense_at(expenseRequest.expenseAt() != null ? DateTimeUtil.toDateTime(expenseRequest.expenseAt()) : LocalDateTime.now());
        expense.setCreatedAt(LocalDateTime.now());
        expense.setUpdatedAt(LocalDateTime.now());

        expense = expenseDao.save(expense);
        log.info("Expense saved successfully: {}", expense.getCategoryId());
        return "Expense saved successfully with id: " + expense.getExpenseId();
    }
    public ExpenseDto getExpenseById(Long expenseId) {
        Expense expense = expenseDao.findById(expenseId)
                .orElseThrow(() -> new NotFoundException("Expense not found with id: " + expenseId));
        return ExpenseDto.builder()
                .expenseId(expense.getExpenseId())
                .userId(expense.getUserId())
                .name(expense.getName())
                .description(expense.getDescription())
                .categoryId(expense.getCategoryId())
                .amount(expense.getAmount())
                .currency(expense.getCurrency().name())
                .expenseAt(expense.getExpense_at())
                .createdAt(expense.getCreatedAt())
                .updatedAt(expense.getUpdatedAt())
                .build();
    }
    public String deleteExpense(Long expenseId) {
        Expense expense = expenseDao.findById(expenseId)
                .orElseThrow(() -> new NotFoundException("Expense not found with id: " + expenseId));
        expenseDao.delete(expense);
        log.info("Expense deleted successfully: {}", expense.getExpenseId());
        return "Expense deleted successfully with id: " + expense.getExpenseId();
    }
    public String updateExpense(Long expenseId, ExpenseRequest expenseRequest) {
        Expense expense = expenseDao.findById(expenseId)
                .orElseThrow(() -> new NotFoundException("Expense not found with id: " + expenseId));
        expense.setUserId(expenseRequest.userId());
        expense.setCategoryId(expenseRequest.categoryId());
        expense.setName(expenseRequest.name());
        expense.setDescription(expenseRequest.description());
        expense.setAmount(expenseRequest.amount());
        expense.setCurrency(Currency.INR);
        expense.setExpense_at(expenseRequest.expenseAt() != null ? DateTimeUtil.toDateTime(expenseRequest.expenseAt()) : LocalDateTime.now());
        expense.setUpdatedAt(LocalDateTime.now());

        expense = expenseDao.save(expense);
        log.info("Expense updated successfully: {}", expense.getExpenseId());
        return "Expense updated successfully with id: " + expense.getExpenseId();
    }
    public List<ExpenseDto> getAllExpenses() {
        return expenseDao.findAll().stream().map(expense ->
                ExpenseDto.builder()
                        .expenseId(expense.getExpenseId())
                        .userId(expense.getUserId())
                        .name(expense.getName())
                        .description(expense.getDescription())
                        .categoryId(expense.getCategoryId())
                        .amount(expense.getAmount())
                        .currency(expense.getCurrency().name())
                        .expenseAt(expense.getExpense_at())
                        .createdAt(expense.getCreatedAt())
                        .updatedAt(expense.getUpdatedAt())
                        .build()).toList();
    }
    public List<ExpenseDto> getExpensesByUserId(Long userId) {
        return expenseDao.findByUserId(userId).stream().map(expense ->
                ExpenseDto.builder()
                        .expenseId(expense.getExpenseId())
                        .userId(expense.getUserId())
                        .name(expense.getName())
                        .description(expense.getDescription())
                        .categoryId(expense.getCategoryId())
                        .amount(expense.getAmount())
                        .currency(expense.getCurrency().name())
                        .expenseAt(expense.getExpense_at())
                        .createdAt(expense.getCreatedAt())
                        .updatedAt(expense.getUpdatedAt())
                        .build()).toList();
    }

}
