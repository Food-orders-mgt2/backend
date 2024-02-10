package com.food_orders.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfiguration {

    @Value("${DB_URL}")
    private String url;

    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
