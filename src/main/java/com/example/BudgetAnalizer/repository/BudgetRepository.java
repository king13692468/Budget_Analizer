package com.example.BudgetAnalizer.repository;

import com.example.BudgetAnalizer.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Budget findByMonthAndCategory(String month, String category);

    List<Budget> findByMonth(String month);

    Budget findByMonthAndCategoryIsNull(String month);  // Overall budget
}