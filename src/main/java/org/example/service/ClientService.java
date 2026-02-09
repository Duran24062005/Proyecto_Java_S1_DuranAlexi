package org.example.service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.example.Model.ClientModel;
import org.example.repository.ClientRepository;

public class ClientService {
    private final ClientRepository clientRepository;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    /**
     * Registra un nuevo cliente con validaciones
     * @param client Cliente a registrar
     * @return true si se registra correctamente, false en caso contrario
     */
    public boolean registerClient(ClientModel client) {
        if (!validateClient(client)) {
            return false;
        }
        return clientRepository.addClient(client);
    }

    /**
     * Valida los datos de un cliente
     * @param client Cliente a validar
     * @return true si los datos son válidos
     */
    private boolean validateClient(ClientModel client) {
        if (client.getName() == null || client.getName().trim().isEmpty()) {
            System.out.println("Error: El nombre del cliente es requerido");
            return false;
        }
        
        if (client.getDni() == null || client.getDni().trim().isEmpty()) {
            System.out.println("Error: La identificación del cliente es requerida");
            return false;
        }
        
        // Validar que el DNI sea único
        if (isDniDuplicated(client.getDni(), client.getId())) {
            System.out.println("Error: Ya existe un cliente con esta identificación");
            return false;
        }
        
        if (!isValidEmail(client.getEmail())) {
            System.out.println("Error: El formato del correo electrónico no es válido");
            return false;
        }
        
        // Validar que el email sea único
        if (isEmailDuplicated(client.getEmail(), client.getId())) {
            System.out.println("Error: Ya existe un cliente con este correo electrónico");
            return false;
        }
        
        if (client.getPhone() == null || client.getPhone().trim().isEmpty()) {
            System.out.println("Error: El teléfono del cliente es requerido");
            return false;
        }
        
        return true;
    }

    /**
     * Valida el formato de un correo electrónico
     * @param email Correo a validar
     * @return true si el correo es válido
     */
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Verifica si existe duplicado de DNI (excluyendo el cliente actual)
     * @param dni DNI a verificar
     * @param clientId ID del cliente actual (0 si es nuevo)
     * @return true si existe duplicado
     */
    private boolean isDniDuplicated(String dni, int clientId) {
        List<ClientModel> clients = clientRepository.getAllClients();
        return clients.stream()
            .anyMatch(c -> c.getDni().equals(dni) && c.getId() != clientId);
    }

    /**
     * Verifica si existe duplicado de email (excluyendo el cliente actual)
     * @param email Email a verificar
     * @param clientId ID del cliente actual (0 si es nuevo)
     * @return true si existe duplicado
     */
    private boolean isEmailDuplicated(String email, int clientId) {
        List<ClientModel> clients = clientRepository.getAllClients();
        return clients.stream()
            .anyMatch(c -> c.getEmail().equals(email) && c.getId() != clientId);
    }

    /**
     * Obtiene todos los clientes
     * @return Lista de todos los clientes
     */
    public List<ClientModel> getAllClients() {
        return clientRepository.getAllClients();
    }

    /**
     * Obtiene un cliente por su ID
     * @param id ID del cliente
     * @return Cliente encontrado, o null si no existe
     */
    public ClientModel getClientById(int id) {
        ClientModel client = clientRepository.getClientById(id);
        if (client == null || client.getId() == 0) {
            System.out.println("Error: El cliente con ID " + id + " no existe");
            return null;
        }
        return client;
    }

    /**
     * Obtiene un cliente por su DNI
     * @param dni Identificación del cliente
     * @return Cliente encontrado, o null si no existe
     */
    public ClientModel getClientByDni(String dni) {
        return clientRepository.getAllClients().stream()
            .filter(c -> c.getDni().equals(dni))
            .findFirst()
            .orElse(null);
    }

    /**
     * Obtiene un cliente por su email
     * @param email Email del cliente
     * @return Cliente encontrado, o null si no existe
     */
    public ClientModel getClientByEmail(String email) {
        return clientRepository.getAllClients().stream()
            .filter(c -> c.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    }

    /**
     * Actualiza un cliente existente
     * @param client Cliente con datos actualizados
     * @return true si se actualiza correctamente, false en caso contrario
     */
    public boolean updateClient(int id, ClientModel client) {
        if (!validateClient(client)) {
            return false;
        }
        
        ClientModel existing = clientRepository.getClientById(id);
        if (existing == null || existing.getId() == 0) {
            System.out.println("Error: El cliente con ID " + client.getId() + " no existe");
            return false;
        }
        
        return clientRepository.updateClient(id, client);
    }

    /**
     * Elimina un cliente
     * @param id ID del cliente a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean deleteClient(int id) {
        ClientModel client = clientRepository.getClientById(id);
        if (client == null || client.getId() == 0) {
            System.out.println("Error: El cliente con ID " + id + " no existe");
            return false;
        }
        return clientRepository.deleteClient(id);
    }

    /**
     * Busca clientes por nombre (búsqueda parcial)
     * @param name Nombre a buscar
     * @return Lista de clientes que coinciden
     */
    public List<ClientModel> searchClientsByName(String name) {
        return clientRepository.getAllClients().stream()
            .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
    }

    /**
     * Obtiene clientes ordenados por nombre
     * @return Lista de clientes ordenados alfabéticamente
     */
    public List<ClientModel> getClientsSortedByName() {
        return clientRepository.getAllClients().stream()
            .sorted((c1, c2) -> c1.getName().compareTo(c2.getName()))
            .collect(Collectors.toList());
    }

    /**
     * Obtiene la cantidad total de clientes registrados
     * @return Cantidad de clientes
     */
    public int countClients() {
        return clientRepository.getAllClients().size();
    }

    /**
     * Verifica si un cliente existe por su ID
     * @param id ID del cliente
     * @return true si existe, false en caso contrario
     */
    public boolean clientExists(int id) {
        ClientModel client = clientRepository.getClientById(id);
        return client != null && client.getId() != 0;
    }

    /**
     * Obtiene todos los números de teléfono únicos de los clientes
     * @return Lista de números de teléfono
     */
    public List<String> getAllPhoneNumbers() {
        return clientRepository.getAllClients().stream()
            .map(ClientModel::getPhone)
            .distinct()
            .collect(Collectors.toList());
    }

    /**
     * Obtiene el cliente con nombre más largo
     * @return Cliente con nombre más largo, o null si no hay clientes
     */
    public ClientModel getClientWithLongestName() {
        return clientRepository.getAllClients().stream()
            .max((c1, c2) -> Integer.compare(c1.getName().length(), c2.getName().length()))
            .orElse(null);
    }
}
