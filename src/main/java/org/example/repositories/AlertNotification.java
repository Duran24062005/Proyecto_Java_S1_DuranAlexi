package org.example.repositories;

import org.example.service.AlertNotificationService;

public class AlertNotification {

    private final AlertNotificationService alertService;

    public AlertNotification(AlertNotificationService alertServ){
        this.alertService = alertServ;
    }

    public AlertNotificationService getPhoneRepository() {
        return alertService;
    }

    public void stockNotificationLow(){
        System.out.println(alertService.stockNotification());
    }
}
