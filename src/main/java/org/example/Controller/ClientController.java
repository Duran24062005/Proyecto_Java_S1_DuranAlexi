package org.example.Controller;

import java.util.List;

import org.example.Model.ClientModel;
import org.example.View.ClientView;
import org.example.service.ClientService;

public class ClientController {

    private final ClientService clientService;
    private final ClientView clientView;

    public ClientController(){
        this.clientService = new ClientService();
        this.clientView = new ClientView();
    }

    public void init(){
        int option;
        do { 
            option = clientView.clientMenu();
            processOPtion(option);
        } while (option > 7 || option < 0);
    };

    public void processOPtion(int option){
        switch (option) {
            case 1 -> createNewClient();
            case 2 -> showAllClients();
            case 3 -> showClientById();
            case 4 -> showClientByDNI();
            case 5 -> updateClient();
            case 6 -> deleteClient();
            default->throw new AssertionError();
        }
    }

    public void createNewClient(){
        ClientModel newClient = clientView.createNewClient();
        boolean isCreated = clientService.registerClient(newClient);
        if (isCreated) {
            System.out.println("Cliente creado existosamente");
        }
    }

    public void showAllClients(){
        List<ClientModel> clients = clientService.getAllClients();
        clientView.getClients(clients);
    }

    public void showClientById(){
        int id = clientView.getId();
        ClientModel client = clientService.getClientById(id);
        clientView.getClient(client);
    }

    public void showClientByDNI(){
        String dni = clientView.getString("DNI");
        ClientModel client = clientService.getClientByDni(dni);
        clientView.getClient(client);
    }

    public void updateClient(){
        int id = clientView.getId();
        ClientModel client = clientView.createNewClient();
        boolean isUpdated = clientService.updateClient(id, client);
        if (isUpdated) {
            System.out.println("Usuario actualizado exitosamente.");
        }
    }

    public void deleteClient(){
        int id = clientView.getId();
        boolean isDeleted = clientService.deleteClient(id);
        if (isDeleted) {
            System.out.println("Usuario eliminado exitosamente");
        }
    }

}
