package com.hema.expensetracker.repository;

import com.hema.expensetracker.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Optional<Budget> findByUserId(int userId);
}