package com.food_orders.controller;

import com.food_orders.entity.Included;
import com.food_orders.repository.IncludedDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/included")
public class IncludedController {
    @Autowired
    private IncludedDAO includedDAO;

    @PostMapping
    public ResponseEntity<Included> addIncluded(@RequestBody Included included) {
        try {
            Included addedIncluded = includedDAO.insert(included);
            return ResponseEntity.ok(addedIncluded);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Included>> getAllIncluded() {
        try {
            List<Included> includedList = includedDAO.findAll();
            return ResponseEntity.ok(includedList);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{includedId}")
    public ResponseEntity<Included> getIncludedById(@PathVariable UUID includedId) {
        try {
            Included included = includedDAO.getById(includedId);
            if (included != null) {
                return ResponseEntity.ok(included);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{includedId}")
    public ResponseEntity<?> deleteIncluded(@PathVariable UUID includedId) {
        try {
            includedDAO.delete(includedId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
