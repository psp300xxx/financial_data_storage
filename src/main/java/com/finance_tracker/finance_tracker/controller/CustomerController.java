package com.finance_tracker.finance_tracker.controller;


import com.finance_tracker.finance_tracker.dto.CustomerDTO;
import com.finance_tracker.finance_tracker.model.Customer;
import com.finance_tracker.finance_tracker.service.CustomerService;
import com.finance_tracker.finance_tracker.service.ICustomerService;
import com.finance_tracker.finance_tracker.util.HashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class CustomerController {

    private final CustomerService customerService;
    private final HashingUtil hashingUtil;

    @Autowired
    public CustomerController(CustomerService customerService, HashingUtil hashingUtil) {
        this.customerService = customerService;
        this.hashingUtil = hashingUtil;
    }

    @PostMapping("/add")
    public CustomerDTO addCustomer(@RequestBody Customer customer){
        Customer storedCustomer = customerService.save(customer);
        if(storedCustomer != null){
            return storedCustomer.convert();
        }
        else {
            throw new RuntimeException("Unable to create new customer");
        }
    }

    @PostMapping("/login")
    public CustomerDTO loginCustomer(@RequestBody Customer customer){
        String username = customer.getUsername();
        String password = customer.getPassword();
        Customer storedCustomer = customerService.findByUsername(username);
        if(storedCustomer != null && checkPassword(storedCustomer, password)){
            return storedCustomer.convert();
        }
        throw new InvalidLoginException("Invalid username or password");
    }

    private boolean checkPassword(Customer customer,  String password){
        String salt = customer.getSalt();
        String hashed = hashingUtil.hashSha256(salt + password);
        return hashed.equals(customer.getPassword());
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Customer> getAllCustomers() {
        // This returns a JSON or XML with the users
        return customerService.findAll();
    }

}
