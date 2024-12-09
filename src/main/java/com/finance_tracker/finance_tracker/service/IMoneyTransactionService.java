package com.finance_tracker.finance_tracker.service;

import com.finance_tracker.finance_tracker.model.MoneyTransfer;

import java.util.Optional;

public interface IMoneyTransactionService {

    Iterable<MoneyTransfer> findAllTransactions();

    Optional<MoneyTransfer> findTransactionById(long id);

    MoneyTransfer createTransaction(MoneyTransfer moneyTransfer);

    MoneyTransfer updateTransaction(MoneyTransfer moneyTransfer);

    MoneyTransfer deleteTransaction(long id, int customerId);

    Iterable<MoneyTransfer> findAllTransactionsByDateAndCustomer(String startingDate, String endingDate, int customerId);
}
