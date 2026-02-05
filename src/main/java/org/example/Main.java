package org.example;

import org.example.Config.DatabaseConnection;
import org.example.Model.ClientModel;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        DatabaseConnection.getConnection();
        ClientModel client = new ClientModel("John Doe", "12345678A", "john.doe@example.com", "555-1234");
        System.out.println(client);
    }
}