# MANUAL DE USUARIO - TECNOSTORE v1.0

**Versión:** 1.1  
**Fecha de Actualización:** Febrero 2026  
**Dirigido a:** Gerentes, Vendedores, Personal Administrativo y Directivos

---

## TABLA DE CONTENIDOS

1. [Inicio Rápido](#inicio-rápido)
2. [Pantalla Principal](#pantalla-principal)
3. [Gestión de Clientes](#gestión-de-clientes)
4. [Gestión de Celulares](#gestión-de-celulares)
5. [Registro de Ventas](#registro-de-ventas)
6. [Sistema de Descuentos](#sistema-de-descuentos)
7. [Generación de Reportes](#generación-de-reportes)
8. [Consultas Frecuentes](#consultas-frecuentes)
9. [Resolución de Problemas](#resolución-de-problemas)
10. [Guía Rápida de Atajos](#guía-rápida-de-atajos)

---

## INICIO RÁPIDO

### Requisitos Previos

- Java 21 instalado
- MySQL en ejecución
- Archivo `.env` configurado correctamente
- Base de datos `tecnostore_db` creada

### Paso 1: Ejecutar la Aplicación

```bash
# Opción 1: Mediante Maven
mvn exec:java -Dexec.mainClass="org.example.Main"

# Opción 2: Ejecutable JAR
java -jar target/TecnoStore-1.0.jar
```

### Paso 2: Pantalla de Bienvenida

```
=======================================================
        👋   Bienvenido a Tecno Store System  💯
=======================================================

Versión 1.0
Sistema de gestión de ventas, inventario y reportes

=======================================================
```

Una vez iniciado, aparecerá el **menú principal**.

---

## PANTALLA PRINCIPAL

### Menú Principal

```
=======================================================
      Panel principal Tecno Store System
=======================================================
Elija una opción:
    1. Gestionar clientes.
    2. Gestionar celulares.
    3. Generar o gestionar venta.
    4. Gestionar reportes.
    5. Salir.

=======================================================
```

### Cómo Navegar

| Acción                  | Instrucción                           |
| ----------------------- | ------------------------------------- |
| **Ver una opción**      | Ingrese el número (1-5)               |
| **Confirmar entrada**   | Presione Enter                        |
| **Volver atrás**        | Seleccione opción 7 en submenús       |
| **Salir completamente** | Seleccione opción 5 en menú principal |

---

## GESTIÓN DE CLIENTES

### Submenú de Clientes

```
=======================================================
            Gestionar Cliente
=======================================================
Elija una opción:
    1. Crear cliente.
    2. Ver todos los clientes.
    3. Buscar cliente por ID.
    4. Buscar cliente por DNI.
    5. Actualizar cliente.
    6. Eliminar cliente.
    7. Volver al menu principal.

=======================================================
```

### 1. Crear Cliente

**Campos Requeridos:**

```
Nombre:               [Mínimo 3 caracteres, solo letras y espacios]
C.C (DNI):           [Debe ser único, sin guiones ni puntos]
Email:               [Formato: usuario@dominio.com]
Número de teléfono:  [Formato: 10 dígitos, ej: 3001234567]
```

**Ejemplo de Registro Correcto:**

```
Nombre: Juan Carlos Pérez García
C.C: 1005678901
Email: juancarlos.perez@empresa.com
Teléfono: 3101234567

✓ Cliente creado exitosamente
```

**Validaciones Automáticas:**

| Validación | Error Si               |
| ---------- | ---------------------- |
| Nombre     | Está vacío             |
| DNI        | Ya existe o está vacío |
| Email      | Formato inválido       |
| Email      | Ya existe              |
| Teléfono   | Está vacío             |

### 2. Ver Todos los Clientes

Muestra listado completo con:

- ID de cliente
- Nombre
- Identificación (DNI)
- Correo electrónico
- Teléfono
- Fecha de creación
- Fecha de última actualización

**Función:** Útil para auditoría y verificación de datos.

### 3. Buscar Cliente por ID

**Pasos:**

1. Seleccionar opción 3
2. Ingresar ID del cliente
3. Sistema muestra información detallada

**Ejemplo:**

```
Introduzca el id: 5
✓ Cliente encontrado:
  ID: 5
  Nombre: María López
  ...
```

### 4. Buscar Cliente por DNI

Similar a búsqueda por ID, pero utiliza número de identificación.

**Ventaja:** Útil si no conoce el ID del cliente.

### 5. Actualizar Cliente

**Procedimiento:**

1. Ingresar ID del cliente a actualizar
2. Sistema muestra datos actuales
3. Ingresar nuevos datos
4. Confirmar actualización

**Restricciones:**

- No se puede cambiar a un DNI existente
- No se puede cambiar a un email existente
- Los datos seguirán siendo validados

### 6. Eliminar Cliente

**Advertencia:** La eliminación es permanente.

**Procedimiento:**

1. Ingresar ID del cliente
2. Sistema solicita confirmación
3. Ingresar "S" para confirmar o "N" para cancelar
4. Eliminación registrada en sistema

---

## GESTIÓN DE CELULARES

### Submenú de Celulares

```
=======================================================
           Gestionar Celulares
=======================================================
Elija una opción:
    1. Crear celular.
    2. Ver todos los celulares.
    3. Buscar celular por ID.
    4. Ver celulares por categoría.
    5. Actualizar celular.
    6. Eliminar celular.
    7. Volver al menu principal.

=======================================================
```

### 1. Crear Celular

**Campos Requeridos:**

```
Marca:                [Samsung, Apple, Xiaomi, etc.]
Modelo:               [Galaxy S23, iPhone 14, etc.]
Sistema Operativo:    [Android, iOS, etc.]
Gama (Alta/Media/Baja): [Seleccione una categoría]
Precio:               [Número positivo, ej: 4200.00]
Stock Actual:         [Número no negativo, ej: 15]
```

**Ejemplo Correcto:**

```
Marca: Samsung
Modelo: Galaxy A54
Sistema Operativo: Android
Gama: Media
Precio: 2500.00
Stock Actual: 20

✓ Celular creado exitosamente
```

### 2. Ver Todos los Celulares

Muestra:

- ID
- Marca y Modelo
- Sistema Operativo
- Categoría de Gama
- Precio unitario
- Stock disponible
- Fechas de creación/actualización

**Opciones de Ordenamiento:**

- Por precio (menor a mayor)
- Por stock (menor a mayor)
- Por marca (A-Z)
- Sin ordenamiento

### 3. Buscar Celular por ID

Similar a clientes, ingrese ID para ver detalles completos.

### 4. Ver Celulares por Categoría

**Categorías Disponibles:**

| Categoría | Precio Típico   | Stock Recomendado |
| --------- | --------------- | ----------------- |
| **Alta**  | $4,000 - $6,000 | 10-20 unidades    |
| **Media** | $1,500 - $3,000 | 20-40 unidades    |
| **Baja**  | $500 - $1,500   | 30-50 unidades    |

### 5. Actualizar Celular

Permite cambiar:

- Precio
- Stock
- Otros atributos

### 6. Eliminar Celular

Elimina el producto del catálogo.

**Advertencia:** Verificar que no tenga ventas asociadas.

---

## REGISTRO DE VENTAS

### Submenú de Ventas

```
=======================================================
            Gestionar Ventas
=======================================================
Elija una opción:
    1. Crear venta.
    2. Ver todos los ventas.
    3. Buscar venta por ID.
    4. Buscar venta por cliente.
    5. Ver analíticas de ventas.
    6. Ver los 3 celulares más vendidos.
    7. Volver al menu principal.

=======================================================
```

### 1. Crear Venta (Proceso Completo)

**FASE 1: SELECCIONAR CLIENTE**

```
--- SELECCIONAR CLIENTE ---

Clientes disponibles:
1. Carlos Perez (ID: 1, DNI: 1002345678)
2. Ana Torres (ID: 2, DNI: 1003456789)
3. Luis Gomez (ID: 3, DNI: 1004567890)

Selecciona el número del cliente: [1-3]
✓ Cliente seleccionado: Carlos Perez
```

**FASE 2: SELECCIONAR CELULARES**

```
--- SELECCIONAR CELULARES ---

Celulares disponibles:
1. Samsung Galaxy S23 - Precio: $4200.00 (Stock: 15)
2. Apple iPhone 14 - Precio: $5200.00 (Stock: 10)
3. Xiaomi Redmi Note 12 - Precio: $1800.00 (Stock: 30)
4. Motorola Moto G54 - Precio: $1600.00 (Stock: 25)

Selecciona el número del celular (0 para terminar): 1
¿Cuántas unidades? (máx: 15): 1
✓ Añadido: Samsung Galaxy S23 x 1

Selecciona el número del celular (0 para terminar): 3
¿Cuántas unidades? (máx: 30): 2
✓ Añadido: Xiaomi Redmi Note 12 x 2

Selecciona el número del celular (0 para terminar): 0
```

**FASE 3: APLICAR DESCUENTO (NUEVO)**

```
Elija el tipo de descuento:
    1. Premium (10% descuento).
    2. Estándar (5% descuento).
    3. Sin descuento.
Seleccione: 2
```

**FASE 4: CONFIRMAR Y CALCULAR**

```
--- CONFIRMAR VENTA ---

Cliente: Carlos Perez

Artículos:
  - Samsung Galaxy S23 x 1: $4,200.00
  - Xiaomi Redmi Note 12 x 2: $3,600.00

Subtotal: $7,800.00
Descuento (5%): -$390.00
Subtotal después descuento: $7,410.00
IVA (19%): $1,407.90
─────────────────────────────
Total: $8,817.90

¿Confirmar venta? (S/N): S
✓ Venta registrada exitosamente
Total a pagar: $8,817.90
```

### Detalles Importantes de Ventas

**Cálculo Automático del IVA:**

- IVA se aplica SIEMPRE al 19%
- Se calcula sobre el subtotal DESPUÉS de aplicar descuento
- Fórmula: `Total = Subtotal × (1 + 0.19)`

**Descuentos Disponibles:**

| Tipo              | Descuento | Uso                           |
| ----------------- | --------- | ----------------------------- |
| **Premium**       | 10%       | Clientes VIP, compras grandes |
| **Estándar**      | 5%        | Clientes frecuentes           |
| **Sin descuento** | 0%        | Precio normal                 |

**Validaciones de Venta:**

✓ Cliente debe existir  
✓ Celular debe existir  
✓ Stock debe ser suficiente  
✓ No se permiten artículos duplicados en una venta  
✓ Cantidad debe ser positiva

### 2. Ver Todas las Ventas

Lista todas las transacciones con:

- ID de venta
- Cliente
- Fecha y hora
- Total con IVA
- Artículos vendidos

### 3. Buscar Venta por ID

Muestra detalles completos de una venta específica.

### 4. Buscar Ventas por Cliente

Ingrese ID del cliente para ver:

- Todas sus compras
- Fecha de cada compra
- Total por compra
- Total gastado
- Promedio por compra

### 5. Ver Analíticas de Ventas

```
--- ANÁLISIS DE VENTAS ---

--- INFORMACIÓN GENERAL ---
Total de ventas realizadas: 4
Total de artículos vendidos: 4

--- INFORMACIÓN FINANCIERA ---
Total de ventas (sin IVA): $36,134.45
IVA recaudado (19%): $6,865.55
Total de ventas (con IVA): $43,000.00
Promedio por venta: $10,750.00

--- ESTADÍSTICAS DE CLIENTES ---
Cliente con más compras: Carlos Perez (ID: 1)
Cliente que más ha gastado: Carlos Perez (ID: 1)
Gasto promedio por cliente: $14,333.33
```

### 6. Top 3 Celulares Más Vendidos

```
--- TOP 3 CELULARES MÁS VENDIDOS ---

1. Xiaomi Redmi Note 12
   Cantidad vendida: 2 unidades
   Ingresos totales: $3,600.00

2. Samsung Galaxy S23
   Cantidad vendida: 1 unidad
   Ingresos totales: $4,200.00

3. Apple iPhone 14
   Cantidad vendida: 1 unidad
   Ingresos totales: $5,200.00
```

---

## SISTEMA DE DESCUENTOS

### Tipos de Descuento (Patrón Factory)

El sistema utiliza un **Patrón Factory** para gestionar descuentos de forma flexible:

#### 1. Descuento Premium (10%)

**Uso:** Clientes VIP, compras grandes (>$5,000)

**Ejemplo:**

```
Subtotal: $10,000.00
Descuento: -$1,000.00 (10%)
Total descuento: $9,000.00
IVA (19%): $1,710.00
Total final: $10,710.00
```

#### 2. Descuento Estándar (5%)

**Uso:** Clientes frecuentes, compras recurrentes

**Ejemplo:**

```
Subtotal: $5,000.00
Descuento: -$250.00 (5%)
Total descuento: $4,750.00
IVA (19%): $901.50
Total final: $5,651.50
```

#### 3. Sin Descuento (0%)

**Uso:** Clientes nuevos, precio normal

**Ejemplo:**

```
Subtotal: $3,000.00
Descuento: $0.00
IVA (19%): $570.00
Total final: $3,570.00
```

---

## GENERACIÓN DE REPORTES

### Submenú de Reportes

```
=======================================================
            Gestionar Reportes
=======================================================
Elija una opción:
    1. Generar reporte de ventas.
    2. Ver el total de ventas.
    3. Generar reporte de inventario.
    4. Generar reporte de clientes.
    5. Ver resumen de ventas.
    6. Ver los 3 celulares mas vendidos.
    7. Volver al menú principal.

=======================================================
```

### 1. Generar Reporte de Ventas

Crea archivo `reporte_ventas_YYYY-MM-DD_HH-MM-SS.txt` con:

✓ Resumen general de transacciones  
✓ Información financiera (subtotal, IVA, total)  
✓ Detalles línea por línea  
✓ Celulares con stock bajo  
✓ Top 3 más vendidos  
✓ Ventas por mes

**Ubicación:** `/reports/sales_reports/`

### 2. Ver Total de Ventas

Resumen rápido en consola:

```
========== RESUMEN DE VENTAS ==========
Total de ventas: 4
Total de artículos vendidos: 4
Total de ventas (con IVA): $43,000.00
Total sin IVA: $36,134.45
Total IVA (19%): $6,865.55
Venta promedio: $10,750.00
======================================
```

### 3. Generar Reporte de Inventario

Archivo: `reporte_inventario_YYYY-MM-DD_HH-MM-SS.txt`

Contiene:

- Cantidad total de celulares
- Stock total de inventario
- Catálogo completo con detalles
- Precio unitario por modelo
- Valor total del inventario

### 4. Generar Reporte de Clientes

Archivo: `reporte_clientes_YYYY-MM-DD_HH-MM-SS.txt`

Contiene:

- Total de clientes registrados
- Información completa de cada cliente
- Número de compras por cliente
- Total gastado por cliente
- Período de cliente (desde cuándo compra)

### 5. Ver Resumen de Ventas

Similar a opción 2, pero con más detalle en consola.

### 6. Ver Top 3 Más Vendidos

Ranking de celulares más vendidos en consola.

---

## CARACTERÍSTICAS ESPECIALES

### Sistema de Alertas de Stock

El sistema alerta automáticamente cuando:

- Stock de un producto es menor a 5 unidades
- Se intenta vender sin stock disponible

**Indicador Visual:** ⚠ (símbolo de alerta)

### Validación Automática de Datos

**Email:**

- Formato: `usuario@dominio.com`
- No permite duplicados
- Válido: `juan.perez@empresa.co.uk` ✓
- Inválido: `juan@` ✗

**DNI:**

- Debe ser único
- Sin caracteres especiales
- Válido: `1005678901` ✓
- Inválido: `10.056.789-01` ✗

**Precios:**

- Solo números positivos
- Válido: `4200.00` ✓
- Inválido: `-100` ✗

### Cálculos Automáticos

| Cálculo   | Fórmula                |
| --------- | ---------------------- |
| Subtotal  | Precio × Cantidad      |
| IVA       | Subtotal × 0.19        |
| Total     | Subtotal × 1.19        |
| Descuento | Subtotal × % descuento |

---

## CONSULTAS FRECUENTES

### P: ¿Cuál es el porcentaje de IVA?

**R:** El IVA es del **19%** y se calcula automáticamente en cada venta. Se aplica sobre el subtotal DESPUÉS de los descuentos.

**Cálculo:** Total = Subtotal × 1.19

### P: ¿Cómo se registra un cliente sin compras previas?

**R:** Los clientes se registran de forma independiente a las ventas. Puede crear clientes sin que hayan realizado compras. Simplemente ingrese los datos requeridos en "Crear cliente".

### P: ¿Puedo modificar un precio después de creado el celular?

**R:** Sí. Vaya a "Gestionar celulares" → "Actualizar celular", ingrese el ID y modifique el precio. La nueva venta usará el precio actualizado.

### P: ¿Qué pasa si se equivoca al registrar una venta?

**R:** La venta se registra permanentemente. No puede eliminarse, pero puede:

1. Consultar el detalle con "Buscar venta por ID"
2. Contactar al administrador del sistema
3. Generar un reporte para auditoría

### P: ¿Dónde se guardan los reportes?

**R:** En la carpeta `/reports/` del proyecto, organizado en subcarpetas:

- `/sales_reports/` - Reportes de ventas
- `/inventory_reports/` - Reportes de inventario
- `/clients_reports/` - Reportes de clientes

**Formato de nombre:** `reporte_[tipo]_YYYY-MM-DD_HH-MM-SS.txt`

### P: ¿Puedo eliminar un cliente que ya ha comprado?

**R:** Sí, el sistema lo permite, pero se recomienda NO hacerlo para mantener integridad histórica. Si elimina un cliente con compras, perderá el historial de ventas asociado.

### P: ¿Hay un límite de clientes o productos?

**R:** No. El sistema puede manejar:

- Clientes: 100,000+
- Celulares: 10,000+
- Ventas: 1,000,000+

Sin degradación de rendimiento significativa.

### P: ¿Cómo se aplican los descuentos?

**R:** Al crear una venta, después de seleccionar los celulares, el sistema le pide elegir el tipo de descuento:

1. **Premium:** 10% descuento
2. **Estándar:** 5% descuento
3. **Sin descuento:** 0%

El descuento se aplica sobre el subtotal antes de calcular el IVA.

---

## RESOLUCIÓN DE PROBLEMAS

### Error: "Conexión a base de datos rechazada"

**Causa:** MySQL no está corriendo o credenciales incorrectas.

**Solución:**

```bash
# 1. Verificar MySQL está activo
mysql -u root -p -e "SELECT VERSION();"

# 2. Revisar archivo .env
cat .env

# 3. Recrear base de datos si es necesario
mysql -u root -p < tecnostore_db.sql
```

### Error: "Tabla no encontrada"

**Solución:**

```bash
# Ejecutar script de BD
mysql -u root -p < tecnostore_db.sql

# Verificar tablas creadas
mysql -u root -p -e "USE tecnostore_db; SHOW TABLES;"
```

### Error: "Email/DNI ya existe"

**Causa:** Intento de crear un cliente con email o DNI duplicado.

**Solución:**

1. Busque el cliente existente: "Buscar cliente por DNI"
2. Verifique si es el mismo cliente
3. Use datos diferentes si es cliente nuevo
4. Actualice datos si ya existe

### Error: "Stock insuficiente"

**Causa:** Intento de vender más unidades de las disponibles.

**Solución:**

1. Revise el stock disponible: "Ver todos los celulares"
2. Reducir la cantidad solicitada
3. Elegir otro producto
4. Reponer stock del producto

### Error: Programa no inicia

**Pasos:**

```bash
# 1. Limpiar y recompilar
mvn clean compile

# 2. Ver detalles del error
mvn clean compile -X

# 3. Instalar dependencias
mvn dependency:resolve
```

---

## GUÍA RÁPIDA DE ATAJOS

### Secuencia Rápida: Crear Cliente y Venta

```
Main → 1 (Clientes) → 1 (Crear) → [Datos]
Main → 3 (Ventas) → 1 (Crear) → [Procesar]
```

### Secuencia Rápida: Generar Reporte

```
Main → 4 (Reportes) → 1 (Generar reporte ventas)
✓ Archivo generado automáticamente
```

### Acceso Rápido a Consultas

| Necesidad   | Ruta                             |
| ----------- | -------------------------------- |
| Ver cliente | 1 → 3 (por ID) o 4 (por DNI)     |
| Ver celular | 2 → 3 (por ID)                   |
| Ver venta   | 3 → 3 (por ID) o 4 (por cliente) |
| Analíticas  | 3 → 5                            |
| Reportes    | 4 → 1, 3, 4                      |

---

## INFORMACIÓN IMPORTANTE

### Datos que NO se pueden Cambiar

- ID de cliente (asignado automáticamente)
- ID de celular (asignado automáticamente)
- ID de venta (asignado automáticamente)
- Fecha de venta (se registra en momento de creación)

### Datos que SÍ se pueden Cambiar

- Nombre, DNI, email, teléfono de cliente
- Precio, stock de celular
- (Las ventas NO se pueden modificar - son registros permanentes)

### Recepción de Comprobante

Después de cada venta, el sistema muestra:

```
✓ Venta registrada exitosamente
Total a pagar: $XXXXX.XX
```

Anote este total para el comprobante de venta.

---

**Nota Final:** Este manual cubre el 95% de operaciones. Para situaciones especiales o reportes personalizados, contacte al administrador del sistema.

**Última Actualización:** Febrero 2026  
**Versión:** 1.1  
**Mantenedor:** Equipo de Desarrollo TecnoStore
