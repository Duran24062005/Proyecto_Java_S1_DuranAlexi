package org.example.Interfaces.repositoryInterfaces;

import java.util.List;

import org.example.Model.SaleModel;

public interface ISaleRepository {
    public boolean addSale(SaleModel client);
    public List<SaleModel> getAllSales();
    public SaleModel getSaleById(int id);
    public boolean updateSale(SaleModel client);
    public boolean deleteSale(int id);
}
