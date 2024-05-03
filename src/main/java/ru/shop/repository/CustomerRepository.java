package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.model.Customer;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

//    List<Customer> customers = new ArrayList<>();
//
//    @Override
//    public void save(Customer customer) {
//        customers.add(customer);
//    }
//
//    @Override
//    public List<Customer> findAll() {
//        return customers;
//    }
//
//    @Override
//    public Optional<Customer> findById(UUID id) {
//        return customers.stream()
//                .filter(customer -> customer.getId().equals(id))
//                .findFirst();
//    }
}
