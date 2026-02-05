package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.example.Config.DatabaseConnection;
import org.example.Interfaces.IClientRepository;
import org.example.Model.ClientModel;

public class ClientRepository implements IClientRepository{

    @Override
    public boolean addClient(ClientModel client) {
        String sql = "INSERT INTO clientes (name, dni, email, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql) ){
            ps.setString(1, client.getName());
            ps.setString(2, client.getDni());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPhone());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ClientModel> getAllClients() {
        List<ClientModel> persons = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ClientModel person = new ClientModel(rs.getInt("id"), rs.getString("name"), rs.getString("dni"), rs.getString("email"), rs.getString("phone"), rs.getDate("created_at"), rs.getDate("created_At"));
                persons.add(person);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return persons;
    }

    @Override
    public ClientModel getClientById(int id) {
        ClientModel client = new ClientModel();
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setDni(rs.getString("dni"));
                client.setEmail(rs.getString("email"));
                client.setPhone(rs.getString("phone"));
                client.setCreatedAt(rs.getDate("created_at"));
                client.setUpdatedAt(rs.getDate("created_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return client;
    }

    @Override
    public boolean updateClient(ClientModel client) {
        String sql = "UPDATE clientes SET name=?, dni=?, email=?, phone=? WHERE id = ?";
        ClientModel toUpdate = getClientById(client.getId());

        if (toUpdate == null) {
            System.out.println("Este usuario no existe");
        }

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getDni());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPhone());
            ps.setInt(5, client.getId());

            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteClient(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteClient'");
    }

    
    
}
