package com.food_orders.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:5173", "https://main--para-dish.netlify.app", "https://para-dish.netlify.app"})
@RestController
public class PingPong {
    @GetMapping("/ping")
    public String PingPong() {
        return "Pong";
    }

    @GetMapping("/")
    public String HelloWord() {
        return "WELCOME TO PARADISH DATA BASE";
    }
}
