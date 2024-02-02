package com.food_orders.entity;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private UUID id;
    private String name;
    private String first_name;
    private String email;
    private String image;
    private String role;
    private String residence;
}
