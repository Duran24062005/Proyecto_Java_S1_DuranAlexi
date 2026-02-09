package org.example;

import org.example.Controller.ClientController;
import org.example.View.MainMenu;


public class Main {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        ClientController client = new ClientController();
        
        int option;
        do { 
            option = menu.mainMenu();

            switch (option) {
                case 1 -> client.init();
                case 2 -> System.out.println("");
                case 3 -> System.out.println("");
                case 4 -> System.out.println("");
                case 5 -> Exit();
                default -> {
                    throw new AssertionError();
                }
            }
            
        } while (option > 5 || option < 0);
        
    }

    public static String Exit(){
        return null;
    }
}