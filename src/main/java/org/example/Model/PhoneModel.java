package org.example.Model;

import java.util.Date;

public class PhoneModel {
    private int id;
    private String brand;
    private String model;
    private String operativeSystem;
    private String rangeCategory;
    private double price;
    private int stock;
    private Date createdAt;
    private Date updatedAt;

    public PhoneModel() {
    }

    public PhoneModel(String brand, String model, String operativeSystem, String rangeCategory, double price, int stock) {
        this.brand = brand;
        this.model = model;
        this.operativeSystem = operativeSystem;
        this.rangeCategory = rangeCategory;
        this.price = price;
        this.stock = stock;
    }

    public PhoneModel(int id, String brand, String model, String operativeSystem, String rangeCategory, double price, int stock, Date createdAt, Date updatedAt) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.operativeSystem = operativeSystem;
        this.rangeCategory = rangeCategory;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOperativeSystem() {
        return operativeSystem;
    }

    public void setOperativeSystem(String operativeSystem) {
        this.operativeSystem = operativeSystem;
    }

    public String getRangeCategory() {
        return rangeCategory;
    }

    public void setRangeCategory(String rangeCategory) {
        this.rangeCategory = rangeCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
        return "PhoneModel{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", operativeSystem='" + operativeSystem + '\'' +
                ", rangeCategory='" + rangeCategory + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}