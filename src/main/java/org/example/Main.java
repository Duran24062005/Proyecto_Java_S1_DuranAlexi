package org.example;

import org.example.Controller.ClientController;
import org.example.View.MainMenu;


public class Main {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        ClientController client = new ClientController();

        System.out.println("""
            =======================================================
                    👋   Bienvenido a Tecno Store System  💯
            =======================================================
                """);
        
        int option;
        do { 
            option = menu.mainMenu();

            switch (option) {
                case 1 -> client.init();
                case 2 -> System.out.println("");
                case 3 -> System.out.println("");
                case 4 -> System.out.println("");
                case 5 -> {
                    System.out.println("\n¡Gracias por usar TecnoStore! Hasta luego.\n");
                    return; // Salir del programa
                }
                default -> System.out.println("Opción inválida. Por favor, ingresa un número entre 1 y 5.\n");
            }
            
        } while (true);
        
    }

    public static String Exit(){
        return null;
    }
}