package org.example.View;

import java.util.Scanner;

/* 
    Menu principal
    Se encarga de mostrar el menú principal
*/
public class MainMenu {

    private final Scanner sc = new Scanner(System.in);

    public int  mainMenu() {
        System.out.println("""
            \n
            =======================================================
                        Bienvenido a Tecno Store System
            =======================================================
            Elija una opción:
                1. Gestionar clientes.
                2. Gestionar celulares.
                3. Generar o gestionar venta.
                4. Gestionar reportes.

            =======================================================
            """);
        
        try {
            return Integer.parseInt(sc.nextLine().trim());

        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un numero valido: " + e);
            return -1;
        } catch (IllegalStateException | java.util.NoSuchElementException e) {
            System.out.println("Error en la lectura de datos.");
            return -1;
        }
    } 
}
