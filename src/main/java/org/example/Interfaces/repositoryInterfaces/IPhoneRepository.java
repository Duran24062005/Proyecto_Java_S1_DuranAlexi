package org.example.Interfaces.repositoryInterfaces;

import java.util.List;

import org.example.Model.PhoneModel;

public interface IPhoneRepository {
    public boolean addPhone(PhoneModel client);
    public List<PhoneModel> getAllPhones();
    public PhoneModel getPhoneById(int id);
    public boolean updatePhone(PhoneModel client);
    public boolean deletePhone(int id);
}
