package org.example.View;

import java.util.List;
import java.util.Scanner;

import org.example.Model.SaleModel;

public class SaleView {

    private final Scanner scanner = new Scanner(System.in);

    public int saleMenu(){
        System.out.println("""
            \n
            =======================================================
                        Estas en Gestionar Cliente
            =======================================================
            Elija una opción:
                1. Crear venta.
                2. Ver todos los ventas.
                3. Buscar venta por ID.
                4. Buscar venta por DNI.
                5. Actualizar venta.
                6. Eliminar venta.

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

    public SaleModel createNewSale(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("           Registro de nueva venta.");
        System.out.println("═".repeat(60));
        
        try {
            System.out.println("ID cliente: ");
            int idClient = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.println("Total: ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            return new SaleModel(idClient, price);

        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un numero valido: " + e);
            return null;
        } catch (IllegalStateException | java.util.NoSuchElementException e) {
            System.out.println("Error en la lectura de datos.");
            return null;
        }
    }

    public void getSales(List<SaleModel> sales){
        if (sales.size() < 0 || sales.isEmpty()) {
            System.out.println("No hay telefonos.");
        }
        System.out.println("Telefonos: (" + sales.size() + ")");
        for (SaleModel sale : sales) {
            System.out.println("""
                =======================================================
                    Id: %s
                    Id Cliente: %s
                    Total: %s
                    F. de creacion: %s
                    F. de actualizacion: %s
                    """.formatted(
                        sale.getId(), 
                        sale.getIdCliente(), 
                        sale.getTotal(),
                        sale.getCreatedAt(),
                        sale.getUpdatedAt()
                    ));
        }
    }

    public void getSale(SaleModel sale){
        if (sale == null) {
            System.out.println("Venta no existente");
        }
        System.out.println("""
                =======================================================
                    Id: %s
                    Id Cliente: %s
                    Total: %s
                    F. de creacion: %s
                    F. de actualizacion: %s
                    """.formatted(
                        sale.getId(), 
                        sale.getIdCliente(), 
                        sale.getTotal(),
                        sale.getCreatedAt(),
                        sale.getUpdatedAt()
                    ));
    }
}
