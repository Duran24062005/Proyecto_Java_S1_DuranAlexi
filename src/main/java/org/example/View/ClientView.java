package org.example.View;

import java.util.List;
import java.util.Scanner;

import org.example.Model.ClientModel;

public class ClientView {

    private final Scanner scanner = new Scanner(System.in);

    public int clientMenu(){
        System.out.println("""
            \n
            =======================================================
                        Estas en Gestionar Cliente
            =======================================================
            Elija una opción:
                1. Crear cliente.
                2. Ver todos los clientes.
                3. Buscar cliente por ID.
                4. Buscar cliente por DNI.
                5. Actualizar cliente.
                6. Eliminar cliente.

            =======================================================
            """);
        
        try {
            return Integer.parseInt(scanner.nextLine().trim());

        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un numero valido: " + e);
            return -1;
        } catch (IllegalStateException | java.util.NoSuchElementException e) {
            System.out.println("Error en la lectura de datos.");
            return -1;
        }
    }

    public ClientModel createNewClient(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("           Registro de Cliente");
        System.out.println("═".repeat(60));
        
        try {
            System.out.println("Nombre: ");
            String name = scanner.nextLine().trim();

            System.out.println("C.C: ");
            String dni = scanner.nextLine().trim();

            System.out.println("Email: ");
            String email = scanner.nextLine().trim();

            System.out.println("Numero de telefono: ");
            String phone = scanner.nextLine().trim();

            return new ClientModel(name, dni, email, phone);

        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un numero valido: " + e);
            return null;
        } catch (IllegalStateException | java.util.NoSuchElementException e) {
            System.out.println("Error en la lectura de datos.");
            return null;
        }
    }

    public void getClients(List<ClientModel> clients){
        if (clients.size() < 0 || clients.isEmpty()) {
            System.out.println("No hay clientes.");
        }
        System.out.println("Clientes: (" + clients.size() + ")");
        for (ClientModel client : clients) {
            System.out.println("""
                =======================================================
                    Id: %s
                    Nombre: %s
                    C.c: %s
                    Email: %s
                    F. de creacion: %s
                    F. de actualizacion: %s
                    """.formatted(
                        client.getId(), 
                        client.getName(), 
                        client.getDni(),
                        client.getEmail(),
                        client.getCreatedAt(),
                        client.getUpdatedAt()
                    ));
        }
    }

    public void getClient(ClientModel client){
        if (client == null) {
            System.out.println("Cliente no existente");
        }
        System.out.println("""
                =======================================================
                    Id: %s
                    Nombre: %s
                    C.c: %s
                    Email: %s
                    F. de creacion: %s
                    F. de actualizacion: %s
                =======================================================
                    """.formatted(
                        client.getId(), 
                        client.getName(), 
                        client.getDni(),
                        client.getEmail(),
                        client.getCreatedAt(),
                        client.getUpdatedAt()
                    ));
    }

    public int updateClient(){
        return 1;
    }

    public int deleteClient(){
        return 1;
    }

}
