package com.hema.expensetracker.controller;

import com.hema.expensetracker.dto.ExpenseResponse;
import com.hema.expensetracker.entity.Category;
import com.hema.expensetracker.entity.Expense;
import com.hema.expensetracker.service.ExpenseService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    // Constructor Injection
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Add Expense
    @PostMapping("/add")
    public ExpenseResponse addExpense(@Valid @RequestBody Expense expense) {

        return expenseService.addExpense(expense);
    }

    // Update Expense
    @PutMapping("/update/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody Expense expense) {

        return expenseService.updateExpense(id, expense);
    }

    // Get Logged-in User Expenses
    @GetMapping("/my")
    public List<ExpenseResponse> getMyExpenses() {

        return expenseService.getExpensesByUser();
    }
 // Get Expense By Id
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {

        return expenseService.getExpenseById(id);
    }

    // Delete Expense
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        expenseService.deleteExpense(id);

        return "Expense deleted successfully";
    }

    // Total Expense
    @GetMapping("/total")
    public double total() {

        return expenseService.getTotalExpense();
    }

    // Expense Count
    @GetMapping("/count")
    public long count() {

        return expenseService.getExpenseCount();
    }

    // Average Expense
    @GetMapping("/average")
    public double average() {

        return expenseService.getAverageExpense();
    }

    // Filter By Category
    @GetMapping("/category/{category}")
    public List<ExpenseResponse> byCategory(
            @PathVariable Category category) {

        return expenseService.getExpensesByCategory(category);
    }

    // Category Summary
    @GetMapping("/category-summary")
    public Map<Category, Double> categorySummary() {

        return expenseService.getCategorySummary();
    }

    // Monthly Summary
    @GetMapping("/monthly-summary")
    public Map<String, Double> monthlySummary() {

        return expenseService.getMonthlySummary();
    }
}