package com.expensetracker.expensetrackerapi.api.category;

import com.expensetracker.expensetrackerapi.api.common.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public CategoryResponse create(CreateCategoryRequest request) {
        Category saved = repository.save(new Category(null, request.name(), request.type()));
        return toResponse(saved);
    }

    public CategoryResponse getById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kategori id " + id + " tidak ditemukan"));
        return toResponse(category);
    }

    public List<CategoryResponse> getAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }

    private CategoryResponse toResponse(Category c) {
        return new CategoryResponse(c.id(), c.name(), c.type());
    }
}
