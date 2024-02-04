package com.food_orders.controller;

import com.food_orders.entity.User;
import com.food_orders.repository.UserDAO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDAO userDAO;


    @PostMapping
    public User addClient(@RequestBody User client) throws SQLException {
        return userDAO.insert(client);
    }

    @GetMapping
    public List<User> getAllUsers() throws SQLException {
        return userDAO.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
        try {
            User user = userDAO.getById(userId);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody User updatedUser) {
        try {
            updatedUser.setId(userId);
            userDAO.update(updatedUser);
            return ResponseEntity.ok(updatedUser);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable UUID userId) {
        try {
            userDAO.delete(userId);

            Map<String, String> response = new HashMap<>();
            response.put("success", "User " + userId + " deleted successfully.");

            return ResponseEntity.ok(response);
        } catch (SQLException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
