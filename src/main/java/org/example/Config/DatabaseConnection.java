package org.example.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/tecnostore_db";
    private static final String USER = "alexidg";
    private static final String PASSWORD = "campus2023";

    public static Connection getConnection() {
        try {
            System.out.println("conexion exitosa");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error de conexión");
            e.printStackTrace();
            return null;
        }
    }
}
