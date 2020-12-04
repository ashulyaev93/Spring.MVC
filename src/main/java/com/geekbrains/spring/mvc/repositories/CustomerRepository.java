package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Customer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomerRepository {

    private List<Customer> customers;

    @PostConstruct
    public void init() {
        this.customers = new ArrayList<>();
        this.customers.add(new Customer(1L, "Alex", "test1@email.ru", "+79000000001"));
        this.customers.add(new Customer(2L, "Dima", "test2@email.ru", "+79000000002"));
        this.customers.add(new Customer(3L, "Anna", "test3@email.ru", "+79000000003"));
        this.customers.add(new Customer(4L, "Lena", "test4@email.ru", "+79000000004"));
    }

    public List<Customer> findAll() {
        return Collections.unmodifiableList(customers);
    }

    public Customer saveOrUpdate(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(customers.size()+1L);
            customers.add(customer);
            return customer;
        } else {
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getId().equals(customer.getId())) {
                    customers.set(i, customer);
                    return customer;
                }
            }
        }
        throw new RuntimeException("Error save or update customer");
    }

    public Customer findById(Long id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        throw new RuntimeException("Customer not found");
    }
}
