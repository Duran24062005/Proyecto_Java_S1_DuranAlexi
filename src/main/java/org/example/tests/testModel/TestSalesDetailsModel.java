package org.example.tests.testModel;

import org.example.Model.SalesDetailsModel;

/**
 * Clase de pruebas para SalesDetailsModel
 * Valida la creación, getters, setters y toString del modelo
 */
public class TestSalesDetailsModel {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   INICIANDO TESTS DE SALESDETAILSMODEL");
        System.out.println("========================================\n");

        testConstructorVacio();
        testConstructorConParametros();
        testConstructorCompleto();
        testGettersYSetters();
        testToString();
        testCalculoSubtotal();

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
            SalesDetailsModel detail = new SalesDetailsModel();
            System.out.println("✓ Constructor vacío creado exitosamente");
            System.out.println("  ID inicial: " + detail.getId());
            System.out.println("  Cantidad inicial: " + detail.getCantidad());
            assert detail.getId() == 0 : "ID debe ser 0 por defecto";
            assert detail.getCantidad() == 0 : "Cantidad debe ser 0 por defecto";
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
            SalesDetailsModel detail = new SalesDetailsModel(1, 1, 1, 4200.00);
            
            System.out.println("✓ Detalle de venta creado:");
            System.out.println("  ID Venta: " + detail.getIdVenta());
            System.out.println("  ID Celular: " + detail.getIdCelular());
            System.out.println("  Cantidad: " + detail.getCantidad());
            System.out.println("  Subtotal: $" + detail.getSubtotal());
            
            assert detail.getIdVenta() == 1 : "ID Venta debe ser 1";
            assert detail.getIdCelular() == 1 : "ID Celular debe ser 1";
            assert detail.getCantidad() == 1 : "Cantidad debe ser 1";
            assert detail.getSubtotal() == 4200.00 : "Subtotal debe ser 4200.00";
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
            SalesDetailsModel detail = new SalesDetailsModel(
                1,
                2,
                3,
                2,
                3600.00,
                "2026-02-05 10:30:00",
                "2026-02-05 10:30:00"
            );
            
            System.out.println("✓ Detalle de venta completo creado:");
            System.out.println("  ID: " + detail.getId());
            System.out.println("  ID Venta: " + detail.getIdVenta());
            System.out.println("  ID Celular: " + detail.getIdCelular());
            System.out.println("  Cantidad: " + detail.getCantidad());
            System.out.println("  Subtotal: $" + detail.getSubtotal());
            System.out.println("  Creado en: " + detail.getCreatedAt());
            
            assert detail.getId() == 1 : "ID debe ser 1";
            assert detail.getIdVenta() == 2 : "ID Venta debe ser 2";
            assert detail.getCreatedAt() != null : "Fecha de creación no debe ser nula";
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
            SalesDetailsModel detail = new SalesDetailsModel();
            
            // Test Setters
            detail.setId(5);
            detail.setIdVenta(3);
            detail.setIdCelular(2);
            detail.setCantidad(2);
            detail.setSubtotal(5200.00);
            detail.setCreatedAt("2026-02-05 11:00:00");
            detail.setUpdatedAt("2026-02-05 11:00:00");
            
            // Test Getters
            System.out.println("✓ Valores asignados mediante setters:");
            System.out.println("  ID: " + detail.getId());
            System.out.println("  ID Venta: " + detail.getIdVenta());
            System.out.println("  ID Celular: " + detail.getIdCelular());
            System.out.println("  Cantidad: " + detail.getCantidad());
            System.out.println("  Subtotal: $" + detail.getSubtotal());
            System.out.println("  Creado en: " + detail.getCreatedAt());
            
            assert detail.getId() == 5 : "ID debe ser 5";
            assert detail.getIdVenta() == 3 : "ID Venta debe ser 3";
            assert detail.getCantidad() == 2 : "Cantidad debe ser 2";
            assert detail.getSubtotal() == 5200.00 : "Subtotal debe ser 5200.00";
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
            SalesDetailsModel detail = new SalesDetailsModel(
                1,
                1,
                1,
                1,
                4200.00,
                "2026-02-05",
                "2026-02-05"
            );
            
            String detailString = detail.toString();
            System.out.println("✓ String generado:");
            System.out.println("  " + detailString);
            
            assert detailString.contains("4200") : "Debe contener el subtotal";
            assert detailString.contains("DetalleVentaModel") : "Debe contener el nombre de la clase";
            System.out.println("  ✓ Validación completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Test 6: Validar cálculo de subtotal
     */
    private static void testCalculoSubtotal() {
        System.out.println("\n[TEST 6] Cálculo de subtotal");
        try {
            System.out.println("✓ Ejemplos de cálculos de subtotal:");
            
            // Ejemplo 1: 1 celular a $4200
            SalesDetailsModel detail1 = new SalesDetailsModel(1, 1, 1, 4200.00);
            System.out.println("  Ejemplo 1: 1 unidad × $4200 = $" + detail1.getSubtotal());
            
            // Ejemplo 2: 2 celulares a $1800
            double precio2 = 1800.00;
            int cantidad2 = 2;
            double subtotal2 = precio2 * cantidad2;
            SalesDetailsModel detail2 = new SalesDetailsModel(3, 3, cantidad2, subtotal2);
            System.out.printf("  Ejemplo 2: %d unidades × $%.2f = $%.2f\n", 
                cantidad2, precio2, detail2.getSubtotal());
            
            // Ejemplo 3: 3 celulares a $5200
            double precio3 = 5200.00;
            int cantidad3 = 3;
            double subtotal3 = precio3 * cantidad3;
            SalesDetailsModel detail3 = new SalesDetailsModel(2, 2, cantidad3, subtotal3);
            System.out.printf("  Ejemplo 3: %d unidades × $%.2f = $%.2f\n", 
                cantidad3, precio3, detail3.getSubtotal());
            
            // Validaciones
            assert detail1.getSubtotal() == 4200.00 : "Subtotal 1 debe ser 4200.00";
            assert detail2.getSubtotal() == 3600.00 : "Subtotal 2 debe ser 3600.00";
            assert detail3.getSubtotal() == 15600.00 : "Subtotal 3 debe ser 15600.00";
            
            // Total de la venta
            double totalVenta = detail1.getSubtotal() + detail2.getSubtotal() + detail3.getSubtotal();
            System.out.printf("\n  Total de venta (sin IVA): $%.2f\n", totalVenta);
            System.out.printf("  Total con IVA (19%%): $%.2f\n", totalVenta * 1.19);
            
            System.out.println("  ✓ Validación de cálculos completada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}