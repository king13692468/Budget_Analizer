package com.example.BudgetAnalizer.repository;

import com.example.BudgetAnalizer.model.Transaction;
import com.example.BudgetAnalizer.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    

    List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Transaction> findByCategory(String category);


    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t " +
            "WHERE t.type = 'EXPENSE' AND t.date BETWEEN :startDate AND :endDate " +
            "GROUP BY t.category")
    List<Object[]> getCategorySpending(@Param("startDate") LocalDate startDate,
                                       @Param("endDate") LocalDate endDate);
}