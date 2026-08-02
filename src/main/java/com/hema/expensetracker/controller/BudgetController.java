package com.hema.expensetracker.controller;

import com.hema.expensetracker.dto.BudgetRequest;
import com.hema.expensetracker.dto.BudgetResponse;
import com.hema.expensetracker.service.BudgetService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    private final BudgetService budgetService;

    // Constructor Injection
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    // Set Budget
    @PostMapping("/set/{userId}")
    public String setBudget(
            @PathVariable int userId,
            @Valid @RequestBody BudgetRequest request) {

        budgetService.setBudget(userId, request);

        return "Budget set successfully";
    }

    // Get Budget Status
    @GetMapping("/status/{userId}")
    public BudgetResponse getBudgetStatus(
            @PathVariable int userId) {

        return budgetService.getBudgetStatus(userId);
    }
}