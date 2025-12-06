package com.project.expensetracker.mapper;

import com.project.expensetracker.dto.ExpenseDTO;
import com.project.expensetracker.model.Expense;

import java.time.LocalDate;

public class ExpenseMapper {

    public static Expense toEntity(ExpenseDTO dto) {
        if (dto == null) return null;
        Expense e = Expense.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .date(dto.getDate() != null ? dto.getDate() : LocalDate.now())
                .build();
        return e;
    }

    public static ExpenseDTO toDto(Expense e) {
        if (e == null) return null;
        return ExpenseDTO.builder()
                .id(e.getId())
                .title(e.getTitle())
                .description(e.getDescription())
                .amount(e.getAmount())
                .date(e.getDate())
                .build();
    }
}
