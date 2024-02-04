package com.food_orders.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private UUID id;
    private Timestamp date_time;
    private Double shipping_cost;
    private Timestamp delivery_date_time;
    private String delivery_place;
    private UUID id_User;
}
