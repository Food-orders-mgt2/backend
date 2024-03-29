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
    private Timestamp Date_time;
    private Double Shipping_cost;
    private Timestamp Delivery_date_time;
    private String Delivery_address;
    private String list_dish_id;
    private UUID Id_User;
    private String Pay_mode;
    private Double total_price;
}