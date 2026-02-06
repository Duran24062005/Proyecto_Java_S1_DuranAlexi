package org.example.service;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.Model.PhoneModel;
import org.example.Model.SaleModel;
import org.example.Model.SalesDetailsModel;
import org.example.repository.PhoneRepository;
import org.example.repository.SaleRepository;
import org.example.repository.SalesDetailsRepository;

public class SaleService {
    private final SaleRepository saleRepository;
    public final SalesDetailsRepository saleDetailsRepository;
    private final PhoneRepository phoneRepository;
    private final PhoneService phoneService;
    private final ClientService clientService;
    
    private static final double IVA_RATE = 0.19; // 19% IVA

    public SaleService() {
        this.saleRepository = new SaleRepository();
        this.saleDetailsRepository = new SalesDetailsRepository();
        this.phoneRepository = new PhoneRepository();
        this.phoneService = new PhoneService();
        this.clientService = new ClientService();
    }

    /**
     * Registra una nueva venta con items
     * @param clientId ID del cliente
     * @param items Lista de items de venta (phoneId, cantidad)
     * @return true si se registra correctamente
     */
    public boolean registerSale(int clientId, List<Map<String, Integer>> items) {
        // Validar cliente
        if (!clientService.clientExists(clientId)) {
            System.out.println("Error: El cliente no existe");
            return false;
        }
        
        // Validar items
        if (items == null || items.isEmpty()) {
            System.out.println("Error: La venta debe contener al menos un artículo");
            return false;
        }
        
        // Validar disponibilidad de stock
        for (Map<String, Integer> item : items) {
            int phoneId = item.get("phoneId");
            int quantity = item.get("quantity");
            
            PhoneModel phone = phoneService.getPhoneById(phoneId);
            if (phone == null) {
                System.out.println("Error: El celular con ID " + phoneId + " no existe");
                return false;
            }
            
            if (phone.getStock() < quantity) {
                System.out.println("Error: Stock insuficiente para " + phone.getBrand() + " " + phone.getModel());
                return false;
            }
        }
        
        // Calcular total
        double subtotal = 0;
        for (Map<String, Integer> item : items) {
            int phoneId = item.get("phoneId");
            int quantity = item.get("quantity");
            
            PhoneModel phone = phoneRepository.getPhoneById(phoneId);
            subtotal += phone.getPrice() * quantity;
        }
        
        // Aplicar IVA
        double total = subtotal * (1 + IVA_RATE);
        
        // Crear venta
        SaleModel sale = new SaleModel(clientId, total);
        if (!saleRepository.addSale(sale)) {
            System.out.println("Error: No se pudo registrar la venta");
            return false;
        }
        
        // Obtener ID de la venta creada
        List<SaleModel> allSales = saleRepository.getAllSales();
        SaleModel newSale = allSales.get(allSales.size() - 1);
        
        // Registrar detalles de venta
        for (Map<String, Integer> item : items) {
            int phoneId = item.get("phoneId");
            int quantity = item.get("quantity");
            
            PhoneModel phone = phoneRepository.getPhoneById(phoneId);
            double itemSubtotal = phone.getPrice() * quantity;
            
            SalesDetailsModel detail = new SalesDetailsModel(
                newSale.getId(),
                phoneId,
                quantity,
                itemSubtotal
            );
            
            if (!saleDetailsRepository.addSaleDetail(detail)) {
                System.out.println("Error: No se pudo registrar el detalle de venta");
                return false;
            }
            
            // Reducir stock
            if (!phoneService.reducePhoneStock(phoneId, quantity)) {
                System.out.println("Error: No se pudo actualizar el stock");
                return false;
            }
        }
        
        return true;
    }

    /**
     * Obtiene todas las ventas
     * @return Lista de todas las ventas
     */
    public List<SaleModel> getAllSales() {
        return saleRepository.getAllSales();
    }

    /**
     * Obtiene una venta por su ID
     * @param id ID de la venta
     * @return Venta encontrada, o null si no existe
     */
    public SaleModel getSaleById(int id) {
        SaleModel sale = saleRepository.getSaleById(id);
        if (sale == null || sale.getId() == 0) {
            System.out.println("Error: La venta con ID " + id + " no existe");
            return null;
        }
        return sale;
    }

    /**
     * Obtiene todas las ventas de un cliente
     * @param clientId ID del cliente
     * @return Lista de ventas del cliente
     */
    public List<SaleModel> getSalesByClientId(int clientId) {
        return saleRepository.getAllSales().stream()
            .filter(s -> s.getIdCliente() == clientId)
            .collect(Collectors.toList());
    }

    /**
     * Obtiene los detalles de una venta
     * @param saleId ID de la venta
     * @return Lista de detalles de la venta
     */
    public List<SalesDetailsModel> getSaleDetails(int saleId) {
        return saleDetailsRepository.getSaleDetailsBySaleId(saleId);
    }

    /**
     * Calcula el total de ventas
     * @return Total de ventas
     */
    public double getTotalSales() {
        return saleRepository.getAllSales().stream()
            .mapToDouble(SaleModel::getTotal)
            .sum();
    }

    /**
     * Calcula el total de ventas sin IVA
     * @return Total de ventas sin IVA
     */
    public double getTotalSalesWithoutIVA() {
        return saleRepository.getAllSales().stream()
            .mapToDouble(sale -> sale.getTotal() / (1 + IVA_RATE))
            .sum();
    }

    /**
     * Calcula el total recaudado por IVA
     * @return Total de IVA
     */
    public double getTotalIVA() {
        return getTotalSales() - getTotalSalesWithoutIVA();
    }

    /**
     * Obtiene la venta más grande
     * @return Venta con el total más alto
     */
    public SaleModel getBiggestSale() {
        return saleRepository.getAllSales().stream()
            .max((s1, s2) -> Double.compare(s1.getTotal(), s2.getTotal()))
            .orElse(null);
    }

    /**
     * Obtiene la venta más pequeña
     * @return Venta con el total más bajo
     */
    public SaleModel getSmallestSale() {
        return saleRepository.getAllSales().stream()
            .min((s1, s2) -> Double.compare(s1.getTotal(), s2.getTotal()))
            .orElse(null);
    }

    /**
     * Calcula el promedio de ventas
     * @return Promedio de venta
     */
    public double getAverageSale() {
        List<SaleModel> sales = saleRepository.getAllSales();
        if (sales.isEmpty()) {
            return 0.0;
        }
        return sales.stream()
            .mapToDouble(SaleModel::getTotal)
            .average()
            .orElse(0.0);
    }

    /**
     * Obtiene los top 3 celulares más vendidos
     * @return Lista con información de los 3 celulares más vendidos
     */
    public List<Map<String, Object>> getTop3MostSoldPhones() {
        List<Integer> topPhoneIds = saleDetailsRepository.getTop3MostSoldPhones();
        return topPhoneIds.stream()
            .map(phoneId -> {
                PhoneModel phone = phoneRepository.getPhoneById(phoneId);
                int totalQuantity = saleDetailsRepository.getSaleDetailsBySaleId(0).stream()
                    .filter(d -> d.getIdCelular() == phoneId)
                    .mapToInt(SalesDetailsModel::getCantidad)
                    .sum();
                
                Map<String, Object> phoneInfo = Map.of(
                    "phone", phone,
                    "totalSold", totalQuantity,
                    "totalRevenue", saleDetailsRepository.getTotalSalesByPhone(phoneId)
                );
                return phoneInfo;
            })
            .collect(Collectors.toList());
    }

    /**
     * Obtiene las ventas totales por mes
     * @return Mapa con mes y total de ventas
     */
    public Map<String, Double> getSalesByMonth() {
        return saleRepository.getAllSales().stream()
            .collect(Collectors.groupingBy(
                sale -> sale.getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate()
                    .withDayOfMonth(1)
                    .toString(),
                Collectors.summingDouble(SaleModel::getTotal)
            ));
    }

    /**
     * Obtiene el total de ventas de un mes específico
     * @param year Año
     * @param month Mes (1-12)
     * @return Total de ventas del mes
     */
    public double getSalesByMonth(int year, int month) {
        YearMonth targetMonth = YearMonth.of(year, month);
        return saleRepository.getAllSales().stream()
            .filter(sale -> {
                java.time.LocalDate date = sale.getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
                return YearMonth.from(date).equals(targetMonth);
            })
            .mapToDouble(SaleModel::getTotal)
            .sum();
    }

    /**
     * Obtiene el número total de ventas
     * @return Cantidad de ventas
     */
    public int countSales() {
        return saleRepository.getAllSales().size();
    }

    /**
     * Obtiene el número total de artículos vendidos
     * @return Cantidad total de artículos
     */
    public int getTotalItemsSold() {
        return saleDetailsRepository.getAllSaleDetails().stream()
            .mapToInt(SalesDetailsModel::getCantidad)
            .sum();
    }

    /**
     * Obtiene el cliente con más compras
     * @return ID del cliente con más compras
     */
    public Integer getClientWithMostPurchases() {
        return saleRepository.getAllSales().stream()
            .collect(Collectors.groupingBy(
                SaleModel::getIdCliente,
                Collectors.counting()
            ))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    /**
     * Calcula el gasto promedio por cliente
     * @return Gasto promedio
     */
    public double getAverageSpendingPerClient() {
        Map<Integer, Double> clientSpending = saleRepository.getAllSales().stream()
            .collect(Collectors.groupingBy(
                SaleModel::getIdCliente,
                Collectors.summingDouble(SaleModel::getTotal)
            ));
        
        if (clientSpending.isEmpty()) {
            return 0.0;
        }
        
        return clientSpending.values().stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0);
    }

    /**
     * Obtiene el cliente que ha gastado más dinero
     * @return ID del cliente con mayor gasto total
     */
    public Integer getClientWithHighestSpending() {
        return saleRepository.getAllSales().stream()
            .collect(Collectors.groupingBy(
                SaleModel::getIdCliente,
                Collectors.summingDouble(SaleModel::getTotal)
            ))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }
}