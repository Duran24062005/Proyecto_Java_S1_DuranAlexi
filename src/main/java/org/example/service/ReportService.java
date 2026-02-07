package org.example.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.example.Model.ClientModel;
import org.example.Model.PhoneModel;
import org.example.Model.SaleModel;
import org.example.Model.SalesDetailsModel;

public class ReportService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    private static final String REPORT_DIR = "reports/";
    private final SaleService saleService;
    private final PhoneService phoneService;
    private final ClientService clientService;

    public ReportService(SaleService saleService, PhoneService phoneService, ClientService clientService) {
        this.saleService = saleService;
        this.phoneService = phoneService;
        this.clientService = clientService;
    }

    private static String generateReportFileName() {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        return REPORT_DIR + "reporte_ventas_" + timestamp + ".txt";
    }

    /**
     * Genera un reporte completo de ventas y lo guarda en archivo
     * @return true si se genera correctamente
     */
    public boolean generateSalesReport() {
        String REPORT_FILE = generateReportFileName();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE))) {
            writer.write("========================================\n");
            writer.write("REPORTE DE VENTAS - TECNOSTORE\n");
            writer.write("========================================\n");
            writer.write("Generado: " + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n\n");
            
            // Resumen general
            writer.write("--- RESUMEN GENERAL ---\n");
            writer.write("Total de ventas: " + saleService.countSales() + "\n");
            writer.write("Total de artículos vendidos: " + saleService.getTotalItemsSold() + "\n");
            writer.write("Total de clientes: " + clientService.countClients() + "\n");
            writer.write("Total de celulares en catálogo: " + phoneService.countPhones() + "\n\n");
            
            // Información financiera
            writer.write("--- INFORMACIÓN FINANCIERA ---\n");
            writer.write(String.format("Total de ventas (con IVA): $%.2f\n", saleService.getTotalSales()));
            writer.write(String.format("Total sin IVA: $%.2f\n", saleService.getTotalSalesWithoutIVA()));
            writer.write(String.format("Total de IVA (19%%): $%.2f\n", saleService.getTotalIVA()));
            writer.write(String.format("Venta promedio: $%.2f\n", saleService.getAverageSale()));
            writer.write(String.format("Gasto promedio por cliente: $%.2f\n\n", 
                saleService.getAverageSpendingPerClient()));
            
            // Detalles de ventas
            writer.write("--- DETALLES DE VENTAS ---\n");
            List<SaleModel> sales = saleService.getAllSales();
            for (SaleModel sale : sales) {
                ClientModel client = clientService.getClientById(sale.getIdCliente());
                writer.write(String.format("\nVenta ID: %d\n", sale.getId()));
                writer.write(String.format("Cliente: %s (ID: %d)\n", client.getName(), client.getId()));
                writer.write(String.format("Fecha: %s\n", sale.getDate()));
                writer.write(String.format("Total: $%.2f\n", sale.getTotal()));
                
                List<SalesDetailsModel> details = saleService.getSaleDetails(sale.getId());
                writer.write("Artículos:\n");
                for (SalesDetailsModel detail : details) {
                    PhoneModel phone = phoneService.getPhoneById(detail.getIdCelular());
                    writer.write(String.format("  - %s %s (Cantidad: %d, Subtotal: $%.2f)\n",
                        phone.getBrand(), phone.getModel(), detail.getCantidad(), detail.getSubtotal()));
                }
            }
            
            // Celulares con stock bajo
            writer.write("\n--- CELULARES CON STOCK BAJO (< 5) ---\n");
            List<PhoneModel> lowStockPhones = phoneService.getPhonesByLowStock();
            if (lowStockPhones.isEmpty()) {
                writer.write("No hay celulares con stock bajo\n");
            } else {
                for (PhoneModel phone : lowStockPhones) {
                    writer.write(String.format("%s %s - Stock: %d\n",
                        phone.getBrand(), phone.getModel(), phone.getStock()));
                }
            }
            
            // Top 3 celulares más vendidos
            writer.write("\n--- TOP 3 CELULARES MÁS VENDIDOS ---\n");
            List<Integer> topPhones = saleService.saleDetailsRepository.getTop3MostSoldPhones();
            int rank = 1;
            for (Integer phoneId : topPhones) {
                PhoneModel phone = phoneService.getPhoneById(phoneId);
                int totalSold = getTotalQuantitySoldByPhone(phoneId);
                writer.write(String.format("%d. %s %s - Cantidad vendida: %d\n",
                    rank++, phone.getBrand(), phone.getModel(), totalSold));
            }
            
            // Ventas por mes
            writer.write("\n--- VENTAS POR MES ---\n");
            Map<String, Double> salesByMonth = saleService.getSalesByMonth();
            for (Map.Entry<String, Double> entry : salesByMonth.entrySet()) {
                writer.write(String.format("Mes: %s - Total: $%.2f\n", entry.getKey(), entry.getValue()));
            }
            
            writer.write("\n========================================\n");
            writer.write("Fin del reporte\n");
            writer.write("========================================\n");
            
            System.out.println("Reporte generado exitosamente en: " + REPORT_FILE);
            return true;
            
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene el total de unidades vendidas de un celular
     * @param phoneId ID del celular
     * @return Total de unidades vendidas
     */
    private int getTotalQuantitySoldByPhone(int phoneId) {
        return saleService.getAllSales().stream()
            .flatMap(sale -> saleService.getSaleDetails(sale.getId()).stream())
            .filter(detail -> detail.getIdCelular() == phoneId)
            .mapToInt(SalesDetailsModel::getCantidad)
            .sum();
    }

    /**
     * Genera un reporte de inventario
     * @return true si se genera correctamente
     */
    public boolean generateInventoryReport() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reporte_inventario.txt"))) {
            writer.write("========================================\n");
            writer.write("REPORTE DE INVENTARIO - TECNOSTORE\n");
            writer.write("========================================\n");
            writer.write("Generado: " + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n\n");
            
            writer.write("--- INFORMACIÓN GENERAL ---\n");
            writer.write("Total de celulares en catálogo: " + phoneService.countPhones() + "\n");
            writer.write("Stock total: " + phoneService.getTotalStock() + "\n");
            writer.write(String.format("Valor promedio por unidad: $%.2f\n\n", phoneService.getAveragePrice()));
            
            writer.write("--- CATÁLOGO DE CELULARES ---\n");
            List<PhoneModel> phones = phoneService.getAllPhones();
            for (PhoneModel phone : phones) {
                writer.write(String.format("\nID: %d\n", phone.getId()));
                writer.write(String.format("Marca: %s\n", phone.getBrand()));
                writer.write(String.format("Modelo: %s\n", phone.getModel()));
                writer.write(String.format("Sistema Operativo: %s\n", phone.getOperativeSystem()));
                writer.write(String.format("Categoría: %s\n", phone.getRangeCategory()));
                writer.write(String.format("Precio: $%.2f\n", phone.getPrice()));
                writer.write(String.format("Stock: %d unidades\n", phone.getStock()));
            }
            
            writer.write("\n========================================\n");
            writer.write("Fin del reporte\n");
            writer.write("========================================\n");
            
            System.out.println("Reporte de inventario generado en: reporte_inventario.txt");
            return true;
            
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Genera un reporte de clientes
     * @return true si se genera correctamente
     */
    public boolean generateClientsReport() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reporte_clientes.txt"))) {
            writer.write("========================================\n");
            writer.write("REPORTE DE CLIENTES - TECNOSTORE\n");
            writer.write("========================================\n");
            writer.write("Generado: " + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n\n");
            
            writer.write("--- INFORMACIÓN GENERAL ---\n");
            writer.write("Total de clientes: " + clientService.countClients() + "\n\n");
            
            writer.write("--- LISTADO DE CLIENTES ---\n");
            List<ClientModel> clients = clientService.getAllClients();
            for (ClientModel client : clients) {
                writer.write(String.format("\nID: %d\n", client.getId()));
                writer.write(String.format("Nombre: %s\n", client.getName()));
                writer.write(String.format("Identificación: %s\n", client.getDni()));
                writer.write(String.format("Email: %s\n", client.getEmail()));
                writer.write(String.format("Teléfono: %s\n", client.getPhone()));
                
                List<SaleModel> clientSales = saleService.getSalesByClientId(client.getId());
                writer.write(String.format("Número de compras: %d\n", clientSales.size()));
                double totalSpent = clientSales.stream()
                    .mapToDouble(SaleModel::getTotal)
                    .sum();
                writer.write(String.format("Total gastado: $%.2f\n", totalSpent));
            }
            
            writer.write("\n========================================\n");
            writer.write("Fin del reporte\n");
            writer.write("========================================\n");
            
            System.out.println("Reporte de clientes generado en: reporte_clientes.txt");
            return true;
            
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Imprime en consola un resumen de ventas
     */
    public void printSalesSummary() {
        System.out.println("\n========== RESUMEN DE VENTAS ==========");
        System.out.println("Total de ventas: " + saleService.countSales());
        System.out.println("Total de artículos vendidos: " + saleService.getTotalItemsSold());
        System.out.printf("Total de ventas (con IVA): $%.2f\n", saleService.getTotalSales());
        System.out.printf("Total sin IVA: $%.2f\n", saleService.getTotalSalesWithoutIVA());
        System.out.printf("Total IVA (19%%): $%.2f\n", saleService.getTotalIVA());
        System.out.printf("Venta promedio: $%.2f\n", saleService.getAverageSale());
        System.out.println("======================================\n");
    }

    /**
     * Imprime en consola el inventario con stock bajo
     */
    public void printLowStockAlert() {
        List<PhoneModel> lowStockPhones = phoneService.getPhonesByLowStock();
        if (lowStockPhones.isEmpty()) {
            System.out.println("\n✓ No hay celulares con stock bajo\n");
            return;
        }
        
        System.out.println("\n ALERTA: CELULARES CON STOCK BAJO (<5)\n");
        for (PhoneModel phone : lowStockPhones) {
            System.out.printf("   %s %s - Stock: %d\n",
                phone.getBrand(), phone.getModel(), phone.getStock());
        }
        System.out.println();
    }

    /**
     * Imprime en consola el top 3 de celulares más vendidos
     */
    public void printTop3Phones() {
        System.out.println("\n========== TOP 3 MÁS VENDIDOS ==========");
        List<Integer> topPhones = saleService.saleDetailsRepository.getTop3MostSoldPhones();
        
        if (topPhones.isEmpty()) {
            System.out.println("No hay ventas registradas");
        } else {
            int rank = 1;
            for (Integer phoneId : topPhones) {
                PhoneModel phone = phoneService.getPhoneById(phoneId);
                int totalSold = getTotalQuantitySoldByPhone(phoneId);
                System.out.printf("%d. %s %s - Cantidad vendida: %d\n",
                    rank++, phone.getBrand(), phone.getModel(), totalSold);
            }
        }
        System.out.println("======================================\n");
    }
}
