package org.example.tests.testModel;

import java.sql.Date;

import org.example.Model.SaleModel;

/**
 * Clase de pruebas para SaleModel
 * Valida la creación, getters, setters y toString del modelo
 */
public class TestSaleModel {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   INICIANDO TESTS DE SALEMODEL");
        System.out.println("========================================\n");

        testConstructorVacio();
        testConstructorConParametros();
        testConstructorCompleto();
        testGettersYSetters();
        testToString();
        testCalculosFinancieros();

        System.out.println("\n========================================");
        System.out.println("   TODOS LOS TESTS COMPLETADOS");
        System.out.println("========================================");
    }

    /**
     * Test 1: Validar constructor vacío
     */
    private static void testConstructorVacio() {
        System.out.println("\n[TEST 1] Constructor vacío");
        try {
            SaleModel sale = new SaleModel();
            System.out.println("✓ Constructor vacío creado exitosamente");
            System.out.println("  ID inicial: " + sale.getId());
            System.out.println("  Total inicial: " + sale.getTotal());
            assert sale.getId() == 0 : "ID debe ser 0 por defecto";
            assert sale.getTotal() == 0 : "Total debe ser 0 por defecto";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 2: Validar constructor con parámetros básicos
     */
    private static void testConstructorConParametros() {
        System.out.println("\n[TEST 2] Constructor con parámetros básicos");
        try {
            SaleModel sale = new SaleModel(1, 4200.00);
            
            System.out.println("✓ Venta creada:");
            System.out.println("  ID Cliente: " + sale.getIdCliente());
            System.out.println("  Total: $" + sale.getTotal());
            
            assert sale.getIdCliente() == 1 : "ID Cliente debe ser 1";
            assert sale.getTotal() == 4200.00 : "Total debe ser 4200.00";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 3: Validar constructor completo con fechas
     */
    private static void testConstructorCompleto() {
        System.out.println("\n[TEST 3] Constructor completo con fechas");
        try {
            Date now = new Date(0);

            SaleModel sale = new SaleModel(
                1,
                2,
                now,
                3600.00,
                now,
                now
            );
            
            System.out.println("✓ Venta completa creada:");
            System.out.println("  ID: " + sale.getId());
            System.out.println("  ID Cliente: " + sale.getIdCliente());
            System.out.println("  Total: $" + sale.getTotal());
            System.out.println("  Fecha: " + sale.getDate());
            System.out.println("  Creado en: " + sale.getCreatedAt());
            
            assert sale.getId() == 1 : "ID debe ser 1";
            assert sale.getIdCliente() == 2 : "ID Cliente debe ser 2";
            assert sale.getDate() != null : "Fecha no debe ser nula";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 4: Validar getters y setters
     */
    private static void testGettersYSetters() {
        System.out.println("\n[TEST 4] Getters y Setters");
        try {
            SaleModel sale = new SaleModel();
            Date testDate = new Date(0);
            
            // Test Setters
            sale.setId(5);
            sale.setIdCliente(3);
            sale.setDate(testDate);
            sale.setTotal(5200.00);
            sale.setCreatedAt(testDate);
            sale.setUpdatedAt(testDate);
            
            // Test Getters
            System.out.println("✓ Valores asignados mediante setters:");
            System.out.println("  ID: " + sale.getId());
            System.out.println("  ID Cliente: " + sale.getIdCliente());
            System.out.println("  Total: $" + sale.getTotal());
            System.out.println("  Fecha: " + sale.getDate());
            
            assert sale.getId() == 5 : "ID debe ser 5";
            assert sale.getIdCliente() == 3 : "ID Cliente debe ser 3";
            assert sale.getTotal() == 5200.00 : "Total debe ser 5200.00";
            assert sale.getDate() != null : "Fecha no debe ser nula";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 5: Validar método toString
     */
    private static void testToString() {
        System.out.println("\n[TEST 5] Método toString");
        try {
            SaleModel sale = new SaleModel(2, 3600.00);
            
            String saleString = sale.toString();
            System.out.println("✓ String generado:");
            System.out.println("  " + saleString);
            
            assert saleString.contains("3600") : "Debe contener el total";
            assert saleString.contains("VentaModel") : "Debe contener el nombre de la clase";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 6: Validar cálculos financieros
     */
    private static void testCalculosFinancieros() {
        System.out.println("\n[TEST 6] Cálculos financieros");
        try {
            System.out.println("✓ Pruebas de cálculos con IVA (19%):");
            
            // Subtotal sin IVA
            double subtotal = 1000.00;
            double ivaRate = 0.19;
            double totalWithIVA = subtotal * (1 + ivaRate);
            
            SaleModel sale = new SaleModel(1, totalWithIVA);
            
            System.out.println("  Subtotal (sin IVA): $" + subtotal);
            System.out.println("  IVA (19%): $" + (subtotal * ivaRate));
            System.out.println("  Total (con IVA): $" + totalWithIVA);
            System.out.println("  Total en modelo: $" + sale.getTotal());
            
            assert sale.getTotal() == totalWithIVA : "El total debe incluir IVA";
            
            // Más ejemplos
            System.out.println("\n  Ejemplos adicionales:");
            double[] subtotals = {500.00, 2000.00, 5000.00};
            for (double sub : subtotals) {
                double total = sub * (1 + ivaRate);
                System.out.printf("    Subtotal: $%.2f → Total con IVA: $%.2f\n", sub, total);
            }
            
            System.out.println("  ✓ Validación de cálculos completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}