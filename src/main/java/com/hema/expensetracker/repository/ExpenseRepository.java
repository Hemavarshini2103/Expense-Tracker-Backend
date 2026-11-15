package com.hema.expensetracker.repository;

import com.hema.expensetracker.entity.Category;
import com.hema.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserId(int userId);

    @Query("SELECT COALESCE(SUM(e.amount),0) FROM Expense e WHERE e.user.id = :userId")
    double getTotalExpenseByUser(@Param("userId") int userId);

    @Query("SELECT COUNT(e) FROM Expense e WHERE e.user.id = :userId")
    long getExpenseCountByUser(@Param("userId") int userId);

    @Query("SELECT COALESCE(AVG(e.amount),0) FROM Expense e WHERE e.user.id = :userId")
    double getAverageExpenseByUser(@Param("userId") int userId);

    List<Expense> findByUserIdAndCategory(int userId, Category category);

    @Query("""
            SELECT e.category, SUM(e.amount)
            FROM Expense e
            WHERE e.user.id = :userId
            GROUP BY e.category
            """)
    List<Object[]> getCategorySummary(@Param("userId") int userId);

    @Query("""
            SELECT YEAR(e.expenseDate),
                   MONTH(e.expenseDate),
                   SUM(e.amount)
            FROM Expense e
            WHERE e.user.id = :userId
              AND e.expenseDate IS NOT NULL
            GROUP BY YEAR(e.expenseDate), MONTH(e.expenseDate)
            ORDER BY YEAR(e.expenseDate), MONTH(e.expenseDate)
            """)
    List<Object[]> getMonthlySummary(@Param("userId") int userId);
    @Query("""
            SELECT COALESCE(SUM(e.amount), 0)
            FROM Expense e
            WHERE e.user.id = :userId
              AND YEAR(e.expenseDate) = YEAR(CURRENT_DATE)
              AND MONTH(e.expenseDate) = MONTH(CURRENT_DATE)
            """)
    double getCurrentMonthExpenseByUser(@Param("userId") int userId);
}