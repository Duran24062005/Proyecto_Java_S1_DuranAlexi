package org.example.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.Config.DatabaseConnection;
import org.example.Interfaces.repositoryInterfaces.IPhoneRepository;
import org.example.Model.PhoneModel;

public class PhoneRepository implements IPhoneRepository {

    @Override
    public boolean addPhone(PhoneModel phone) {
        String sql = "INSERT INTO celulares (brand, model, operative_sistem, range_category, price, stock) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phone.getBrand());
            ps.setString(2, phone.getModel());
            ps.setString(3, phone.getOperativeSystem());
            ps.setString(4, phone.getRangeCategory());
            ps.setDouble(5, phone.getPrice());
            ps.setInt(6, phone.getStock());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<PhoneModel> getAllPhones() {
        List<PhoneModel> phones = new ArrayList<>();
        String sql = "SELECT * FROM celulares";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PhoneModel phone = new PhoneModel(
                    rs.getInt("id"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getString("operative_sistem"),
                    rs.getString("range_category"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getDate("created_at"),
                    rs.getDate("updated_at")
                );
                phones.add(phone);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return phones;
    }

    @Override
    public PhoneModel getPhoneById(int id) {
        PhoneModel phone = new PhoneModel();
        String sql = "SELECT * FROM celulares WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                phone.setId(rs.getInt("id"));
                phone.setBrand(rs.getString("brand"));
                phone.setModel(rs.getString("model"));
                phone.setOperativeSystem(rs.getString("operative_sistem"));
                phone.setRangeCategory(rs.getString("range_category"));
                phone.setPrice(rs.getDouble("price"));
                phone.setStock(rs.getInt("stock"));
                phone.setCreatedAt(rs.getDate("created_at"));
                phone.setUpdatedAt(rs.getDate("updated_at"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return phone;
    }

    @Override
    public boolean updatePhone(PhoneModel phone) {
        String sql = "UPDATE celulares SET brand=?, model=?, operative_sistem=?, range_category=?, price=?, stock=? WHERE id = ?";
        PhoneModel toUpdate = getPhoneById(phone.getId());

        if (toUpdate == null || toUpdate.getId() == 0) {
            System.out.println("Este celular no existe");
            return false;
        }

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phone.getBrand());
            ps.setString(2, phone.getModel());
            ps.setString(3, phone.getOperativeSystem());
            ps.setString(4, phone.getRangeCategory());
            ps.setDouble(5, phone.getPrice());
            ps.setInt(6, phone.getStock());
            ps.setInt(7, phone.getId());

            ps.execute();
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePhone(int id) {
        String sql = "DELETE FROM celulares WHERE id = ?";
        PhoneModel toDelete = getPhoneById(id);

        if (toDelete == null || toDelete.getId() == 0) {
            System.out.println("Este celular no existe");
            return false;
        }
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Obtiene todos los celulares con stock bajo (menor a 5 unidades)
     * @return Lista de celulares con stock bajo
     */
    public List<PhoneModel> getPhonesLowStock() {
        List<PhoneModel> phones = new ArrayList<>();
        String sql = "SELECT * FROM celulares WHERE stock < 5";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PhoneModel phone = new PhoneModel(
                    rs.getInt("id"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getString("operative_sistem"),
                    rs.getString("range_category"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getDate("created_at"),
                    rs.getDate("updated_at")
                );
                phones.add(phone);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return phones;
    }

    /**
     * Actualiza el stock de un celular
     * @param phoneId ID del celular
     * @param newStock Nuevo valor del stock
     * @return true si se actualiza correctamente, false en caso contrario
     */
    public boolean updatePhoneStock(int phoneId, int newStock) {
        String sql = "UPDATE celulares SET stock=? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newStock);
            ps.setInt(2, phoneId);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}