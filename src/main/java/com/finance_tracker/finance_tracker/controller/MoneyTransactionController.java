package com.finance_tracker.finance_tracker.controller;

import com.finance_tracker.finance_tracker.model.MoneyTransfer;
import com.finance_tracker.finance_tracker.service.MoneyTransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transfers")
public class MoneyTransactionController {

    private final MoneyTransactionService moneyTransactionService;

    @Autowired
    public MoneyTransactionController(MoneyTransactionService moneyTransactionService) {
        this.moneyTransactionService = moneyTransactionService;
    }

    @PostMapping("/add")
    public MoneyTransfer addMoneyTransaction(@RequestBody MoneyTransfer moneyTransfer) {
        return moneyTransactionService.createTransaction(moneyTransfer);
    }

    @GetMapping("/all")
    public Iterable<MoneyTransfer> getAllMoneyTransactions(@RequestBody MoneyTransferFilter filter) {
        int customerId = filter.getCustomerId();
        return moneyTransactionService.findAllTransactionsByDateAndCustomer(filter.getAfterDate(), filter.getBeforeDate(), customerId);
    }

    @PostMapping("/modify")
    @Transactional
    public MoneyTransfer modifyMoneyTransaction(@RequestBody MoneyTransfer moneyTransfer) {
        Optional<MoneyTransfer> transaction = moneyTransactionService.findTransactionById(moneyTransfer.getId());
        if (!transaction.isPresent()) {
            throw new NotExistingTransferException("Not existing transfer with id " + moneyTransfer.getId());
        }
        MoneyTransfer t = transaction.get();
        t.setAmount(moneyTransfer.getAmount());
        t.setCurrencyCode(moneyTransfer.getCurrencyCode());
        return moneyTransactionService.updateTransaction(t);
    }



}
