package com.finance_tracker.finance_tracker.repository;

import com.finance_tracker.finance_tracker.model.Customer;
import com.finance_tracker.finance_tracker.model.MoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface IMoneyTransferRepository extends CrudRepository<MoneyTransfer, Long> {

    List<MoneyTransfer> findAllByCustomerId(int customerId);

    List<MoneyTransfer> findAllById(Long id);

    List<MoneyTransfer> findAllByExecutionTimeBefore(ZonedDateTime executionTime);

    List<MoneyTransfer> findAllByExecutionTimeAfter(ZonedDateTime executionTime);

    List<MoneyTransfer> findAllByExecutionTimeBetween(ZonedDateTime start, ZonedDateTime end);

    List<MoneyTransfer> findAllByExecutionTimeBeforeAndCustomerId(ZonedDateTime executionTime, long customerId);

    List<MoneyTransfer> findAllByExecutionTimeAfterAndCustomerId(ZonedDateTime executionTime, long customerId);

    List<MoneyTransfer> findAllByExecutionTimeBetweenAndCustomerId(ZonedDateTime start, ZonedDateTime end, long customerId);

}
