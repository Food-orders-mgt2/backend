package com.food_orders.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {

    public Connection getConnection() throws SQLException {
        String dbURL = System.getenv("dbURL");
        String dbUSERNAME = System.getenv("dbUSERNAME");
        String dbPASSWORD = System.getenv("dbPASSWORD");

        System.out.println("dbURL : " + dbURL);
        System.out.println("dbUSERNAME : " + dbUSERNAME);

        return DriverManager.getConnection(dbURL, dbUSERNAME, dbPASSWORD);
    }
}
