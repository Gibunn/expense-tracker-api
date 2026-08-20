package com.expensetracker.expensetrackerapi.api.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(
        Long id,
        BigDecimal amount,
        LocalDate occurredOn,
        String note,
        Long categoryId
) {
    public Transaction withId(Long newId) {
        return new Transaction(newId, amount, occurredOn, note, categoryId);
     }
}
