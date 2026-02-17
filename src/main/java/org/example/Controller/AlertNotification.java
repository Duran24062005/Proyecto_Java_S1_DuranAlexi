package org.example.Controller;

import java.util.Optional;
import java.util.Scanner;

import org.example.Model.PhoneModel;
import org.example.View.PhoneView;
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
        Optional<PhoneModel> phones = alertService.stockNotification();
        if (!phones.isEmpty()) {
            boolean question = true;
            while(question) {
                System.out.println("""
                    Hay celulares con estok bajo, elija una opción
                        1. ver celulares
                        2. ingnorar
                """);
                try {
                    System.out.println("Debes ingresar números válidos\n");
                    int sc = new Scanner(System.in).nextInt();
                    switch (sc) {
        
                        case 1 -> {
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
                        }
                        case 2 -> {
                            question = false;
                            System.out.println('Ok');
                        }
                        default -> System.out.println('Elija una opcion valida');
                    }

        } catch (Exception e) {
            System.out.println(e);
            }
        }
    }
}