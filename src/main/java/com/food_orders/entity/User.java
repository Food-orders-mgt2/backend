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
    private String password;
    private String image;
    private String role;
    private String residence;

    public User(String name, String first_name, String email, String password, String image, String role) {
        this.name = name;
        this.first_name = first_name;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
    }
}