package com.finance_tracker.finance_tracker.service;

import com.finance_tracker.finance_tracker.model.Customer;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ICustomerService {

    @Transactional
    Customer save(Customer customer);

    Iterable<Customer> findAll();

    Customer findByUsername(String username);

}
