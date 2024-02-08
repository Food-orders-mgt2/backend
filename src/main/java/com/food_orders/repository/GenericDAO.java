package com.food_orders.repository;


import com.food_orders.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface GenericDAO<T> {
    T insert(T toAdd) throws SQLException;

    List<T> findAll() throws SQLException;

    T getById(UUID clientId) throws SQLException;

    void update(T updatedClient) throws SQLException;

    void delete(UUID clientId) throws SQLException;

}
