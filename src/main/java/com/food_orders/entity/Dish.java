package com.food_orders.entity;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Dish {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private String image;
}
