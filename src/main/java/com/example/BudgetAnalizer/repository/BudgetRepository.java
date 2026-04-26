package com.example.BudgetAnalizer.repository;

import com.example.BudgetAnalizer.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Budget findByMonth(String month);
}
