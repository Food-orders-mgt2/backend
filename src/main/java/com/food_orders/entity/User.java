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
    private String username;
    private String email;
    private String password;
}


