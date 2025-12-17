package com.adri.taskmanager.dao;

import java.io.InputStream;
import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static final Properties properties = new Properties();

    static {
        try (InputStream is = ConnectionManager.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (is == null) {
                throw new RuntimeException("No se pudo encontrar el archivo db.properties");
            }

            properties.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando db.properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // fuerza el registro del driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password")
        );
    }
}
