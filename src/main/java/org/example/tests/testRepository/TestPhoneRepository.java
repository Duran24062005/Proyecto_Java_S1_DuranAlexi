package org.example.tests.testRepository;

import java.util.List;

import org.example.Config.DatabaseConnection;
import org.example.Model.PhoneModel;
import org.example.repository.PhoneRepository;

/**
 * Clase de pruebas para PhoneRepository
 * Valida operaciones CRUD y consultas especiales de celulares
 */
public class TestPhoneRepository {
    private static PhoneRepository phoneRepository;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   INICIANDO TESTS DE PHONEREPOSITORY");
        System.out.println("========================================\n");

        try {
            // Inicializar conexión
            DatabaseConnection.getConnection();
            phoneRepository = new PhoneRepository();

            // Ejecutar tests
            testGetAllPhones();
            testGetPhoneById();
            testAddPhone();
            testUpdatePhone();
            testUpdatePhoneStock();
            testGetPhonesByLowStock();
            testDeletePhone();

            System.out.println("\n========================================");
            System.out.println("   TODOS LOS TESTS COMPLETADOS");
            System.out.println("========================================");

        } catch (Exception e) {
            System.out.println("✗ Error en la inicialización: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Test 1: Obtener todos los celulares
     */
    private static void testGetAllPhones() {
        System.out.println("\n[TEST 1] Obtener todos los celulares");
        try {
            List<PhoneModel> phones = phoneRepository.getAllPhones();
            System.out.println("✓ Celulares obtenidos: " + phones.size());
            
            if (!phones.isEmpty()) {
                System.out.println("  Primeros 3 celulares:");
                phones.stream().limit(3).forEach(phone ->
                    System.out.printf("    - %s %s (Precio: $%.2f, Stock: %d)\n",
                        phone.getBrand(), phone.getModel(), phone.getPrice(), phone.getStock())
                );
            }
            assert !phones.isEmpty() : "Debe haber al menos un celular";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 2: Obtener celular por ID
     */
    private static void testGetPhoneById() {
        System.out.println("\n[TEST 2] Obtener celular por ID");
        try {
            // Obtener el primer celular disponible
            List<PhoneModel> allPhones = phoneRepository.getAllPhones();
            if (allPhones.isEmpty()) {
                System.out.println("⚠ No hay celulares en la base de datos");
                return;
            }
            
            int phoneId = allPhones.get(0).getId();
            PhoneModel phone = phoneRepository.getPhoneById(phoneId);
            
            System.out.println("✓ Celular encontrado:");
            System.out.println("  ID: " + phone.getId());
            System.out.println("  Marca: " + phone.getBrand());
            System.out.println("  Modelo: " + phone.getModel());
            System.out.println("  Precio: $" + phone.getPrice());
            System.out.println("  Stock: " + phone.getStock());
            System.out.println("  SO: " + phone.getOperativeSystem());
            System.out.println("  Gama: " + phone.getRangeCategory());
            
            assert phone.getId() == phoneId : "ID debe coincidir";
            assert phone.getBrand() != null : "Marca no debe ser nula";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 3: Añadir nuevo celular
     */
    private static void testAddPhone() {
        System.out.println("\n[TEST 3] Añadir nuevo celular");
        try {
            PhoneModel newPhone = new PhoneModel(
                "OnePlus",
                "12 Pro",
                "Android",
                "Alta",
                3999.99,
                20
            );
            
            boolean added = phoneRepository.addPhone(newPhone);
            System.out.println("✓ Celular añadido: " + added);
            System.out.println("  Marca: " + newPhone.getBrand());
            System.out.println("  Modelo: " + newPhone.getModel());
            System.out.println("  Precio: $" + newPhone.getPrice());
            System.out.println("  Stock: " + newPhone.getStock());
            
            assert added : "El celular debe ser añadido correctamente";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 4: Actualizar celular
     */
    private static void testUpdatePhone() {
        System.out.println("\n[TEST 4] Actualizar celular");
        try {
            // Obtener un celular existente
            List<PhoneModel> phones = phoneRepository.getAllPhones();
            if (phones.isEmpty()) {
                System.out.println("⚠ No hay celulares para actualizar");
                return;
            }
            
            PhoneModel phoneToUpdate = phones.get(0);
            System.out.println("✓ Celular antes de actualización:");
            System.out.println("  Precio anterior: $" + phoneToUpdate.getPrice());
            System.out.println("  Stock anterior: " + phoneToUpdate.getStock());
            
            // Modificar datos
            phoneToUpdate.setPrice(phoneToUpdate.getPrice() + 100);
            phoneToUpdate.setStock(phoneToUpdate.getStock() + 5);
            
            boolean updated = phoneRepository.updatePhone(phoneToUpdate);
            System.out.println("\n  Celular después de actualización:");
            System.out.println("  Precio nuevo: $" + phoneToUpdate.getPrice());
            System.out.println("  Stock nuevo: " + phoneToUpdate.getStock());
            System.out.println("  Actualización exitosa: " + updated);
            
            assert updated : "El celular debe ser actualizado";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 5: Actualizar stock de celular
     */
    private static void testUpdatePhoneStock() {
        System.out.println("\n[TEST 5] Actualizar stock de celular");
        try {
            // Obtener un celular
            List<PhoneModel> phones = phoneRepository.getAllPhones();
            if (phones.isEmpty()) {
                System.out.println("⚠ No hay celulares para actualizar stock");
                return;
            }
            
            PhoneModel phone = phones.get(0);
            int oldStock = phone.getStock();
            int newStock = 50;
            
            System.out.println("✓ Actualización de stock:");
            System.out.println("  Celular: " + phone.getBrand() + " " + phone.getModel());
            System.out.println("  Stock anterior: " + oldStock);
            System.out.println("  Stock nuevo: " + newStock);
            
            boolean updated = phoneRepository.updatePhoneStock(phone.getId(), newStock);
            
            PhoneModel updatedPhone = phoneRepository.getPhoneById(phone.getId());
            System.out.println("  Stock verificado: " + updatedPhone.getStock());
            System.out.println("  Actualización exitosa: " + updated);
            
            assert updated : "El stock debe actualizarse";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 6: Obtener celulares con stock bajo
     */
    private static void testGetPhonesByLowStock() {
        System.out.println("\n[TEST 6] Obtener celulares con stock bajo (< 5)");
        try {
            List<PhoneModel> lowStockPhones = phoneRepository.getPhonesLowStock();
            
            System.out.println("✓ Celulares con stock bajo: " + lowStockPhones.size());
            
            if (lowStockPhones.isEmpty()) {
                System.out.println("  No hay celulares con stock bajo");
            } else {
                System.out.println("  Celulares con stock bajo:");
                lowStockPhones.forEach(phone ->
                    System.out.printf("    - %s %s (Stock: %d)\n",
                        phone.getBrand(), phone.getModel(), phone.getStock())
                );
            }
            
            // Validar que todos tengan stock < 5
            for (PhoneModel phone : lowStockPhones) {
                assert phone.getStock() < 5 : "Stock debe ser menor a 5";
            }
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 7: Eliminar celular
     */
    private static void testDeletePhone() {
        System.out.println("\n[TEST 7] Eliminar celular");
        try {
            // Crear un celular de prueba para eliminar
            PhoneModel testPhone = new PhoneModel(
                "TestBrand",
                "TestModel",
                "TestOS",
                "Media",
                999.99,
                5
            );
            
            boolean added = phoneRepository.addPhone(testPhone);
            if (!added) {
                System.out.println("⚠ No se pudo crear el celular de prueba para eliminar");
                return;
            }
            
            // Obtener el ID del celular creado
            List<PhoneModel> allPhones = phoneRepository.getAllPhones();
            PhoneModel createdPhone = allPhones.stream()
                .filter(p -> p.getBrand().equals("TestBrand"))
                .findFirst()
                .orElse(null);
            
            if (createdPhone == null) {
                System.out.println("⚠ No se encontró el celular de prueba");
                return;
            }
            
            System.out.println("✓ Celular de prueba creado (ID: " + createdPhone.getId() + ")");
            
            boolean deleted = phoneRepository.deletePhone(createdPhone.getId());
            System.out.println("  Celular eliminado: " + deleted);
            
            PhoneModel deletedPhone = phoneRepository.getPhoneById(createdPhone.getId());
            System.out.println("  Verificación post-eliminación: ID = " + deletedPhone.getId());
            
            assert deleted : "El celular debe ser eliminado";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}