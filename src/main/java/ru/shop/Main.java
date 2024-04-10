package ru.shop;

import ru.shop.exception.BadOrderCountException;
import ru.shop.model.Customer;
import ru.shop.model.Product;
import ru.shop.model.ProductType;
import ru.shop.repository.*;
import ru.shop.service.*;

import java.util.UUID;

public class Main {
    private static final ProductRepository productRepository = new ProductRepositoryImpl();
    private static final CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private static final OrderRepository orderRepository = new OrderRepositoryImpl();

    private static final ProductService productService = new ProductServiceImpl(productRepository);
    private static final CustomerService customerService = new CustomerServiceImpl(customerRepository);
    private static final OrderService orderService = new OrderServiceImpl(orderRepository);

    private static final String CONST = "CONST";
    private static final long LONG_CONST = 99999999999L;

    public static void main(String[] args) {
        // Customers
        var customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName("customer1");
        customer.setAge(33);
        customer.setPhone("89997776655");

        customerService.save(customer);

        var customer2 = new Customer();
        customer2.setId(UUID.randomUUID());
        customer2.setName("customer2");
        customer2.setAge(19);
        customer2.setPhone("89997776655");

        customerService.save(customer2);

        // Products
        var product = new Product();

        product.setId(UUID.randomUUID());
        product.setName("product1");
        product.setCost(99);
        product.setProductType(ProductType.GOOD);

        var product2 = new Product();

        product2.setId(UUID.randomUUID());
        product2.setName("product2");
        product2.setCost(55);
        product2.setProductType(ProductType.SERVICE);

        productService.save(product);
        productService.save(product2);

        // Orders
        try {
            orderService.add(customer, product, -1);
        } catch (BadOrderCountException e) {
            System.out.println(e.getMessage());
        }

        try {
            orderService.add(customer2, product2, 100);
        } catch (BadOrderCountException e) {
            System.out.println(e.getMessage());
        }

        try {
            orderService.add(customer2, product, 1000);
        } catch (BadOrderCountException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Кол-во заказчиков: " + customerRepository.findAll().size());
        System.out.println("Всего заказов: " + orderRepository.findAll().size());
        System.out.println("Всего продуктов: " + productService.findAll().size());

        System.out.println("Продуктов типа " + ProductType.GOOD + ": " + productService.findByProductType(ProductType.GOOD).size());
        System.out.println("Продуктов типа " + ProductType.SERVICE + ": " + productService.findByProductType(ProductType.SERVICE).size());

        System.out.println("Сумма заказа для " + customer2 + ": " + orderService.getTotalCustomerAmount(customer2));
        System.out.println("Кол-во заказов для " + customer2 + ": " + orderService.findByCustomer(customer2).size());
    }

}