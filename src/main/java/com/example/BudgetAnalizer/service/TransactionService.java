package com.example.BudgetAnalizer.service;

import com.example.BudgetAnalizer.model.*;
import com.example.BudgetAnalizer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private BudgetRepository budgetRepo;

    // Add transaction with validation
    public String addTransaction(Transaction t) {
        if (t.getDate() == null) {
            t.setDate(LocalDate.now());
        }

        if (t.getCategory() == null || t.getCategory().isEmpty()) {
            t.setCategory("Uncategorized");
        }

        transactionRepo.save(t);

        // Check if this transaction causes budget exceed
        String budgetStatus = checkBudgetForDate(t.getDate());
        if (budgetStatus.contains("Exceeded")) {
            return "Transaction added but " + budgetStatus;
        }

        return "Transaction Added Successfully";
    }

    // Get all transactions
    public List<Transaction> getAll() {
        return transactionRepo.findAll();
    }

    // Get transaction by ID
    public Transaction getTransaction(Long id) {
        return transactionRepo.findById(id).orElse(null);
    }

    // Update transaction
    @Transactional
    public String updateTransaction(Long id, Transaction updatedTransaction) {
        Transaction existing = transactionRepo.findById(id).orElse(null);
        if (existing == null) {
            return "Transaction not found";
        }

        existing.setDescription(updatedTransaction.getDescription());
        existing.setAmount(updatedTransaction.getAmount());
        existing.setType(updatedTransaction.getType());
        existing.setCategory(updatedTransaction.getCategory());
        existing.setDate(updatedTransaction.getDate());

        transactionRepo.save(existing);
        return "Transaction Updated Successfully";
    }

    // Delete transaction
    @Transactional
    public String deleteTransaction(Long id) {
        if (transactionRepo.existsById(id)) {
            transactionRepo.deleteById(id);
            return "Transaction Deleted Successfully";
        }
        return "Transaction not found";
    }

    // Filter transactions by date range
    public List<Transaction> getTransactionsByDateRange(LocalDate start, LocalDate end) {
        return transactionRepo.findByDateBetween(start, end);
    }

    // Filter by category
    public List<Transaction> getTransactionsByCategory(String category) {
        return transactionRepo.findByCategory(category);
    }

    // Improved budget setting (updates if exists)
    public String setBudget(double amount, String category) {
        String month = YearMonth.now().toString(); // YYYY-MM format

        Budget existingBudget;
        if (category == null || category.isEmpty()) {
            existingBudget = budgetRepo.findByMonthAndCategoryIsNull(month);
        } else {
            existingBudget = budgetRepo.findByMonthAndCategory(month, category);
        }

        if (existingBudget != null) {
            existingBudget.setLimitAmount(amount);
            budgetRepo.save(existingBudget);
            return "Budget Updated for " + (category == null ? "Overall" : category);
        } else {
            Budget newBudget = new Budget(month, category, amount);
            budgetRepo.save(newBudget);
            return "Budget Set for " + (category == null ? "Overall" : category);
        }
    }

    // Check budget for current date
    public String checkBudget() {
        return checkBudgetForDate(LocalDate.now());
    }

    // Check budget for specific date - UPDATED with ₹
    public String checkBudgetForDate(LocalDate date) {
        String month = YearMonth.from(date).toString();

        List<Budget> budgets = budgetRepo.findByMonth(month);
        if (budgets.isEmpty()) return "No budget set for " + month;

        // Get monthly expenses
        LocalDate startOfMonth = date.withDayOfMonth(1);
        LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth());

        List<Transaction> monthlyExpenses = transactionRepo.findByDateBetween(startOfMonth, endOfMonth)
                .stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .collect(Collectors.toList());

        double totalExpense = monthlyExpenses.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();

        // Check overall budget
        Budget overallBudget = budgetRepo.findByMonthAndCategoryIsNull(month);
        StringBuilder result = new StringBuilder();

        if (overallBudget != null) {
            double percentage = (totalExpense / overallBudget.getLimitAmount()) * 100;
            if (totalExpense > overallBudget.getLimitAmount()) {
                result.append(String.format("⚠️ Overall Budget Exceeded! Used: ₹%.2f / ₹%.2f (%.1f%%)",
                        totalExpense, overallBudget.getLimitAmount(), percentage));
            } else if (percentage >= 80) {
                result.append(String.format("⚠️ Warning: Overall budget at %.1f%% (Used: ₹%.2f / ₹%.2f)",
                        percentage, totalExpense, overallBudget.getLimitAmount()));
            } else {
                result.append(String.format("✅ Overall Budget: ₹%.2f / ₹%.2f (%.1f%%)",
                        totalExpense, overallBudget.getLimitAmount(), percentage));
            }
        }

        // Check category budgets
        Map<String, Double> categorySpending = monthlyExpenses.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getCategory() == null ? "Uncategorized" : t.getCategory(),
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        for (Budget budget : budgets) {
            if (budget.getCategory() != null) {
                Double spent = categorySpending.getOrDefault(budget.getCategory(), 0.0);
                double percentage = (spent / budget.getLimitAmount()) * 100;

                if (spent > budget.getLimitAmount()) {
                    result.append(String.format("\n⚠️ %s Budget Exceeded! Used: ₹%.2f / ₹%.2f (%.1f%%)",
                            budget.getCategory(), spent, budget.getLimitAmount(), percentage));
                } else if (percentage >= 80) {
                    result.append(String.format("\n⚠️ Warning: %s budget at %.1f%% (Used: ₹%.2f / ₹%.2f)",
                            budget.getCategory(), percentage, spent, budget.getLimitAmount()));
                } else {
                    result.append(String.format("\n✅ %s Budget: ₹%.2f / ₹%.2f (%.1f%%)",
                            budget.getCategory(), spent, budget.getLimitAmount(), percentage));
                }
            }
        }

        return result.toString();
    }

    // Get monthly summary - UPDATED with ₹ in string format
    public Map<String, Object> getMonthlySummary(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Transaction> transactions = transactionRepo.findByDateBetween(startDate, endDate);

        double totalIncome = transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpense = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double savings = totalIncome - totalExpense;
        double savingsRate = totalIncome > 0 ? (savings / totalIncome) * 100 : 0;

        Map<String, Double> categoryBreakdown = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(
                        t -> t.getCategory() == null ? "Uncategorized" : t.getCategory(),
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        Map<String, Object> summary = new HashMap<>();
        summary.put("year", year);
        summary.put("month", month);
        summary.put("totalIncome", String.format("₹%.2f", totalIncome));
        summary.put("totalExpense", String.format("₹%.2f", totalExpense));
        summary.put("savings", String.format("₹%.2f", savings));
        summary.put("savingsRate", String.format("%.1f%%", savingsRate));
        summary.put("categoryBreakdown", categoryBreakdown);
        summary.put("transactionCount", transactions.size());

        return summary;
    }

    // Get top spending categories - amounts are already numeric, frontend can format
    public List<Map<String, Object>> getTopCategories(int year, int month, int limit) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Object[]> results = transactionRepo.getCategorySpending(startDate, endDate);

        return results.stream()
                .limit(limit)
                .map(row -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("category", row[0]);
                    map.put("amount", String.format("₹%.2f", row[1])); // Format as ₹
                    map.put("rawAmount", row[1]); // Keep raw for calculations if needed
                    return map;
                })
                .collect(Collectors.toList());
    }
}