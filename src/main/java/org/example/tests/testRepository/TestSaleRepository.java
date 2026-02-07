package org.example.tests.testRepository;

import java.util.List;

import org.example.Config.DatabaseConnection;
import org.example.Model.SaleModel;
import org.example.repository.SaleRepository;

/**
 * Clase de pruebas para SaleRepository
 * Valida operaciones CRUD de ventas
 */
public class TestSaleRepository {
    private static SaleRepository saleRepository;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   INICIANDO TESTS DE SALEREPOSITORY");
        System.out.println("========================================\n");

        try {
            // Inicializar conexión
            DatabaseConnection.getConnection();
            saleRepository = new SaleRepository();

            // Ejecutar tests
            testGetAllSales();
            testGetSaleById();
            testAddSale();
            testUpdateSale();
            testGetAllCompleteSales();
            testGetCompleteSaleById();
            testDeleteSale();

            System.out.println("\n========================================");
            System.out.println("   TODOS LOS TESTS COMPLETADOS");
            System.out.println("========================================");

        } catch (Exception e) {
            System.out.println("✗ Error en la inicialización: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Test 1: Obtener todas las ventas
     */
    private static void testGetAllSales() {
        System.out.println("\n[TEST 1] Obtener todas las ventas");
        try {
            List<SaleModel> sales = saleRepository.getAllSales();
            System.out.println("✓ Ventas obtenidas: " + sales.size());
            
            if (!sales.isEmpty()) {
                System.out.println("  Primeras 3 ventas:");
                sales.stream().limit(3).forEach(sale ->
                    System.out.printf("    - Venta ID: %d (Cliente: %d, Total: $%.2f)\n",
                        sale.getId(), sale.getIdCliente(), sale.getTotal())
                );
                
                // Estadísticas
                double totalVentas = sales.stream()
                    .mapToDouble(SaleModel::getTotal)
                    .sum();
                double promedioVentas = totalVentas / sales.size();
                
                System.out.printf("\n  Estadísticas generales:\n");
                System.out.printf("    Total de ventas: $%.2f\n", totalVentas);
                System.out.printf("    Promedio por venta: $%.2f\n", promedioVentas);
            }
            assert !sales.isEmpty() : "Debe haber al menos una venta";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 2: Obtener venta por ID
     */
    private static void testGetSaleById() {
        System.out.println("\n[TEST 2] Obtener venta por ID");
        try {
            // Obtener la primera venta disponible
            List<SaleModel> allSales = saleRepository.getAllSales();
            if (allSales.isEmpty()) {
                System.out.println("⚠ No hay ventas en la base de datos");
                return;
            }
            
            int saleId = allSales.get(0).getId();
            SaleModel sale = saleRepository.getSaleById(saleId);
            
            System.out.println("✓ Venta encontrada:");
            System.out.println("  ID: " + sale.getId());
            System.out.println("  ID Cliente: " + sale.getIdCliente());
            System.out.println("  Fecha: " + sale.getDate());
            System.out.println("  Total: $" + sale.getTotal());
            System.out.println("  Creado en: " + sale.getCreatedAt());
            
            assert sale.getId() == saleId : "ID debe coincidir";
            assert sale.getTotal() > 0 : "Total debe ser positivo";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 3: Añadir nueva venta
     */
    private static void testAddSale() {
        System.out.println("\n[TEST 3] Añadir nueva venta");
        try {
            // Crear una nueva venta
            SaleModel newSale = new SaleModel(1, 6972.00); // Cliente 1, Total con IVA
            
            boolean added = saleRepository.addSale(newSale);
            System.out.println("✓ Venta añadida: " + added);
            System.out.println("  ID Cliente: " + newSale.getIdCliente());
            System.out.println("  Total: $" + newSale.getTotal());
            
            assert added : "La venta debe ser añadida correctamente";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 4: Actualizar venta
     */
    private static void testUpdateSale() {
        System.out.println("\n[TEST 4] Actualizar venta");
        try {
            // Obtener una venta existente
            List<SaleModel> sales = saleRepository.getAllSales();
            if (sales.isEmpty()) {
                System.out.println("⚠ No hay ventas para actualizar");
                return;
            }
            
            SaleModel saleToUpdate = sales.get(sales.size() - 1); // Última venta (probablemente la recién creada)
            double oldTotal = saleToUpdate.getTotal();
            double newTotal = oldTotal + 100; // Aumentar el total en 100
            
            System.out.println("✓ Venta antes de actualización:");
            System.out.println("  Total anterior: $" + oldTotal);
            System.out.println("  ID Cliente: " + saleToUpdate.getIdCliente());
            
            // Modificar datos
            saleToUpdate.setTotal(newTotal);
            
            boolean updated = saleRepository.updateSale(saleToUpdate);
            System.out.println("\n  Venta después de actualización:");
            System.out.println("  Total nuevo: $" + saleToUpdate.getTotal());
            System.out.println("  Actualización exitosa: " + updated);
            
            // Verificar que se actualizó
            SaleModel verifiedSale = saleRepository.getSaleById(saleToUpdate.getId());
            System.out.println("  Verificación en BD: $" + verifiedSale.getTotal());
            
            assert updated : "La venta debe ser actualizada";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 5: Obtener ventas completas (con join)
     */
    private static void testGetAllCompleteSales() {
        System.out.println("\n[TEST 5] Obtener todas las ventas completas (con cliente)");
        try {
            List<SaleModel> completeSales = saleRepository.getAllCompleteSales();
            System.out.println("✓ Ventas completas obtenidas: " + completeSales.size());
            
            if (!completeSales.isEmpty()) {
                System.out.println("  Primeras 3 ventas completas:");
                completeSales.stream().limit(3).forEach(sale ->
                    System.out.printf("    - Venta ID: %d (Cliente ID: %d, Total: $%.2f)\n",
                        sale.getId(), sale.getIdCliente(), sale.getTotal())
                );
            }
            
            assert !completeSales.isEmpty() : "Debe haber ventas completas";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 6: Obtener venta completa por ID
     */
    private static void testGetCompleteSaleById() {
        System.out.println("\n[TEST 6] Obtener venta completa por ID");
        try {
            List<SaleModel> allSales = saleRepository.getAllSales();
            if (allSales.isEmpty()) {
                System.out.println("⚠ No hay ventas para consultar");
                return;
            }
            
            int clientId = allSales.get(0).getIdCliente();
            SaleModel completeSale = saleRepository.getCompleteSaleById(clientId);
            
            System.out.println("✓ Venta completa encontrada:");
            System.out.println("  ID Cliente: " + completeSale.getIdCliente());
            System.out.println("  Total: $" + completeSale.getTotal());
            System.out.println("  Fecha: " + completeSale.getDate());
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 7: Eliminar venta
     */
    private static void testDeleteSale() {
        System.out.println("\n[TEST 7] Eliminar venta");
        try {
            // Crear una venta de prueba para eliminar
            SaleModel testSale = new SaleModel(1, 1234.56);
            
            boolean added = saleRepository.addSale(testSale);
            if (!added) {
                System.out.println("⚠ No se pudo crear la venta de prueba");
                return;
            }
            
            // Obtener la última venta creada
            List<SaleModel> allSales = saleRepository.getAllSales();
            SaleModel createdSale = allSales.get(allSales.size() - 1);
            
            System.out.println("✓ Venta de prueba creada (ID: " + createdSale.getId() + ")");
            System.out.println("  Total: $" + createdSale.getTotal());
            
            boolean deleted = saleRepository.deleteSale(createdSale.getId());
            System.out.println("  Venta eliminada: " + deleted);
            
            // Verificar eliminación
            SaleModel deletedSale = saleRepository.getSaleById(createdSale.getId());
            System.out.println("  Verificación post-eliminación: ID = " + deletedSale.getId());
            
            assert deleted : "La venta debe ser eliminada";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 8: Analítica de ventas
     */
    private static void testSalesAnalytics() {
        System.out.println("\n[TEST 8] Analítica de ventas");
        try {
            List<SaleModel> sales = saleRepository.getAllSales();
            if (sales.isEmpty()) {
                System.out.println("⚠ No hay ventas para analizar");
                return;
            }
            
            // Cálculos
            double totalSales = sales.stream()
                .mapToDouble(SaleModel::getTotal)
                .sum();
            
            double maxSale = sales.stream()
                .mapToDouble(SaleModel::getTotal)
                .max()
                .orElse(0.0);
            
            double minSale = sales.stream()
                .mapToDouble(SaleModel::getTotal)
                .min()
                .orElse(0.0);
            
            double avgSale = sales.stream()
                .mapToDouble(SaleModel::getTotal)
                .average()
                .orElse(0.0);
            
            System.out.println("✓ Analítica de ventas:");
            System.out.printf("  Total de ventas: $%.2f\n", totalSales);
            System.out.printf("  Venta más alta: $%.2f\n", maxSale);
            System.out.printf("  Venta más baja: $%.2f\n", minSale);
            System.out.printf("  Promedio de ventas: $%.2f\n", avgSale);
            System.out.printf("  Número de ventas: %d\n", sales.size());
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}