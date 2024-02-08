package com.food_orders.repository;

import com.food_orders.entity.Dish;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DishDAO implements GenericDAO<Dish> {
    private final Connection connection;

    public DishDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Dish insert(Dish toAdd) throws SQLException {
        String sql = "INSERT INTO \"Dish\" (id, name, price, image) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, toAdd.getId());
            preparedStatement.setString(2, toAdd.getName());
            preparedStatement.setDouble(3, toAdd.getPrice());
            preparedStatement.setString(4, toAdd.getImage());

            preparedStatement.executeUpdate();
        }

        return toAdd;
    }

    @Override
    public List<Dish> findAll() throws SQLException {
        String sql = "SELECT * FROM \"Dish\"";
        List<Dish> dishes = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Dish dish = new Dish(
                        resultSet.getObject("id", UUID.class),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"));
                dishes.add(dish);
            }
        }

        return dishes;
    }

    @Override
    public Dish getById(UUID dishId) throws SQLException {
        String sql = "SELECT * FROM \"Dish\" WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, dishId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Dish(
                            resultSet.getObject("id", UUID.class),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getString("image"));
                }
            }
        }

        return null;
    }

    @Override
    public void update(Dish updatedDish) throws SQLException {
        String sql = "UPDATE \"Dish\" SET name = ?, price = ?, image = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, updatedDish.getName());
            preparedStatement.setDouble(2, updatedDish.getPrice());
            preparedStatement.setString(3, updatedDish.getImage());
            preparedStatement.setObject(4, updatedDish.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(UUID dishId) throws SQLException {
        String sql = "DELETE FROM \"Dish\" WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, dishId);
            preparedStatement.executeUpdate();
        }
    }
}
