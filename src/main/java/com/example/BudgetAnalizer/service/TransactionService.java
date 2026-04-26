package com.example.BudgetAnalizer.service;

import com.example.BudgetAnalizer.model.*;
import com.example.BudgetAnalizer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private BudgetRepository budgetRepo;

    public String addTransaction(Transaction t) {
        transactionRepo.save(t);
        return "Transaction Added";
    }

    public List<Transaction> getAll() {
        return transactionRepo.findAll();
    }

    public String setBudget(double amount) {
        String month = LocalDate.now().toString().substring(0, 7);

        Budget b = new Budget();
        b.setLimitAmount(amount);
        b.setMonth(month);

        budgetRepo.save(b);
        return "Budget Set";
    }

    public String checkBudget() {
        String month = LocalDate.now().toString().substring(0, 7);

        Budget budget = budgetRepo.findByMonth(month);
        if (budget == null) return "No budget set";

        double totalExpense = transactionRepo.findByType("EXPENSE")
                .stream()
                .mapToDouble(Transaction::getAmount)
                .sum();

        if (totalExpense > budget.getLimitAmount()) {
            return "⚠️ Budget Exceeded!";
        }

        return "Within Budget";
    }
}
