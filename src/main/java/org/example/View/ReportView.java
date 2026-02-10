package org.example.View;

import java.util.Scanner;

public class ReportView {

    private final Scanner scanner = new Scanner(System.in);

    public int reportMenu(){
        System.out.println("""
            \n
            =======================================================
                        Estas en Gestionar Cliente
            =======================================================
            Elija una opción:
                1. Generar reporte.
                2. Ver el total de ventas.
                3. Generar reporte de inventario.
                4. Generar reporte de clientes.
                5. Ver resumen de ventas.
                6. Ver los 3 celulares mas vendidos.
                7. Volver al menu principal.

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
}
