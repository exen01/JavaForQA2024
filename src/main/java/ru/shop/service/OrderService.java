package ru.shop.service;

import ru.shop.exception.BadOrderCountException;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;

import java.util.List;

public interface OrderService {
    void add(Customer customer, Product product, long count) throws BadOrderCountException;

    List<Order> findAll();

    List<Order> findByCustomer(Customer customer);

    long getTotalCustomerAmount(Customer customer);
}
