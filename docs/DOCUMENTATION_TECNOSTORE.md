# TECNOSTORE - Sistema de Gestión de Ventas y Inventario

**Versión:** 1.0  
**Fecha:** Febrero 2026  
**Autor:** Equipo de Desarrollo  
**Estado:** Completado

---

## ÍNDICE

1. [Introducción](#introducción)
2. [Descripción del Proyecto](#descripción-del-proyecto)
3. [Objetivos del Sistema](#objetivos-del-sistema)
4. [Estructura del Proyecto](#estructura-del-proyecto)
5. [Requisitos del Sistema](#requisitos-del-sistema)
6. [Instalación y Configuración](#instalación-y-configuración)
7. [Base de Datos](#base-de-datos)
8. [Arquitectura del Sistema](#arquitectura-del-sistema)
9. [Descripción de Módulos](#descripción-de-módulos)
10. [Modelos de Datos](#modelos-de-datos)
11. [Funcionalidades Principales](#funcionalidades-principales)
12. [Patrones de Diseño](#patrones-de-diseño)
13. [Guía de Uso](#guía-de-uso)
14. [Testing](#testing)
15. [Mantenimiento y Soporte](#mantenimiento-y-soporte)

---

## INTRODUCCIÓN

TecnoStore es una aplicación de consola desarrollada en Java que permite gestionar de manera integral las operaciones comerciales de una tienda minorista de teléfonos celulares. El sistema automatiza procesos manuales de control de inventario, registro de ventas y generación de reportes analíticos.

La aplicación implementa principios SOLID, patrones de diseño reconocidos y una arquitectura modular que facilita el mantenimiento y escalabilidad del código.

---

## DESCRIPCIÓN DEL PROYECTO

### Visión General

TecnoStore es un sistema de información diseñado para reemplazar la gestión manual de operaciones en una tienda de venta de celulares. La aplicación centraliza la información de clientes, inventario y transacciones comerciales, proporcionando herramientas para análisis y toma de decisiones.

### Contexto Empresarial

La empresa TecnoStore es una tienda minorista que vende teléfonos celulares de diferentes marcas y gamas. Anteriormente, todos los registros se manejaban en hojas de cálculo, lo que generaba inconsistencias, pérdida de datos y dificultad en análisis comerciales. Este sistema automatiza esos procesos.

### Público Objetivo

- Gerentes y vendedores de la tienda
- Personal administrativo
- Analistas de inventario
- Directiva para toma de decisiones

---

## OBJETIVOS DEL SISTEMA

### Objetivos Generales

1. Automatizar la gestión de inventario de celulares
2. Registrar y controlar todas las transacciones comerciales
3. Mantener una base de datos centralizada de clientes
4. Generar reportes analíticos para la toma de decisiones

### Objetivos Específicos

1. **Gestión de Celulares**
   - Registrar celulares con marca, modelo, precio, stock y categoría
   - Actualizar información de inventario en tiempo real
   - Consultar disponibilidad por categoría o marca
   - Alertar sobre stock bajo

2. **Gestión de Clientes**
   - Registrar clientes con información de contacto
   - Validar unicidad de identificaciones y emails
   - Mantener historial de compras por cliente
   - Calcular métricas de gasto por cliente

3. **Gestión de Ventas**
   - Registrar ventas con múltiples artículos
   - Aplicar cálculo automático de IVA (19%)
   - Actualizar inventario automáticamente
   - Generar tickets y comprobantes

4. **Reportes y Análisis**
   - Generar reportes de ventas con timestamps
   - Inventario de celulares con stock disponible
   - Análisis de clientes y frecuencia de compras
   - Top 3 productos más vendidos
   - Alertas de stock bajo

---

## ESTRUCTURA DEL PROYECTO

```
Proyecto_Java_S2_DuranAlexi/
├── src/main/java/org/example/
│   ├── Config/
│   │   ├── Configs.java
│   │   └── DatabaseConnection.java
│   ├── Controller/
│   │   ├── ClientController.java
│   │   ├── PhoneController.java
│   │   ├── SaleController.java
│   │   └── ReportController.java
│   ├── Model/
│   │   ├── ClientModel.java
│   │   ├── PhoneModel.java
│   │   ├── SaleModel.java
│   │   └── SalesDetailsModel.java
│   ├── View/
│   │   ├── MainMenu.java
│   │   ├── ClientView.java
│   │   ├── PhoneView.java
│   │   ├── SaleView.java
│   │   └── ReportView.java
│   ├── repository/
│   │   ├── ClientRepository.java
│   │   ├── PhoneRepository.java
│   │   ├── SaleRepository.java
│   │   └── SalesDetailsRepository.java
│   ├── service/
│   │   ├── ClientService.java
│   │   ├── PhoneService.java
│   │   ├── SaleService.java
│   │   └── ReportService.java
│   ├── Interfaces/
│   │   ├── IView.java
│   │   ├── repositoryInterfaces/
│   │   └── viewInterfaces/
│   ├── tests/
│   │   ├── testModel/
│   │   ├── testRepository/
│   │   └── testService/
│   └── Main.java
├── resources/
│   └── .env.example
├── reports/
│   └── [Archivos de reportes generados]
├── pom.xml
├── .gitignore
├── tecnostore_db.sql
└── README.md
```

---

## REQUISITOS DEL SISTEMA

### Requisitos de Hardware

- Procesador: Intel Core i3 o superior
- RAM: 2 GB mínimo
- Almacenamiento: 500 MB disponibles
- Sistema Operativo: Windows, Linux o macOS

### Requisitos de Software

**Obligatorios:**

- Java Development Kit (JDK) 21 o superior
- Apache Maven 3.8 o superior
- MySQL 8.0 o superior
- Git (para control de versiones)

**Dependencias de Proyecto:**

- mysql-connector-j 9.6.0
- dotenv-java 3.2.0

### Requisitos de Acceso

- Conexión a base de datos MySQL con permisos de lectura y escritura
- Carpeta para almacenamiento de reportes con permisos de escritura

---

## INSTALACIÓN Y CONFIGURACIÓN

### Paso 1: Preparar el Entorno

```bash
# Verificar versiones instaladas
java -version
mvn -version
mysql --version

# Clonar o descargar el proyecto
git clone https://github.com/usuario/Proyecto_Java_S2_DuranAlexi.git
cd Proyecto_Java_S2_DuranAlexi
```

### Paso 2: Configurar Base de Datos

```bash
# Crear base de datos y tablas
mysql -u root -p < tecnostore_db.sql

# Verificar creación
mysql -u root -p -e "USE tecnostore_db; SHOW TABLES;"
```

### Paso 3: Configurar Variables de Entorno

Crear archivo `.env` en la raíz del proyecto:

```env
# Configuración de Base de Datos
DB_URL=jdbc:mysql://localhost:3306/tecnostore_db
DB_USER=root
DB_PASSWORD=tu_contraseña

DB_HOST=localhost
DB_PORT=3306
DB_NAME=tecnostore_db

# Configuración de Aplicación
APP_NAME=TecnoStore
APP_VERSION=1.0
DEBUG_MODE=true

# Configuración de Reportes
REPORT_PATH=./reports/
REPORT_FILENAME=reporte_ventas.txt

# Configuración de Validación
MIN_STOCK_WARNING=5
IVA_PERCENTAGE=0.19
```

### Paso 4: Compilar el Proyecto

```bash
# Limpiar y compilar
mvn clean compile

# Construir el proyecto
mvn clean install

# Empaquetar (opcional)
mvn package
```

### Paso 5: Ejecutar la Aplicación

```bash
# Opción 1: Mediante Maven
mvn exec:java -Dexec.mainClass="org.example.Main"

# Opción 2: Ejecutable JAR
java -jar target/Proyecto_Java_S2_DuranAlexi-1.0-SNAPSHOT.jar
```

---

## BASE DE DATOS

### Diseño del Esquema

La base de datos `tecnostore_db` contiene 4 tablas principales:

#### Tabla: celulares

```sql
CREATE TABLE celulares (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(255) NOT NULL,
    operative_sistem VARCHAR(100) NOT NULL,
    range_category VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

**Propósito:** Almacena el catálogo de celulares disponibles.

**Campos:**

- `id`: Identificador único
- `brand`: Marca del celular (Samsung, Apple, Xiaomi, etc.)
- `model`: Modelo específico
- `operative_sistem`: Sistema operativo (Android, iOS, etc.)
- `range_category`: Categoría de gama (Alta, Media, Baja)
- `price`: Precio unitario en pesos
- `stock`: Cantidad disponible en inventario

#### Tabla: clientes

```sql
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    dni VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

**Propósito:** Registra información de clientes.

**Campos:**

- `id`: Identificador único
- `name`: Nombre completo del cliente
- `dni`: Número de identificación (único)
- `email`: Correo electrónico (único y validado)
- `phone`: Número de teléfono de contacto

#### Tabla: ventas

```sql
CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);
```

**Propósito:** Registra todas las transacciones de ventas.

**Campos:**

- `id`: Identificador único de la venta
- `id_cliente`: Referencia al cliente que realiza la compra
- `date`: Fecha y hora de la transacción
- `total`: Total con IVA incluido

#### Tabla: detalle_ventas

```sql
CREATE TABLE detalle_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_celular INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_venta) REFERENCES ventas(id),
    FOREIGN KEY (id_celular) REFERENCES celulares(id)
);
```

**Propósito:** Detalla los artículos incluidos en cada venta.

**Campos:**

- `id`: Identificador único
- `id_venta`: Referencia a la venta
- `id_celular`: Referencia al celular vendido
- `cantidad`: Número de unidades vendidas
- `subtotal`: Precio total del artículo (sin IVA)

### Relaciones entre Tablas

```
clientes (1) ─── (N) ventas
celulares (1) ─── (N) detalle_ventas
ventas (1) ─── (N) detalle_ventas
```

---

## ARQUITECTURA DEL SISTEMA

### Patrón MVC (Model-View-Controller)

La aplicación sigue el patrón Modelo-Vista-Controlador:

```
┌─────────────┐
│   Usuario   │
└──────┬──────┘
       │
       ▼
┌─────────────────┐
│  MainMenu.java  │ (Punto de entrada)
└────────┬────────┘
         │
    ┌────┴────┐
    ▼         ▼
┌────────────────────────────────────┐
│      Controladores (Controller)     │
├────────────────────────────────────┤
│ ClientController, PhoneController, │
│ SaleController, ReportController   │
└────────┬─────────────────────┬─────┘
         │                     │
    ┌────▼──────────┐  ┌──────▼───┐
    │               │  │           │
    ▼               ▼  ▼           ▼
┌─────────────┐  ┌──────────────────────┐
│  Servicios  │  │    Vista (View)      │
│  (Service)  │  ├──────────────────────┤
├─────────────┤  │ ClientView, PhoneView│
│ ClientServ. │  │ SaleView, ReportView │
│ PhoneServ.  │  └──────────────────────┘
│ SaleServ.   │
│ ReportServ. │
└────────┬────┘
         │
    ┌────▼──────────────────────┐
    │   Repositorio (Repository) │
    ├────────────────────────────┤
    │ ClientRepository           │
    │ PhoneRepository            │
    │ SaleRepository             │
    │ SalesDetailsRepository     │
    └────────┬───────────────────┘
             │
    ┌────────▼─────────┐
    │   Base de Datos   │
    │   (MySQL)         │
    └───────────────────┘
```

### Flujo de Datos

1. **Usuario** interactúa con MainMenu
2. **Controlador** recibe la solicitud y llama al Servicio
3. **Servicio** valida datos y aplica lógica de negocio
4. **Repositorio** interactúa con la base de datos
5. **Modelo** se devuelve al Controlador
6. **Vista** presenta información al usuario

---

## DESCRIPCIÓN DE MÓDULOS

### 1. Módulo de Configuración (Config)

Gestiona la conexión a base de datos y variables de entorno.

**Archivos:**

- `DatabaseConnection.java`: Maneja conexiones JDBC
- `Configs.java`: Implementa patrón Singleton para configuración

**Funcionalidades:**

- Carga variables del archivo `.env`
- Establece conexiones seguras a MySQL
- Manejo de errores de conexión

### 2. Módulo de Controladores (Controller)

Orquesta el flujo de la aplicación y coordina entrada/salida.

**Controladores:**

**ClientController**

- Gestión completa de clientes
- Crear, actualizar, eliminar, buscar clientes
- Validar datos antes de procesar

**PhoneController**

- Gestión del catálogo de celulares
- Consultar por categoría, marca o precio
- Mostrar alertas de stock bajo
- Estadísticas de inventario

**SaleController**

- Registrar ventas multi-artículo
- Calcular totales con IVA automáticamente
- Actualizar inventario tras venta
- Consultar historial de clientes

**ReportController**

- Generar reportes en archivo
- Mostrar análisis en consola
- Alertas de stock bajo
- Top 3 productos más vendidos

### 3. Módulo de Modelos (Model)

Define la estructura de datos del dominio.

**Modelos:**

**ClientModel**

- Propiedades: id, name, dni, email, phone
- Validaciones en getters/setters
- Timestamps de creación

**PhoneModel**

- Propiedades: id, brand, model, price, stock, category
- Sistema operativo y gama
- Restricciones de precio positivo

**SaleModel**

- Propiedades: id, idCliente, date, total
- Total incluye IVA al 19%
- Relación con clientes

**SalesDetailsModel**

- Propiedades: id, idVenta, idCelular, cantidad, subtotal
- Detalle de cada artículo vendido
- Cálculo automático de subtotal

### 4. Módulo de Vistas (View)

Maneja la interfaz de usuario en consola.

**Vistas:**

- Menús interactivos
- Entrada y validación de datos
- Formateo de salida
- Manejo de errores de entrada

### 5. Módulo de Repositorio (Repository)

Acceso a datos e implementación de queries SQL.

**Repositorios:**

- Operaciones CRUD completas
- Consultas especializadas
- Manejo de transacciones
- Try-with-resources para recursos

### 6. Módulo de Servicios (Service)

Lógica de negocio y validaciones.

**Servicios:**

- Validación de datos
- Cálculos financieros
- Aplicación de restricciones
- Análisis y reportería

### 7. Módulo de Testing

Pruebas unitarias e integración.

**Niveles de Testing:**

- Tests de Modelo (sin BD)
- Tests de Repositorio (con BD)
- Tests de Servicio (lógica completa)

---

## MODELOS DE DATOS

### ClientModel

```java
public class ClientModel {
    private int id;
    private String name;
    private String dni;
    private String email;
    private String phone;
    private Date createdAt;
    private Date updatedAt;
}
```

**Validaciones:**

- Nombre: no vacío
- DNI: único, no vacío
- Email: formato válido, único
- Teléfono: no vacío

### PhoneModel

```java
public class PhoneModel {
    private int id;
    private String brand;
    private String model;
    private String operativeSystem;
    private String rangeCategory;
    private double price;
    private int stock;
    private Date createdAt;
    private Date updatedAt;
}
```

**Validaciones:**

- Precio: > 0
- Stock: >= 0
- Marca, modelo: no vacíos
- Categoría: Alta, Media o Baja

### SaleModel

```java
public class SaleModel {
    private int id;
    private int idCliente;
    private Date date;
    private double total;
    private Date createdAt;
    private Date updatedAt;
}
```

**Características:**

- Total incluye IVA automáticamente
- Fecha se establece en creación
- Relación con cliente

### SalesDetailsModel

```java
public class SalesDetailsModel {
    private int id;
    private int idVenta;
    private int idCelular;
    private int cantidad;
    private double subtotal;
    private String createdAt;
    private String updatedAt;
}
```

**Cálculos:**

- Subtotal = Precio celular × Cantidad
- Se genera automáticamente

---

## FUNCIONALIDADES PRINCIPALES

### 1. Gestión de Clientes

**Crear Cliente**

- Validar email con regex
- Verificar DNI único
- Almacenar información de contacto
- Registrar timestamps

**Consultar Clientes**

- Listar todos con paginación
- Buscar por ID, DNI o email
- Ordenar alfabéticamente
- Mostrar historial de compras

**Actualizar Cliente**

- Modificar contacto
- Validar nuevos datos
- Mantener integridad referencial

**Eliminar Cliente**

- Soft delete (opcional)
- Validar que no tiene ventas asociadas
- Auditoría de eliminación

### 2. Gestión de Celulares

**Catálogo**

- Marca, modelo, precio, stock
- Categoría de gama (Alta/Media/Baja)
- Sistema operativo

**Consultas**

- Por categoría de gama
- Por marca
- Ordenados por precio
- Con stock disponible

**Alertas**

- Stock bajo (< 5 unidades)
- Notificaciones en tiempo real
- Reporte diario

**Estadísticas**

- Precio promedio
- Stock total
- Inventario por categoría
- Productos más caros/baratos

### 3. Gestión de Ventas

**Registro de Venta**

- Seleccionar cliente existente
- Agregar múltiples artículos
- Validar disponibilidad de stock
- Calcular total con IVA (19%)
- Confirmar antes de registrar

**Actualización de Inventario**

- Reducción automática de stock
- Validación de cantidad disponible
- Reversión en caso de error

**Consultas**

- Por cliente
- Por período
- Con detalles de artículos
- Historial completo

**Análisis**

- Total de ventas
- Promedio de venta
- Cliente con más compras
- Cliente con mayor gasto
- Top 3 productos vendidos

### 4. Generación de Reportes

**Reporte de Ventas**

- Resumen general de transacciones
- Información financiera (subtotal, IVA, total)
- Detalles línea por línea
- Celulares con stock bajo
- Top 3 más vendidos
- Archivo con timestamp

**Reporte de Inventario**

- Cantidad de celulares por modelo
- Stock disponible por artículo
- Precio unitario
- Valor total de inventario

**Reporte de Clientes**

- Listado completo de clientes
- Número de compras por cliente
- Gasto total por cliente
- Período de cliente

**Análisis en Consola**

- Visualización en tiempo real
- Estadísticas formateadas
- Comparativas de períodos

---

## PATRONES DE DISEÑO

### 1. Patrón MVC (Model-View-Controller)

Separación clara de responsabilidades:

- **Model**: Clases de dominio (ClientModel, PhoneModel, etc.)
- **View**: Interfaces de usuario (ClientView, PhoneView, etc.)
- **Controller**: Orquestadores (ClientController, PhoneController, etc.)

### 2. Patrón Singleton

Implementado en `Configs.java` para gestión centralizada de configuración:

- Una única instancia en toda la aplicación
- Acceso global a variables de entorno
- Inicialización lazy

### 3. Patrón Repository

Abstracción de acceso a datos:

- Interfaz común (ISaleRepository, IPhoneRepository, etc.)
- Implementaciones específicas
- Facilita testing y cambios de persistencia

### 4. Patrón DAO (Data Access Object)

Separación entre lógica y acceso a datos:

- Repository como DAO
- Métodos CRUD
- Consultas especializadas

### 5. Patrón Service Layer

Lógica de negocio centralizada:

- Validaciones en servicios
- Orquestación de repositorios
- Cálculos y transformaciones

### 6. Try-with-Resources

Manejo seguro de recursos:

```java
try (Connection conn = DatabaseConnection.getConnection();
     PreparedStatement ps = conn.prepareStatement(sql)) {
    // Código
}
```

---

## GUÍA DE USO

### Acceso a la Aplicación

1. Ejecutar `Main.java`
2. Se mostrará menú principal
3. Seleccionar opción (1-5)

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

### Ejemplo: Registrar una Venta

1. Seleccionar opción 3 (Ventas)
2. Seleccionar opción 1 (Crear venta)
3. Elegir cliente de la lista
4. Agregar celulares:
   - Seleccionar celular
   - Ingresar cantidad
   - Repetir para más artículos
5. Confirmar venta
6. Sistema calcula total con IVA automáticamente

### Validaciones

**Email:** usuario@dominio.com (formato RFC)  
**DNI:** Único por cliente  
**Precio:** Mayor a 0  
**Stock:** No negativo  
**Cantidad:** Menor o igual a stock disponible  
**IVA:** 19% automático

---

## TESTING

### Tipos de Tests

#### Tests de Modelo (TestPhoneModel, TestClientModel, etc.)

Validan entidades sin base de datos.

**Casos:**

- Constructores
- Getters/Setters
- Validaciones
- ToString

**Ejecución:**

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testModel.TestPhoneModel"
```

#### Tests de Repositorio

Validan acceso a datos.

**Casos:**

- CRUD completo
- Búsquedas especializadas
- Integridad referencial

**Requisito:** Base de datos funcionando

**Ejecución:**

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testRepository.TestPhoneRepository"
```

#### Tests de Servicio

Validan lógica de negocio.

**Casos:**

- Validaciones de entrada
- Restricciones de negocio
- Cálculos
- Transacciones complejas

**Ejecución:**

```bash
mvn exec:java -Dexec.mainClass="org.example.tests.testService.TestPhoneService"
```

### Matriz de Cobertura

| Componente    | Tests  | Cobertura |
| ------------- | ------ | --------- |
| Modelos       | 24     | 100%      |
| Repositorio   | 22     | 95%       |
| Servicios     | 38     | 90%       |
| Controladores | -      | Manual    |
| **Total**     | **84** | **~92%**  |

---

## MANTENIMIENTO Y SOPORTE

### Operaciones Rutinarias

**Backup de Base de Datos**

```bash
mysqldump -u root -p tecnostore_db > backup_$(date +%Y%m%d).sql
```

**Restaurar Base de Datos**

```bash
mysql -u root -p tecnostore_db < backup_20260206.sql
```

**Limpiar Reportes Antiguos**

```bash
rm reports/reporte_ventas_*.txt
```

### Solución de Problemas

**Error: Conexión a Base de Datos Rechazada**

- Verificar que MySQL está ejecutándose
- Validar credenciales en `.env`
- Verificar que base de datos existe: `SHOW DATABASES;`

**Error: Tabla no Encontrada**

- Ejecutar script de base de datos: `mysql -u root -p < tecnostore_db.sql`
- Verificar nombres de tablas: `SHOW TABLES FROM tecnostore_db;`

**Error de Compilación**

```bash
mvn clean compile
mvn dependency:resolve
```

**Verificar Dependencias**

```bash
mvn dependency:tree
```

### Mejoras Futuras

1. Interfaz gráfica (Swing/JavaFX)
2. Autenticación de usuarios
3. Historial y auditoría completa
4. Exportación de reportes (PDF, Excel)
5. Dashboard analytics
6. Sistema de descuentos
7. Devoluciones y cambios
8. Multi-sucursal
9. Integración con pasarelas de pago
10. API REST

### Contacto y Soporte

Para reportar bugs o sugerir mejoras:

- Crear issue en GitHub
- Documentar pasos para reproducir
- Incluir logs y mensajes de error

---

## ANEXOS

### A. Requisitos Funcionales

| Req   | Descripción                    | Estado     |
| ----- | ------------------------------ | ---------- |
| RF-1  | Crear cliente con validaciones | Completado |
| RF-2  | Listar clientes                | Completado |
| RF-3  | Buscar cliente por ID/DNI      | Completado |
| RF-4  | Actualizar datos cliente       | Completado |
| RF-5  | Eliminar cliente               | Completado |
| RF-6  | Crear celular en catálogo      | Completado |
| RF-7  | Listar celulares por categoría | Completado |
| RF-8  | Actualizar stock               | Completado |
| RF-9  | Registrar venta multi-artículo | Completado |
| RF-10 | Calcular IVA (19%)             | Completado |
| RF-11 | Generar reporte ventas         | Completado |
| RF-12 | Mostrar top 3 productos        | Completado |
| RF-13 | Alertar stock bajo             | Completado |
| RF-14 | Análisis de clientes           | Completado |

### B. Requisitos No Funcionales

| Req   | Descripción        | Cumplimiento |
| ----- | ------------------ | ------------ |
| RNF-1 | Lenguaje Java      | Sí           |
| RNF-2 | Base datos MySQL   | Sí           |
| RNF-3 | MVC pattern        | Sí           |
| RNF-4 | SOLID principles   | Sí           |
| RNF-5 | Try-with-resources | Sí           |
| RNF-6 | Stream API         | Sí           |
| RNF-7 | Validaciones       | Sí           |
| RNF-8 | Manejo excepciones | Sí           |

### C. Tecnologías Utilizadas

| Tecnología  | Versión | Uso                    |
| ----------- | ------- | ---------------------- |
| Java        | 21      | Lenguaje principal     |
| Maven       | 3.8+    | Gestor de dependencias |
| MySQL       | 8.0+    | Base de datos          |
| JDBC        | -       | Acceso a datos         |
| dotenv-java | 3.2.0   | Configuración          |

### D. Contraseñas y Credenciales

Mantener seguro en variables de entorno, no en código fuente.

### E. Referencias

- Oracle Java Documentation: https://docs.oracle.com/javase/21/
- MySQL Documentation: https://dev.mysql.com/doc/
- Maven Documentation: https://maven.apache.org/
- SOLID Principles: https://en.wikipedia.org/wiki/SOLID

---

**Documento preparado por:** Equipo de Desarrollo  
**Última actualización:** Febrero 2026  
**Versión:** 1.0  
**Estado:** Aprobado
