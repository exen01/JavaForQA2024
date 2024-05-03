package ru.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "customer", schema = "public")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    UUID id;
    String name;
    String phone;
    long age;
}
