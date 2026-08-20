package com.expensetracker.expensetrackerapi.api.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(
        Long id,
        BigDecimal amount,
        LocalDate occuredOn,
        String note,
        Long categoryId,
        String categoryName,
) {
}
