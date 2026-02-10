package org.example.View;

import java.util.List;
import java.util.Scanner;

import org.example.Model.PhoneModel;

public class PhoneView {
    private final Scanner scanner = new Scanner(System.in);

    public int phoneMenu(){
        System.out.println("""
            \n
            =======================================================
                     Gestionar Celulares
            =======================================================
            Elija una opción:
                1. Crear celular.
                2. Ver todos los celulares.
                3. Buscar celular por ID.
                4. Ver celulares por categoría.
                5. Actualizar celular.
                6. Eliminar celular.
                7. Volver al menu principal.

            =======================================================
            """);
        
        try {
            return Integer.parseInt(scanner.nextLine().trim());

        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un número válido\n");
            return -1;
        } catch (IllegalStateException | java.util.NoSuchElementException e) {
            System.out.println("Error en la lectura de datos.");
            return -1;
        }
    }

    public PhoneModel createNewPhone(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("           Registro de Celular");
        System.out.println("═".repeat(60));
        
        try {
            System.out.print("Marca: ");
            String brand = scanner.nextLine().trim();

            System.out.print("Modelo: ");
            String model = scanner.nextLine().trim();

            System.out.print("Sistema operativo: ");
            String operativeSystem = scanner.nextLine().trim();

            System.out.print("Gama (Alta/Media/Baja): ");
            String rangeCategory = scanner.nextLine().trim();

            System.out.print("Precio: ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Stock actual: ");
            int stock = Integer.parseInt(scanner.nextLine().trim());

            return new PhoneModel(brand, model, operativeSystem, rangeCategory, price, stock);

        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar números válidos\n");
            return null;
        } catch (IllegalStateException | java.util.NoSuchElementException e) {
            System.out.println("Error en la lectura de datos.");
            return null;
        }
    }

    public void getPhones(List<PhoneModel> phones){
        if (phones == null || phones.isEmpty()) {
            System.out.println("No hay celulares registrados.");
            return;
        }
        
        System.out.println("\n" + "═".repeat(70));
        System.out.println("                      CATÁLOGO DE CELULARES");
        System.out.println("═".repeat(70));
        System.out.printf("Total de celulares: %d\n\n", phones.size());
        
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
        
        System.out.println("═".repeat(70) + "\n");
    }

    public void getPhone(PhoneModel phone){
        if (phone == null) {
            System.out.println("Celular no encontrado\n");
            return;
        }
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("              DETALLES DEL CELULAR");
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
        System.out.println("═".repeat(50) + "\n");
    }

    public int getId(){
        try {
            System.out.print("Ingresa el ID del celular: ");
            return Integer.parseInt(scanner.nextLine().trim());

        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un número válido\n");
            return -1;
        } catch (IllegalStateException | java.util.NoSuchElementException e) {
            System.out.println("Error en la lectura de datos.");
            return -1;
        }
    }

    public String getString(String prompt){
        try {
            System.out.print("Ingresa " + prompt + ": ");
            return scanner.nextLine().trim();

        } catch (IllegalStateException | java.util.NoSuchElementException e) {
            System.out.println("Error en la lectura de datos.");
            return "";
        }
    }

    public int getOption(){
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un número válido");
            return -1;
        }
    }

    public int updatePhone(){
        return 1;
    }

    public int deletePhone(){
        return 1;
    }

}
