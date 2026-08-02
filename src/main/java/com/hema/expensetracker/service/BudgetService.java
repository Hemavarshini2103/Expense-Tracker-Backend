package com.hema.expensetracker.service;

import com.hema.expensetracker.dto.BudgetRequest;
import com.hema.expensetracker.dto.BudgetResponse;

public interface BudgetService {

    void setBudget(int userId, BudgetRequest request);

    BudgetResponse getBudgetStatus(int userId);
}