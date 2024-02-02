package com.food_orders.configuration;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DatabaseConfigurationIT {

    @Test
    void testDatabaseConnection() {
        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();

        try {
            Connection connection = databaseConfiguration.getConnection();
            assertNotNull(connection);
            System.out.println("Connection established successfully!");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
