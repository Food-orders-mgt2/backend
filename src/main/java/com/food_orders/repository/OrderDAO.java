package com.food_orders.repository;

import com.food_orders.entity.Order;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class OrderDAO implements GenericDAO<Order> {
    private final Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Order insert(Order toAdd) throws SQLException {
        String sql = "INSERT INTO \"Order\" (id, date_time, shipping_cost, delivery_date_time, delivery_place, id_User, pay_mode) " +
                "VALUES (uuid_generate_v4(), ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, toAdd.getDate_time());
            preparedStatement.setDouble(2, toAdd.getShipping_cost());
            preparedStatement.setTimestamp(3, toAdd.getDelivery_date_time());
            preparedStatement.setString(4, toAdd.getDelivery_place());
            preparedStatement.setObject(5, toAdd.getId_User());
            preparedStatement.setString(6, toAdd.getPay_mode());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    UUID generatedId = (UUID) generatedKeys.getObject(1);
                    toAdd.setId(generatedId);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        }

        return toAdd;
    }


    @Override
    public List<Order> findAll() throws SQLException {
        String sql = "SELECT * FROM \"Order\"";
        List<Order> orders = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getObject("id", UUID.class),
                        resultSet.getTimestamp("date_time"),
                        resultSet.getDouble("shipping_cost"),
                        resultSet.getTimestamp("delivery_date_time"),
                        resultSet.getString("delivery_place"),
                        resultSet.getObject("id_User", UUID.class),
                        resultSet.getString("pay_mode"));
                orders.add(order);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return orders;
    }

    @Override
    public Order getById(UUID orderId) throws SQLException {
        String sql = "SELECT * FROM \"Order\" WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Order(
                            resultSet.getObject("id", UUID.class),
                            resultSet.getTimestamp("date_time"),
                            resultSet.getDouble("shipping_cost"),
                            resultSet.getTimestamp("delivery_date_time"),
                            resultSet.getString("delivery_place"),
                            resultSet.getObject("id_User", UUID.class),
                            resultSet.getString("pay_mode"));
                }
            }
        }

        return null;
    }

    @Override
    public void update(Order updatedOrder) throws SQLException {
        String sql = "UPDATE \"Order\" SET date_time = ?, shipping_cost = ?, delivery_date_time = ?, " +
                "delivery_place = ?, id_User = ?, pay_mode = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, updatedOrder.getDate_time());
            preparedStatement.setDouble(2, updatedOrder.getShipping_cost());
            preparedStatement.setTimestamp(3, updatedOrder.getDelivery_date_time());
            preparedStatement.setString(4, updatedOrder.getDelivery_place());
            preparedStatement.setObject(5, updatedOrder.getId_User());
            preparedStatement.setString(6, updatedOrder.getPay_mode());
            preparedStatement.setObject(7, updatedOrder.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(UUID orderId) throws SQLException {
        String sql = "DELETE FROM \"Order\" WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, orderId);
            preparedStatement.executeUpdate();
        }
    }
}
