package com.expensetracker.expensetrackerapi.api.transaction;

import com.expensetracker.expensetrackerapi.api.category.Category;
import com.expensetracker.expensetrackerapi.api.category.CategoryRepository;
import com.expensetracker.expensetrackerapi.api.common.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponse create(CreateTransactionRequest request) {
        Category category = categoryRepository.findById(request.categoryId()).orElseThrow(() -> new NotFoundException("Kategori id " + request.categoryId() + " tidak ditemukan"));

        Transaction saved = transactionRepository.save(new Transaction(null, request.amount(), request.occuredOn(), request.note(), request.categoryId()));

        return toResponse(saved, category);
    }

   public List<TransactionResponse> getAll(Long categoryId) {
        List<Transaction> transaction = categoryId == null ? transactionRepository.findAll() : transactionRepository.findByCategoryId(categoryId);

        return transaction.stream().map(tx -> {
            Category category = categoryRepository.findById(tx.categoryId()).orElseThrow();
            return toResponse(tx, category);
        }).toList();
   }

    private TransactionResponse toResponse(Transaction transaction, Category category) {
        return new TransactionResponse(transaction.id(), transaction.amount(), transaction.occurredOn(), transaction.note(), category.id(), category.name());
    }
}
