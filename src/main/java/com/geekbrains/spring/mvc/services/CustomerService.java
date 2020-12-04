package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.model.Customer;
import com.geekbrains.spring.mvc.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveOrUpdate(Customer customer) {
        return customerRepository.saveOrUpdate(customer);
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
