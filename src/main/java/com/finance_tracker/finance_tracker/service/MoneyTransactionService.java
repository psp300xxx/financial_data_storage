package com.finance_tracker.finance_tracker.service;

import com.finance_tracker.finance_tracker.model.MoneyTransfer;
import com.finance_tracker.finance_tracker.repository.IMoneyTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Optional;

@Service
public class MoneyTransactionService implements IMoneyTransactionService {

    private final IMoneyTransferRepository moneyTransferRepository;

    @Autowired
    public MoneyTransactionService(IMoneyTransferRepository moneyTransferRepository) {
        super();
        this.moneyTransferRepository = moneyTransferRepository;
    }

    @Override
    public Iterable<MoneyTransfer> findAllTransactions() {
        return moneyTransferRepository.findAll();
    }

    @Override
    public Optional<MoneyTransfer> findTransactionById(long id) {
        return moneyTransferRepository.findById(id);
    }


    @Override
    public MoneyTransfer createTransaction(MoneyTransfer moneyTransfer) {
        MoneyTransfer newMoneyTransfer = moneyTransferRepository.save(moneyTransfer);
        return newMoneyTransfer;
    }

    @Override
    public MoneyTransfer updateTransaction(MoneyTransfer moneyTransfer) {
        Optional<MoneyTransfer> optionalMoneyTransfer = moneyTransferRepository.findById(moneyTransfer.getId());
        if (!optionalMoneyTransfer.isPresent()) {
            return null;
        }
        MoneyTransfer updatedMoneyTransfer = optionalMoneyTransfer.get();
        updatedMoneyTransfer.setAmount(moneyTransfer.getAmount());
        if(moneyTransfer.getCurrencyCode()!=null) {
            updatedMoneyTransfer.setCurrencyCode(moneyTransfer.getCurrencyCode());
        }
        return moneyTransferRepository.save(updatedMoneyTransfer);
    }

    @Override
    public MoneyTransfer deleteTransaction(long id, int customerId) {
        Optional<MoneyTransfer> optionalMoneyTransfer = findTransactionById(id);
        if (!optionalMoneyTransfer.isPresent()) {
            return null;
        }
        MoneyTransfer moneyTransfer = optionalMoneyTransfer.get();
        if(customerId!=moneyTransfer.getCustomer().getId()) {
            return null;
        }
        moneyTransferRepository.deleteById(id);
        return moneyTransfer;
    }

    @Override
    public Iterable<MoneyTransfer> findAllTransactionsByDateAndCustomer(String startingDate, String endingDate, int customerId) {
        if (startingDate == null && endingDate == null) {
            return moneyTransferRepository.findAllByCustomerId(customerId);
        }
        try {
            if (startingDate == null) {
                ZonedDateTime endDate = ZonedDateTime.parse(endingDate);
                return moneyTransferRepository.findAllByExecutionTimeAfterAndCustomerId(endDate, customerId);
            }
            if (endingDate == null) {
                ZonedDateTime startDate = ZonedDateTime.parse(startingDate);
                return moneyTransferRepository.findAllByExecutionTimeBeforeAndCustomerId(startDate, customerId);
            }

            ZonedDateTime startDate = ZonedDateTime.parse(startingDate);
            ZonedDateTime endDate = ZonedDateTime.parse(endingDate);
            return moneyTransferRepository.findAllByExecutionTimeBetweenAndCustomerId(startDate, endDate, customerId);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use ISO-8601 format.", e);
        }
    }

}
