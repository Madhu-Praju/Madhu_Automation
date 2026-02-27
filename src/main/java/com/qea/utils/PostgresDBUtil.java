package com.qea.utils;

import java.sql.*;
import java.util.*;

/**
 * Utility class for PostgreSQL database operations.
 * Fetches data as List of Maps for easy comparison with frontend data.
 */
public class PostgresDBUtil {

    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private Connection connection;

    public PostgresDBUtil() {
        Properties prop = new configReader().init_pop();
        this.host = prop.getProperty("postgres.host", "localhost");
        this.port = prop.getProperty("postgres.port", "5432");
        this.database = prop.getProperty("postgres.database");
        this.username = prop.getProperty("postgres.username");
        this.password = prop.getProperty("postgres.password");
    }

    public PostgresDBUtil(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    /**
     * Opens a connection to the PostgreSQL database.
     */
    public void connect() {
        try {
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("[PostgresDBUtil] Connected to: " + database);
        } catch (SQLException e) {
            throw new RuntimeException("[PostgresDBUtil] Connection failed: " + e.getMessage(), e);
        }
    }

    /**
     * Executes a SELECT query and returns results as a List of Maps.
     * Each Map represents a row with column names as keys.
     *
     * @param query SQL SELECT query
     * @return List of row data as Maps
     */
    public List<Map<String, String>> executeQuery(String query) {
        List<Map<String, String>> results = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String colName = metaData.getColumnLabel(i);
                    String value = rs.getString(i);
                    row.put(colName, value);
                }
                results.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("[PostgresDBUtil] Query failed: " + e.getMessage(), e);
        }
        return results;
    }

    /**
     * Executes a parameterized SELECT query to prevent SQL injection.
     *
     * @param query  SQL query with ? placeholders
     * @param params parameter values
     * @return List of row data as Maps
     */
    public List<Map<String, String>> executeQuery(String query, Object... params) {
        List<Map<String, String>> results = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    Map<String, String> row = new LinkedHashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        String colName = metaData.getColumnLabel(i);
                        String value = rs.getString(i);
                        row.put(colName, value);
                    }
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("[PostgresDBUtil] Parameterized query failed: " + e.getMessage(), e);
        }
        return results;
    }

    /**
     * Fetches a single value from the database (first column of first row).
     *
     * @param query SQL query
     * @param params optional parameters
     * @return the value as String, or null if no result
     */
    public String fetchSingleValue(String query, Object... params) {
        List<Map<String, String>> results = executeQuery(query, params);
        if (!results.isEmpty()) {
            return results.get(0).values().iterator().next();
        }
        return null;
    }

    /**
     * Fetches a single column as a list of values.
     *
     * @param query      SQL query
     * @param columnName column to extract
     * @return list of values for that column
     */
    public List<String> fetchColumn(String query, String columnName) {
        List<String> values = new ArrayList<>();
        for (Map<String, String> row : executeQuery(query)) {
            values.add(row.get(columnName));
        }
        return values;
    }

    /**
     * Closes the database connection.
     */
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("[PostgresDBUtil] Connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("[PostgresDBUtil] Error closing connection: " + e.getMessage());
        }
    }
}
