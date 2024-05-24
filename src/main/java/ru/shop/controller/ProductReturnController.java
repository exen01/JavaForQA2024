package ru.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shop.model.ProductReturn;
import ru.shop.service.ProductReturnService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product-return")
public class ProductReturnController {
    private final ProductReturnService productReturnService;

    @Autowired
    public ProductReturnController(ProductReturnService productReturnService) {
        this.productReturnService = productReturnService;
    }

    @GetMapping
    public List<ProductReturn> getAll() {
        return productReturnService.findAll();
    }

    @GetMapping("/{id}")
    public ProductReturn getById(@PathVariable UUID id) {
        return productReturnService.findById(id);
    }
}
