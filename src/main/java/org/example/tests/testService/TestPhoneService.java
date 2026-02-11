package org.example.tests.testService;

import java.util.List;

import org.example.Config.DatabaseConnection;
import org.example.Model.PhoneModel;
import org.example.service.PhoneService;

/**
 * Clase de pruebas para PhoneService
 * Valida lógica de negocio y validaciones de celulares
 */
public class TestPhoneService {
    private static PhoneService phoneService;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   INICIANDO TESTS DE PHONESERVICE");
        System.out.println("========================================\n");

        try {
            // Inicializar conexión
            DatabaseConnection.getConnection();
            phoneService = new PhoneService();

            // Ejecutar tests
            testRegisterPhoneValid();
            testRegisterPhoneInvalid();
            testGetAllPhones();
            testGetPhoneById();
            testGetPhonesByCategory();
            testGetPhonesByBrand();
            testGetPhonesSortedByPrice();
            testGetPhonesByLowStock();
            testCalculateAveragePrice();
            testCalculateTotalStock();
            testGetMostAndCheapestPhone();
            testReducePhoneStock();
            testUpdatePhoneStock();

            System.out.println("\n========================================");
            System.out.println("   TODOS LOS TESTS COMPLETADOS");
            System.out.println("========================================");

        } catch (Exception e) {
            System.out.println("✗ Error en la inicialización: " + e.getMessage());
        }
    }

    /**
     * Test 1: Registrar celular válido
     */
    private static void testRegisterPhoneValid() {
        System.out.println("\n[TEST 1] Registrar celular válido");
        try {
            PhoneModel newPhone = new PhoneModel(
                "Oppo",
                "A77",
                "Android",
                "Media",
                1500.00,
                25
            );
            
            boolean registered = phoneService.registerPhone(newPhone);
            System.out.println("✓ Celular registrado: " + registered);
            System.out.println("  Marca: " + newPhone.getBrand());
            System.out.println("  Modelo: " + newPhone.getModel());
            System.out.println("  Precio: $" + newPhone.getPrice());
            System.out.println("  Stock: " + newPhone.getStock());
            
            assert registered : "El celular debe ser registrado";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 2: Registrar celular inválido (validaciones)
     */
    private static void testRegisterPhoneInvalid() {
        System.out.println("\n[TEST 2] Registrar celular inválido (validaciones)");
        try {
            // Test 2a: Precio negativo
            System.out.println("  2a. Validar precio negativo:");
            PhoneModel invalidPhone1 = new PhoneModel(
                "Brand",
                "Model",
                "Android",
                "Media",
                -1000.00,  // Precio negativo
                10
            );
            boolean result1 = phoneService.registerPhone(invalidPhone1);
            System.out.println("     Resultado (debe ser false): " + result1);
            assert !result1 : "No debe registrar con precio negativo";
            
            // Test 2b: Stock negativo
            System.out.println("  2b. Validar stock negativo:");
            PhoneModel invalidPhone2 = new PhoneModel(
                "Brand",
                "Model",
                "Android",
                "Media",
                1000.00,
                -5  // Stock negativo
            );
            boolean result2 = phoneService.registerPhone(invalidPhone2);
            System.out.println("     Resultado (debe ser false): " + result2);
            assert !result2 : "No debe registrar con stock negativo";
            
            // Test 2c: Marca vacía
            System.out.println("  2c. Validar marca vacía:");
            PhoneModel invalidPhone3 = new PhoneModel(
                "",  // Marca vacía
                "Model",
                "Android",
                "Media",
                1000.00,
                10
            );
            boolean result3 = phoneService.registerPhone(invalidPhone3);
            System.out.println("     Resultado (debe ser false): " + result3);
            assert !result3 : "No debe registrar con marca vacía";
            
            System.out.println("  ✓ Validación de restricciones completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 3: Obtener todos los celulares
     */
    private static void testGetAllPhones() {
        System.out.println("\n[TEST 3] Obtener todos los celulares");
        try {
            List<PhoneModel> phones = phoneService.getPhonesSorted();
            System.out.println("✓ Celulares obtenidos: " + phones.size());
            
            if (!phones.isEmpty()) {
                System.out.println("  Primeros 3 celulares:");
                phones.stream().limit(3).forEach(phone ->
                    System.out.printf("    - %s %s: $%.2f (%s)\n",
                        phone.getBrand(), phone.getModel(), phone.getPrice(), phone.getRangeCategory())
                );
            }
            
            int totalCount = phoneService.countPhones();
            System.out.println("  Total de celulares registrados: " + totalCount);
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 4: Obtener celular por ID
     */
    private static void testGetPhoneById() {
        System.out.println("\n[TEST 4] Obtener celular por ID");
        try {
            List<PhoneModel> allPhones = phoneService.getPhonesSorted();
            if (allPhones.isEmpty()) {
                System.out.println("⚠ No hay celulares disponibles");
                return;
            }
            
            int phoneId = allPhones.get(0).getId();
            PhoneModel phone = phoneService.getPhoneById(phoneId);
            
            System.out.println("✓ Celular encontrado:");
            System.out.println("  ID: " + phone.getId());
            System.out.println("  Marca: " + phone.getBrand());
            System.out.println("  Modelo: " + phone.getModel());
            System.out.println("  Precio: $" + phone.getPrice());
            System.out.println("  Stock: " + phone.getStock());
            System.out.println("  Gama: " + phone.getRangeCategory());
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 5: Obtener celulares por categoría
     */
    private static void testGetPhonesByCategory() {
        System.out.println("\n[TEST 5] Obtener celulares por categoría");
        try {
            String[] categories = {"Alta", "Media", "Baja"};
            
            for (String category : categories) {
                List<PhoneModel> phones = phoneService.getPhonesByCategory(category);
                System.out.println("✓ Categoría '" + category + "': " + phones.size() + " celulares");
                
                if (!phones.isEmpty()) {
                    phones.forEach(phone ->
                        System.out.printf("    - %s %s: $%.2f\n",
                            phone.getBrand(), phone.getModel(), phone.getPrice())
                    );
                }
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 6: Obtener celulares por marca
     */
    private static void testGetPhonesByBrand() {
        System.out.println("\n[TEST 6] Obtener celulares por marca");
        try {
            List<String> brands = phoneService.getAllBrands();
            System.out.println("✓ Marcas disponibles: " + brands.size());
            System.out.println("  Marcas: " + brands);
            
            if (!brands.isEmpty()) {
                String brand = brands.get(0);
                List<PhoneModel> phonesByBrand = phoneService.getPhonesByBrand(brand);
                System.out.println("\n  Celulares de " + brand + ": " + phonesByBrand.size());
                phonesByBrand.forEach(phone ->
                    System.out.printf("    - %s: $%.2f\n", phone.getModel(), phone.getPrice())
                );
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 7: Obtener celulares ordenados por precio
     */
    private static void testGetPhonesSortedByPrice() {
        System.out.println("\n[TEST 7] Obtener celulares ordenados por precio");
        try {
            List<PhoneModel> phonesSorted = phoneService.getPhonesSortedByPrice();
            System.out.println("✓ Celulares ordenados por precio (menor a mayor):");
            
            System.out.println("  Top 5 más baratos:");
            phonesSorted.stream().limit(5).forEach((phone) ->
                System.out.printf("    %d. %s %s: $%.2f\n",
                    phonesSorted.indexOf(phone) + 1, phone.getBrand(), phone.getModel(), phone.getPrice())
            );
            
            System.out.println("  Top 5 más caros:");
            phonesSorted.stream().skip(Math.max(0, phonesSorted.size() - 5)).forEach((phone) ->
                System.out.printf("    %d. %s %s: $%.2f\n",
                    phonesSorted.indexOf(phone) + 1, phone.getBrand(), phone.getModel(), phone.getPrice())
            );
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 8: Obtener celulares con stock bajo
     */
    private static void testGetPhonesByLowStock() {
        System.out.println("\n[TEST 8] Obtener celulares con stock bajo (< 5)");
        try {
            List<PhoneModel> lowStockPhones = phoneService.getPhonesByLowStock();
            System.out.println("✓ Celulares con stock bajo: " + lowStockPhones.size());
            
            if (lowStockPhones.isEmpty()) {
                System.out.println("  ℹ No hay celulares con stock bajo");
            } else {
                lowStockPhones.forEach(phone ->
                    System.out.printf("    ⚠ %s %s - Stock: %d\n",
                        phone.getBrand(), phone.getModel(), phone.getStock())
                );
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 9: Calcular precio promedio
     */
    private static void testCalculateAveragePrice() {
        System.out.println("\n[TEST 9] Calcular precio promedio");
        try {
            double avgPrice = phoneService.getAveragePrice();
            List<PhoneModel> phones = phoneService.getPhonesSorted();
            
            System.out.println("✓ Análisis de precios:");
            System.out.printf("  Total de celulares: %d\n", phones.size());
            System.out.printf("  Precio promedio: $%.2f\n", avgPrice);
            
            if (!phones.isEmpty()) {
                PhoneModel cheapest = phoneService.getCheapestPhone();
                PhoneModel mostExpensive = phoneService.getMostExpensivePhone();
                
                System.out.printf("  Celular más barato: $%.2f (%s %s)\n",
                    cheapest.getPrice(), cheapest.getBrand(), cheapest.getModel());
                System.out.printf("  Celular más caro: $%.2f (%s %s)\n",
                    mostExpensive.getPrice(), mostExpensive.getBrand(), mostExpensive.getModel());
                System.out.printf("  Rango de precios: $%.2f - $%.2f\n",
                    cheapest.getPrice(), mostExpensive.getPrice());
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 10: Calcular stock total
     */
    private static void testCalculateTotalStock() {
        System.out.println("\n[TEST 10] Calcular stock total");
        try {
            int totalStock = phoneService.getTotalStock();
            List<PhoneModel> phones = phoneService.getPhonesSorted();
            
            System.out.println("✓ Análisis de inventario:");
            System.out.printf("  Total de celulares: %d\n", phones.size());
            System.out.printf("  Stock total: %d unidades\n", totalStock);
            
            if (!phones.isEmpty()) {
                double avgStock = (double) totalStock / phones.size();
                System.out.printf("  Stock promedio por modelo: %.2f unidades\n", avgStock);
                
                phones.stream()
                    .sorted((p1, p2) -> Integer.compare(p2.getStock(), p1.getStock()))
                    .limit(5)
                    .forEach(phone ->
                        System.out.printf("    - %s %s: %d unidades\n",
                            phone.getBrand(), phone.getModel(), phone.getStock())
                    );
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 11: Obtener celular más y menos caro
     */
    private static void testGetMostAndCheapestPhone() {
        System.out.println("\n[TEST 11] Obtener celular más y menos caro");
        try {
            PhoneModel mostExpensive = phoneService.getMostExpensivePhone();
            PhoneModel cheapest = phoneService.getCheapestPhone();
            
            System.out.println("✓ Análisis de extremos:");
            
            if (mostExpensive != null) {
                System.out.println("  Celular más caro:");
                System.out.printf("    %s %s: $%.2f\n",
                    mostExpensive.getBrand(), mostExpensive.getModel(), mostExpensive.getPrice());
            }
            
            if (cheapest != null) {
                System.out.println("  Celular más barato:");
                System.out.printf("    %s %s: $%.2f\n",
                    cheapest.getBrand(), cheapest.getModel(), cheapest.getPrice());
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 12: Reducir stock de celular
     */
    private static void testReducePhoneStock() {
        System.out.println("\n[TEST 12] Reducir stock de celular");
        try {
            List<PhoneModel> phones = phoneService.getPhonesSorted();
            if (phones.isEmpty()) {
                System.out.println("⚠ No hay celulares disponibles");
                return;
            }
            
            PhoneModel phone = phones.get(0);
            int originalStock = phone.getStock();
            int reduction = 2;
            
            System.out.println("✓ Reducción de stock:");
            System.out.println("  Celular: " + phone.getBrand() + " " + phone.getModel());
            System.out.println("  Stock original: " + originalStock);
            System.out.println("  Cantidad a reducir: " + reduction);
            
            boolean reduced = phoneService.reducePhoneStock(phone.getId(), reduction);
            System.out.println("  Reducción exitosa: " + reduced);
            
            if (reduced) {
                PhoneModel updatedPhone = phoneService.getPhoneById(phone.getId());
                System.out.println("  Stock después: " + updatedPhone.getStock());
                assert updatedPhone.getStock() == (originalStock - reduction) : "Stock debe ser correcto";
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 13: Actualizar stock de celular
     */
    private static void testUpdatePhoneStock() {
        System.out.println("\n[TEST 13] Actualizar stock de celular");
        try {
            List<PhoneModel> phones = phoneService.getPhonesSorted();
            if (phones.isEmpty()) {
                System.out.println("⚠ No hay celulares disponibles");
                return;
            }
            
            PhoneModel phone = phones.get(0);
            int newStock = 100;
            
            System.out.println("✓ Actualización de stock:");
            System.out.println("  Celular: " + phone.getBrand() + " " + phone.getModel());
            System.out.println("  Stock nuevo: " + newStock);
            
            boolean updated = phoneService.updatePhoneStock(phone.getId(), newStock);
            System.out.println("  Actualización exitosa: " + updated);
            
            if (updated) {
                PhoneModel updatedPhone = phoneService.getPhoneById(phone.getId());
                System.out.println("  Stock verificado: " + updatedPhone.getStock());
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}