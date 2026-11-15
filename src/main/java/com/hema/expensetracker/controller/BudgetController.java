package com.hema.expensetracker.controller;

import com.hema.expensetracker.dto.BudgetRequest;
import com.hema.expensetracker.dto.BudgetResponse;
import com.hema.expensetracker.service.BudgetService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import com.hema.expensetracker.service.AuthService;
import org.springframework.security.core.Authentication;
@RestController
@RequestMapping("/budget")
public class BudgetController {

    private final BudgetService budgetService;
    private final AuthService authService;

    // Constructor Injection
    public BudgetController(BudgetService budgetService, AuthService authService) {
        this.budgetService = budgetService;
        this.authService = authService;
    }

    // Set Budget
    @PostMapping("/set")
    public String setBudget(
            Authentication authentication,
            @Valid @RequestBody BudgetRequest request) {

        int userId = authService.getUserId(authentication.getName());

        budgetService.setBudget(userId, request);

        return "Budget set successfully";
    }

    // Get Budget Status
    @GetMapping("/status")
    public BudgetResponse getBudgetStatus(Authentication authentication) {

        int userId = authService.getUserId(authentication.getName());

        return budgetService.getBudgetStatus(userId);
    }
}