package org.example.Model;

import java.sql.Date;

public class SaleModel {
    private int id;
    private int idCliente;
    private Date date;
    private double total;
    private Date createdAt;
    private Date updatedAt;

    public SaleModel() {
    }

    public SaleModel(int idCliente, double total) {
        this.idCliente = idCliente;
        this.total = total;
    }

    public SaleModel(int id, int idCliente, Date date, double total, Date createdAt, Date updatedAt) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
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