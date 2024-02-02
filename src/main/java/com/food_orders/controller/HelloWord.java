package com.food_orders.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWord {
    @GetMapping("/")
    public String HelloWord(String hello) {
        return "Hello Bro";
    }
}
