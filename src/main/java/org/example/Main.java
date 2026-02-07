package org.example;

import org.example.Config.DatabaseConnection;
import org.example.service.ClientService;
import org.example.service.PhoneService;
import org.example.service.ReportService;
import org.example.service.SaleService;

public class Main {
    public static void main(String[] args) {

        DatabaseConnection.getConnection();
        SaleService saleService = new SaleService();
        PhoneService phoneService = new PhoneService();
        ClientService clientService = new ClientService();
        ReportService service = new ReportService(saleService, phoneService, clientService);
        service.generateSalesReport();
        
    }
}