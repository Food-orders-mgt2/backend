package com.food_orders.controller;

import com.food_orders.entity.Contains;
import com.food_orders.repository.ContainsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:5173", "https://main--para-dish.netlify.app", "https://para-dish.netlify.app"})
@RestController
@RequestMapping("/contains")
public class ContainsController {
    @Autowired
    private ContainsDAO containsDAO;

    @PostMapping
    public ResponseEntity<Contains> addContains(@RequestBody Contains contains) {
        try {
            Contains addedContains = containsDAO.insert(contains);
            return ResponseEntity.ok(addedContains);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Contains>> getAllContains() {
        try {
            List<Contains> containsList = containsDAO.findAll();
            return ResponseEntity.ok(containsList);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{containsId}")
    public ResponseEntity<Contains> getContainsById(@PathVariable UUID containsId) {
        try {
            Contains contains = containsDAO.getById(containsId);
            if (contains != null) {
                return ResponseEntity.ok(contains);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{containsId}")
    public ResponseEntity<?> deleteContains(@PathVariable UUID containsId) {
        try {
            containsDAO.delete(containsId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
