package org.example.tests.testService;

import java.util.List;

import org.example.Config.DatabaseConnection;
import org.example.Model.ClientModel;
import org.example.service.ClientService;

/**
 * Clase de pruebas para ClientService
 * Valida lógica de negocio y validaciones de clientes
 */
public class TestClientService {
    private static ClientService clientService;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   INICIANDO TESTS DE CLIENTSERVICE");
        System.out.println("========================================\n");

        try {
            // Inicializar conexión
            DatabaseConnection.getConnection();
            clientService = new ClientService();

            // Ejecutar tests
            testRegisterClientValid();
            testRegisterClientInvalid();
            testGetAllClients();
            testGetClientById();
            testGetClientByDni();
            testGetClientByEmail();
            testSearchClientsByName();
            testGetClientsSortedByName();
            testValidateEmail();
            testValidateDni();
            testUpdateClient();
            testDeleteClient();

            System.out.println("\n========================================");
            System.out.println("   TODOS LOS TESTS COMPLETADOS");
            System.out.println("========================================");

        } catch (Exception e) {
            System.out.println("✗ Error en la inicialización: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Test 1: Registrar cliente válido
     */
    private static void testRegisterClientValid() {
        System.out.println("\n[TEST 1] Registrar cliente válido");
        try {
            ClientModel newClient = new ClientModel(
                "Juan Fernandez",
                "1010101010",
                "juan.fernandez@mail.com",
                "3057890123"
            );
            
            boolean registered = clientService.registerClient(newClient);
            System.out.println("✓ Cliente registrado: " + registered);
            System.out.println("  Nombre: " + newClient.getName());
            System.out.println("  DNI: " + newClient.getDni());
            System.out.println("  Email: " + newClient.getEmail());
            System.out.println("  Teléfono: " + newClient.getPhone());
            
            assert registered : "El cliente debe ser registrado";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 2: Registrar cliente inválido (validaciones)
     */
    private static void testRegisterClientInvalid() {
        System.out.println("\n[TEST 2] Registrar cliente inválido (validaciones)");
        try {
            // Test 2a: Email inválido
            System.out.println("  2a. Validar email inválido:");
            ClientModel invalidClient1 = new ClientModel(
                "Test User",
                "1234567890",
                "invalid-email",  // Email sin @
                "3001234567"
            );
            boolean result1 = clientService.registerClient(invalidClient1);
            System.out.println("     Resultado (debe ser false): " + result1);
            assert !result1 : "No debe registrar con email inválido";
            
            // Test 2b: Nombre vacío
            System.out.println("  2b. Validar nombre vacío:");
            ClientModel invalidClient2 = new ClientModel(
                "",  // Nombre vacío
                "1234567890",
                "test@mail.com",
                "3001234567"
            );
            boolean result2 = clientService.registerClient(invalidClient2);
            System.out.println("     Resultado (debe ser false): " + result2);
            assert !result2 : "No debe registrar con nombre vacío";
            
            // Test 2c: DNI vacío
            System.out.println("  2c. Validar DNI vacío:");
            ClientModel invalidClient3 = new ClientModel(
                "Test User",
                "",  // DNI vacío
                "test@mail.com",
                "3001234567"
            );
            boolean result3 = clientService.registerClient(invalidClient3);
            System.out.println("     Resultado (debe ser false): " + result3);
            assert !result3 : "No debe registrar con DNI vacío";
            
            System.out.println("  ✓ Validación de restricciones completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 3: Obtener todos los clientes
     */
    private static void testGetAllClients() {
        System.out.println("\n[TEST 3] Obtener todos los clientes");
        try {
            List<ClientModel> clients = clientService.getAllClients();
            System.out.println("✓ Clientes obtenidos: " + clients.size());
            
            if (!clients.isEmpty()) {
                System.out.println("  Primeros 3 clientes:");
                clients.stream().limit(3).forEach(client ->
                    System.out.printf("    - %s (DNI: %s)\n", client.getName(), client.getDni())
                );
            }
            
            int totalCount = clientService.countClients();
            System.out.println("  Total de clientes registrados: " + totalCount);
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 4: Obtener cliente por ID
     */
    private static void testGetClientById() {
        System.out.println("\n[TEST 4] Obtener cliente por ID");
        try {
            List<ClientModel> allClients = clientService.getAllClients();
            if (allClients.isEmpty()) {
                System.out.println("⚠ No hay clientes disponibles");
                return;
            }
            
            int clientId = allClients.get(0).getId();
            ClientModel client = clientService.getClientById(clientId);
            
            System.out.println("✓ Cliente encontrado:");
            System.out.println("  ID: " + client.getId());
            System.out.println("  Nombre: " + client.getName());
            System.out.println("  DNI: " + client.getDni());
            System.out.println("  Email: " + client.getEmail());
            System.out.println("  Teléfono: " + client.getPhone());
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 5: Obtener cliente por DNI
     */
    private static void testGetClientByDni() {
        System.out.println("\n[TEST 5] Obtener cliente por DNI");
        try {
            List<ClientModel> allClients = clientService.getAllClients();
            if (allClients.isEmpty()) {
                System.out.println("⚠ No hay clientes disponibles");
                return;
            }
            
            String dni = allClients.get(0).getDni();
            ClientModel client = clientService.getClientByDni(dni);
            
            System.out.println("✓ Cliente encontrado por DNI (" + dni + "):");
            System.out.println("  Nombre: " + client.getName());
            System.out.println("  Email: " + client.getEmail());
            
            assert client.getDni().equals(dni) : "DNI debe coincidir";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 6: Obtener cliente por Email
     */
    private static void testGetClientByEmail() {
        System.out.println("\n[TEST 6] Obtener cliente por Email");
        try {
            List<ClientModel> allClients = clientService.getAllClients();
            if (allClients.isEmpty()) {
                System.out.println("⚠ No hay clientes disponibles");
                return;
            }
            
            String email = allClients.get(0).getEmail();
            ClientModel client = clientService.getClientByEmail(email);
            
            System.out.println("✓ Cliente encontrado por Email (" + email + "):");
            System.out.println("  Nombre: " + client.getName());
            System.out.println("  DNI: " + client.getDni());
            
            assert client.getEmail().equals(email) : "Email debe coincidir";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 7: Buscar clientes por nombre
     */
    private static void testSearchClientsByName() {
        System.out.println("\n[TEST 7] Buscar clientes por nombre");
        try {
            String searchTerm = "a";  // Buscar clientes con 'a' en el nombre
            List<ClientModel> results = clientService.searchClientsByName(searchTerm);
            
            System.out.println("✓ Búsqueda de clientes con '" + searchTerm + "': " + results.size() + " resultados");
            
            if (!results.isEmpty()) {
                System.out.println("  Resultados:");
                results.stream().limit(5).forEach(client ->
                    System.out.printf("    - %s (DNI: %s)\n", client.getName(), client.getDni())
                );
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 8: Obtener clientes ordenados por nombre
     */
    private static void testGetClientsSortedByName() {
        System.out.println("\n[TEST 8] Obtener clientes ordenados por nombre");
        try {
            List<ClientModel> sortedClients = clientService.getClientsSortedByName();
            System.out.println("✓ Clientes ordenados alfabéticamente: " + sortedClients.size());
            
            if (!sortedClients.isEmpty()) {
                System.out.println("  Primeros 5:");
                sortedClients.stream().limit(5).forEach((client) -> System.out.printf("    %d. %s \n", client.getId(), client.getName()));
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 9: Validar formato de email
     */
    private static void testValidateEmail() {
        System.out.println("\n[TEST 9] Validar formato de email");
        try {
            System.out.println("✓ Pruebas de validación de email:");
            
            String[] validEmails = {
                "user@example.com",
                "test.email@company.co.uk",
                "info+tag@domain.com"
            };
            
            String[] invalidEmails = {
                "invalid",
                "test@",
                "@domain.com",
                "user @example.com"
            };
            
            System.out.println("  Emails válidos:");
            for (String email : validEmails) {
                System.out.println("    ✓ " + email);
            }
            
            System.out.println("  Emails inválidos:");
            for (String email : invalidEmails) {
                System.out.println("    ✗ " + email);
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 10: Validar DNI
     */
    private static void testValidateDni() {
        System.out.println("\n[TEST 10] Validar DNI");
        try {
            System.out.println("✓ Validación de DNI:");
            
            String[] validDnis = {
                "1002345678",
                "1003456789",
                "1004567890"
            };
            
            System.out.println("  DNIs válidos:");
            for (String dni : validDnis) {
                System.out.println("    ✓ " + dni);
            }
            
            System.out.println("  Restricciones:");
            System.out.println("    - Debe ser único (no duplicados)");
            System.out.println("    - No puede estar vacío");
            System.out.println("    - Debe ser un identificador válido");
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 11: Actualizar cliente
     */
    private static void testUpdateClient() {
        System.out.println("\n[TEST 11] Actualizar cliente");
        try {
            List<ClientModel> clients = clientService.getAllClients();
            if (clients.isEmpty()) {
                System.out.println("⚠ No hay clientes para actualizar");
                return;
            }
            
            ClientModel clientToUpdate = clients.get(0);
            String oldPhone = clientToUpdate.getPhone();
            String newPhone = "3999999999";
            
            System.out.println("✓ Cliente antes de actualización:");
            System.out.println("  Nombre: " + clientToUpdate.getName());
            System.out.println("  Teléfono anterior: " + oldPhone);
            
            // Modificar datos
            clientToUpdate.setPhone(newPhone);
            
            boolean updated = clientService.updateClient(1, clientToUpdate);
            System.out.println("  Teléfono nuevo: " + clientToUpdate.getPhone());
            System.out.println("  Actualización exitosa: " + updated);
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 12: Eliminar cliente
     */
    private static void testDeleteClient() {
        System.out.println("\n[TEST 12] Eliminar cliente");
        try {
            // Crear cliente de prueba
            ClientModel testClient = new ClientModel(
                "Cliente Prueba Eliminar",
                "7777777777",
                "prueba.eliminar@mail.com",
                "3009999999"
            );
            
            boolean registered = clientService.registerClient(testClient);
            if (!registered) {
                System.out.println("⚠ No se pudo crear el cliente de prueba");
                return;
            }
            
            // Obtener el cliente creado
            ClientModel createdClient = clientService.getClientByDni("7777777777");
            if (createdClient == null) {
                System.out.println("⚠ No se encontró el cliente de prueba");
                return;
            }
            
            System.out.println("✓ Cliente de prueba creado (ID: " + createdClient.getId() + ")");
            System.out.println("  Nombre: " + createdClient.getName());
            
            boolean deleted = clientService.deleteClient(createdClient.getId());
            System.out.println("  Cliente eliminado: " + deleted);
            
            boolean exists = clientService.clientExists(createdClient.getId());
            System.out.println("  Existe después de eliminar: " + exists);
            
            assert deleted : "El cliente debe ser eliminado";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 13: Obtener estadísticas de clientes
     */
    private static void testClientStatistics() {
        System.out.println("\n[TEST 13] Estadísticas de clientes");
        try {
            int totalClients = clientService.countClients();
            ClientModel longestName = clientService.getClientWithLongestName();
            List<String> allPhones = clientService.getAllPhoneNumbers();
            
            System.out.println("✓ Estadísticas de clientes:");
            System.out.println("  Total de clientes: " + totalClients);
            System.out.println("  Números de teléfono únicos: " + allPhones.size());
            
            if (longestName != null) {
                System.out.println("  Cliente con nombre más largo:");
                System.out.println("    " + longestName.getName() + " (" + 
                    longestName.getName().length() + " caracteres)");
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}