# GUÍA TÉCNICA DETALLADA - TECNOSTORE

**Versión:** 1.0  
**Fecha:** Febrero 2026

---

## TABLA DE CONTENIDOS

1. [Flujos de Casos de Uso](#flujos-de-casos-de-uso)
2. [Ejemplos de Código](#ejemplos-de-código)
3. [Flujo de Datos Detallado](#flujo-de-datos-detallado)
4. [Cálculos y Fórmulas](#cálculos-y-fórmulas)
5. [Validaciones Implementadas](#validaciones-implementadas)
6. [Integración de Capas](#integración-de-capas)
7. [Ciclo de Vida de una Venta](#ciclo-de-vida-de-una-venta)
8. [Análisis y Reportería](#análisis-y-reportería)

---

## FLUJOS DE CASOS DE USO

### Caso de Uso: Registrar Nuevo Cliente

**Flujo Principal:**

1. Usuario selecciona "1. Gestionar Clientes" en menú principal
2. Se muestra submenú de cliente
3. Usuario selecciona "1. Crear cliente"
4. Sistema solicita:
   - Nombre: "Carlos Perez"
   - DNI: "1002345678"
   - Email: "carlos@mail.com"
   - Teléfono: "3001234567"

5. ClientController llama ClientService.registerClient()
6. ClientService valida:
   - Nombre no vacío
   - DNI único en BD
   - Email formato válido
   - Email único en BD
   - Teléfono no vacío

7. Si pasa validaciones:
   - ClientService llama ClientRepository.addClient()
   - Repository ejecuta INSERT en BD
   - Retorna true/false
   - Muestra mensaje de éxito

8. Si falla validación:
   - Muestra error específico
   - Permite reintentar

**Precondiciones:**

- Conexión a base de datos activa
- Usuario autenticado

**Postcondiciones:**

- Cliente registrado en BD
- Timestamps de creación establecidos

---

### Caso de Uso: Registrar Venta Multi-artículo

**Flujo Principal:**

1. Usuario selecciona "3. Generar venta"
2. Sistema solicita ID del cliente
3. Usuario selecciona cliente de lista mostrada
4. Sistema valida que cliente existe
5. Sistema muestra listado de celulares disponibles
6. Usuario selecciona celular y cantidad
7. Sistema valida:
   - Celular existe
   - Cantidad <= stock disponible
   - No está duplicado en venta
8. Agrega a lista de items (puede repetir)
9. Usuario ingresa "0" para terminar
10. Sistema calcula:
    - Subtotal por artículo (precio × cantidad)
    - Subtotal total (suma de items)
    - IVA (subtotal × 0.19)
    - Total (subtotal × 1.19)
11. Muestra confirmación con detalles
12. Usuario confirma con "S"
13. Sistema:
    - Inserta venta en tabla VENTAS
    - Inserta detalles en DETALLE_VENTAS
    - Reduce stock de cada celular
    - Retorna confirmación

**Cálculos Automáticos:**

```
Item 1: Samsung Galaxy S23 × 1 = $4,200.00
Item 2: Xiaomi Redmi Note 12 × 2 = $3,600.00
─────────────────────────────────
Subtotal: $7,800.00
IVA (19%): $1,482.00
─────────────────────────────────
TOTAL: $9,282.00
```

**Validaciones:**

- Cliente debe existir
- Celular debe existir
- Stock debe ser suficiente
- No duplicar artículos en una venta
- Cantidad debe ser positiva

---

## EJEMPLOS DE CÓDIGO

### Ejemplo 1: Registrar Cliente (Servicio)

```java
public boolean registerClient(ClientModel client) {
    // Validar datos
    if (!validateClient(client)) {
        return false;
    }

    // Llamar al repositorio
    return clientRepository.addClient(client);
}

private boolean validateClient(ClientModel client) {
    // Validar nombre
    if (client.getName() == null ||
        client.getName().trim().isEmpty()) {
        System.out.println("Error: El nombre es requerido");
        return false;
    }

    // Validar DNI único
    if (isDniDuplicated(client.getDni(), client.getId())) {
        System.out.println("Error: DNI ya existe");
        return false;
    }

    // Validar email
    if (!isValidEmail(client.getEmail())) {
        System.out.println("Error: Email inválido");
        return false;
    }

    return true;
}

private boolean isValidEmail(String email) {
    String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
    return Pattern.compile(regex).matcher(email).matches();
}
```

### Ejemplo 2: Registrar Venta (Controlador)

```java
public void createNewSale() {
    // Seleccionar cliente
    List<ClientModel> clients = clientService.getAllClients();
    ClientModel selectedClient = clients.get(clientIndex - 1);

    // Seleccionar celulares
    List<PhoneModel> phones = phoneService.getAllPhones();
    List<Map<String, Integer>> items = new ArrayList<>();

    while (addingItems) {
        int phoneIndex = scanner.nextInt() - 1;
        if (phoneIndex == -1) break;

        PhoneModel phone = phones.get(phoneIndex);
        int quantity = scanner.nextInt();

        // Validar stock
        if (quantity > phone.getStock()) {
            System.out.println("Stock insuficiente");
            continue;
        }

        Map<String, Integer> item = new HashMap<>();
        item.put("phoneId", phone.getId());
        item.put("quantity", quantity);
        items.add(item);
    }

    // Registrar venta
    boolean registered = saleService.registerSale(
        selectedClient.getId(),
        items
    );

    if (registered) {
        System.out.println("Venta registrada exitosamente");
    }
}
```

### Ejemplo 3: Cálculo de IVA en Servicio

```java
private static final double IVA_RATE = 0.19; // 19%

public boolean registerSale(int clientId,
                            List<Map<String, Integer>> items) {
    // Calcular subtotal
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
    saleRepository.addSale(sale);

    // Actualizar stock
    for (Map<String, Integer> item : items) {
        phoneService.reducePhoneStock(
            item.get("phoneId"),
            item.get("quantity")
        );
    }

    return true;
}
```

### Ejemplo 4: Validación de Email (Servicio)

```java
private static final String EMAIL_REGEX =
    "^[A-Za-z0-9+_.-]+@(.+)$";
private static final Pattern EMAIL_PATTERN =
    Pattern.compile(EMAIL_REGEX);

private boolean isValidEmail(String email) {
    if (email == null || email.trim().isEmpty()) {
        return false;
    }
    return EMAIL_PATTERN.matcher(email).matches();
}

// Ejemplos de emails válidos e inválidos
// Válidos:
//   user@example.com
//   test.email@domain.co.uk
//   info+tag@company.com
//
// Inválidos:
//   invalid (sin @)
//   test@ (sin dominio)
//   @domain.com (sin usuario)
```

### Ejemplo 5: Consulta Especializada (Repositorio)

```java
public List<PhoneModel> getPhonesLowStock() {
    List<PhoneModel> phones = new ArrayList<>();
    String sql = "SELECT * FROM celulares WHERE stock < 5";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            PhoneModel phone = new PhoneModel(
                rs.getInt("id"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getString("operative_sistem"),
                rs.getString("range_category"),
                rs.getDouble("price"),
                rs.getInt("stock"),
                rs.getDate("created_at"),
                rs.getDate("updated_at")
            );
            phones.add(phone);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return phones;
}
```

### Ejemplo 6: Generación de Reporte

```java
public boolean generateSalesReport() {
    String filename = "reporte_ventas_" +
        LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")
        ) + ".txt";

    try (BufferedWriter writer =
         new BufferedWriter(new FileWriter(filename))) {

        writer.write("========================================\n");
        writer.write("REPORTE DE VENTAS - TECNOSTORE\n");
        writer.write("========================================\n");
        writer.write("Generado: " +
            LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            ) + "\n\n");

        // Escribir datos
        List<SaleModel> sales = saleService.getAllSales();
        for (SaleModel sale : sales) {
            writer.write(String.format(
                "Venta ID: %d | Total: $%.2f\n",
                sale.getId(),
                sale.getTotal()
            ));
        }

        writer.write("\n========================================\n");
        writer.write("Fin del reporte\n");
        writer.write("========================================\n");

        return true;
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
        return false;
    }
}
```

---

## FLUJO DE DATOS DETALLADO

### Flujo: Crear Cliente

```
Usuario (Consola)
        ↓
    MainMenu
        ↓
    ClientController
    .createNewClient()
        ↓
    ClientView
    .createNewClient()
        ↓ (retorna ClientModel)
    ClientService
    .registerClient(client)
        ↓
    Validaciones:
    - Nombre no vacío
    - DNI único
    - Email válido
    - Email único
        ↓ (si pasan)
    ClientRepository
    .addClient(client)
        ↓
    SQL: INSERT INTO clientes(...)
        ↓
    MySQL (tecnostore_db)
    tabla: clientes
        ↓
    true/false
        ↓ (retorna)
    Mensaje en consola
        ↓
    Usuario ve confirmación
```

### Flujo: Registrar Venta

```
Usuario (Console)
        ↓
    SaleController
    .createNewSale()
        ↓
    SaleView (muestra clientes)
        ↓
    Cliente seleccionado
        ↓
    SaleView (muestra celulares)
        ↓
    Items agregados por usuario
        ↓
    SaleService
    .registerSale(clientId, items)
        ↓
    Validaciones:
    - Cliente existe
    - Celulares existen
    - Stock disponible
        ↓ (si pasan)
    Cálculos:
    Subtotal = Σ(precio × cantidad)
    IVA = Subtotal × 0.19
    Total = Subtotal × 1.19
        ↓
    SaleRepository
    .addSale(saleModel)
        ↓
    SQL: INSERT INTO ventas(...)
        ↓
    MySQL: tabla ventas
        ↓
    Para cada item:
        ↓
    SalesDetailsRepository
    .addSaleDetail(detail)
        ↓
    SQL: INSERT INTO detalle_ventas(...)
        ↓
    MySQL: tabla detalle_ventas
        ↓
    PhoneService
    .reducePhoneStock(phoneId, qty)
        ↓
    PhoneRepository
    .updatePhoneStock(phoneId, newStock)
        ↓
    SQL: UPDATE celulares SET stock=...
        ↓
    MySQL: tabla celulares
        ↓
    Confirmación en consola
        ↓
    Usuario ve resumen
```

---

## CÁLCULOS Y FÓRMULAS

### Cálculo de IVA (19%)

**Fórmula Base:**

```
IVA_RATE = 0.19
Subtotal = Precio × Cantidad
Total = Subtotal × (1 + IVA_RATE)
IVA = Total - Subtotal
```

**Ejemplo Numérico:**

```
Precio unitario: $1,000.00
Cantidad: 2 unidades

Subtotal = $1,000 × 2 = $2,000.00
IVA (19%) = $2,000 × 0.19 = $380.00
Total = $2,000 + $380 = $2,380.00

O también: $2,000 × 1.19 = $2,380.00
```

**Validación Inversa:**

```
Total con IVA = $2,380.00
Subtotal = Total ÷ 1.19 = $2,000.00
IVA = $2,380 - $2,000 = $380.00
```

### Cálculo de Promedio de Venta

```
Total de Ventas = $50,000.00
Número de Ventas = 5
Promedio = $50,000 / 5 = $10,000.00
```

### Estadísticas de Stock

```
Stock Total = Σ stock de todos los celulares
Stock Promedio = Stock Total / Cantidad de modelos

Ejemplo:
Samsung: 15 unidades
Apple: 10 unidades
Xiaomi: 30 unidades
Motorola: 25 unidades
Huawei: 12 unidades
─────────────────
Total: 92 unidades
Promedio: 92 / 5 = 18.4 unidades
```

### Gasto Promedio por Cliente

```
Total de Ventas = $100,000.00
Número de Clientes Únicos = 4
Gasto Promedio = $100,000 / 4 = $25,000.00
```

---

## VALIDACIONES IMPLEMENTADAS

### Validación de Cliente

| Campo    | Regla           | Mensaje                           |
| -------- | --------------- | --------------------------------- |
| Nombre   | No vacío        | "El nombre es requerido"          |
| DNI      | Único, no vacío | "DNI ya existe" o "DNI requerido" |
| Email    | Formato válido  | "Email inválido"                  |
| Email    | Único           | "Email ya en uso"                 |
| Teléfono | No vacío        | "Teléfono requerido"              |

### Validación de Celular

| Campo  | Regla           | Mensaje                       |
| ------ | --------------- | ----------------------------- |
| Marca  | No vacía        | "Marca requerida"             |
| Modelo | No vacío        | "Modelo requerido"            |
| Precio | > 0             | "Precio debe ser positivo"    |
| Stock  | >= 0            | "Stock no puede ser negativo" |
| SO     | No vacío        | "Sistema operativo requerido" |
| Gama   | Alta/Media/Baja | "Gama inválida"               |

### Validación de Venta

| Elemento   | Regla         | Validación                          |
| ---------- | ------------- | ----------------------------------- |
| Cliente    | Debe existir  | SELECT \* FROM clientes WHERE id=?  |
| Celular    | Debe existir  | SELECT \* FROM celulares WHERE id=? |
| Stock      | Suficiente    | stock >= cantidad solicitada        |
| Cantidad   | Positiva      | cantidad > 0                        |
| Duplicados | No permitidos | Verificar items en venta            |

### Patrón Regex Email

```java
Patrón: ^[A-Za-z0-9+_.-]+@(.+)$

Explicación:
^ = inicio
[A-Za-z0-9+_.-]+ = caracteres válidos (al menos 1)
@ = símbolo @
(.+) = dominio (al menos 1 carácter)
$ = fin

Ejemplos válidos:
  user@example.com
  test.email@company.co.uk
  info+tag@domain.com
  user_name@domain.com

Ejemplos inválidos:
  invalid (sin @)
  user@ (sin dominio)
  @domain.com (sin usuario)
  user@.com (dominio inválido)
```

---

## INTEGRACIÓN DE CAPAS

### Diagrama de Interacción: Registrar Cliente

```
┌────────────┐
│   View     │
│ClientView  │
└──────┬─────┘
       │ createNewClient()
       ▼
┌─────────────────────┐
│   Controller        │
│ClientController     │
├─────────────────────┤
│- clientService      │
│- clientView         │
└──────┬──────────────┘
       │ registerClient()
       ▼
┌─────────────────────┐
│    Service          │
│ClientService        │
├─────────────────────┤
│- validateClient()   │
│- isDniDuplicated()  │
│- isValidEmail()     │
│- clientRepository   │
└──────┬──────────────┘
       │ addClient()
       ▼
┌─────────────────────┐
│   Repository        │
│ClientRepository     │
├─────────────────────┤
│- preparedStatement  │
│- executeUpdate()    │
└──────┬──────────────┘
       │ INSERT SQL
       ▼
┌─────────────────────┐
│   Database          │
│MySQL tecnostore_db  │
├─────────────────────┤
│  Tabla: clientes    │
└─────────────────────┘
```

### Transacción de Venta (Múltiples Tablas)

```
SaleService.registerSale(clientId, items)
    │
    ├─ SaleRepository.addSale()
    │  └─ INSERT INTO ventas → obtiene sale.id
    │
    ├─ Para cada item:
    │  │
    │  ├─ SalesDetailsRepository.addSaleDetail()
    │  │  └─ INSERT INTO detalle_ventas
    │  │
    │  └─ PhoneRepository.updatePhoneStock()
    │     └─ UPDATE celulares SET stock = stock - cantidad
    │
    └─ Retorna true/false
```

---

## CICLO DE VIDA DE UNA VENTA

### Estado 1: Preparación

1. Usuario inicia proceso de venta
2. Selecciona cliente
3. Valida existencia de cliente en BD

### Estado 2: Selección de Artículos

1. Obtiene listado de celulares
2. Usuario selecciona celular y cantidad
3. Valida:
   - Celular existe
   - Stock disponible
   - No duplicado

### Estado 3: Cálculo

1. Calcula subtotal por artículo (precio × cantidad)
2. Suma todos los subtotales
3. Calcula IVA: subtotal × 0.19
4. Calcula total: subtotal × 1.19

### Estado 4: Confirmación

1. Muestra resumen de venta
2. Usuario confirma con "S"
3. Si confirma → Estado 5
4. Si cancela → Vuelve a Estado 2

### Estado 5: Procesamiento

1. Inserta venta en tabla VENTAS
2. Para cada item:
   - Inserta en DETALLE_VENTAS
   - Actualiza stock en CELULARES
3. Valida que todas operaciones exitosas

### Estado 6: Finalización

1. Muestra confirmación con ID de venta
2. Total final con IVA
3. Retorna a menú de ventas

---

## ANÁLISIS Y REPORTERÍA

### Consultas de Análisis

**Top 3 Celulares Más Vendidos:**

```sql
SELECT id_celular
FROM detalle_ventas
GROUP BY id_celular
ORDER BY SUM(cantidad) DESC
LIMIT 3;
```

**Total de Ventas por Mes:**

```sql
SELECT MONTH(date) as mes, SUM(total) as total
FROM ventas
GROUP BY MONTH(date)
ORDER BY MONTH(date) DESC;
```

**Celulares con Stock Bajo:**

```sql
SELECT * FROM celulares WHERE stock < 5;
```

**Cliente con Mayor Gasto:**

```sql
SELECT id_cliente, SUM(total) as gasto_total
FROM ventas
GROUP BY id_cliente
ORDER BY gasto_total DESC
LIMIT 1;
```

**Cliente con Más Compras:**

```sql
SELECT id_cliente, COUNT(*) as numero_compras
FROM ventas
GROUP BY id_cliente
ORDER BY numero_compras DESC
LIMIT 1;
```

### Reportes Generados

**Estructura de Reporte de Ventas:**

```
========================================
REPORTE DE VENTAS - TECNOSTORE
========================================
Generado: 06/02/2026 12:13:10

--- RESUMEN GENERAL ---
Total de ventas: 4
Total de artículos vendidos: 4
Total de clientes: 5
Total de celulares en catálogo: 5

--- INFORMACIÓN FINANCIERA ---
Total de ventas (con IVA): $43000,00
Total sin IVA: $36134,45
Total de IVA (19%): $6865,55
Venta promedio: $10750,00
Gasto promedio por cliente: $14333,33

--- DETALLES DE VENTAS ---
Venta ID: 1
Cliente: Alexi (ID: 1)
Fecha: 2026-02-05
Total: $4200,00
Artículos:
  - Samsung Galaxy S23 (Cantidad: 1, Subtotal: $4200,00)

--- CELULARES CON STOCK BAJO (< 5) ---
No hay celulares con stock bajo

--- TOP 3 CELULARES MÁS VENDIDOS ---
1. Xiaomi Redmi Note 12 - Cantidad vendida: 2
2. Samsung Galaxy S23 - Cantidad vendida: 1
3. Apple iPhone 14 - Cantidad vendida: 1

--- VENTAS POR MES ---
Mes: FEBRUARY - Total: $43000,00

========================================
Fin del reporte
========================================
```

---

**Fin de Guía Técnica Detallada**

Para más información consulte la documentación principal: DOCUMENTACION_TECNOSTORE.md
