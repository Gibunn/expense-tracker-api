package com.expensetracker.expensetrackerapi.api.category;

public record Category(Long id, String name, CategoryType type) {
    public Category withId(Long newId) {
        return new Category(newId, name, type);
    }
}
