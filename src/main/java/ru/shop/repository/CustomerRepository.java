package ru.shop.repository;

import ru.shop.model.Customer;

import java.util.List;

public interface CustomerRepository {
    void save(Customer customer);

    List<Customer> findAll();
}
