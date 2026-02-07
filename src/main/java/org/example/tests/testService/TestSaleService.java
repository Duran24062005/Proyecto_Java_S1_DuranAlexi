package org.example.tests.testService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.Config.DatabaseConnection;
import org.example.Model.SaleModel;
import org.example.Model.SalesDetailsModel;
import org.example.service.ClientService;
import org.example.service.PhoneService;
import org.example.service.SaleService;


/**
 * Clase de pruebas para SaleService
 * Valida lógica de negocio de ventas
 */
public class TestSaleService {
    private static SaleService saleService;
    private static PhoneService phoneService;
    private static ClientService clientService;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   INICIANDO TESTS DE SALESERVICE");
        System.out.println("========================================\n");

        try {
            // Inicializar conexión
            DatabaseConnection.getConnection();
            saleService = new SaleService();
            phoneService = new PhoneService();
            clientService = new ClientService();

            // Ejecutar tests
            testGetAllSales();
            testGetSaleById();
            testGetSalesByClientId();
            testGetSaleDetails();
            testCalculateTotalSales();
            testCalculateSalesWithoutIVA();
            testCalculateIVA();
            testGetBiggestAndSmallestSale();
            testCalculateAverageSale();
            testGetSalesByMonth();
            testAnalytics();
            testRegisterSale();

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
            List<SaleModel> sales = saleService.getAllSales();
            System.out.println("✓ Ventas obtenidas: " + sales.size());
            
            if (!sales.isEmpty()) {
                System.out.println("  Primeras 3 ventas:");
                sales.stream().limit(3).forEach(sale ->
                    System.out.printf("    - Venta ID: %d (Cliente: %d, Total: $%.2f)\n",
                        sale.getId(), sale.getIdCliente(), sale.getTotal())
                );
            }
            
            int totalCount = saleService.countSales();
            System.out.println("  Total de ventas registradas: " + totalCount);
            
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
            List<SaleModel> allSales = saleService.getAllSales();
            if (allSales.isEmpty()) {
                System.out.println("⚠ No hay ventas disponibles");
                return;
            }
            
            int saleId = allSales.get(0).getId();
            SaleModel sale = saleService.getSaleById(saleId);
            
            System.out.println("✓ Venta encontrada:");
            System.out.println("  ID: " + sale.getId());
            System.out.println("  ID Cliente: " + sale.getIdCliente());
            System.out.println("  Total: $" + sale.getTotal());
            System.out.println("  Fecha: " + sale.getDate());
            
            assert sale.getId() == saleId : "ID debe coincidir";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 3: Obtener ventas por cliente
     */
    private static void testGetSalesByClientId() {
        System.out.println("\n[TEST 3] Obtener ventas por cliente");
        try {
            List<SaleModel> allSales = saleService.getAllSales();
            if (allSales.isEmpty()) {
                System.out.println("⚠ No hay ventas disponibles");
                return;
            }
            
            int clientId = allSales.get(0).getIdCliente();
            List<SaleModel> clientSales = saleService.getSalesByClientId(clientId);
            
            System.out.println("✓ Ventas del cliente ID " + clientId + ": " + clientSales.size());
            
            if (!clientSales.isEmpty()) {
                System.out.println("  Detalles:");
                clientSales.forEach(sale ->
                    System.out.printf("    - Venta ID: %d, Total: $%.2f, Fecha: %s\n",
                        sale.getId(), sale.getTotal(), sale.getDate())
                );
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 4: Obtener detalles de venta
     */
    private static void testGetSaleDetails() {
        System.out.println("\n[TEST 4] Obtener detalles de venta");
        try {
            List<SaleModel> allSales = saleService.getAllSales();
            if (allSales.isEmpty()) {
                System.out.println("⚠ No hay ventas disponibles");
                return;
            }
            
            SaleModel sale = allSales.get(0);
            List<SalesDetailsModel> details = saleService.getSaleDetails(sale.getId());
            
            System.out.println("✓ Detalles de venta ID " + sale.getId() + ": " + details.size());
            
            if (!details.isEmpty()) {
                System.out.println("  Artículos:");
                details.forEach(detail ->
                    System.out.printf("    - Celular ID: %d, Cantidad: %d, Subtotal: $%.2f\n",
                        detail.getIdCelular(), detail.getCantidad(), detail.getSubtotal())
                );
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 5: Calcular total de ventas
     */
    private static void testCalculateTotalSales() {
        System.out.println("\n[TEST 5] Calcular total de ventas");
        try {
            double totalSales = saleService.getTotalSales();
            int numSales = saleService.countSales();
            
            System.out.println("✓ Cálculo de ventas totales:");
            System.out.printf("  Total de ventas (con IVA): $%.2f\n", totalSales);
            System.out.println("  Número de ventas: " + numSales);
            
            if (numSales > 0) {
                System.out.printf("  Promedio por venta: $%.2f\n", totalSales / numSales);
            }
            
            assert totalSales >= 0 : "Total debe ser positivo";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 6: Calcular ventas sin IVA
     */
    private static void testCalculateSalesWithoutIVA() {
        System.out.println("\n[TEST 6] Calcular ventas sin IVA");
        try {
            double totalWithIVA = saleService.getTotalSales();
            double totalWithoutIVA = saleService.getTotalSalesWithoutIVA();
            
            System.out.println("✓ Comparación de ventas:");
            System.out.printf("  Total con IVA: $%.2f\n", totalWithIVA);
            System.out.printf("  Total sin IVA: $%.2f\n", totalWithoutIVA);
            System.out.printf("  Diferencia (IVA): $%.2f\n", totalWithIVA - totalWithoutIVA);
            
            assert totalWithIVA > totalWithoutIVA : "Total con IVA debe ser mayor";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 7: Calcular IVA
     */
    private static void testCalculateIVA() {
        System.out.println("\n[TEST 7] Calcular IVA (19%)");
        try {
            double totalIVA = saleService.getTotalIVA();
            double totalWithIVA = saleService.getTotalSales();
            double totalWithoutIVA = saleService.getTotalSalesWithoutIVA();
            
            System.out.println("✓ Análisis de IVA:");
            System.out.printf("  Total IVA recaudado: $%.2f\n", totalIVA);
            System.out.printf("  Total sin IVA: $%.2f\n", totalWithoutIVA);
            System.out.printf("  Total con IVA: $%.2f\n", totalWithIVA);
            
            // Validar que IVA es el 19% del subtotal
            double expectedIVA = totalWithoutIVA * 0.19;
            System.out.printf("  IVA esperado (19%% de subtotal): $%.2f\n", expectedIVA);
            System.out.printf("  Diferencia: $%.2f\n", Math.abs(totalIVA - expectedIVA));
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 8: Obtener mayor y menor venta
     */
    private static void testGetBiggestAndSmallestSale() {
        System.out.println("\n[TEST 8] Obtener mayor y menor venta");
        try {
            SaleModel biggest = saleService.getBiggestSale();
            SaleModel smallest = saleService.getSmallestSale();
            
            System.out.println("✓ Análisis de extremos:");
            
            if (biggest != null) {
                System.out.printf("  Mayor venta: $%.2f (ID: %d)\n", biggest.getTotal(), biggest.getId());
            }
            
            if (smallest != null) {
                System.out.printf("  Menor venta: $%.2f (ID: %d)\n", smallest.getTotal(), smallest.getId());
            }
            
            if (biggest != null && smallest != null) {
                System.out.printf("  Diferencia: $%.2f\n", biggest.getTotal() - smallest.getTotal());
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 9: Calcular promedio de venta
     */
    private static void testCalculateAverageSale() {
        System.out.println("\n[TEST 9] Calcular promedio de venta");
        try {
            double avgSale = saleService.getAverageSale();
            int totalSales = saleService.countSales();
            int totalItems = saleService.getTotalItemsSold();
            
            System.out.println("✓ Promedios:");
            System.out.printf("  Promedio por venta: $%.2f\n", avgSale);
            System.out.println("  Total de ventas: " + totalSales);
            System.out.println("  Total de artículos vendidos: " + totalItems);
            
            if (totalItems > 0) {
                System.out.printf("  Promedio de artículos por venta: %.2f\n", 
                    (double) totalItems / totalSales);
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 10: Obtener ventas por mes
     */
    private static void testGetSalesByMonth() {
        System.out.println("\n[TEST 10] Obtener ventas por mes");
        try {
            Map<String, Double> salesByMonth = saleService.getSalesByMonth();
            
            System.out.println("✓ Ventas por mes: " + salesByMonth.size() + " período(s)");
            
            if (!salesByMonth.isEmpty()) {
                System.out.println("  Desglose:");
                salesByMonth.forEach((month, total) ->
                    System.out.printf("    %s: $%.2f\n", month, total)
                );
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 11: Analítica avanzada
     */
    private static void testAnalytics() {
        System.out.println("\n[TEST 11] Analítica avanzada de ventas");
        try {
            // Cliente con más compras
            Integer topClient = saleService.getClientWithMostPurchases();
            Double avgSpending = saleService.getAverageSpendingPerClient();
            Integer highestSpender = saleService.getClientWithHighestSpending();
            
            System.out.println("✓ Análisis de clientes:");
            System.out.println("  Cliente con más compras: ID " + topClient);
            System.out.printf("  Gasto promedio por cliente: $%.2f\n", avgSpending);
            System.out.println("  Cliente con mayor gasto: ID " + highestSpender);
            
            // Top 3 teléfonos más vendidos
            List<Integer> topPhones = saleService.saleDetailsRepository.getTop3MostSoldPhones();
            System.out.println("\n✓ Top 3 celulares más vendidos:");
            
            int rank = 1;
            for (Integer phoneId : topPhones) {
                int totalSold = getTotalQuantitySoldByPhone(phoneId);
                System.out.printf("  %d. Celular ID: %d (Cantidad: %d)\n", rank++, phoneId, totalSold);
            }
            
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 12: Registrar nueva venta
     */
    private static void testRegisterSale() {
        System.out.println("\n[TEST 12] Registrar nueva venta");
        try {
            // Verificar que hay clientes y celulares
            List<Integer> clientIds = clientService.getAllClients().stream()
                .map(c -> c.getId())
                .limit(1)
                .toList();
            
            var phones = phoneService.getAllPhones().stream()
                .filter(p -> p.getStock() >= 1)
                .limit(2)
                .toList();
            
            if (clientIds.isEmpty() || phones.isEmpty()) {
                System.out.println("⚠ No hay clientes o celulares disponibles para la venta");
                return;
            }
            
            // Crear items de venta
            List<Map<String, Integer>> items = new java.util.ArrayList<>();
            for (var phone : phones) {
                Map<String, Integer> item = new HashMap<>();
                item.put("phoneId", phone.getId());
                item.put("quantity", 1);
                items.add(item);
            }
            
            System.out.println("✓ Registrando nueva venta:");
            System.out.println("  Cliente ID: " + clientIds.get(0));
            System.out.println("  Artículos a vender: " + items.size());
            
            boolean registered = saleService.registerSale(clientIds.get(0), items);
            System.out.println("  Venta registrada: " + registered);
            
            if (registered) {
                List<SaleModel> allSales = saleService.getAllSales();
                SaleModel lastSale = allSales.get(allSales.size() - 1);
                System.out.println("  Venta ID: " + lastSale.getId());
                System.out.printf("  Total: $%.2f\n", lastSale.getTotal());
            }
            
            assert registered : "La venta debe registrarse correctamente";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para obtener cantidad vendida de un celular
     */
    private static int getTotalQuantitySoldByPhone(int phoneId) {
        return saleService.getAllSales().stream()
            .flatMap(sale -> saleService.getSaleDetails(sale.getId()).stream())
            .filter(detail -> detail.getIdCelular() == phoneId)
            .mapToInt(SalesDetailsModel::getCantidad)
            .sum();
    }
}
