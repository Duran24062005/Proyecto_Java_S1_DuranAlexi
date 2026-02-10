package org.example.Controller;

import org.example.View.ReportView;
import org.example.service.ClientService;
import org.example.service.PhoneService;
import org.example.service.ReportService;
import org.example.service.SaleService;

/**
 * Controlador para la gestión de reportes
 * Maneja la generación de reportes en archivos y consola
 */
public class ReportController {

    private final ReportService reportService;
    private final ReportView reportView;
    private final PhoneService phoneService;
    private final ClientService clientService;
    private final SaleService saleService;

    public ReportController(){
        this.saleService = new SaleService();
        this.phoneService = new PhoneService();
        this.clientService = new ClientService();
        this.reportService = new ReportService(saleService, phoneService, clientService);
        this.reportView = new ReportView();
    }

    /**
     * Inicia el menú principal de gestión de reportes
     */
    public void init(){
        int option;
        do { 
            option = reportView.reportMenu();
            processOption(option);
        } while (option != 7);
    }

    /**
     * Procesa la opción seleccionada en el menú
     * @param option Opción del menú
     */
    public void processOption(int option){
        switch (option) {
            case 1 -> generateCompleteReport();
            case 2 -> printSalesResumeConsole();
            case 3 -> generateInventoryReport();
            case 4 -> generateClientsReport();
            case 5 -> printSalesAnalytics();
            case 6 -> printTop3Phones();
            case 7 -> System.out.println("\n <- Volviendo al menú principal \n");
            default -> System.out.println("Opción inválida. Por favor, ingresa un número entre 1 y 7.\n");
        }
    }

    /**
     * Genera un reporte completo de ventas en archivo
     */
    public void generateCompleteReport(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("    GENERANDO REPORTE DE VENTAS");
        System.out.println("═".repeat(60));
        
        try {
            boolean success = reportService.generateSalesReport();
            
            if (success) {
                System.out.println("\n✓ Reporte generado exitosamente");
                System.out.println("  Ubicación: reports/reporte_ventas_[TIMESTAMP].txt");
                System.out.println("\n  El reporte incluye:");
                System.out.println("  • Resumen general de ventas");
                System.out.println("  • Información financiera (totales, promedios, IVA)");
                System.out.println("  • Detalles de cada venta");
                System.out.println("  • Celulares con stock bajo");
                System.out.println("  • Top 3 celulares más vendidos");
                System.out.println("  • Ventas por mes");
            } else {
                System.out.println("\n✗ Error al generar el reporte");
            }
            
        } catch (Exception e) {
            System.out.println("\n✗ Error: " + e.getMessage());
        }
        
        System.out.println();
    }

    /**
     * Imprime un resumen de ventas en consola
     */
    public void printSalesResumeConsole(){
        System.out.println();
        reportService.printSalesSummary();
    }

    /**
     * Genera un reporte de inventario en archivo
     */
    public void generateInventoryReport(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("   GENERANDO REPORTE DE INVENTARIO");
        System.out.println("═".repeat(60));
        
        try {
            boolean success = reportService.generateInventoryReport();
            
            if (success) {
                System.out.println("\n✓ Reporte de inventario generado exitosamente");
                System.out.println("  Ubicación: reporte_inventario.txt");
                System.out.println("\n  El reporte incluye:");
                System.out.println("  • Cantidad total de celulares");
                System.out.println("  • Stock total de inventario");
                System.out.println("  • Catálogo completo de celulares");
                System.out.println("  • Información detallada de cada modelo");
            } else {
                System.out.println("\n✗ Error al generar el reporte");
            }
            
        } catch (Exception e) {
            System.out.println("\n✗ Error: " + e.getMessage());
        }
        
        System.out.println();
    }

    /**
     * Genera un reporte de clientes en archivo
     */
    public void generateClientsReport(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("    GENERANDO REPORTE DE CLIENTES");
        System.out.println("═".repeat(60));
        
        try {
            boolean success = reportService.generateClientsReport();
            
            if (success) {
                System.out.println("\n✓ Reporte de clientes generado exitosamente");
                System.out.println("  Ubicación: reporte_clientes.txt");
                System.out.println("\n  El reporte incluye:");
                System.out.println("  • Total de clientes registrados");
                System.out.println("  • Información detallada de cada cliente");
                System.out.println("  • Número de compras por cliente");
                System.out.println("  • Total gastado por cliente");
            } else {
                System.out.println("\n✗ Error al generar el reporte");
            }
            
        } catch (Exception e) {
            System.out.println("\n✗ Error: " + e.getMessage());
        }
        
        System.out.println();
    }

    /**
     * Imprime análisis de ventas en consola
     */
    public void printSalesAnalytics(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("      ANÁLISIS DETALLADO DE VENTAS");
        System.out.println("═".repeat(60));
        
        int totalSales = saleService.countSales();
        
        if (totalSales == 0) {
            System.out.println("\n✗ No hay ventas registradas para analizar\n");
            return;
        }
        
        System.out.println("\n--- INFORMACIÓN GENERAL ---");
        System.out.println("Total de ventas: " + totalSales);
        System.out.println("Total de artículos vendidos: " + saleService.getTotalItemsSold());
        System.out.println("Total de clientes: " + clientService.countClients());
        System.out.println("Total de celulares en catálogo: " + phoneService.countPhones());
        
        System.out.println("\n--- INFORMACIÓN FINANCIERA ---");
        System.out.printf("Total de ventas (con IVA): $%.2f\n", saleService.getTotalSales());
        System.out.printf("Total sin IVA: $%.2f\n", saleService.getTotalSalesWithoutIVA());
        System.out.printf("Total de IVA (19%%): $%.2f\n", saleService.getTotalIVA());
        System.out.printf("Venta promedio: $%.2f\n", saleService.getAverageSale());
        System.out.printf("Gasto promedio por cliente: $%.2f\n", 
            saleService.getAverageSpendingPerClient());
        
        System.out.println("\n--- CELULARES CON STOCK BAJO (< 5) ---");
        var lowStockPhones = phoneService.getPhonesByLowStock();
        
        if (lowStockPhones.isEmpty()) {
            System.out.println("✓ Todos los celulares tienen stock adecuado");
        } else {
            System.out.println("⚠ ALERTA: " + lowStockPhones.size() + " celular(es) con stock bajo:");
            lowStockPhones.forEach(phone ->
                System.out.printf("  - %s %s: %d unidades\n", 
                    phone.getBrand(), phone.getModel(), phone.getStock())
            );
        }
        
        System.out.println("\n--- ESTADÍSTICAS DE PRECIOS ---");
        System.out.printf("Precio promedio de celulares: $%.2f\n", phoneService.getAveragePrice());
        var mostExpensive = phoneService.getMostExpensivePhone();
        var cheapest = phoneService.getCheapestPhone();
        
        if (mostExpensive != null) {
            System.out.printf("Celular más caro: %s %s ($%.2f)\n", 
                mostExpensive.getBrand(), mostExpensive.getModel(), mostExpensive.getPrice());
        }
        
        if (cheapest != null) {
            System.out.printf("Celular más barato: %s %s ($%.2f)\n", 
                cheapest.getBrand(), cheapest.getModel(), cheapest.getPrice());
        }
        
        System.out.println("\n--- ESTADÍSTICAS DE INVENTARIO ---");
        System.out.println("Stock total: " + phoneService.getTotalStock() + " unidades");
        System.out.printf("Stock promedio por modelo: %.2f unidades\n", 
            (double) phoneService.getTotalStock() / phoneService.countPhones());
        
        System.out.println("\n" + "═".repeat(60) + "\n");
    }

    /**
     * Imprime el top 3 de celulares más vendidos en consola
     */
    public void printTop3Phones(){
        System.out.println();
        reportService.printTop3Phones();
    }

    /**
     * Muestra un menú adicional para opciones de reportes
     */
    public void showAdvancedOptions(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("     OPCIONES AVANZADAS DE REPORTES");
        System.out.println("═".repeat(60));
        System.out.println("1. Reportes en archivo (txt)");
        System.out.println("2. Análisis en consola");
        System.out.println("3. Alertas de stock bajo");
        System.out.println("4. Volver");
        System.out.println("═".repeat(60));
        System.out.print("Selecciona una opción: ");
    }

    /**
     * Muestra una alerta de stock bajo
     */
    public void showLowStockAlert(){
        System.out.println();
        reportService.printLowStockAlert();
    }

    /**
     * Genera todos los reportes disponibles
     */
    public void generateAllReports(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  GENERANDO TODOS LOS REPORTES");
        System.out.println("═".repeat(60));
        
        System.out.print("\n[1/3] Generando reporte de ventas... ");
        boolean success1 = reportService.generateSalesReport();
        System.out.println(success1 ? "✓" : "✗");
        
        System.out.print("[2/3] Generando reporte de inventario... ");
        boolean success2 = reportService.generateInventoryReport();
        System.out.println(success2 ? "✓" : "✗");
        
        System.out.print("[3/3] Generando reporte de clientes... ");
        boolean success3 = reportService.generateClientsReport();
        System.out.println(success3 ? "✓" : "✗");
        
        if (success1 && success2 && success3) {
            System.out.println("\n✓ Todos los reportes se generaron exitosamente");
            System.out.println("\nArchivos generados:");
            System.out.println("  • reporte_ventas_[TIMESTAMP].txt");
            System.out.println("  • reporte_inventario.txt");
            System.out.println("  • reporte_clientes.txt");
        } else {
            System.out.println("\n⚠ Algunos reportes no se generaron correctamente");
        }
        
        System.out.println();
    }

    /**
     * Obtiene el total de palabras en un reporte
     */
    public void showReportStatistics(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("   ESTADÍSTICAS DE REPORTES");
        System.out.println("═".repeat(60));
        
        System.out.println("\nÚltimos reportes generados:");
        System.out.println("  • reporte_ventas_[TIMESTAMP].txt");
        System.out.println("  • reporte_inventario.txt");
        System.out.println("  • reporte_clientes.txt");
        
        System.out.println("\nUbicación: ./");
        System.out.println("\nPara más detalles, consulta los archivos generados.");
        System.out.println("═".repeat(60) + "\n");
    }
}