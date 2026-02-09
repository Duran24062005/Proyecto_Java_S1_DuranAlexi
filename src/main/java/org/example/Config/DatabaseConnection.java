package org.example.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.SQLNonTransientConnectionException;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {

    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public DatabaseConnection() {
        try (Connection conn = getConnection();) {
            System.out.println("Conexión a BD establecida correctamente");
            conn.close();
        } catch (RuntimeException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public static Connection getConnection() {

        // Validación de variables de entorno
        if (URL == null || USER == null || PASSWORD == null) {
            throw new IllegalStateException(
                "Configuración inválida: verifica DB_URL, DB_USER y DB_PASSWORD en el archivo .env"
            );
        }

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // System.out.println("Conexión a BD establecida correctamente");
            return conn;

        } catch (SQLInvalidAuthorizationSpecException e) {
            // Credenciales incorrectas
            throw new RuntimeException(
                "Error de autenticación: usuario o contraseña incorrectos (DB_USER / DB_PASSWORD)",
                e
            );

        } catch (SQLNonTransientConnectionException e) {
            // Host, puerto, base de datos
            throw new RuntimeException(
                "No se pudo conectar al servidor MySQL. Revisa host, puerto o nombre de la BD en DB_URL",
                e
            );

        } catch (SQLException e) {
            // Error SQL genérico
            throw new RuntimeException(
                "Error SQL. Código: " + e.getErrorCode() +
                " | Estado SQL: " + e.getSQLState(),
                e
            );
        }
    }
}
