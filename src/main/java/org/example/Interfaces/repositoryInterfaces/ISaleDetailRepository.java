package org.example.Interfaces.repositoryInterfaces;

import java.util.List;

import org.example.Model.SalesDetailsModel;

public interface ISaleDetailRepository {
    public boolean addSaleDetail(SalesDetailsModel client);
    public List<SalesDetailsModel> getAllSaleDetails();
    public SalesDetailsModel getSaleDetailById(int id);
    public boolean updateSaleDetail(SalesDetailsModel client);
    public boolean deleteSaleDetail(int id);
}
