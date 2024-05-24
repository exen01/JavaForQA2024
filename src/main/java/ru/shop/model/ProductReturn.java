package ru.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "product_return")
public class ProductReturn {
    @Id
    private UUID id;
    private UUID orderId;
    private LocalDate date;
    private int quantity;
}
