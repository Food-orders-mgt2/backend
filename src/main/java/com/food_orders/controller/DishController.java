package com.food_orders.controller;

import com.food_orders.entity.Dish;
import com.food_orders.repository.DishDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = {"http://localhost:5173", "https://main--para-dish.netlify.app"})
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishDAO dishDAO;

    @PostMapping
    public ResponseEntity<Dish> addDish(@RequestBody Dish dish) {
        try {
            Dish addedDish = dishDAO.insert(dish);
            return ResponseEntity.ok(addedDish);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes() {
        try {
            List<Dish> dishes = dishDAO.findAll();
            return ResponseEntity.ok(dishes);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{dishId}")
    public ResponseEntity<Dish> getDishById(@PathVariable UUID dishId) {
        try {
            Dish dish = dishDAO.getById(dishId);
            if (dish != null) {
                return ResponseEntity.ok(dish);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{dishId}")
    public ResponseEntity<Dish> updateDish(@PathVariable UUID dishId, @RequestBody Dish updatedDish) {
        try {
            dishDAO.update(updatedDish);
            return ResponseEntity.ok(updatedDish);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{dishId}")
    public ResponseEntity<?> deleteDish(@PathVariable UUID dishId) {
        try {
            dishDAO.delete(dishId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
