package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.Config.DatabaseConnection;
import org.example.Interfaces.repositoryInterfaces.ISaleRepository;
import org.example.Model.SaleModel;

public class SaleRepository implements ISaleRepository {

    @Override
    public boolean addSale(SaleModel sale) {
        String sql = "INSERT INTO ventas (id_cliente, total) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql) ){
            ps.setInt(1, sale.getIdCliente());
            ps.setDouble(2, sale.getTotal());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<SaleModel> getAllSales() {
        List<SaleModel> sales = new ArrayList<>();
        String sql = "SELECT * FROM ventas";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SaleModel sale = new SaleModel(rs.getInt("id"), rs.getInt("id_cliente"), rs.getDate("date"), rs.getDouble("total"), rs.getDate("created_at"), rs.getDate("created_At"));
                sales.add(sale);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return sales;
    }

    public List<SaleModel> getAllCompleteSales() {
        List<SaleModel> sales = new ArrayList<>();
        String sql = "SELECT * FROM clientes join ventas on clientes.id = ventas.id_cliente";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SaleModel sale = new SaleModel(rs.getInt("id"), rs.getInt("id_cliente"), rs.getDate("date"), rs.getDouble("total"), rs.getDate("created_at"), rs.getDate("created_At"));
                sales.add(sale);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return sales;
    }

    @Override
    public SaleModel getSaleById(int id) {

        SaleModel sale = new SaleModel();
        String sql = "SELECT * FROM ventas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sale.setId(rs.getInt("id"));
                sale.setIdCliente(rs.getInt("id_cliente"));
                sale.setDate(rs.getDate("date"));
                sale.setTotal(rs.getDouble("total"));
                sale.setCreatedAt(rs.getDate("created_at"));
                sale.setUpdatedAt(rs.getDate("created_at"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return sale;
    }

    public SaleModel getCompleteSaleById(int id) {

        SaleModel sale = new SaleModel();
        String sql = "SELECT * FROM clientes join ventas on clientes.id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sale.setId(rs.getInt("id"));
                sale.setIdCliente(rs.getInt("id_cliente"));
                sale.setDate(rs.getDate("date"));
                sale.setTotal(rs.getDouble("total"));
                sale.setCreatedAt(rs.getDate("created_at"));
                sale.setUpdatedAt(rs.getDate("created_at"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return sale;
    }

    @Override
    public boolean updateSale(SaleModel sale) {
        String sql = "UPDATE ventas SET id_cliente=?, total=? WHERE id = ?";
        SaleModel toUpdate = getSaleById(sale.getId());

        if (toUpdate == null) {
            System.out.println("Este usuario no existe");
            return false;
        }

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sale.getIdCliente());
            ps.setDouble(2, sale.getTotal());
            ps.setInt(3, sale.getId());

            ps.execute();
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSale(int id) {
        String sql = "DELETE FROM ventas  WHERE id = ?";
        SaleModel toUpdate = getSaleById(id);

        if (toUpdate == null) {
            System.out.println("Este usuario no existe");
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
    
}
