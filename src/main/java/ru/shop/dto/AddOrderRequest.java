package ru.shop.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddOrderRequest {
    private UUID productId;
    private UUID customerId;
    private int count;
}
