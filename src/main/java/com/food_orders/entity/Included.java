package com.food_orders.entity;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Included {
    private UUID id;
    private UUID id_Order;
    private UUID id_Dish;
}
