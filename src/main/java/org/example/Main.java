package org.example;

import org.example.Config.DatabaseConnection;
import org.example.Model.ClientModel;
import org.example.repository.ClientRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n");
        DatabaseConnection.getConnection();
        ClientRepository repo = new ClientRepository();

        // Create new client
        // ClientModel client = new ClientModel("Alexi", "123476", "alexi.duran@example.com", "324086");
        // repo.addClient(client);
        
        // Show all clients
        System.out.println("\n");
        System.out.println(repo.getAllClients());

        // Find client by id
        System.out.println("\n");
        System.out.println(repo.getClientById(5));

        // Update client
        ClientModel clientToUp = new ClientModel(1, "Alexi", "123476", "alexi.duran@ex.com", "324086");
        repo.updateClient(clientToUp);
    }
}