package ru.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    UUID id;
    String name;
    long cost;
    ProductType productType;
}
