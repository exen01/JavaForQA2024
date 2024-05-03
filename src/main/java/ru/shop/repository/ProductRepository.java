package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.model.Product;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

//    List<Product> products = new ArrayList<>();
//
//    public void save(Product product) {
//        products.add(product);
//    }
//
//    public List<Product> findAll() {
//        return products;
//    }
//
//    @Override
//    public Optional<Product> findById(UUID id) {
//        return products.stream()
//                .filter(product -> product.getId().equals(id))
//                .findFirst();
//    }

}
