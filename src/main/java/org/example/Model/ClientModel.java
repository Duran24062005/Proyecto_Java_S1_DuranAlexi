package org.example.Model;

import java.util.Date;

public class ClientModel {
    private int id;
    private String name;
    private String dni;
    private String email;
    private String phone;
    private Date createdAt;
    private Date updatedAt;

    public ClientModel() {
    }

    public ClientModel(String name, String dni, String email, String phone) {
        this.name = name;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
    }

    public ClientModel(int id, String name, String dni, String email, String phone) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
    }

    public ClientModel(int id, String name, String dni, String email, String phone, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "ClientModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
