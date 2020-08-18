package com.codecool.shop.dao.database;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class DatabaseManager {
    private static final Properties properties = new Properties();
    private static DatabaseManager instance = null;

    private DatabaseManager(String propFile) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFile);
        properties.load(inputStream);
    }

    public static DatabaseManager getInstance(String propFile) throws IOException {
        if (instance == null) {
            instance = new DatabaseManager(propFile);
        }
        return instance;
    }

    public void initDatabase() {
        executeUpdateFromFile("src/main/resources/init_db.sql");
    }

    public Connection getConnection() {
        try {
            String url = "jdbc:postgresql://" + properties.getProperty("url") + "/" + properties.getProperty("database");
            String user = properties.getProperty("user");
            String pass = properties.getProperty("password");
            return DriverManager.getConnection( url, user, pass);
        } catch (SQLException e) {
            System.err.println("ERROR: Connection error.");
            e.printStackTrace();
        }
        return null;
    }

    public void executeUpdate(String query) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLTimeoutException e) {
            System.err.println("ERROR: SQL Timeout on execute update.");
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLTimeoutException e) {
            System.err.println("ERROR: SQL Timeout on execute query");
        }
        return null;
    }

    public void executeUpdateFromFile(String filePath) {
        String query = "";
        try {
            query = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
