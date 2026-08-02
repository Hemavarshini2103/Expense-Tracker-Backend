package com.hema.expensetracker.dto;

import jakarta.validation.constraints.Positive;

public class BudgetRequest {

    @Positive(message = "Monthly budget must be greater than 0")
    private double monthlyBudget;

    public BudgetRequest() {
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }
}