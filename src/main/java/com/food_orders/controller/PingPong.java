package com.food_orders.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPong {
    @GetMapping("/ping")
    public String PingPong(String ping) {
        return "Pong";
    }
}
