package com.food_orders.repository;

import com.food_orders.entity.Ingredient;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class IngredientDAO implements GenericDAO<Ingredient> {
    private final Connection connection;

    public IngredientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Ingredient insert(Ingredient toAdd) throws SQLException {
        String sql = "INSERT INTO \"Ingredient\" (id, name) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, toAdd.getId());
            preparedStatement.setString(2, toAdd.getName());

            preparedStatement.executeUpdate();
        }

        return toAdd;
    }

    @Override
    public List<Ingredient> findAll() throws SQLException {
        String sql = "SELECT * FROM \"Ingredient\"";
        List<Ingredient> ingredients = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient(
                        resultSet.getObject("id", UUID.class),
                        resultSet.getString("name"));
                ingredients.add(ingredient);
            }
        }

        return ingredients;
    }

    @Override
    public Ingredient getById(UUID ingredientId) throws SQLException {
        String sql = "SELECT * FROM \"Ingredient\" WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, ingredientId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Ingredient(
                            resultSet.getObject("id", UUID.class),
                            resultSet.getString("name"));
                }
            }
        }

        return null;
    }

    @Override
    public void update(Ingredient updatedIngredient) throws SQLException {
        String sql = "UPDATE \"Ingredient\" SET name = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, updatedIngredient.getName());
            preparedStatement.setObject(2, updatedIngredient.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(UUID ingredientId) throws SQLException {
        String sql = "DELETE FROM \"Ingredient\" WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, ingredientId);
            preparedStatement.executeUpdate();
        }
    }
}
