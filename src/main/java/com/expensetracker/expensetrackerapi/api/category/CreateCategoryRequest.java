package com.expensetracker.expensetrackerapi.api.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCategoryRequest(
        @NotBlank(message = "nama kategori wajib diisi") String name,
        @NotNull(message = "tipe kategori wajib diisi") CategoryType type
) {
}
