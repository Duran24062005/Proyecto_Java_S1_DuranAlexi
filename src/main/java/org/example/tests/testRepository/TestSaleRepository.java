package org.example.tests.testRepository;

import org.example.Config.DatabaseConnection;
import org.example.repository.SaleRepository;

public class TestSaleRepository {
    public static void main(String[] args) {
        System.out.println("\n");
        DatabaseConnection.getConnection();
        SaleRepository repo = new SaleRepository();

        // Create new client
        // SaleModel client = new SaleModel(1, 30000.0);
        // repo.addSale(client);
        
        // Show all clients
        // System.out.println("\n");
        // System.out.println(repo.getAllSales());

        // Get all complete
        System.out.println(repo.getAllCompleteSales());

        // Find client by id
        // System.out.println("\n");
        // System.out.println(repo.getSaleById(5));

        // Update client
        // SaleModel clientToUp = new SaleModel(7, 324086.0);
        // repo.updateSale(clientToUp);

        // Delete client
        // repo.deleteSale(7);
        // System.out.println(repo.getAllSales());
    }
}


/*[
    VentaModel{id=1, idCliente=1, date='2026-02-05', total=4200.0, createdAt='2026-02-05', updatedAt='2026-02-05'}, 
    VentaModel{id=2, idCliente=2, date='2026-02-05', total=3600.0, createdAt='2026-02-05', updatedAt='2026-02-05'}, 
    VentaModel{id=3, idCliente=3, date='2026-02-05', total=5200.0, createdAt='2026-02-05', updatedAt='2026-02-05'}, 
    VentaModel{id=1, idCliente=1, date='2026-02-05', total=30000.0, createdAt='2026-02-05', updatedAt='2026-02-05'}
]*/