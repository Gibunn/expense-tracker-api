package com.expensetracker.expensetrackerapi.api.category;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryCategoryRepository implements CategoryRepository {
    private final ConcurrentHashMap<Long, Category> storage = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(0);

    @Override
    public Category save(Category category) {
        if (category.id() == null) {
            Category withId = category.withId(idSequence.incrementAndGet());
            storage.put(withId.id(), withId);
            return withId;
        }
        storage.put(category.id(), category);
        return category;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Category> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
