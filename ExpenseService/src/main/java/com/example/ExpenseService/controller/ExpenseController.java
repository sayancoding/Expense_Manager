package com.example.ExpenseService.controller;

import com.example.ExpenseService.dto.ExpenseDto;
import com.example.ExpenseService.dto.ExpenseRequest;
import com.example.ExpenseService.service.ExpenseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
@Tag(name = "Expense Service", description = "APIs for managing expenses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public String createExpense(@RequestBody ExpenseRequest expenseRequest) {
        return expenseService.saveExpense(expenseRequest);
    }

    @GetMapping
    public List<ExpenseDto> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{expenseId}")
    public ExpenseDto getExpenseById(@PathVariable Long expenseId) {
        return expenseService.getExpenseById(expenseId);
    }

    @DeleteMapping("/{expenseId}")
    public String deleteExpense(@PathVariable Long expenseId) {
        return expenseService.deleteExpense(expenseId);
    }
    @PutMapping("/{expenseId}")
    public String updateExpense(@PathVariable Long expenseId, @RequestBody ExpenseRequest expenseRequest) {
        return expenseService.updateExpense(expenseId, expenseRequest);
    }
    @GetMapping("/user/{userId}")
    public List<ExpenseDto> getExpensesByUserId(@PathVariable Long userId) {
        return expenseService.getExpensesByUserId(userId);
    }

}
