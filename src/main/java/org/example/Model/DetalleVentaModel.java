package org.example.Model;

public class DetalleVentaModel {
    private int id;
    private int idVenta;
    private int idCelular;
    private int cantidad;
    private double subtotal;
    private String createdAt;
    private String updatedAt;

    public DetalleVentaModel() {
    }

    public DetalleVentaModel(int idVenta, int idCelular, int cantidad, double subtotal) {
        this.idVenta = idVenta;
        this.idCelular = idCelular;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public DetalleVentaModel(int id, int idVenta, int idCelular, int cantidad, double subtotal, String createdAt, String updatedAt) {
        this.id = id;
        this.idVenta = idVenta;
        this.idCelular = idCelular;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCelular() {
        return idCelular;
    }

    public void setIdCelular(int idCelular) {
        this.idCelular = idCelular;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "DetalleVentaModel{" +
                "id=" + id +
                ", idVenta=" + idVenta +
                ", idCelular=" + idCelular +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}