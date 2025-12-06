package com.project.expensetracker.controller;

import com.project.expensetracker.dto.ExpenseDTO;
import com.project.expensetracker.mapper.ExpenseMapper;
import com.project.expensetracker.model.Expense;
import com.project.expensetracker.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ExpenseDTO> addExpense(@Valid @RequestBody ExpenseDTO expenseDto) {
        Expense expense = ExpenseMapper.toEntity(expenseDto);
        Expense saved = service.addExpense(expense);
        return new ResponseEntity<>(ExpenseMapper.toDto(saved), HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        List<Expense> list = service.getAllExpenses();
        List<ExpenseDTO> dtos = list.stream().map(ExpenseMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    // GET with Pagination + Sorting
    @GetMapping("/paged")
    public ResponseEntity<List<ExpenseDTO>> getExpensesPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        List<ExpenseDTO> dtos = service.getExpensesPaged(page, size, sortBy, sortDir)
                .stream()
                .map(ExpenseMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpense(@PathVariable Long id) {
        Expense e = service.getById(id);
        return e != null ? ResponseEntity.ok(ExpenseMapper.toDto(e)) : ResponseEntity.notFound().build();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDTO expenseDto) {
        Expense expense = ExpenseMapper.toEntity(expenseDto);
        Expense updated = service.updateExpense(id, expense);
        return updated != null ? ResponseEntity.ok(ExpenseMapper.toDto(updated)) : ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        service.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

  

}
