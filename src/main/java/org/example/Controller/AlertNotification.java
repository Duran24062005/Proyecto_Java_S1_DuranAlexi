package org.example.Controller;

import java.util.List;
import java.util.Scanner;

import org.example.Model.PhoneModel;
import org.example.service.AlertNotificationService;

public class AlertNotification {

    private final AlertNotificationService alertService;

    public AlertNotification(AlertNotificationService alertServ){
        this.alertService = alertServ;
    }

    public AlertNotificationService getPhoneRepository() {
        return alertService;
    }

    public void stockNotificationLow(){
        // System.out.println(alertService.stockNotification());
        List<PhoneModel> phones = alertService.stockNotification();
        
        if (!phones.isEmpty()) {
            boolean question = true;
            int opt;
            while(question) {
                System.out.println("""
                --- ALERTA DE STOCK BAJO ---
                Elija una opción
                    1. ver celulares.
                    2. Generar un reporte.
                    3. ingnorar.
                -----------------------------
                """);
                try {
                    Scanner sc = new Scanner(System.in);
                    opt = sc.nextInt();
                    switch (opt) {
                        case 1 -> {
                            System.out.println("Hay " + phones.size() + " quipos con stock menor a 5.");
                            for (PhoneModel phone : phones) {
                                System.out.println("""
                                    =======================================================
                                    Id: %s
                                    Marca: %s 
                                    Modelo: %s 
                                    SO: %s 
                                    Gama: %s 
                                    Precio: %s 
                                    Stock: %s
                                    Creado: %s
                                    Actualizado: %s
                                    
                                    """.formatted(
                                        phone.getId(),
                                        phone.getBrand(),
                                        phone.getModel(),
                                        phone.getOperativeSystem(),
                                        phone.getRangeCategory(),
                                        phone.getPrice(),
                                        phone.getStock(),
                                        phone.getCreatedAt(),
                                        phone.getUpdatedAt()
                                    ));
                                }
                                question = false;
                            }
                            case 2 -> {
                                ReportController newReport = new ReportController();
                                newReport.generateLowStockReport();
                                question = false;
                            }
                            case 3 -> {
                                question = false;
                            }
                            default -> System.out.println("Elija una opción valida");
                        }
                }catch (Exception e) {
                    System.out.println("\nPor favor elija una opción valida.");
                }
            }
        }
    }
}