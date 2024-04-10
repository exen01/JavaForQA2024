package ru.shop.service;

import ru.shop.model.Product;
import ru.shop.model.ProductType;

import java.util.List;

public interface ProductService {
    void save(Product product);

    List<Product> findAll();

    List<Product> findByProductType(ProductType productType);
}
