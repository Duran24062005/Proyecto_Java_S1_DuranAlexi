package org.example.Interfaces.viewInterfaces;


import org.example.Model.ClientModel;

public interface IClientView {
    public int registerNewClient();
    public int getAllClients();
    public ClientModel getClientById();
}
