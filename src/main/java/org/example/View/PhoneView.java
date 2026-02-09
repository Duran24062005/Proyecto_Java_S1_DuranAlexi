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
                        Estas en Gestionar Cliente
            =======================================================
            Elija una opción:
                1. Crear telefono.
                2. Ver todos los telefonos.
                3. Buscar telefono por ID.
                4. Buscar telefono por DNI.
                5. Actualizar telefono.
                6. Eliminar telefono.

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

    public PhoneModel createNewPhone(){
        System.out.println("\n" + "═".repeat(60));
        System.out.println("           Registro de Telefono.");
        System.out.println("═".repeat(60));
        
        try {
            System.out.println("Marca: ");
            String brand = scanner.nextLine().trim();
            
            System.out.println("Modelo: ");
            String model = scanner.nextLine().trim();

            System.out.println("Systema operativo: ");
            String operativeSystem = scanner.nextLine().trim();

            System.out.println("Gama: ");
            String rangeCategory = scanner.nextLine().trim();

            System.out.println("Precio: ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            System.out.println("Stock actual: ");
            int stock = Integer.parseInt(scanner.nextLine().trim());

            return new PhoneModel(brand, model, operativeSystem, rangeCategory, price, stock);

        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un numero valido: " + e);
            return null;
        } catch (IllegalStateException | java.util.NoSuchElementException e) {
            System.out.println("Error en la lectura de datos.");
            return null;
        }
    }

    public void getPhones(List<PhoneModel> phones){
        if (phones.size() < 0 || phones.isEmpty()) {
            System.out.println("No hay telefonos.");
        }
        System.out.println("Telefonos: (" + phones.size() + ")");
        for (PhoneModel phone : phones) {
            System.out.println("""
                =======================================================
                    Id: %s
                    Marca: %s
                    Modelo: %s
                    Sistema operativo: %s
                    Gama: %s
                    Precio: %s
                    F. de creacion: %s
                    F. de actualizacion: %s
                    """.formatted(
                        phone.getId(), 
                        phone.getBrand(), 
                        phone.getModel(),
                        phone.getOperativeSystem(),
                        phone.getRangeCategory(),
                        phone.getPrice(),
                        phone.getCreatedAt(),
                        phone.getUpdatedAt()
                    ));
        }
    }

    public void getPhone(PhoneModel phone){
        if (phone == null) {
            System.out.println("Telefono no existente");
        }
        System.out.println("""
                =======================================================
                    Id: %s
                    Marca: %s
                    Modelo: %s
                    Sistema operativo: %s
                    Gama: %s
                    Precio: %s
                    F. de creacion: %s
                    F. de actualizacion: %s
                    """.formatted(
                        phone.getId(), 
                        phone.getBrand(), 
                        phone.getModel(),
                        phone.getOperativeSystem(),
                        phone.getRangeCategory(),
                        phone.getPrice(),
                        phone.getCreatedAt(),
                        phone.getUpdatedAt()
                    ));
    }

    public int updatePhone(){
        return 1;
    }

    public int deletePhone(){
        return 1;
    }

}
