package com.food_orders.repository;

import com.food_orders.entity.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserDAO implements GenericDAO<User> {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User insert(User toAdd) throws SQLException {
        String sql = "INSERT INTO \"User\" " +
                "(name, first_name, email, password, image, role, residence)" +
                " VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, toAdd.getName());
            preparedStatement.setString(2, toAdd.getFirst_name());
            preparedStatement.setString(3, toAdd.getEmail());
            preparedStatement.setString(4, toAdd.getPassword());
            preparedStatement.setString(5, toAdd.getImage());
            preparedStatement.setString(6, toAdd.getRole());
            preparedStatement.setString(7, toAdd.getResidence());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    UUID generatedId = (UUID) generatedKeys.getObject(1);
                    toAdd.setId(generatedId);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e; // Rethrow the exception to indicate failure
        }

        return toAdd;
    }


    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM \"User\"";

        List<User> allUsers = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet result = preparedStatement.executeQuery()) {

            while (result.next()) {
                allUsers.add(new User(
                        result.getObject("id", UUID.class),
                        result.getString("name"),
                        result.getString("first_name"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("image"),
                        result.getString("role"),
                        result.getString("residence")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return allUsers;
    }

    @Override
    public User getById(UUID userId) throws SQLException {
        String sql = "SELECT * FROM \"User\" WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, userId);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    return new User(
                            result.getObject("id", UUID.class),
                            result.getString("name"),
                            result.getString("first_name"),
                            result.getString("email"),
                            result.getString("password"),
                            result.getString("image"),
                            result.getString("role"),
                            result.getString("residence"));
                }
                return null;
            }
        }
    }

    @Override
    public void update(User updatedUser) throws SQLException {
        String sql = "UPDATE \"User\" SET name = ?, first_name = ?, email = ?, password = ?, image = ?, role = ?, residence = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, updatedUser.getName());
            preparedStatement.setString(2, updatedUser.getFirst_name());
            preparedStatement.setString(3, updatedUser.getEmail());
            preparedStatement.setString(4, updatedUser.getPassword());
            preparedStatement.setString(5, updatedUser.getImage());
            preparedStatement.setString(6, updatedUser.getRole());
            preparedStatement.setString(7, updatedUser.getResidence());
            preparedStatement.setObject(8, updatedUser.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(UUID userId) throws SQLException {
        String sql = "DELETE FROM \"User\" WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, userId);
            preparedStatement.executeUpdate();
        }
    }
}