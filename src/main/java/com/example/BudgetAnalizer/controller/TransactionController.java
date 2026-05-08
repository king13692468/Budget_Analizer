package com.example.BudgetAnalizer.controller;

import com.example.BudgetAnalizer.model.Transaction;
import com.example.BudgetAnalizer.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService service;

    // Create transaction
    @PostMapping("/transaction")
    public String add(@RequestBody Transaction t) {
        return service.addTransaction(t);
    }

    // Get all transactions
    @GetMapping("/transactions")
    public List<Transaction> getAll() {
        return service.getAll();
    }

    // Get transaction by ID
    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return service.getTransaction(id);
    }

    // Update transaction
    @PutMapping("/transaction/{id}")
    public String updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return service.updateTransaction(id, transaction);
    }

    // Delete transaction
    @DeleteMapping("/transaction/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        return service.deleteTransaction(id);
    }

    // Set overall budget
    @PostMapping("/budget")
    public String setBudget(@RequestParam double amount) {
        return service.setBudget(amount, null);
    }

    // Set category-specific budget
    @PostMapping("/budget/category")
    public String setCategoryBudget(@RequestParam double amount, @RequestParam String category) {
        return service.setBudget(amount, category);
    }

    // Check budget status
    @GetMapping("/budget-status")
    public String checkBudget() {
        return service.checkBudget();
    }

    // Filter transactions by date range
    @GetMapping("/transactions/date-range")
    public List<Transaction> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.getTransactionsByDateRange(start, end);
    }

    // Filter transactions by category
    @GetMapping("/transactions/category/{category}")
    public List<Transaction> getByCategory(@PathVariable String category) {
        return service.getTransactionsByCategory(category);
    }

    // Monthly summary
    @GetMapping("/summary/{year}/{month}")
    public Map<String, Object> getMonthlySummary(@PathVariable int year, @PathVariable int month) {
        return service.getMonthlySummary(year, month);
    }

    // Top spending categories
    @GetMapping("/top-categories/{year}/{month}")
    public List<Map<String, Object>> getTopCategories(
            @PathVariable int year,
            @PathVariable int month,
            @RequestParam(defaultValue = "5") int limit) {
        return service.getTopCategories(year, month, limit);
    }
}