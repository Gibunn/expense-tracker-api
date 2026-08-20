package com.expensetracker.expensetrackerapi.api.transaction;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService service;

    TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    List<TransactionResponse> getAll(@RequestParam(required = false) Long categoryId) {
        return service.getAll(categoryId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TransactionResponse create(@Valid @RequestBody CreateTransactionRequest request) {
        return service.create(request);
    }
}
