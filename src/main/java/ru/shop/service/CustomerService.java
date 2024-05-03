package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.exception.EntityNotFoundException;
import ru.shop.model.Customer;
import ru.shop.model.Product;
import ru.shop.repository.CustomerRepository;
import ru.shop.repository.IRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repository;

    public void save(Customer customer) {
        repository.save(customer);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
