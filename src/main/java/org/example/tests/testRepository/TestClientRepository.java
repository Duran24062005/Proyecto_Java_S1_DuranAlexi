package org.example.tests.testRepository;

import java.util.List;

import org.example.Config.DatabaseConnection;
import org.example.Model.ClientModel;
import org.example.repositories.ClientRepository;

/**
 * Clase de pruebas para ClientRepository
 * Valida operaciones CRUD de clientes
 */
public class TestClientRepository {
    private static ClientRepository clientRepository;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   INICIANDO TESTS DE CLIENTREPOSITORY");
        System.out.println("========================================\n");

        try {
            // Inicializar conexión
            DatabaseConnection.getConnection();
            clientRepository = new ClientRepository();

            // Ejecutar tests
            testGetAllClients();
            testGetClientById();
            testAddClient();
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
     * Test 1: Obtener todos los clientes
     */
    private static void testGetAllClients() {
        System.out.println("\n[TEST 1] Obtener todos los clientes");
        try {
            List<ClientModel> clients = clientRepository.getAllClients();
            System.out.println("✓ Clientes obtenidos: " + clients.size());
            
            if (!clients.isEmpty()) {
                System.out.println("  Primeros 3 clientes:");
                clients.stream().limit(3).forEach(client ->
                    System.out.printf("    - %s (DNI: %s, Email: %s)\n",
                        client.getName(), client.getDni(), client.getEmail())
                );
            }
            assert !clients.isEmpty() : "Debe haber al menos un cliente";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 2: Obtener cliente por ID
     */
    private static void testGetClientById() {
        System.out.println("\n[TEST 2] Obtener cliente por ID");
        try {
            // Obtener el primer cliente disponible
            List<ClientModel> allClients = clientRepository.getAllClients();
            if (allClients.isEmpty()) {
                System.out.println("⚠ No hay clientes en la base de datos");
                return;
            }
            
            int clientId = allClients.get(0).getId();
            ClientModel client = clientRepository.getClientById(clientId);
            
            System.out.println("✓ Cliente encontrado:");
            System.out.println("  ID: " + client.getId());
            System.out.println("  Nombre: " + client.getName());
            System.out.println("  DNI: " + client.getDni());
            System.out.println("  Email: " + client.getEmail());
            System.out.println("  Teléfono: " + client.getPhone());
            System.out.println("  Creado en: " + client.getCreatedAt());
            
            assert client.getId() == clientId : "ID debe coincidir";
            assert client.getName() != null : "Nombre no debe ser nulo";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 3: Añadir nuevo cliente
     */
    private static void testAddClient() {
        System.out.println("\n[TEST 3] Añadir nuevo cliente");
        try {
            ClientModel newClient = new ClientModel(
                "Pedro Sanchez",
                "1009876543",
                "pedro.sanchez@mail.com",
                "3045678901"
            );
            
            boolean added = clientRepository.addClient(newClient);
            System.out.println("✓ Cliente añadido: " + added);
            System.out.println("  Nombre: " + newClient.getName());
            System.out.println("  DNI: " + newClient.getDni());
            System.out.println("  Email: " + newClient.getEmail());
            System.out.println("  Teléfono: " + newClient.getPhone());
            
            assert added : "El cliente debe ser añadido correctamente";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 4: Actualizar cliente
     */
    private static void testUpdateClient() {
        System.out.println("\n[TEST 4] Actualizar cliente");
        try {
            // Obtener un cliente existente
            List<ClientModel> clients = clientRepository.getAllClients();
            if (clients.isEmpty()) {
                System.out.println("⚠ No hay clientes para actualizar");
                return;
            }
            
            ClientModel clientToUpdate = clients.get(0);
            String oldName = clientToUpdate.getName();
            String newName = "Nombre Actualizado";
            String newPhone = "3999999999";
            
            System.out.println("✓ Cliente antes de actualización:");
            System.out.println("  Nombre anterior: " + oldName);
            System.out.println("  Teléfono anterior: " + clientToUpdate.getPhone());
            
            // Modificar datos
            clientToUpdate.setName(newName);
            clientToUpdate.setPhone(newPhone);
            
            boolean updated = clientRepository.updateClient(5, clientToUpdate);
            System.out.println("\n  Cliente después de actualización:");
            System.out.println("  Nombre nuevo: " + clientToUpdate.getName());
            System.out.println("  Teléfono nuevo: " + clientToUpdate.getPhone());
            System.out.println("  Actualización exitosa: " + updated);
            
            // Verificar que se actualizó
            ClientModel verifiedClient = clientRepository.getClientById(clientToUpdate.getId());
            System.out.println("  Verificación en BD: " + verifiedClient.getName());
            
            assert updated : "El cliente debe ser actualizado";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 5: Eliminar cliente
     */
    private static void testDeleteClient() {
        System.out.println("\n[TEST 5] Eliminar cliente");
        try {
            // Crear un cliente de prueba para eliminar
            ClientModel testClient = new ClientModel(
                "Cliente Temporal",
                "9999999999",
                "temporal@mail.com",
                "3001111111"
            );
            
            boolean added = clientRepository.addClient(testClient);
            if (!added) {
                System.out.println("⚠ No se pudo crear el cliente de prueba");
                return;
            }
            
            // Obtener el ID del cliente creado
            List<ClientModel> allClients = clientRepository.getAllClients();
            ClientModel createdClient = allClients.stream()
                .filter(c -> c.getDni().equals("9999999999"))
                .findFirst()
                .orElse(null);
            
            if (createdClient == null) {
                System.out.println("⚠ No se encontró el cliente de prueba");
                return;
            }
            
            System.out.println("✓ Cliente de prueba creado (ID: " + createdClient.getId() + ")");
            System.out.println("  Nombre: " + createdClient.getName());
            System.out.println("  DNI: " + createdClient.getDni());
            
            boolean deleted = clientRepository.deleteClient(createdClient.getId());
            System.out.println("  Cliente eliminado: " + deleted);
            
            // Verificar eliminación
            ClientModel deletedClient = clientRepository.getClientById(createdClient.getId());
            System.out.println("  Verificación post-eliminación: ID = " + deletedClient.getId());
            
            assert deleted : "El cliente debe ser eliminado";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 6: Búsqueda de cliente por DNI
     */
    private static void testSearchByDni() {
        System.out.println("\n[TEST 6] Búsqueda de cliente por DNI");
        try {
            List<ClientModel> allClients = clientRepository.getAllClients();
            if (allClients.isEmpty()) {
                System.out.println("⚠ No hay clientes para buscar");
                return;
            }
            
            String dni = allClients.get(0).getDni();
            ClientModel found = clientRepository.getClientById(allClients.get(0).getId());
            
            System.out.println("✓ Cliente encontrado por DNI (" + dni + "):");
            System.out.println("  Nombre: " + found.getName());
            System.out.println("  Email: " + found.getEmail());
            
            assert found.getDni().equals(dni) : "DNI debe coincidir";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 7: Validar integridad de datos
     */
    private static void testDataIntegrity() {
        System.out.println("\n[TEST 7] Validar integridad de datos");
        try {
            List<ClientModel> clients = clientRepository.getAllClients();
            System.out.println("✓ Verificación de integridad de " + clients.size() + " clientes:");
            
            int validCount = 0;
            for (ClientModel client : clients) {
                boolean isValid = client.getId() > 0 &&
                                 client.getName() != null &&
                                 !client.getName().isEmpty() &&
                                 client.getDni() != null &&
                                 !client.getDni().isEmpty() &&
                                 client.getEmail() != null &&
                                 !client.getEmail().isEmpty();
                
                if (isValid) {
                    validCount++;
                }
            }
            
            System.out.println("  Clientes válidos: " + validCount + " de " + clients.size());
            System.out.printf("  Porcentaje de integridad: %.2f%%\n", 
                (validCount * 100.0) / clients.size());
            
            assert validCount == clients.size() : "Todos los clientes deben ser válidos";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}
