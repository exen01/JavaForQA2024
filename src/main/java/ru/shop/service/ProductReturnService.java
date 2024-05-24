package ru.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shop.exception.BadProductReturnCountException;
import ru.shop.exception.EntityNotFoundException;
import ru.shop.model.Order;
import ru.shop.model.ProductReturn;
import ru.shop.repository.ProductReturnRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ProductReturnService {
    private final ProductReturnRepository productReturnRepository;
    private final OrderService orderService;

    @Autowired
    public ProductReturnService(ProductReturnRepository productReturnRepository, OrderService orderService) {
        this.productReturnRepository = productReturnRepository;
        this.orderService = orderService;
    }

    public void add(UUID orderId, int count) {
        Order order = orderService.getById(orderId);
        if (count > order.getCount()) {
            throw new BadProductReturnCountException("Return count is greater than ordered count");
        }

        ProductReturn productReturn = new ProductReturn();
        productReturn.setOrderId(orderId);
        productReturn.setDate(LocalDate.now());
        productReturn.setQuantity(count);

        productReturnRepository.save(productReturn);
    }

    public List<ProductReturn> findAll() {
        return productReturnRepository.findAll();
    }

    public ProductReturn findById(UUID id) {
        return productReturnRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
