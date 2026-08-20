package com.expensetracker.expensetrackerapi.api.transaction;

import com.expensetracker.expensetrackerapi.api.category.Category;
import com.expensetracker.expensetrackerapi.api.category.CategoryRepository;
import com.expensetracker.expensetrackerapi.api.common.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryTransactionRepository implements TransactionRepository {
   private final ConcurrentHashMap<Long, Transaction> storage = new ConcurrentHashMap<>();
   private final AtomicLong idSequence = new AtomicLong(0);
   private final CategoryRepository categoryRepository;

   InMemoryTransactionRepository(CategoryRepository categoryRepository) {
       this.categoryRepository = categoryRepository;
   }

    @Override
    public Transaction save(Transaction transaction) {
        categoryRepository.findById(transaction.categoryId()).orElseThrow(() -> new NotFoundException("Kategori id " + transaction.categoryId() + " tidak ditemukan"));

        if (transaction.id() == null) {
            Transaction withId = transaction.withId(idSequence.incrementAndGet());
            storage.put(withId.id(), withId);
            return withId;
        }

        storage.put(transaction.id(), transaction);
        return transaction;
    }

    @Override
    public Optional<Transaction> findById(Long id) {
       return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Transaction> findAll() {
       return List.copyOf(storage.values());
    }

    @Override
    public List<Transaction> findByCategoryId(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Kategori id " + id + " tidak ditemukan"));

        List<Transaction> filteredData = storage.values().stream().filter(val -> val.categoryId() == id).collect(Collectors.toList());
        return filteredData;
    }
}
