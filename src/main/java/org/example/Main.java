package org.example;

import org.example.View.ClientView;
import org.example.View.MainMenu;
import org.example.repository.ClientRepository;
import org.example.service.ClientService;


public class Main {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        ClientView clView = new ClientView();
        ClientRepository repo = new ClientRepository();
        ClientService clientServixde = new ClientService();

        clView.getClients(clientServixde.getAllClients());
        

        // do { 
            // menu.mainMenu();
            // 
        // } while (true);
        
    }
}