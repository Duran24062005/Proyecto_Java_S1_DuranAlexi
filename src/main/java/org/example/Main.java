package org.example;

import org.example.Controller.AlertNotification;
import org.example.Controller.ClientController;
import org.example.Controller.PhoneController;
import org.example.Controller.ReportController;
import org.example.Controller.SaleController;
import org.example.View.MainMenu;
import org.example.repositories.PhoneRepository;
import org.example.service.AlertNotificationService;


/**
 * Clase principal de la aplicación TecnoStore
 * Gestiona el flujo principal y menú de la aplicación
 */
public class Main {
    
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        
        // Inicializar controladores
        ClientController clientController = new ClientController();
        PhoneController phoneController = new PhoneController();
        SaleController saleController = new SaleController();
        ReportController reportController = new ReportController();

        System.out.println("""
            
            =======================================================
                    👋   Bienvenido a Tecno Store System  💯
            =======================================================
                
            Versión 1.0
            Sistema de gestión de ventas, inventario y reportes
                
            =======================================================
            """);
        
        int option;
        boolean running = true;
        
        while (running) { 
            PhoneRepository repo = new PhoneRepository();
            AlertNotificationService service = new AlertNotificationService(repo);
            AlertNotification alertNotification = new AlertNotification(service);
            alertNotification.stockNotificationLow();

            option = menu.mainMenu();


            switch (option) {
                case 1 -> {
                    System.out.println("\n Entrando a Gestión de Clientes...");
                    clientController.init();
                }
                
                case 2 -> {
                    System.out.println("\n Entrando a Gestión de Celulares...");
                    phoneController.init();
                }
                
                case 3 -> {
                    System.out.println("\n Entrando a Gestión de Ventas...");
                    saleController.init();
                }
                
                case 4 -> {
                    System.out.println("\n Entrando a Gestión de Reportes...");
                    reportController.init();
                }
                
                case 5 -> {
                    System.out.println("\n" + "═".repeat(60));
                    System.out.println("           ¡Gracias por usar TecnoStore!");
                    System.out.println("═".repeat(60));
                    System.out.println("\n Hasta luego...\n");
                    running = false;
                }
                
                default -> System.out.println(" Opción inválida. Por favor, ingresa un número entre 1 y 5.\n");
            }
        }
    }
}