package org.example.Interfaces.repositoryInterfaces;

import java.util.List;

import org.example.Model.ClientModel;

public interface IClienteRepository {
    public boolean addClient(ClientModel client);
    public List<ClientModel> getAllClients();
    public ClientModel getClientById(int id);
    public boolean updateClient(ClientModel client);
    public boolean deleteClient(int id);
}
