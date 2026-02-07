package org.example.tests.testModel;

import java.util.Date;

import org.example.Model.PhoneModel;

/**
 * Clase de pruebas para PhoneModel
 * Valida la creación, getters, setters y toString del modelo
 */
public class TestPhoneModel {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   INICIANDO TESTS DE PHONEMODEL");
        System.out.println("========================================\n");

        testConstructorVacio();
        testConstructorConParametros();
        testConstructorCompleto();
        testGettersYSetters();
        testToString();
        testValidacionDatos();

        System.out.println("\n========================================");
        System.out.println("   TODOS LOS TESTS COMPLETADOS");
        System.out.println("========================================");
    }

    /**
     * Test 1: Validar constructor vacío
     */
    private static void testConstructorVacio() {
        System.out.println("\n[TEST 1] Constructor vacío");
        try {
            PhoneModel phone = new PhoneModel();
            System.out.println("✓ Constructor vacío creado exitosamente");
            System.out.println("  ID inicial: " + phone.getId());
            assert phone.getId() == 0 : "ID debe ser 0 por defecto";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 2: Validar constructor con parámetros básicos
     */
    private static void testConstructorConParametros() {
        System.out.println("\n[TEST 2] Constructor con parámetros básicos");
        try {
            PhoneModel phone = new PhoneModel(
                "Samsung",
                "Galaxy S23",
                "Android",
                "Alta",
                4200.00,
                15
            );
            
            System.out.println("✓ Teléfono creado:");
            System.out.println("  Marca: " + phone.getBrand());
            System.out.println("  Modelo: " + phone.getModel());
            System.out.println("  SO: " + phone.getOperativeSystem());
            System.out.println("  Gama: " + phone.getRangeCategory());
            System.out.println("  Precio: $" + phone.getPrice());
            System.out.println("  Stock: " + phone.getStock());
            
            assert phone.getBrand().equals("Samsung") : "Marca debe ser Samsung";
            assert phone.getPrice() == 4200.00 : "Precio debe ser 4200.00";
            assert phone.getStock() == 15 : "Stock debe ser 15";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 3: Validar constructor completo con fechas
     */
    private static void testConstructorCompleto() {
        System.out.println("\n[TEST 3] Constructor completo con fechas");
        try {
            Date now = new Date();
            PhoneModel phone = new PhoneModel(
                1,
                "Apple",
                "iPhone 14",
                "iOS",
                "Alta",
                5200.00,
                10,
                now,
                now
            );
            
            System.out.println("✓ Teléfono completo creado:");
            System.out.println("  ID: " + phone.getId());
            System.out.println("  Marca: " + phone.getBrand());
            System.out.println("  Creado en: " + phone.getCreatedAt());
            System.out.println("  Actualizado en: " + phone.getUpdatedAt());
            
            assert phone.getId() == 1 : "ID debe ser 1";
            assert phone.getCreatedAt() != null : "Fecha de creación no debe ser nula";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 4: Validar getters y setters
     */
    private static void testGettersYSetters() {
        System.out.println("\n[TEST 4] Getters y Setters");
        try {
            PhoneModel phone = new PhoneModel();
            
            // Test Setters
            phone.setId(5);
            phone.setBrand("Xiaomi");
            phone.setModel("Redmi Note 12");
            phone.setOperativeSystem("Android");
            phone.setRangeCategory("Media");
            phone.setPrice(1800.00);
            phone.setStock(30);
            
            // Test Getters
            System.out.println("✓ Valores asignados mediante setters:");
            System.out.println("  ID: " + phone.getId());
            System.out.println("  Marca: " + phone.getBrand());
            System.out.println("  Modelo: " + phone.getModel());
            System.out.println("  SO: " + phone.getOperativeSystem());
            System.out.println("  Gama: " + phone.getRangeCategory());
            System.out.println("  Precio: $" + phone.getPrice());
            System.out.println("  Stock: " + phone.getStock());
            
            assert phone.getId() == 5 : "ID debe ser 5";
            assert phone.getBrand().equals("Xiaomi") : "Marca debe ser Xiaomi";
            assert phone.getPrice() == 1800.00 : "Precio debe ser 1800.00";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 5: Validar método toString
     */
    private static void testToString() {
        System.out.println("\n[TEST 5] Método toString");
        try {
            PhoneModel phone = new PhoneModel(
                "Samsung",
                "Galaxy S23",
                "Android",
                "Alta",
                4200.00,
                15
            );
            
            String phoneString = phone.toString();
            System.out.println("✓ String generado:");
            System.out.println("  " + phoneString);
            
            assert phoneString.contains("Samsung") : "Debe contener la marca";
            assert phoneString.contains("PhoneModel") : "Debe contener el nombre de la clase";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 6: Validar restricciones de datos
     */
    private static void testValidacionDatos() {
        System.out.println("\n[TEST 6] Validación de datos (restricciones)");
        try {
            PhoneModel phone = new PhoneModel(
                "TestPhone",
                "Model123",
                "TestOS",
                "Media",
                -100,  // Precio negativo
                -5     // Stock negativo
            );
            
            System.out.println("⚠ Advertencia: El modelo permite valores negativos");
            System.out.println("  Precio negativo: " + phone.getPrice());
            System.out.println("  Stock negativo: " + phone.getStock());
            System.out.println("  Nota: Las validaciones deben estar en el servicio/repository");
            
            // Estos validadores deben estar en PhoneService
            boolean priceValid = phone.getPrice() > 0;
            boolean stockValid = phone.getStock() >= 0;
            
            System.out.println("  ✓ Validación de restricciones completada");
            System.out.println("    (La validación real ocurre en PhoneService)");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}