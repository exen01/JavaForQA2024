package ru.shop.service;

import ru.shop.model.Customer;

import java.util.List;

public interface CustomerService {
    void save(Customer customer);
    List<Customer> findAll();
}
