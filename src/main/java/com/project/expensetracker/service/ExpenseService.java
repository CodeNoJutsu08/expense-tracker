package com.project.expensetracker.service;

import com.project.expensetracker.model.Expense;
import com.project.expensetracker.repository.ExpenseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Expense addExpense(Expense expense) {
        return repo.save(expense);
    }

    // GET ALL
    public List<Expense> getAllExpenses() {
        return repo.findAll();
    }

    // GET BY ID
    public Expense getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // UPDATE
    public Expense updateExpense(Long id, Expense expense) {
        return repo.findById(id).map(e -> {
            e.setTitle(expense.getTitle());
            e.setDescription(expense.getDescription());
            e.setAmount(expense.getAmount());
            e.setDate(expense.getDate());
            return repo.save(e);
        }).orElse(null);
    }

    // DELETE
    public void deleteExpense(Long id) {
        repo.deleteById(id);
    }

    // GET with Pagination + Sorting
    public List<Expense> getExpensesPaged(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repo.findAll(pageable).getContent();
    }
}
