package com.food_orders.configuration;

<<<<<<< HEAD
import com.food_orders.repository.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Configuration
public class DatabaseConfiguration {
//
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//
//    @Value("${spring.datasource.username}")
//    private String dbUsername;
//
//    @Value("${spring.datasource.password}")
//

    private String dbUrl = "jdbc:postgresql://localhost/paradish";
    private String dbUsername = "postgres";
    private String dbPassword = "12345678";

    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    @Bean
    public UserDAO userDAO(Connection connection) {
        return new UserDAO(connection);
=======
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
>>>>>>> cdf1c897bbbbd252b54d9e8c13a70b9288fc6ccd
    }
}
