package com.food_orders.controller;

import com.food_orders.entity.Ingredient;
import com.food_orders.repository.IngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    @Autowired
    private IngredientDAO ingredientDAO;

    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        try {
            Ingredient addedIngredient = ingredientDAO.insert(ingredient);
            return ResponseEntity.ok(addedIngredient);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        try {
            List<Ingredient> ingredients = ingredientDAO.findAll();
            return ResponseEntity.ok(ingredients);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{ingredientId}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable UUID ingredientId) {
        try {
            Ingredient ingredient = ingredientDAO.getById(ingredientId);
            if (ingredient != null) {
                return ResponseEntity.ok(ingredient);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{ingredientId}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable UUID ingredientId, @RequestBody Ingredient updatedIngredient) {
        try {
            updatedIngredient.setId(ingredientId);
            ingredientDAO.update(updatedIngredient);
            return ResponseEntity.ok(updatedIngredient);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<?> deleteIngredient(@PathVariable UUID ingredientId) {
        try {
            ingredientDAO.delete(ingredientId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
