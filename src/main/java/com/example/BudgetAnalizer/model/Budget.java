package com.example.BudgetAnalizer.model;

import jakarta.persistence.*;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String month;  // Format: YYYY-MM
    private String category;  // null means overall budget
    private double limitAmount;

    // Constructors
    public Budget() {}

    public Budget(String month, String category, double limitAmount) {
        this.month = month;
        this.category = category;
        this.limitAmount = limitAmount;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getLimitAmount() { return limitAmount; }
    public void setLimitAmount(double limitAmount) { this.limitAmount = limitAmount; }
}