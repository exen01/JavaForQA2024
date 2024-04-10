package ru.shop.repository;

import ru.shop.model.Product;

import java.util.List;

public interface ProductRepository {
    void save(Product product);
    List<Product> findAll();
}
