package com.food_orders.controller;

import com.food_orders.entity.Order;
import com.food_orders.repository.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:5173", "https://main--para-dish.netlify.app", "https://para-dish.netlify.app"})
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderDAO orderDAO;

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) throws SQLException {
        Order addedOrder = orderDAO.insert(order);
        return ResponseEntity.ok(addedOrder);
    }

    @GetMapping
    public List<Order> getAllOrders() throws SQLException {
        return orderDAO.findAll();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID orderId) {
        try {
            Order order = orderDAO.getById(orderId);
            if (order != null) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable UUID orderId, @RequestBody Order updatedOrder) {
        try {
            updatedOrder.setId(orderId);
            orderDAO.update(updatedOrder);
            return ResponseEntity.ok(updatedOrder);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Map<String, String>> deleteOrder(@PathVariable UUID orderId) {
        try {
            orderDAO.delete(orderId);
            Map<String, String> response = new HashMap<>();
            response.put("success", "Order " + orderId + " deleted successfully.");
            return ResponseEntity.ok(response);
        } catch (SQLException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
