package com.food_orders.repository;

import com.food_orders.entity.Included;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class IncludedDAO implements GenericDAO<Included> {
    private final Connection connection;

    public IncludedDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Included insert(Included toAdd) throws SQLException {
        String sql = "INSERT INTO \"included\" (id, id_Order, id_Dish) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, toAdd.getId());
            preparedStatement.setObject(2, toAdd.getId_Order());
            preparedStatement.setObject(3, toAdd.getId_Dish());

            preparedStatement.executeUpdate();
        }

        return toAdd;
    }

    @Override
    public List<Included> findAll() throws SQLException {
        String sql = "SELECT * FROM \"included\"";
        List<Included> includedList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Included included = new Included(
                        resultSet.getObject("id", UUID.class),
                        resultSet.getObject("id_Order", UUID.class),
                        resultSet.getObject("id_Dish", UUID.class));
                includedList.add(included);
            }
        }

        return includedList;
    }

    @Override
    public Included getById(UUID includedId) throws SQLException {
        String sql = "SELECT * FROM \"included\" WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, includedId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Included(
                            resultSet.getObject("id", UUID.class),
                            resultSet.getObject("id_Order", UUID.class),
                            resultSet.getObject("id_Dish", UUID.class));
                }
            }
        }

        return null;
    }

    @Override
    public void update(Included updatedIncluded) throws SQLException {
        // Since Included table has only foreign keys, there's no need to update records
        throw new UnsupportedOperationException("Update operation is not supported for Included table.");
    }

    @Override
    public void delete(UUID includedId) throws SQLException {
        String sql = "DELETE FROM \"included\" WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, includedId);
            preparedStatement.executeUpdate();
        }
    }
}
