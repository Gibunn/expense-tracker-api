package com.expensetracker.expensetrackerapi.api.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTransactionRequest(
        @NotNull @Positive BigDecimal amount,
        @NotNull @PastOrPresent LocalDate occuredOn,
        String note,
        @NotNull Long categoryId
        ) {
}
