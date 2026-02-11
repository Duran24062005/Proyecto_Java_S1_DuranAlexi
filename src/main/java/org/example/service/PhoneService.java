package org.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.example.Interfaces.patternInterfaces.strategy.ISortingStrategy;
import org.example.Model.PhoneModel;
import org.example.pattern.strategy.NoSortStrategy;
import org.example.repository.PhoneRepository;

public class PhoneService {
    private final PhoneRepository phoneRepository;
    private ISortingStrategy sortingStrategy;

    public PhoneService() {
        this.phoneRepository = new PhoneRepository();
        this.sortingStrategy = new NoSortStrategy();
    }

    // Setter para cambiar la estrategia
    public void setSortingStrategy(ISortingStrategy strategy) {
        this.sortingStrategy = strategy;
        System.out.println("Estrategia cambiada a: " + strategy.getStrategyName());
    }

    // Getter para saber la estrategi actual
    public ISortingStrategy getSortingStrategy() {
        return sortingStrategy;
    }

    /**
     * Registra un nuevo celular con validaciones
     * @param phone Celular a registrar
     * @return true si se registra correctamente, false en caso contrario
     */
    public boolean registerPhone(PhoneModel phone) {
        if (!validatePhone(phone)) {
            return false;
        }
        return phoneRepository.addPhone(phone);
    }

    /**
     * Valida los datos de un celular
     * @param phone Celular a validar
     * @return true si los datos son válidos
     */
    private boolean validatePhone(PhoneModel phone) {
        if (phone.getBrand() == null || phone.getBrand().trim().isEmpty()) {
            System.out.println("Error: La marca del celular es requerida");
            return false;
        }
        
        if (phone.getModel() == null || phone.getModel().trim().isEmpty()) {
            System.out.println("Error: El modelo del celular es requerido");
            return false;
        }
        
        if (phone.getPrice() <= 0) {
            System.out.println("Error: El precio debe ser positivo");
            return false;
        }
        
        if (phone.getStock() < 0) {
            System.out.println("Error: El stock no puede ser negativo");
            return false;
        }
        
        if (phone.getOperativeSystem() == null || phone.getOperativeSystem().trim().isEmpty()) {
            System.out.println("Error: El sistema operativo es requerido");
            return false;
        }
        
        if (phone.getRangeCategory() == null || phone.getRangeCategory().trim().isEmpty()) {
            System.out.println("Error: La categoría de gama es requerida");
            return false;
        }
        
        return true;
    }

    /**
     * Obtiene todos los celulares
     * @return Lista de todos los celulares
     */
    public List<PhoneModel> getPhonesSorted() {
        List<PhoneModel> phones = phoneRepository.getAllPhones();
        sortingStrategy.sort(phones);
        return phones;
    }

    /**
     * Obtiene un celular por su ID
     * @param id ID del celular
     * @return Celular encontrado, o null si no existe
     */
    public PhoneModel getPhoneById(int id) {
        PhoneModel phone = phoneRepository.getPhoneById(id);
        if (phone == null || phone.getId() == 0) {
            System.out.println("Error: El celular con ID " + id + " no existe");
            return null;
        }
        return phone;
    }

    /**
     * Actualiza un celular existente
     * @param phone Celular con datos actualizados
     * @return true si se actualiza correctamente, false en caso contrario
     */
    public boolean updatePhone(PhoneModel phone) {
        if (!validatePhone(phone)) {
            return false;
        }
        
        PhoneModel existing = phoneRepository.getPhoneById(phone.getId());
        if (existing == null || existing.getId() == 0) {
            System.out.println("Error: El celular con ID " + phone.getId() + " no existe");
            return false;
        }
        
        return phoneRepository.updatePhone(phone);
    }

    /**
     * Elimina un celular
     * @param id ID del celular a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean deletePhone(int id) {
        PhoneModel phone = phoneRepository.getPhoneById(id);
        if (phone == null || phone.getId() == 0) {
            System.out.println("Error: El celular con ID " + id + " no existe");
            return false;
        }
        return phoneRepository.deletePhone(id);
    }

    /**
     * Obtiene celulares con stock bajo (menor a 5 unidades)
     * @return Lista de celulares con stock bajo
     */
    public List<PhoneModel> getPhonesByLowStock() {
        return phoneRepository.getPhonesLowStock();
    }

    /**
     * Obtiene celulares ordenados por precio (de menor a mayor)
     * @return Lista de celulares ordenados por precio
     */
    public List<PhoneModel> getPhonesSortedByPrice() {
        return phoneRepository.getAllPhones().stream()
            .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
            .collect(Collectors.toList());
    }

    /**
     * Obtiene celulares de una categoría específica
     * @param category Categoría de gama (Alta, Media, Baja)
     * @return Lista de celulares de la categoría
     */
    public List<PhoneModel> getPhonesByCategory(String category) {
        return phoneRepository.getAllPhones().stream()
            .filter(p -> p.getRangeCategory().equalsIgnoreCase(category))
            .collect(Collectors.toList());
    }

    /**
     * Obtiene celulares de una marca específica
     * @param brand Marca del celular
     * @return Lista de celulares de la marca
     */
    public List<PhoneModel> getPhonesByBrand(String brand) {
        return phoneRepository.getAllPhones().stream()
            .filter(p -> p.getBrand().equalsIgnoreCase(brand))
            .collect(Collectors.toList());
    }

    /**
     * Obtiene el precio promedio de los celulares
     * @return Precio promedio
     */
    public double getAveragePrice() {
        List<PhoneModel> phones = phoneRepository.getAllPhones();
        if (phones.isEmpty()) {
            return 0.0;
        }
        return phones.stream()
            .mapToDouble(PhoneModel::getPrice)
            .average()
            .orElse(0.0);
    }

    /**
     * Obtiene el stock total de todos los celulares
     * @return Stock total
     */
    public int getTotalStock() {
        return phoneRepository.getAllPhones().stream()
            .mapToInt(PhoneModel::getStock)
            .sum();
    }

    /**
     * Obtiene el celular más caro
     * @return Celular con el precio más alto, o null si no hay celulares
     */
    public PhoneModel getMostExpensivePhone() {
        return phoneRepository.getAllPhones().stream()
            .max((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
            .orElse(null);
    }

    /**
     * Obtiene el celular más barato
     * @return Celular con el precio más bajo, o null si no hay celulares
     */
    public PhoneModel getCheapestPhone() {
        return phoneRepository.getAllPhones().stream()
            .min((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
            .orElse(null);
    }

    /**
     * Actualiza el stock de un celular
     * @param phoneId ID del celular
     * @param newStock Nuevo valor del stock
     * @return true si se actualiza correctamente
     */
    public boolean updatePhoneStock(int phoneId, int newStock) {
        if (newStock < 0) {
            System.out.println("Error: El stock no puede ser negativo");
            return false;
        }
        return phoneRepository.updatePhoneStock(phoneId, newStock);
    }

    /**
     * Reduce el stock de un celular por una compra
     * @param phoneId ID del celular
     * @param quantity Cantidad a restar del stock
     * @return true si se reduce correctamente
     */
    public boolean reducePhoneStock(int phoneId, int quantity) {
        PhoneModel phone = phoneRepository.getPhoneById(phoneId);
        
        if (phone == null || phone.getId() == 0) {
            System.out.println("Error: El celular no existe");
            return false;
        }
        
        if (quantity <= 0) {
            System.out.println("Error: La cantidad debe ser mayor a 0");
            return false;
        }
        
        if (phone.getStock() < quantity) {
            System.out.println("Error: Stock insuficiente. Stock disponible: " + phone.getStock());
            return false;
        }
        
        return phoneRepository.updatePhoneStock(phoneId, phone.getStock() - quantity);
    }

    /**
     * Cuenta la cantidad total de celulares registrados
     * @return Cantidad de celulares
     */
    public int countPhones() {
        return phoneRepository.getAllPhones().size();
    }

    /**
     * Obtiene todas las marcas únicas de celulares
     * @return Lista de marcas
     */
    public List<String> getAllBrands() {
        return phoneRepository.getAllPhones().stream()
            .map(PhoneModel::getBrand)
            .distinct()
            .collect(Collectors.toList());
    }
}
