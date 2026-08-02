package com.hema.expensetracker.service;

import com.hema.expensetracker.dto.ExpenseResponse;
import com.hema.expensetracker.entity.Category;
import com.hema.expensetracker.entity.Expense;
import com.hema.expensetracker.entity.User;
import com.hema.expensetracker.exception.ResourceNotFoundException;
import com.hema.expensetracker.repository.ExpenseRepository;
import com.hema.expensetracker.security.SecurityUtil;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final SecurityUtil securityUtil;

    // Constructor Injection
    public ExpenseServiceImpl(
            ExpenseRepository expenseRepository,
            SecurityUtil securityUtil) {

        this.expenseRepository = expenseRepository;
        this.securityUtil = securityUtil;
    }

    // Add Expense
    @Override
    public ExpenseResponse addExpense(Expense expense) {

        User user = securityUtil.getCurrentUser();

        expense.setUser(user);

        Expense savedExpense = expenseRepository.save(expense);

        return map(savedExpense);
    }

    // Get Logged-in User Expenses
    @Override
    public List<ExpenseResponse> getExpensesByUser() {

        User user = securityUtil.getCurrentUser();

        return expenseRepository.findByUserId(user.getId())
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
    @Override
    public Expense getExpenseById(Long id) {

        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    // Update Expense
    @Override
    public Expense updateExpense(Long id, Expense expense) {

        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Expense not found with id : " + id));

        existingExpense.setTitle(expense.getTitle());
        existingExpense.setAmount(expense.getAmount());
        existingExpense.setCategory(expense.getCategory());
        existingExpense.setExpenseDate(expense.getExpenseDate());

        return expenseRepository.save(existingExpense);
    }

    // Delete Expense
    @Override
    public void deleteExpense(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Expense not found with id : " + id));

        expenseRepository.delete(expense);
    }

    // Total Expense
    @Override
    public double getTotalExpense() {

        User user = securityUtil.getCurrentUser();

        return expenseRepository.getTotalExpenseByUser(user.getId());
    }

    // Expense Count
    @Override
    public long getExpenseCount() {

        User user = securityUtil.getCurrentUser();

        return expenseRepository.getExpenseCountByUser(user.getId());
    }

    // Average Expense
    @Override
    public double getAverageExpense() {

        User user = securityUtil.getCurrentUser();

        return expenseRepository.getAverageExpenseByUser(user.getId());
    }
    // Get Expenses By Category
    @Override
    public List<ExpenseResponse> getExpensesByCategory(Category category) {

        User user = securityUtil.getCurrentUser();

        return expenseRepository.findByUserIdAndCategory(user.getId(), category)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    // Category Summary
    @Override
    public Map<Category, Double> getCategorySummary() {

        User user = securityUtil.getCurrentUser();

        List<Object[]> results =
                expenseRepository.getCategorySummary(user.getId());

        Map<Category, Double> summary = new HashMap<>();

        for (Object[] row : results) {

            summary.put(
                    (Category) row[0],
                    ((Number) row[1]).doubleValue());
        }

        return summary;
    }

    // Monthly Summary
    @Override
    public Map<String, Double> getMonthlySummary() {

        User user = securityUtil.getCurrentUser();

        List<Object[]> results =
                expenseRepository.getMonthlySummary(user.getId());

        Map<String, Double> summary = new LinkedHashMap<>();

        for (Object[] row : results) {

            Integer year = (Integer) row[0];
            Integer month = (Integer) row[1];
            Double total = ((Number) row[2]).doubleValue();

            summary.put(
                    year + "-" + String.format("%02d", month),
                    total);
        }

        return summary;
    }

    // Convert Entity to DTO
    private ExpenseResponse map(Expense expense) {

        ExpenseResponse response = new ExpenseResponse();

        response.setId(expense.getId());
        response.setTitle(expense.getTitle());
        response.setAmount(expense.getAmount());
        response.setExpenseDate(expense.getExpenseDate());
        response.setCategory(expense.getCategory());
        response.setUserId(expense.getUser().getId());

        return response;
    }
}