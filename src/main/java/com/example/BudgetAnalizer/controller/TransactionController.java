package com.example.BudgetAnalizer.controller;



import com.example.BudgetAnalizer.model.Transaction;
import com.example.BudgetAnalizer.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/transaction")
    public String add(@RequestBody Transaction t) {
        return service.addTransaction(t);
    }

    @GetMapping("/transactions")
    public List<Transaction> getAll() {
        return service.getAll();
    }

    @PostMapping("/budget")
    public String setBudget(@RequestParam double amount) {
        return service.setBudget(amount);
    }

    @GetMapping("/budget-status")
    public String checkBudget() {
        return service.checkBudget();
    }
}
