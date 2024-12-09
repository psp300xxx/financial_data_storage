package com.finance_tracker.finance_tracker.repository;

import com.finance_tracker.finance_tracker.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findByUsername(String username);

    Customer findById(long id);

    void deleteById(long id);


}
