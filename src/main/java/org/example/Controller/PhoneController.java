package org.example.Controller;

import java.util.List;

import org.example.Model.PhoneModel;
import org.example.View.PhoneView;
import org.example.pattern.strategy.NoSortStrategy;
import org.example.pattern.strategy.SortByBrandStrategy;
import org.example.pattern.strategy.SortByPriceStrategy;
import org.example.pattern.strategy.SortByStockStrategy;
import org.example.service.PhoneService;

/**
 * Controlador para la gestión de celulares
 * Maneja las operaciones CRUD y consultas relacionadas con celulares
 */
public class PhoneController {

    private final PhoneService phoneService;
    private final PhoneView phoneView;

    public PhoneController(){
        this.phoneService = new PhoneService();
        this.phoneView = new PhoneView();
    }

    /**
     * Inicia el menú principal de gestión de celulares
     */
    public void init(){
        int option;
        do { 
            option = phoneView.phoneMenu();
            processOption(option);
        } while (option != 7);
    }

    /**
     * Procesa la opción seleccionada en el menú
     * @param option Opción del menú
     */
    public void processOption(int option){
        switch (option) {
            case 1 -> createNewPhone();
            case 2 -> showAllPhones();
            case 3 -> showPhoneById();
            case 4 -> showPhonesByCategory();
            case 5 -> updatePhone();
            case 6 -> deletePhone();
            case 7 -> System.out.println("\n <- Volviendo al menú principal \n");
            default -> System.out.println("Opción inválida. Por favor, ingresa un número entre 1 y 7.\n");
        }
    }

    /**
     * Crea un nuevo celular
     */
    public void createNewPhone(){
        PhoneModel newPhone = phoneView.createNewPhone();
        if (newPhone == null) {
            System.out.println("✗ Error al crear el celular");
            return;
        }
        
        boolean isCreated = phoneService.registerPhone(newPhone);
        if (isCreated) {
            System.out.println("✓ Celular creado exitosamente\n");
        } else {
            System.out.println("✗ No se pudo crear el celular. Verifica los datos.\n");
        }
    }

    /**
     * Muestra todos los celulares
     */
    public void showAllPhones(){
        int option;
        boolean question = true;
        while (question) {
            option = phoneView.showPhonesMenu();
            switch (option) {
                case 1 -> { 
                    phoneService.setSortingStrategy(new SortByPriceStrategy());
                    question = false;
                }
                case 2 -> { 
                    phoneService.setSortingStrategy(new SortByStockStrategy());
                    question = false;
                }
                case 3 -> { 
                    phoneService.setSortingStrategy(new SortByBrandStrategy());
                    question = false;
                }
                case 4 -> { 
                    phoneService.setSortingStrategy(new NoSortStrategy());
                    question = false;
                }
                default -> System.out.println("\nElija una opción valida");
            }
        }
        List<PhoneModel> phones = phoneService.getPhonesSorted();
        if (phones.isEmpty()) {
            System.out.println("No hay celulares registrados\n");
            return;
        }
        phoneView.getPhones(phones);
    }

    /**
     * Muestra un celular por ID
     */
    public void showPhoneById(){
        int id = phoneView.getId();
        PhoneModel phone = phoneService.getPhoneById(id);
        if (phone == null) {
            System.out.println("✗ Celular no encontrado\n");
            return;
        }
        phoneView.getPhone(phone);
    }

    /**
     * Muestra celulares por categoría
     */
    public void showPhonesByCategory(){
        System.out.println("\n¿Cuál es la categoría?");
        System.out.println("1. Alta");
        System.out.println("2. Media");
        System.out.println("3. Baja");
        System.out.print("Selecciona: ");
        
        int option = phoneView.getOption();
        String category = switch (option) {
            case 1 -> "Alta";
            case 2 -> "Media";
            case 3 -> "Baja";
            default -> null;
        };
        
        if (category == null) {
            System.out.println("✗ Opción inválida\n");
            return;
        }
        
        List<PhoneModel> phones = phoneService.getPhonesByCategory(category);
        if (phones.isEmpty()) {
            System.out.println("✗ No hay celulares en la categoría: " + category + "\n");
            return;
        }
        
        System.out.println("\n=== Celulares categoría: " + category + " ===\n");
        phoneView.getPhones(phones);
    }

    /**
     * Actualiza un celular existente
     */
    public void updatePhone(){
        int id = phoneView.getId();
        PhoneModel phoneToUpdate = phoneService.getPhoneById(id);
        
        if (phoneToUpdate == null) {
            System.out.println("✗ Celular no encontrado\n");
            return;
        }
        
        System.out.println("\nDatos actuales:");
        System.out.println("  Marca: " + phoneToUpdate.getBrand());
        System.out.println("  Modelo: " + phoneToUpdate.getModel());
        System.out.println("  Precio: $" + phoneToUpdate.getPrice());
        System.out.println("  Stock: " + phoneToUpdate.getStock());
        
        PhoneModel updatedPhone = phoneView.createNewPhone();
        if (updatedPhone == null) {
            System.out.println("✗ Error al actualizar los datos\n");
            return;
        }
        
        updatedPhone.setId(id);
        boolean isUpdated = phoneService.updatePhone(updatedPhone);
        
        if (isUpdated) {
            System.out.println("✓ Celular actualizado exitosamente\n");
        } else {
            System.out.println("✗ No se pudo actualizar el celular\n");
        }
    }

    /**
     * Elimina un celular
     */
    public void deletePhone(){
        int id = phoneView.getId();
        PhoneModel phone = phoneService.getPhoneById(id);
        
        if (phone == null) {
            System.out.println("✗ Celular no encontrado\n");
            return;
        }
        
        System.out.println("\n¿Deseas eliminar el siguiente celular?");
        System.out.println("  Marca: " + phone.getBrand());
        System.out.println("  Modelo: " + phone.getModel());
        System.out.println("  (S/N): ");
        
        String confirmation = phoneView.getString("respuesta");
        
        if (!confirmation.equalsIgnoreCase("S")) {
            System.out.println("✗ Operación cancelada\n");
            return;
        }
        
        boolean isDeleted = phoneService.deletePhone(id);
        
        if (isDeleted) {
            System.out.println("✓ Celular eliminado exitosamente\n");
        } else {
            System.out.println("✗ No se pudo eliminar el celular\n");
        }
    }

    /**
     * Muestra estadísticas de celulares
     */
    public void showPhoneStatistics(){
        System.out.println("\n========== ESTADÍSTICAS DE CELULARES ==========\n");
        
        int totalPhones = phoneService.countPhones();
        System.out.println("Total de celulares: " + totalPhones);
        
        if (totalPhones == 0) {
            System.out.println("No hay celulares registrados\n");
            return;
        }
        
        double avgPrice = phoneService.getAveragePrice();
        int totalStock = phoneService.getTotalStock();
        PhoneModel mostExpensive = phoneService.getMostExpensivePhone();
        PhoneModel cheapest = phoneService.getCheapestPhone();
        
        System.out.printf("Precio promedio: $%.2f\n", avgPrice);
        System.out.println("Stock total: " + totalStock + " unidades");
        System.out.printf("Stock promedio por modelo: %.2f unidades\n", (double) totalStock / totalPhones);
        
        if (mostExpensive != null) {
            System.out.printf("Celular más caro: %s %s ($%.2f)\n", 
                mostExpensive.getBrand(), mostExpensive.getModel(), mostExpensive.getPrice());
        }
        
        if (cheapest != null) {
            System.out.printf("Celular más barato: %s %s ($%.2f)\n", 
                cheapest.getBrand(), cheapest.getModel(), cheapest.getPrice());
        }
        
        // Mostrar celulares con stock bajo
        List<PhoneModel> lowStockPhones = phoneService.getPhonesByLowStock();
        if (!lowStockPhones.isEmpty()) {
            System.out.println("\n⚠ ALERTA: Celulares con stock bajo (< 5):");
            lowStockPhones.forEach(phone ->
                System.out.printf("  - %s %s: %d unidades\n", 
                    phone.getBrand(), phone.getModel(), phone.getStock())
            );
        } else {
            System.out.println("\n✓ Todos los celulares tienen stock adecuado");
        }
        
        System.out.println("\n===========================================\n");
    }
}