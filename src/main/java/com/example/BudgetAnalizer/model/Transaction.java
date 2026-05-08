package com.example.BudgetAnalizer.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String category;
    private LocalDate date;
    private String month;  // Keep this field

    // Constructors
    public Transaction() {}

    public Transaction(String description, double amount, TransactionType type, String category, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
        this.month = date.toString().substring(0, 7); // Auto-set month on creation
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) {
        this.date = date;
        this.month = date.toString().substring(0, 7); // Update month when date changes
    }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
}