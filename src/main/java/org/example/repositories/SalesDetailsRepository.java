package org.example.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.Config.DatabaseConnection;
import org.example.Interfaces.repositoryInterfaces.ISaleDetailRepository;
import org.example.Model.SalesDetailsModel;

public class SalesDetailsRepository implements ISaleDetailRepository {

    @Override
    public boolean addSaleDetail(SalesDetailsModel saleDetail) {
        String sql = "INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, saleDetail.getIdVenta());
            ps.setInt(2, saleDetail.getIdCelular());
            ps.setInt(3, saleDetail.getCantidad());
            ps.setDouble(4, saleDetail.getSubtotal());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<SalesDetailsModel> getAllSaleDetails() {
        List<SalesDetailsModel> saleDetails = new ArrayList<>();
        String sql = "SELECT * FROM detalle_ventas";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SalesDetailsModel detail = new SalesDetailsModel(
                    rs.getInt("id"),
                    rs.getInt("id_venta"),
                    rs.getInt("id_celular"),
                    rs.getInt("cantidad"),
                    rs.getDouble("subtotal"),
                    rs.getString("created_at"),
                    rs.getString("updated_at")
                );
                saleDetails.add(detail);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return saleDetails;
    }

    @Override
    public SalesDetailsModel getSaleDetailById(int id) {
        SalesDetailsModel detail = new SalesDetailsModel();
        String sql = "SELECT * FROM detalle_ventas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                detail.setId(rs.getInt("id"));
                detail.setIdVenta(rs.getInt("id_venta"));
                detail.setIdCelular(rs.getInt("id_celular"));
                detail.setCantidad(rs.getInt("cantidad"));
                detail.setSubtotal(rs.getDouble("subtotal"));
                detail.setCreatedAt(rs.getString("created_at"));
                detail.setUpdatedAt(rs.getString("updated_at"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return detail;
    }

    @Override
    public boolean updateSaleDetail(SalesDetailsModel saleDetail) {
        String sql = "UPDATE detalle_ventas SET id_venta=?, id_celular=?, cantidad=?, subtotal=? WHERE id = ?";
        SalesDetailsModel toUpdate = getSaleDetailById(saleDetail.getId());

        if (toUpdate == null || toUpdate.getId() == 0) {
            System.out.println("Este detalle de venta no existe");
            return false;
        }

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, saleDetail.getIdVenta());
            ps.setInt(2, saleDetail.getIdCelular());
            ps.setInt(3, saleDetail.getCantidad());
            ps.setDouble(4, saleDetail.getSubtotal());
            ps.setInt(5, saleDetail.getId());

            ps.execute();
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSaleDetail(int id) {
        String sql = "DELETE FROM detalle_ventas WHERE id = ?";
        SalesDetailsModel toDelete = getSaleDetailById(id);

        if (toDelete == null || toDelete.getId() == 0) {
            System.out.println("Este detalle de venta no existe");
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
     * Obtiene todos los detalles de venta asociados a una venta específica
     * @param saleId ID de la venta
     * @return Lista de detalles de la venta
     */
    public List<SalesDetailsModel> getSaleDetailsBySaleId(int saleId) {
        List<SalesDetailsModel> saleDetails = new ArrayList<>();
        String sql = "SELECT * FROM detalle_ventas WHERE id_venta = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, saleId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SalesDetailsModel detail = new SalesDetailsModel(
                    rs.getInt("id"),
                    rs.getInt("id_venta"),
                    rs.getInt("id_celular"),
                    rs.getInt("cantidad"),
                    rs.getDouble("subtotal"),
                    rs.getString("created_at"),
                    rs.getString("updated_at")
                );
                saleDetails.add(detail);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return saleDetails;
    }

    /**
     * Obtiene los detalles de venta con información completa (celular y venta)
     * @return Lista de detalles de venta con información completa
     */
    public List<SalesDetailsModel> getAllCompleteSaleDetails() {
        List<SalesDetailsModel> saleDetails = new ArrayList<>();
        String sql = "SELECT dv.* FROM detalle_ventas dv " +
                     "JOIN ventas v ON dv.id_venta = v.id " +
                     "JOIN celulares c ON dv.id_celular = c.id";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SalesDetailsModel detail = new SalesDetailsModel(
                    rs.getInt("id"),
                    rs.getInt("id_venta"),
                    rs.getInt("id_celular"),
                    rs.getInt("cantidad"),
                    rs.getDouble("subtotal"),
                    rs.getString("created_at"),
                    rs.getString("updated_at")
                );
                saleDetails.add(detail);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return saleDetails;
    }

    /**
     * Obtiene el celular más vendido según la cantidad total vendida
     * @return ID del celular más vendido, 0 si no hay datos
     */
    public int getMostSoldPhone() {
        String sql = "SELECT id_celular FROM detalle_ventas GROUP BY id_celular ORDER BY SUM(cantidad) DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("id_celular");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Obtiene los top 3 celulares más vendidos
     * @return Lista con los IDs de los 3 celulares más vendidos
     */
    public List<Integer> getTop3MostSoldPhones() {
        List<Integer> topPhones = new ArrayList<>();
        String sql = "SELECT id_celular FROM detalle_ventas GROUP BY id_celular ORDER BY SUM(cantidad) DESC LIMIT 3";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                topPhones.add(rs.getInt("id_celular"));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return topPhones;
    }

    /**
     * Calcula las ventas totales de un celular específico
     * @param phoneId ID del celular
     * @return Total de ventas del celular
     */
    public double getTotalSalesByPhone(int phoneId) {
        String sql = "SELECT SUM(subtotal) as total FROM detalle_ventas WHERE id_celular = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, phoneId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return 0.0;
    }
}