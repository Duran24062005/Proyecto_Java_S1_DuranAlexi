package org.example.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.example.Model.ClientModel;
import org.example.Model.PhoneModel;
import org.example.Model.SaleModel;
import org.example.Model.SalesDetailsModel;
import org.example.View.SaleView;
import org.example.service.ClientService;
import org.example.service.PhoneService;
import org.example.service.SaleService;

/**
 * Controlador para la gestión de ventas
 * Maneja el registro, consulta y análisis de ventas
 */
public class SaleController {

    private final SaleService saleService;
    private final PhoneService phoneService;
    private final ClientService clientService;
    private final SaleView saleView;
    private final Scanner scanner;

    public SaleController(){
        this.saleService = new SaleService();
        this.phoneService = new PhoneService();
        this.clientService = new ClientService();
        this.saleView = new SaleView();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Inicia el menú principal de gestión de ventas
     */
    public void init(){
        int option;
        do { 
            option = saleView.saleMenu();
            processOption(option);
        } while (option != 7);
    }

    /**
     * Procesa la opción seleccionada en el menú
     * @param option Opción del menú
     */
    public void processOption(int option){
        switch (option) {
            case 1 -> createNewSale();
            case 2 -> showAllSales();
            case 3 -> showSaleById();
            case 4 -> showSalesByClient();
            case 5 -> showSalesAnalytics();
            case 6 -> showTop3Phones();
            case 7 -> System.out.println("\n <- Volviendo al menú principal \n");
            default -> System.out.println("Opción inválida. Por favor, ingresa un número entre 1 y 7.\n");
        }
    }

    /**
     * Crea una nueva venta con múltiples artículos
     */
    public void createNewSale(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("           REGISTRAR NUEVA VENTA");
        System.out.println("═".repeat(60));
        
        try {
            // 1. Seleccionar cliente
            System.out.println("\n--- SELECCIONAR CLIENTE ---");
            List<ClientModel> clients = clientService.getAllClients();
            
            if (clients.isEmpty()) {
                System.out.println("No hay clientes registrados. Debes registrar un cliente primero.\n");
                return;
            }
            
            System.out.println("\nClientes disponibles:");
            for (int i = 0; i < clients.size(); i++) {
                ClientModel client = clients.get(i);
                System.out.printf("%d. %s (ID: %d, DNI: %s)\n", 
                    i + 1, client.getName(), client.getId(), client.getDni());
            }
            
            System.out.print("Selecciona el número del cliente: ");
            int clientIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
            
            if (clientIndex < 0 || clientIndex >= clients.size()) {
                System.out.println("Selección inválida\n");
                return;
            }
            
            ClientModel selectedClient = clients.get(clientIndex);
            System.out.println("Cliente seleccionado: " + selectedClient.getName());
            
            // 2. Seleccionar celulares
            System.out.println("\n--- SELECCIONAR CELULARES ---");
            List<PhoneModel> phones = phoneService.getAllPhones();
            
            if (phones.isEmpty()) {
                System.out.println("No hay celulares disponibles.\n");
                return;
            }
            
            System.out.println("\nCelulares disponibles:");
            for (int i = 0; i < phones.size(); i++) {
                PhoneModel phone = phones.get(i);
                System.out.printf("%d. %s %s - Precio: $%.2f (Stock: %d)\n", 
                    i + 1, phone.getBrand(), phone.getModel(), phone.getPrice(), phone.getStock());
            }
            
            List<Map<String, Integer>> items = new ArrayList<>();
            boolean addingItems = true;
            
            while (addingItems) {
                System.out.print("\nSelecciona el número del celular (0 para terminar): ");
                int phoneIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                
                if (phoneIndex == -1) {
                    addingItems = false;
                    break;
                }
                
                if (phoneIndex < 0 || phoneIndex >= phones.size()) {
                    System.out.println("Selección inválida");
                    continue;
                }
                
                PhoneModel selectedPhone = phones.get(phoneIndex);
                
                System.out.print("¿Cuántas unidades? (máx: " + selectedPhone.getStock() + "): ");
                int quantity = Integer.parseInt(scanner.nextLine().trim());
                
                if (quantity <= 0 || quantity > selectedPhone.getStock()) {
                    System.out.println("Cantidad inválida");
                    continue;
                }
                
                // Verificar si el celular ya está en la venta
                boolean exists = items.stream()
                    .anyMatch(item -> item.get("phoneId") == selectedPhone.getId());
                
                if (exists) {
                    System.out.println("Este celular ya fue añadido a la venta");
                    continue;
                }
                
                Map<String, Integer> item = new HashMap<>();
                item.put("phoneId", selectedPhone.getId());
                item.put("quantity", quantity);
                items.add(item);
                
                System.out.printf("Añadido: %s %s x %d\n", 
                    selectedPhone.getBrand(), selectedPhone.getModel(), quantity);
            }
            
            if (items.isEmpty()) {
                System.out.println("La venta debe tener al menos un artículo\n");
                return;
            }

            
            boolean isDiscountSet = true;
            String setDiscountType = "";

            while (isDiscountSet) {
                System.out.println("""
                Elija el tipo de descuento:
                    1. Premium.
                    2. Satandar.
                    3. Sin descuento.
                """);
                int discount = Integer.parseInt(scanner.nextLine());
                switch (discount) {
                    case 1 -> {
                        setDiscountType = "premium";
                        isDiscountSet = false;
                    }
                    case 2 -> {
                        setDiscountType = "standar";
                        isDiscountSet = false;
                    }
                    case 3 -> {
                        setDiscountType = "none";
                        isDiscountSet = false;
                    }
                    default -> System.out.println("Seleccione una opción valida.");
                }
            }
            
            // 3. Registrar venta
            System.out.println("\n--- CONFIRMAR VENTA ---");
            double subtotal = 0;
            
            System.out.println("\nDetalles de la venta:");
            System.out.println("Cliente: " + selectedClient.getName());
            System.out.println("\nArtículos:");
            
            for (Map<String, Integer> item : items) {
                int phoneId = item.get("phoneId");
                int quantity = item.get("quantity");
                PhoneModel phone = phoneService.getPhoneById(phoneId);
                double itemSubtotal = phone.getPrice() * quantity;
                subtotal += itemSubtotal;
                
                System.out.printf("  - %s %s x %d: $%.2f\n", 
                    phone.getBrand(), phone.getModel(), quantity, itemSubtotal);
            }
            
            double total = subtotal * 1.19; // IVA 19%
            
            System.out.printf("\nSubtotal: $%.2f\n", subtotal);
            System.out.printf("IVA (19%%): $%.2f\n", subtotal * 0.19);
            System.out.printf("Total: $%.2f\n", total);
            
            System.out.print("\n¿Confirmar venta? (S/N): ");
            String confirmation = scanner.nextLine().trim();
            
            if (!confirmation.equalsIgnoreCase("S")) {
                System.out.println("Venta cancelada\n");
                return;
            }
            
            // Registrar venta
            boolean registered = saleService.registerSale(selectedClient.getId(), items, setDiscountType);
            
            if (registered) {
                System.out.println("\n Venta registrada exitosamente"); 
                System.out.printf("Total a pagar: $%.2f\n\n", total);
            } else {
                System.out.println("\n No se pudo registrar la venta\n");
            }
            
        } catch (NumberFormatException e) {
            System.out.println(" Error: Debes ingresar números válidos\n");
        } catch (Exception e) {
            System.out.println(" Error inesperado: " + e.getMessage() + "\n");
        }
    }

    /**
     * Muestra todas las ventas
     */
    public void showAllSales(){
        List<SaleModel> sales = saleService.getAllSales();
        
        if (sales.isEmpty()) {
            System.out.println(" No hay ventas registradas\n");
            return;
        }
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("              TODAS LAS VENTAS");
        System.out.println("═".repeat(60));
        System.out.printf("Total de ventas: %d\n\n", sales.size());
        
        for (SaleModel sale : sales) {
            ClientModel client = clientService.getClientById(sale.getIdCliente());
            List<SalesDetailsModel> details = saleService.getSaleDetails(sale.getId());
            
            System.out.printf("Venta ID: %d\n", sale.getId());
            System.out.printf("Cliente: %s (ID: %d)\n", client.getName(), client.getId());
            System.out.printf("Fecha: %s\n", sale.getDate());
            System.out.printf("Total: $%.2f\n", sale.getTotal());
            System.out.println("Artículos: " + details.size());
            
            for (SalesDetailsModel detail : details) {
                PhoneModel phone = phoneService.getPhoneById(detail.getIdCelular());
                System.out.printf("  - %s %s (Cantidad: %d, Subtotal: $%.2f)\n",
                    phone.getBrand(), phone.getModel(), detail.getCantidad(), detail.getSubtotal());
            }
            
            System.out.println("---");
        }
        System.out.println("═".repeat(60) + "\n");
    }

    /**
     * Muestra una venta específica por ID
     */
    public void showSaleById(){
        System.out.print("\nIngresa el ID de la venta: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        
        SaleModel sale = saleService.getSaleById(id);
        
        if (sale == null) {
            System.out.println(" Venta no encontrada\n");
            return;
        }
        
        ClientModel client = clientService.getClientById(sale.getIdCliente());
        List<SalesDetailsModel> details = saleService.getSaleDetails(sale.getId());
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("         DETALLES DE LA VENTA");
        System.out.println("═".repeat(60));
        System.out.printf("Venta ID: %d\n", sale.getId());
        System.out.printf("Cliente: %s (DNI: %s)\n", client.getName(), client.getDni());
        System.out.printf("Teléfono cliente: %s\n", client.getPhone());
        System.out.printf("Fecha: %s\n", sale.getDate());
        System.out.printf("Total: $%.2f\n\n", sale.getTotal());
        
        System.out.println("Artículos comprados:");
        for (SalesDetailsModel detail : details) {
            PhoneModel phone = phoneService.getPhoneById(detail.getIdCelular());
            System.out.printf("  - %s %s\n", phone.getBrand(), phone.getModel());
            System.out.printf("    Cantidad: %d\n", detail.getCantidad());
            System.out.printf("    Precio unitario: $%.2f\n", detail.getSubtotal() / detail.getCantidad());
            System.out.printf("    Subtotal: $%.2f\n", detail.getSubtotal());
        }
        
        System.out.println("═".repeat(60) + "\n");
    }

    /**
     * Muestra las ventas de un cliente específico
     */
    public void showSalesByClient(){
        System.out.print("\nIngresa el ID del cliente: ");
        int clientId = Integer.parseInt(scanner.nextLine().trim());
        
        ClientModel client = clientService.getClientById(clientId);
        
        if (client == null) {
            System.out.println(" Cliente no encontrado\n");
            return;
        }
        
        List<SaleModel> clientSales = saleService.getSalesByClientId(clientId);
        
        if (clientSales.isEmpty()) {
            System.out.println(" El cliente no tiene ventas registradas\n");
            return;
        }
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("       VENTAS DEL CLIENTE");
        System.out.println("═".repeat(60));
        System.out.printf("Cliente: %s (DNI: %s)\n", client.getName(), client.getDni());
        System.out.printf("Número de compras: %d\n\n", clientSales.size());
        
        double totalSpent = 0;
        for (SaleModel sale : clientSales) {
            totalSpent += sale.getTotal();
            System.out.printf("Venta ID: %d - Fecha: %s - Total: $%.2f\n",
                sale.getId(), sale.getDate(), sale.getTotal());
        }
        
        System.out.printf("\nTotal gastado: $%.2f\n", totalSpent);
        System.out.printf("Promedio por compra: $%.2f\n", totalSpent / clientSales.size());
        System.out.println("═".repeat(60) + "\n");
    }

    /**
     * Muestra análisis y estadísticas de ventas
     */
    public void showSalesAnalytics(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("       ANÁLISIS DE VENTAS");
        System.out.println("═".repeat(60));
        
        int totalSales = saleService.countSales();
        
        if (totalSales == 0) {
            System.out.println("No hay ventas registradas\n");
            return;
        }
        
        double totalWithIVA = saleService.getTotalSales();
        double totalWithoutIVA = saleService.getTotalSalesWithoutIVA();
        double totalIVA = saleService.getTotalIVA();
        double avgSale = saleService.getAverageSale();
        int totalItems = saleService.getTotalItemsSold();
        
        SaleModel biggest = saleService.getBiggestSale();
        SaleModel smallest = saleService.getSmallestSale();
        
        System.out.println("\n--- INFORMACIÓN GENERAL ---");
        System.out.println("Total de ventas realizadas: " + totalSales);
        System.out.println("Total de artículos vendidos: " + totalItems);
        
        System.out.println("\n--- INFORMACIÓN FINANCIERA ---");
        System.out.printf("Total de ventas (sin IVA): $%.2f\n", totalWithoutIVA);
        System.out.printf("IVA recaudado (19%%): $%.2f\n", totalIVA);
        System.out.printf("Total de ventas (con IVA): $%.2f\n", totalWithIVA);
        System.out.printf("Promedio por venta: $%.2f\n", avgSale);
        System.out.printf("Promedio de artículos por venta: %.2f\n", (double) totalItems / totalSales);
        
        System.out.println("\n--- VENTA MAYOR Y MENOR ---");
        if (biggest != null) {
            System.out.printf("Venta más grande: ID %d - $%.2f\n", biggest.getId(), biggest.getTotal());
        }
        if (smallest != null) {
            System.out.printf("Venta más pequeña: ID %d - $%.2f\n", smallest.getId(), smallest.getTotal());
        }
        
        System.out.println("\n--- ESTADÍSTICAS DE CLIENTES ---");
        Integer topClient = saleService.getClientWithMostPurchases();
        Integer highSpender = saleService.getClientWithHighestSpending();
        double avgSpending = saleService.getAverageSpendingPerClient();
        
        if (topClient != null) {
            ClientModel client = clientService.getClientById(topClient);
            System.out.printf("Cliente con más compras: %s (ID: %d)\n", client.getName(), client.getId());
        }
        
        if (highSpender != null) {
            ClientModel client = clientService.getClientById(highSpender);
            System.out.printf("Cliente que más ha gastado: %s (ID: %d)\n", client.getName(), client.getId());
        }
        
        System.out.printf("Gasto promedio por cliente: $%.2f\n", avgSpending);
        
        System.out.println("═".repeat(60) + "\n");
    }

    /**
     * Muestra el top 3 de celulares más vendidos
     */
    public void showTop3Phones(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("   TOP 3 CELULARES MÁS VENDIDOS");
        System.out.println("═".repeat(60) + "\n");
        
        List<Integer> topPhones = saleService.saleDetailsRepository.getTop3MostSoldPhones();
        
        if (topPhones.isEmpty()) {
            System.out.println("No hay ventas registradas\n");
            return;
        }
        
        int rank = 1;
        for (Integer phoneId : topPhones) {
            PhoneModel phone = phoneService.getPhoneById(phoneId);
            int totalSold = getTotalQuantitySoldByPhone(phoneId);
            double totalRevenue = saleService.saleDetailsRepository.getTotalSalesByPhone(phoneId);
            
            System.out.printf("%d. %s %s\n", rank++, phone.getBrand(), phone.getModel());
            System.out.printf("   Cantidad vendida: %d unidades\n", totalSold);
            System.out.printf("   Ingresos totales: $%.2f\n\n", totalRevenue);
        }
        
        System.out.println("═".repeat(60) + "\n");
    }

    /**
     * Método auxiliar para obtener la cantidad total vendida de un celular
     */
    private int getTotalQuantitySoldByPhone(int phoneId) {
        return saleService.getAllSales().stream()
            .flatMap(sale -> saleService.getSaleDetails(sale.getId()).stream())
            .filter(detail -> detail.getIdCelular() == phoneId)
            .mapToInt(SalesDetailsModel::getCantidad)
            .sum();
    }
}