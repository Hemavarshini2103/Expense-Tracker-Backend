package com.hema.expensetracker.service;

import com.hema.expensetracker.dto.ExpenseResponse;
import com.hema.expensetracker.entity.Category;
import com.hema.expensetracker.entity.Expense;

import java.util.List;
import java.util.Map;

public interface ExpenseService {

    ExpenseResponse addExpense(Expense expense);

    Expense updateExpense(Long id, Expense expense);

    List<ExpenseResponse> getExpensesByUser();
    Expense getExpenseById(Long id);

    List<ExpenseResponse> getExpensesByCategory(Category category);

    void deleteExpense(Long id);

    double getTotalExpense();

    long getExpenseCount();

    double getAverageExpense();

    Map<Category, Double> getCategorySummary();

    Map<String, Double> getMonthlySummary();
}