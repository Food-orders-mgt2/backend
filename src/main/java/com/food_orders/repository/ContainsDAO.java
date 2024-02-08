package com.food_orders.repository;

import com.food_orders.entity.Contains;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ContainsDAO implements GenericDAO<Contains> {
    private final Connection connection;

    public ContainsDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Contains insert(Contains toAdd) throws SQLException {
        String sql = "INSERT INTO \"contains\" (id, id_Dish, id_Ingredient) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, toAdd.getId());
            preparedStatement.setObject(2, toAdd.getId_Dish());
            preparedStatement.setObject(3, toAdd.getId_Ingredient());

            preparedStatement.executeUpdate();
        }

        return toAdd;
    }

    @Override
    public List<Contains> findAll() throws SQLException {
        String sql = "SELECT * FROM \"contains\"";
        List<Contains> containsList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Contains contains = new Contains(
                        resultSet.getObject("id", UUID.class),
                        resultSet.getObject("id_Dish", UUID.class),
                        resultSet.getObject("id_Ingredient", UUID.class));
                containsList.add(contains);
            }
        }

        return containsList;
    }

    @Override
    public Contains getById(UUID containsId) throws SQLException {
        String sql = "SELECT * FROM \"contains\" WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, containsId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Contains(
                            resultSet.getObject("id", UUID.class),
                            resultSet.getObject("id_Dish", UUID.class),
                            resultSet.getObject("id_Ingredient", UUID.class));
                }
            }
        }

        return null;
    }

    @Override
    public void update(Contains updatedContains) throws SQLException {
        // Since Contains table has only foreign keys, there's no need to update records
        throw new UnsupportedOperationException("Update operation is not supported for Contains table.");
    }

    @Override
    public void delete(UUID containsId) throws SQLException {
        String sql = "DELETE FROM \"contains\" WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, containsId);
            preparedStatement.executeUpdate();
        }
    }
}
