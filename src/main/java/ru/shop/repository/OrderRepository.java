package ru.shop.repository;

import ru.shop.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    void save(Order order);

    List<Order> findAll();

    List<Order> findCustomerOrders(UUID customerId);
}
