package com.food_orders.entity;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Contains {
    private UUID id;
    private UUID id_Dish;
    private UUID id_Ingredient;
}
