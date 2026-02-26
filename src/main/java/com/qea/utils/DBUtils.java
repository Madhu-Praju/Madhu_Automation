package com.qea.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBUtils {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    // Database configuration - update these or load from config.properties
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    public DBUtils() {
        // Load from config.properties
        configReader confReader = new configReader();
        Properties prop = confReader.init_pop();
        
        this.dbUrl = prop.getProperty("db.url", "jdbc:mysql://localhost:3306/your_database");
        this.dbUsername = prop.getProperty("db.username", "root");
        this.dbPassword = prop.getProperty("db.password", "password");
    }

    public DBUtils(String url, String username, String password) {
        this.dbUrl = url;
        this.dbUsername = username;
        this.dbPassword = password;
    }

    /**
     * Establish database connection
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("Database connection established successfully");
        }
        return connection;
    }

    /**
     * Execute SELECT query and return results as List of Maps
     */
    public List<Map<String, Object>> executeQuery(String query) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();
        
        statement = getConnection().createStatement();
        resultSet = statement.executeQuery(query);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        
        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            results.add(row);
        }
        return results;
    }

    /**
     * Execute UPDATE/INSERT/DELETE query
     */
    public int executeUpdate(String query) throws SQLException {
        statement = getConnection().createStatement();
        return statement.executeUpdate(query);
    }

    /**
     * Close all database resources
     */
    public void closeConnection() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            System.out.println("Database connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
