package ru.shop.service;

import ru.shop.exception.BadOrderCountException;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void add(Customer customer, Product product, long count) throws BadOrderCountException {
        if (count <= 0) {
            throw new BadOrderCountException(count);
        }

        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setCustomerId(customer.getId());
        order.setProductId(product.getId());
        order.setCount(count);
        order.setAmount(count * product.getCost());
        orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByCustomer(Customer customer) {
        return orderRepository.findCustomerOrders(customer.getId());
    }

    public long getTotalCustomerAmount(Customer customer) {
        return orderRepository.findAll()
                .stream()
                .filter(it -> it.getCustomerId() == customer.getId())
                .mapToLong(Order::getAmount)
                .sum();
    }

}
