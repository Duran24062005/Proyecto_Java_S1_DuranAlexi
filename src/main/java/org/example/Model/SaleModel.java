package org.example.Model;

public class SaleModel {
    private int id;
    private int idCliente;
    private String date;
    private double total;
    private String createdAt;
    private String updatedAt;

    public SaleModel() {
    }

    public SaleModel(int idCliente, String date, double total) {
        this.idCliente = idCliente;
        this.date = date;
        this.total = total;
    }

    public SaleModel(int id, int idCliente, String date, double total, String createdAt, String updatedAt) {
        this.id = id;
        this.idCliente = idCliente;
        this.date = date;
        this.total = total;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
        return "VentaModel{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", date='" + date + '\'' +
                ", total=" + total +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}