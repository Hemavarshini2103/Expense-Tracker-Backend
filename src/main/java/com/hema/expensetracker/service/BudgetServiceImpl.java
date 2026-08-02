package com.hema.expensetracker.service;

import com.hema.expensetracker.dto.BudgetRequest;
import com.hema.expensetracker.dto.BudgetResponse;
import com.hema.expensetracker.entity.Budget;
import com.hema.expensetracker.entity.User;
import com.hema.expensetracker.exception.ResourceNotFoundException;
import com.hema.expensetracker.repository.BudgetRepository;
import com.hema.expensetracker.repository.ExpenseRepository;
import com.hema.expensetracker.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    // Constructor Injection
    public BudgetServiceImpl(BudgetRepository budgetRepository,
                             UserRepository userRepository,
                             ExpenseRepository expenseRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void setBudget(int userId, BudgetRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Budget budget = budgetRepository.findByUserId(userId)
                .orElse(new Budget());

        budget.setUser(user);
        budget.setMonthlyBudget(request.getMonthlyBudget());

        budgetRepository.save(budget);
    }

    @Override
    public BudgetResponse getBudgetStatus(int userId) {

        Budget budget = budgetRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found"));

        double spent = expenseRepository.getTotalExpenseByUser(userId);

        BudgetResponse response = new BudgetResponse();

        response.setBudget(budget.getMonthlyBudget());
        response.setSpent(spent);
        response.setRemaining(budget.getMonthlyBudget() - spent);

        return response;
    }
}