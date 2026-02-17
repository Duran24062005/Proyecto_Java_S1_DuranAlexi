package org.example.service;

import java.util.List;

import org.example.Model.PhoneModel;
import org.example.repositories.PhoneRepository;

public class AlertNotificationService {

    private final PhoneRepository phoneRepository;

    public AlertNotificationService(PhoneRepository phoneRepo){
        this.phoneRepository = phoneRepo;
    }

    public PhoneRepository getPhoneRepository() {
        return phoneRepository;
    }


    /**
     * @return
     */
    public List<PhoneModel> stockNotification(){
       List<PhoneModel > phones = phoneRepository.getAllPhones();
       return  phones.stream().filter(p -> p.getStock() < 5 ).toList();

    }
}
