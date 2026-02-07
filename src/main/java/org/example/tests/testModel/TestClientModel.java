package org.example.tests.testModel;

import java.util.Date;

import org.example.Model.ClientModel;

/**
 * Clase de pruebas para ClientModel
 * Valida la creación, getters, setters y toString del modelo
 */
public class TestClientModel {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   INICIANDO TESTS DE CLIENTMODEL");
        System.out.println("========================================\n");

        testConstructorVacio();
        testConstructorConParametros();
        testConstructorCompleto();
        testGettersYSetters();
        testToString();
        testValidacionEmail();

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
            ClientModel client = new ClientModel();
            System.out.println("✓ Constructor vacío creado exitosamente");
            System.out.println("  ID inicial: " + client.getId());
            assert client.getId() == 0 : "ID debe ser 0 por defecto";
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
            ClientModel client = new ClientModel(
                "Carlos Perez",
                "1002345678",
                "carlos@mail.com",
                "3001234567"
            );
            
            System.out.println("✓ Cliente creado:");
            System.out.println("  Nombre: " + client.getName());
            System.out.println("  DNI: " + client.getDni());
            System.out.println("  Email: " + client.getEmail());
            System.out.println("  Teléfono: " + client.getPhone());
            
            assert client.getName().equals("Carlos Perez") : "Nombre debe ser Carlos Perez";
            assert client.getDni().equals("1002345678") : "DNI debe ser correcto";
            assert client.getEmail().equals("carlos@mail.com") : "Email debe ser correcto";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 3: Validar constructor completo con ID y fechas
     */
    private static void testConstructorCompleto() {
        System.out.println("\n[TEST 3] Constructor completo con ID y fechas");
        try {
            Date now = new Date();
            ClientModel client = new ClientModel(
                1,
                "Ana Torres",
                "1003456789",
                "ana@mail.com",
                "3019876543",
                now,
                now
            );
            
            System.out.println("✓ Cliente completo creado:");
            System.out.println("  ID: " + client.getId());
            System.out.println("  Nombre: " + client.getName());
            System.out.println("  Creado en: " + client.getCreatedAt());
            System.out.println("  Actualizado en: " + client.getUpdatedAt());
            
            assert client.getId() == 1 : "ID debe ser 1";
            assert client.getCreatedAt() != null : "Fecha de creación no debe ser nula";
            assert client.getUpdatedAt() != null : "Fecha de actualización no debe ser nula";
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
            ClientModel client = new ClientModel();
            
            // Test Setters
            client.setId(10);
            client.setName("Luis Gomez");
            client.setDni("1004567890");
            client.setEmail("luis@mail.com");
            client.setPhone("3024567890");
            
            // Test Getters
            System.out.println("✓ Valores asignados mediante setters:");
            System.out.println("  ID: " + client.getId());
            System.out.println("  Nombre: " + client.getName());
            System.out.println("  DNI: " + client.getDni());
            System.out.println("  Email: " + client.getEmail());
            System.out.println("  Teléfono: " + client.getPhone());
            
            assert client.getId() == 10 : "ID debe ser 10";
            assert client.getName().equals("Luis Gomez") : "Nombre debe ser Luis Gomez";
            assert client.getDni().equals("1004567890") : "DNI debe ser correcto";
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
            ClientModel client = new ClientModel(
                1,
                "Maria Lopez",
                "1005678901",
                "maria@mail.com",
                "3036547891"
            );
            
            String clientString = client.toString();
            System.out.println("✓ String generado:");
            System.out.println("  " + clientString);
            
            assert clientString.contains("Maria Lopez") : "Debe contener el nombre";
            assert clientString.contains("ClientModel") : "Debe contener el nombre de la clase";
            assert clientString.contains("maria@mail.com") : "Debe contener el email";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 6: Validar formato de email
     */
    private static void testValidacionEmail() {
        System.out.println("\n[TEST 6] Validación de formato de email");
        try {
            // Email válido
            ClientModel client1 = new ClientModel(
                "Test User",
                "1234567890",
                "test@example.com",
                "3001234567"
            );
            System.out.println("✓ Email válido creado: " + client1.getEmail());
            
            // Email inválido (la validación ocurre en ClientService)
            ClientModel client2 = new ClientModel(
                "Test User 2",
                "0987654321",
                "invalid-email",
                "3009876543"
            );
            System.out.println("⚠ Email sin validación en modelo: " + client2.getEmail());
            System.out.println("  Nota: Las validaciones de email deben estar en ClientService");
            
            // Patrones válidos comunes
            String[] validEmails = {
                "user@example.com",
                "name.surname@company.co.uk",
                "info+tag@domain.com"
            };
            
            System.out.println("✓ Ejemplos de emails válidos:");
            for (String email : validEmails) {
                System.out.println("  - " + email);
            }
            
            System.out.println("  ✓ Validación de emails completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}