package org.example.Config;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Clase de configuración centralizada.
 * Implementa patrón Singleton para acceso estático a variables de entorno.
 * 
 * Uso:
 *  String dbHost = Config.get("DB_HOST");
 *  int dbPort = Config.getInt("DB_PORT");
 */
public class Configs {
    private static Configs instance;
    private Dotenv dotenv;

    // ==================== Constructor Privado ====================
    private Configs() {
        try {
            this.dotenv = Dotenv.load();
        } catch (Exception e) {
            System.out.println("⚠️  Advertencia: No se encontró archivo .env, usando valores por defecto");
            this.dotenv = null;
        }
    }

    // ==================== Singleton ====================
    /**
     * Obtiene la instancia única de Config (Singleton)
     */
    public static synchronized Configs getInstance() {
        if (instance == null) {
            instance = new Configs();
        }
        return instance;
    }

    // ==================== Métodos de acceso ====================
    /**
     * Obtiene un valor de configuración como String
     * 
     * @param key Clave de la variable de entorno
     * @return Valor de la variable o null si no existe
     */
    public static String get(String key) {
        return getInstance().getValue(key);
    }

    /**
     * Obtiene un valor de configuración con valor por defecto
     * 
     * @param key Clave de la variable de entorno
     * @param defaultValue Valor por defecto si no existe
     * @return Valor de la variable o defaultValue
     */
    public static String get(String key, String defaultValue) {
        String value = getInstance().getValue(key);
        return (value != null) ? value : defaultValue;
    }

    /**
     * Obtiene un valor de configuración como entero
     * 
     * @param key Clave de la variable de entorno
     * @return Valor convertido a int
     */
    public static int getInt(String key) {
        String value = getInstance().getValue(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("❌ Error: No se pudo convertir '" + key + "' a int");
            return 0;
        }
    }

    /**
     * Obtiene un valor de configuración como entero con valor por defecto
     * 
     * @param key Clave de la variable de entorno
     * @param defaultValue Valor por defecto
     * @return Valor convertido a int o defaultValue
     */
    public static int getInt(String key, int defaultValue) {
        String value = getInstance().getValue(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException | NullPointerException e) {
            return defaultValue;
        }
    }

    /**
     * Obtiene un valor de configuración como double
     * 
     * @param key Clave de la variable de entorno
     * @return Valor convertido a double
     */
    public static double getDouble(String key) {
        String value = getInstance().getValue(key);
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("❌ Error: No se pudo convertir '" + key + "' a double");
            return 0.0;
        }
    }

    /**
     * Obtiene un valor de configuración como double con valor por defecto
     * 
     * @param key Clave de la variable de entorno
     * @param defaultValue Valor por defecto
     * @return Valor convertido a double o defaultValue
     */
    public static double getDouble(String key, double defaultValue) {
        String value = getInstance().getValue(key);
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException | NullPointerException e) {
            return defaultValue;
        }
    }

    /**
     * Obtiene un valor de configuración como booleano
     * 
     * @param key Clave de la variable de entorno
     * @return true si el valor es "true", "1", "yes" (case-insensitive)
     */
    public static boolean getBoolean(String key) {
        String value = getInstance().getValue(key);
        return value != null && 
               (value.equalsIgnoreCase("true") || 
                value.equalsIgnoreCase("1") || 
                value.equalsIgnoreCase("yes"));
    }

    /**
     * Obtiene un valor de configuración como booleano con valor por defecto
     * 
     * @param key Clave de la variable de entorno
     * @param defaultValue Valor por defecto
     * @return true/false según valor o defaultValue
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = getInstance().getValue(key);
        if (value == null) return defaultValue;
        return value.equalsIgnoreCase("true") || 
               value.equalsIgnoreCase("1") || 
               value.equalsIgnoreCase("yes");
    }

    // ==================== Método privado de obtención ====================
    /**
     * Método privado que obtiene el valor real de la variable de entorno
     */
    private String getValue(String key) {
        if (dotenv != null) {
            return dotenv.get(key);
        }
        return System.getenv(key);
    }

    // ==================== Método de inicialización ====================
    /**
     * Inicializa la configuración (opcional, llamar al inicio de Main)
     */
    public static void init() {
        getInstance();
        if (Configs.getBoolean("DEBUG_MODE")) {
            System.out.println("✓ Configuración cargada correctamente");
            System.out.println("  - Base de datos: " + Configs.get("DB_HOST") + ":" + Configs.get("DB_PORT"));
            System.out.println("  - Aplicación: " + Configs.get("APP_NAME") + " v" + Configs.get("APP_VERSION"));
        }
    }

    // ==================== Método para obtener URL de conexión ====================
    /**
     * Construye la URL de conexión JDBC
     */
    public static String getDbUrl() {
        return String.format("jdbc:mysql://%s:%s/%s",
                Configs.get("DB_HOST", "localhost"),
                Configs.get("DB_PORT", "3306"),
                Configs.get("DB_NAME", "tecnostore_db"));
    }

    /**
     * Obtiene el usuario de BD
     */
    public static String getDbUser() {
        return Configs.get("DB_USER", "root");
    }

    /**
     * Obtiene la contraseña de BD
     */
    public static String getDbPassword() {
        return Configs.get("DB_PASSWORD", "");
    }

    // ==================== Método para listar todas las configuraciones ====================
    /**
     * Método de debug: lista todas las configuraciones cargadas
     */
    public static void printAllConfig() {
        System.out.println("\n════════════════════════════════════════════");
        System.out.println("     CONFIGURACIÓN ACTUAL DEL SISTEMA");
        System.out.println("════════════════════════════════════════════");
        
        // Base de datos
        System.out.println("\n📊 BASE DE DATOS:");
        System.out.println("  DB_HOST: " + Configs.get("DB_HOST"));
        System.out.println("  DB_PORT: " + Configs.get("DB_PORT"));
        System.out.println("  DB_NAME: " + Configs.get("DB_NAME"));
        System.out.println("  DB_USER: " + Configs.get("DB_USER"));
        
        // Aplicación
        System.out.println("\n📱 APLICACIÓN:");
        System.out.println("  APP_NAME: " + Configs.get("APP_NAME"));
        System.out.println("  APP_VERSION: " + Configs.get("APP_VERSION"));
        System.out.println("  DEBUG_MODE: " + Configs.getBoolean("DEBUG_MODE"));
        
        // Reportes
        System.out.println("\n📄 REPORTES:");
        System.out.println("  REPORT_PATH: " + Configs.get("REPORT_PATH"));
        System.out.println("  REPORT_FILENAME: " + Configs.get("REPORT_FILENAME"));
        
        // Validación
        System.out.println("\n✔️  VALIDACIÓN:");
        System.out.println("  MIN_STOCK_WARNING: " + Configs.getInt("MIN_STOCK_WARNING"));
        System.out.println("  IVA_PERCENTAGE: " + Configs.getDouble("IVA_PERCENTAGE"));
        
        System.out.println("\n════════════════════════════════════════════\n");
    }
}
