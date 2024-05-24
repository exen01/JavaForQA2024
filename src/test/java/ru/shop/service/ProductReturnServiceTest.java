package ru.shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.shop.exception.BadProductReturnCountException;
import ru.shop.exception.EntityNotFoundException;
import ru.shop.model.Order;
import ru.shop.model.ProductReturn;
import ru.shop.repository.OrderRepository;
import ru.shop.repository.ProductReturnRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductReturnServiceTest {

    private final ProductReturnRepository productReturnRepository = Mockito.mock();
    private final OrderRepository orderRepository = Mockito.mock();
    private final OrderService orderService = new OrderService(orderRepository);
    private final ProductReturnService productReturnService = new ProductReturnService(productReturnRepository, orderService);

    private UUID orderId;
    private Order order;

    @BeforeEach
    void setUp() {
        orderId = UUID.randomUUID();
        order = new Order(orderId, UUID.randomUUID(), UUID.randomUUID(), 10, 1000);
    }

    @Test
    void addProductReturnSuccess() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        productReturnService.add(orderId, 5);

        verify(productReturnRepository, times(1)).save(any(ProductReturn.class));
    }

    @Test
    void findAllProductReturnsSuccess() {
        List<ProductReturn> productReturns = new ArrayList<>();
        productReturns.add(new ProductReturn());
        when(productReturnRepository.findAll()).thenReturn(productReturns);

        List<ProductReturn> result = productReturnService.findAll();

        assertEquals(1, result.size());
        verify(productReturnRepository, times(1)).findAll();
    }

    @Test
    void findByIdProductReturnSuccess() {
        UUID returnId = UUID.randomUUID();
        ProductReturn productReturn = new ProductReturn();
        productReturn.setId(returnId);

        when(productReturnRepository.findById(returnId)).thenReturn(Optional.of(productReturn));

        ProductReturn result = productReturnService.findById(returnId);

        assertEquals(returnId, result.getId());
        verify(productReturnRepository, times(1)).findById(returnId);
    }

    @Test
    void addProductReturnThrowsException() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        BadProductReturnCountException exception = assertThrows(
                BadProductReturnCountException.class,
                () -> productReturnService.add(orderId, 15)
        );

        assertEquals("Return count is greater than ordered count", exception.getMessage());
        verify(productReturnRepository, never()).save(any(ProductReturn.class));
    }

    @Test
    void findByIdProductReturnNotFound() {
        UUID returnId = UUID.randomUUID();

        when(productReturnRepository.findById(returnId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> productReturnService.findById(returnId)
        );

        verify(productReturnRepository, times(1)).findById(returnId);
    }
}