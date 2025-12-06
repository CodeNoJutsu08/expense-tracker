package com.project.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.expensetracker.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
