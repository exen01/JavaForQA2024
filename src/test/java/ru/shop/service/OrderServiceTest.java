package ru.shop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.shop.exception.BadOrderCountException;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.repository.OrderRepository;

import java.util.List;
import java.util.UUID;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class OrderServiceTest {

    private final OrderRepository orderRepository = Mockito.mock();
    private final OrderService orderService = new OrderService(orderRepository);

    @Test
    public void shouldAddOrder() {
        Customer customer = new Customer();
        Product product = new Product();

        orderService.add(customer, product, 10);

        verify(orderRepository).save(any());
    }

    @Test
    public void shouldThrowExceptionAdd() {
        Customer customer = new Customer();
        Product product = new Product();

        Assertions.assertThrows(BadOrderCountException.class, () -> orderService.add(customer, product, 0));
    }

    @Test
    void findByCustomer() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer(customerId, "name", "phone", 20);

        Order customerOrder = new Order(UUID.randomUUID(), customerId, UUID.randomUUID(), 10, 10);
        Order notCustomerOrder = new Order(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 10, 10);

        Mockito.when(orderRepository.findAll()).thenReturn(List.of(customerOrder, notCustomerOrder));

        List<Order> lookUpResult = orderService.findByCustomer(customer);

        Assertions.assertEquals(1, lookUpResult.size());
        Assertions.assertEquals(customerOrder, lookUpResult.get(0));
    }

    @Test
    void getTotalCustomerAmount() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer(customerId, "name", "phone", 20);

        Order customerOrder = new Order(UUID.randomUUID(), customerId, UUID.randomUUID(), 10, 10);
        Order customerOrder2 = new Order(UUID.randomUUID(), customerId, UUID.randomUUID(), 10, 20);
        Order notCustomerOrder = new Order(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 10, 10);

        Mockito.when(orderRepository.findAll()).thenReturn(List.of(customerOrder, customerOrder2, notCustomerOrder));

        long result = orderService.getTotalCustomerAmount(customer);

        Assertions.assertEquals(30, result);
    }
}