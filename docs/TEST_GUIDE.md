# GUÍA COMPLETA DE TESTS - TECNOSTORE

## 📋 Descripción General

Esta guía proporciona instrucciones detalladas para ejecutar todos los tests del proyecto TecnoStore. Los tests están organizados en tres categorías principales:

1. **Tests de Modelos (Model Tests)** - Validan la integridad de las entidades
2. **Tests de Repositorio (Repository Tests)** - Validan operaciones CRUD en BD
3. **Tests de Servicios (Service Tests)** - Validan lógica de negocio

---

## 📁 Estructura de Archivos de Tests

```
src/main/java/org/example/tests/
├── testModel/
│   ├── TestPhoneModel.java
│   ├── TestClientModel.java
│   ├── TestSaleModel.java
│   └── TestSalesDetailsModel.java
├── testRepository/
│   ├── TestPhoneRepository.java
│   ├── TestClientRepository.java
│   └── TestSaleRepository.java
└── testService/
    ├── TestPhoneService.java
    ├── TestClientService.java
    └── TestSaleService.java
```

---

## 🧪 TESTS DE MODELOS (Model Tests)

### 1. TestPhoneModel.java

**Ubicación:** `src/main/java/org/example/tests/testModel/TestPhoneModel.java`

**Descripción:** Valida la clase PhoneModel

**Tests incluidos:**

- ✓ Constructor vacío
- ✓ Constructor con parámetros básicos
- ✓ Constructor completo con fechas
- ✓ Getters y Setters
- ✓ Método toString
- ✓ Validación de datos (restricciones)

**Ejecución:**

```bash
cd [ruta-del-proyecto]
mvn compile
mvn exec:java -Dexec.mainClass="org.example.tests.testModel.TestPhoneModel"
```

**Salida esperada:**

```
========================================
   INICIANDO TESTS DE PHONEMODEL
========================================

[TEST 1] Constructor vacío
✓ Constructor vacío creado exitosamente
  ID inicial: 0
  ✓ Validación completada

[TEST 2] Constructor con parámetros básicos
✓ Teléfono creado:
  Marca: Samsung
  Modelo: Galaxy S23
  ...
```

---

### 2. TestClientModel.java

**Ubicación:** `src/main/java/org/example/tests/testModel/TestClientModel.java`

**Descripción:** Valida la clase ClientModel

**Tests incluidos:**

- ✓ Constructor vacío
- ✓ Constructor con parámetros básicos
- ✓ Constructor completo con ID y fechas
- ✓ Getters y Setters
- ✓ Método toString
- ✓ Validación de formato de email

**Ejecución:**

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testModel.TestClientModel"
```

---

### 3. TestSaleModel.java

**Ubicación:** `src/main/java/org/example/tests/testModel/TestSaleModel.java`

**Descripción:** Valida la clase SaleModel

**Tests incluidos:**

- ✓ Constructor vacío
- ✓ Constructor con parámetros básicos
- ✓ Constructor completo con fechas
- ✓ Getters y Setters
- ✓ Método toString
- ✓ Cálculos financieros (con IVA)

**Ejecución:**

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testModel.TestSaleModel"
```

---

### 4. TestSalesDetailsModel.java

**Ubicación:** `src/main/java/org/example/tests/testModel/TestSalesDetailsModel.java`

**Descripción:** Valida la clase SalesDetailsModel

**Tests incluidos:**

- ✓ Constructor vacío
- ✓ Constructor con parámetros básicos
- ✓ Constructor completo con fechas
- ✓ Getters y Setters
- ✓ Método toString
- ✓ Cálculo de subtotal (precio × cantidad)

**Ejecución:**

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testModel.TestSalesDetailsModel"
```

---

## 🗄️ TESTS DE REPOSITORIO (Repository Tests)

### ⚠️ REQUISITOS PREVIOS

Estos tests requieren conexión a la base de datos. Asegúrese de:

1. **Configurar archivo `.env`:**

```env
DB_URL=jdbc:mysql://localhost:3306/tecnostore_db
DB_USER=root
DB_PASSWORD=tu_contraseña
```

2. **Crear la base de datos:**

```bash
mysql -u root -p < tecnostore_db.sql
```

3. **Verificar conexión MySQL:**

```bash
mysql -u root -p -e "use tecnostore_db; SELECT COUNT(*) FROM celulares;"
```

---

### 1. TestPhoneRepository.java

**Ubicación:** `src/main/java/org/example/tests/testRepository/TestPhoneRepository.java`

**Descripción:** Valida operaciones CRUD de celulares en BD

**Tests incluidos:**

- ✓ Obtener todos los celulares
- ✓ Obtener celular por ID
- ✓ Añadir nuevo celular
- ✓ Actualizar celular
- ✓ Actualizar stock de celular
- ✓ Obtener celulares con stock bajo (< 5)
- ✓ Eliminar celular

**Ejecución:**

```bash
mvn compile
mvn exec:java -Dexec.mainClass="org.example.tests.testRepository.TestPhoneRepository"
```

**Salida esperada:**

```
========================================
   INICIANDO TESTS DE PHONEREPOSITORY
========================================

[TEST 1] Obtener todos los celulares
✓ Celulares obtenidos: 5
  Primeros 3 celulares:
    - Samsung Galaxy S23 (Precio: $4200.00, Stock: 15)
    ...
```

---

### 2. TestClientRepository.java

**Ubicación:** `src/main/java/org/example/tests/testRepository/TestClientRepository.java`

**Descripción:** Valida operaciones CRUD de clientes en BD

**Tests incluidos:**

- ✓ Obtener todos los clientes
- ✓ Obtener cliente por ID
- ✓ Añadir nuevo cliente
- ✓ Actualizar cliente
- ✓ Eliminar cliente
- ✓ Búsqueda de cliente por DNI
- ✓ Validar integridad de datos

**Ejecución:**

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testRepository.TestClientRepository"
```

---

### 3. TestSaleRepository.java

**Ubicación:** `src/main/java/org/example/tests/testRepository/TestSaleRepository.java`

**Descripción:** Valida operaciones CRUD de ventas en BD

**Tests incluidos:**

- ✓ Obtener todas las ventas
- ✓ Obtener venta por ID
- ✓ Añadir nueva venta
- ✓ Actualizar venta
- ✓ Obtener ventas completas (con join)
- ✓ Obtener venta completa por ID
- ✓ Eliminar venta
- ✓ Analítica de ventas

**Ejecución:**

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testRepository.TestSaleRepository"
```

---

## 🔧 TESTS DE SERVICIOS (Service Tests)

### ⚠️ REQUISITOS PREVIOS

Igual a los tests de repositorio (conexión a BD necesaria)

---

### 1. TestPhoneService.java

**Ubicación:** `src/main/java/org/example/tests/testService/TestPhoneService.java`

**Descripción:** Valida lógica de negocio de celulares

**Tests incluidos:**

- ✓ Registrar celular válido
- ✓ Registrar celular inválido (validaciones)
- ✓ Obtener todos los celulares
- ✓ Obtener celular por ID
- ✓ Obtener celulares por categoría (Alta, Media, Baja)
- ✓ Obtener celulares por marca
- ✓ Obtener celulares ordenados por precio
- ✓ Obtener celulares con stock bajo
- ✓ Calcular precio promedio
- ✓ Calcular stock total
- ✓ Obtener celular más y menos caro
- ✓ Reducir stock de celular
- ✓ Actualizar stock de celular

**Ejecución:**

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testService.TestPhoneService"
```

**Validaciones clave:**

```
✓ Precio debe ser positivo (> 0)
✓ Stock no puede ser negativo
✓ Marca, modelo y SO son requeridos
✓ Gama debe ser válida (Alta, Media, Baja)
```

---

### 2. TestClientService.java

**Ubicación:** `src/main/java/org/example/tests/testService/TestClientService.java`

**Descripción:** Valida lógica de negocio de clientes

**Tests incluidos:**

- ✓ Registrar cliente válido
- ✓ Registrar cliente inválido (validaciones)
- ✓ Obtener todos los clientes
- ✓ Obtener cliente por ID
- ✓ Obtener cliente por DNI
- ✓ Obtener cliente por Email
- ✓ Buscar clientes por nombre
- ✓ Obtener clientes ordenados por nombre
- ✓ Validar formato de email
- ✓ Validar DNI
- ✓ Actualizar cliente
- ✓ Eliminar cliente
- ✓ Estadísticas de clientes

**Ejecución:**

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testService.TestClientService"
```

**Validaciones clave:**

```
✓ Email debe tener formato válido (user@domain.com)
✓ DNI debe ser único
✓ Email debe ser único
✓ Nombre, DNI y teléfono son requeridos
✓ No puede haber duplicados
```

---

### 3. TestSaleService.java

**Ubicación:** `src/main/java/org/example/tests/testService/TestSaleService.java`

**Descripción:** Valida lógica de negocio de ventas

**Tests incluidos:**

- ✓ Obtener todas las ventas
- ✓ Obtener venta por ID
- ✓ Obtener ventas por cliente
- ✓ Obtener detalles de venta
- ✓ Calcular total de ventas
- ✓ Calcular ventas sin IVA
- ✓ Calcular IVA (19%)
- ✓ Obtener mayor y menor venta
- ✓ Calcular promedio de venta
- ✓ Obtener ventas por mes
- ✓ Analítica avanzada (top clientes, top productos)
- ✓ Registrar nueva venta

**Ejecución:**

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testService.TestSaleService"
```

**Validaciones clave:**

```
✓ IVA se calcula al 19% del subtotal
✓ Total = Subtotal × (1 + 0.19)
✓ El cliente debe existir
✓ Los celulares deben tener stock disponible
✓ Stock se reduce automáticamente
```

---

## 🚀 EJECUCIÓN TODOS LOS TESTS

### Opción 1: Tests de Modelos (Sin BD)

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testModel.TestPhoneModel"
mvn exec:java -Dexec.mainClass="org.example.tests.testModel.TestClientModel"
mvn exec:java -Dexec.mainClass="org.example.tests.testModel.TestSaleModel"
mvn exec:java -Dexec.mainClass="org.example.tests.testModel.TestSalesDetailsModel"
```

### Opción 2: Tests de Repositorio (Con BD)

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testRepository.TestPhoneRepository"
mvn exec:java -Dexec.mainClass="org.example.tests.testRepository.TestClientRepository"
mvn exec:java -Dexec.mainClass="org.example.tests.testRepository.TestSaleRepository"
```

### Opción 3: Tests de Servicios (Con BD)

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testService.TestPhoneService"
mvn exec:java -Dexec.mainClass="org.example.tests.testService.TestClientService"
mvn exec:java -Dexec.mainClass="org.example.tests.testService.TestSaleService"
```

---

## 📊 Interpretación de Resultados

### ✓ Test Exitoso

```
[TEST 1] Obtener todos los celulares
✓ Celulares obtenidos: 5
  ...
  ✓ Validación completada
```

### ✗ Test Fallido

```
✗ Error: La conexión a la base de datos no fue exitosa
```

### ⚠ Test con Advertencia

```
⚠ No hay celulares disponibles para pruebas
```

---

## 🔍 Solución de Problemas

### Error: "No se encontró la clase"

```bash
mvn clean compile
```

### Error de conexión a BD

```bash
# Verificar servicio MySQL
sudo systemctl status mysql

# Iniciar MySQL si está detenido
sudo systemctl start mysql

# Verificar credenciales en .env
cat .env
```

### Error: "Tabla no encontrada"

```bash
# Recrear base de datos
mysql -u root -p < tecnostore_db.sql

# Verificar tablas
mysql -u root -p -e "use tecnostore_db; SHOW TABLES;"
```

### Error de dependencias

```bash
mvn clean install
mvn compile
```

---

## 📋 Checklist de Validación

Después de ejecutar todos los tests, verifique:

- [ ] Todos los tests de modelos pasan
- [ ] Tests de repositorio se conectan a BD correctamente
- [ ] Tests de servicio validan restricciones
- [ ] Los cálculos de IVA son correctos (19%)
- [ ] Las búsquedas retornan resultados correctos
- [ ] Las operaciones CRUD funcionan correctamente
- [ ] No hay datos duplicados
- [ ] El stock se actualiza correctamente
- [ ] Las fechas se registran adecuadamente

---

## 📝 Notas Importantes

1. **Tests de Modelos:** No requieren conexión a BD, se ejecutan localmente
2. **Tests de Repositorio:** Requieren BD funcionando, ejecutan operaciones reales
3. **Tests de Servicios:** Validan lógica de negocio con datos reales
4. **IVA:** Se calcula siempre al 19% como por defecto
5. **Stock Bajo:** Se considera stock bajo cualquier cantidad menor a 5 unidades

---

## 🎯 Próximos Pasos

Después de validar los tests:

1. Crear menú de consola en `Main.java`
2. Implementar generador de reportes (`ReportService`)
3. Agregar más tests de integración
4. Documentar casos de uso

---

**Versión:** 1.0  
**Última actualización:** Febrero 2026  
**Autor:** Equipo de Desarrollo - TecnoStore
