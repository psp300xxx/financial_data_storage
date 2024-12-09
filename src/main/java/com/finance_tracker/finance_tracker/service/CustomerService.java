package com.finance_tracker.finance_tracker.service;

import com.finance_tracker.finance_tracker.model.Customer;
import com.finance_tracker.finance_tracker.repository.ICustomerRepository;
import com.finance_tracker.finance_tracker.util.HashingUtil;
import com.finance_tracker.finance_tracker.util.IHashingUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService{

    private final ICustomerRepository customerRepository;
    private final IHashingUtil hashingUtil;


    @Autowired
    public CustomerService(ICustomerRepository customerRepository, IHashingUtil hashingUtil) {
        this.customerRepository = customerRepository;
        this.hashingUtil = hashingUtil;
    }


    @Transactional
    public Customer save(Customer customer) {
        String saltedPwd = customer.getSalt() + customer.getPassword();
        System.out.println(saltedPwd);
        String hash = this.hashingUtil.hashSha256(saltedPwd);
        customer.setPassword(hash);
        return customerRepository.save(customer);
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findByUsername(String username) {
        List<Customer> list = customerRepository.findByUsername(username);
        if(list.isEmpty() || list.size() > 1){
            return null;
        }
        return list.get(0);
    }

}
