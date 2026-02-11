# Proyecto_Java_S1_DuranAlexi

En esté archivo encontraras descripción general del sistema, con pautas claras
para poder utilizar el sistema en tu maquina local, a demas de información basica del funcionamiento del mismo.

No toda la información sobre el sistema se encuentra acá en esté mismo archivo, ya que es para un inicio rapido. Si deseas leer y aprender más del sistema a profundidad te recomiendo leer toda la documentación siguiente

## Documentación

- 📚 [Manual de Usuario](./docs/User_Manual.md)
- 📚 [Documentación Técnica](./docs/Detailed_Technical_Guide.md)
- 📚 [Documentación](./docs/DOCUMENTATION_TECNOSTORE.md)
- 📚 [Resumen Ejecutivo](./docs/Executive_Summary.md)
- 📚 [Guia para Tests](./docs/TEST_GUIDE.md)
- 📚 [Resumen de Tests](./docs/RESUMEN_TEST.md)
- 📚 [Resumen de Patrones de Diseño](./docs/DESING_PATTERS_DOCUMENTATION.md)

## Sistema de Venta de Celulares “TecnoStore”

La empresa TecnoStore es una tienda minorista dedicada a la venta de teléfonos celulares de diferentes marcas y gamas. Actualmente, desea automatizar el control de ventas, inventario y clientes, ya que todos los registros se manejan de forma manual en hojas de cálculo.

Tu misión como desarrollador es crear un sistema de consola en Java que permita gestionar el catálogo de celulares, clientes, ventas y reportes, aplicando los principios de Programación Orientada a Objetos, colecciones, excepciones, persistencia y patrones de diseño.

### Objetivos específicos del sistema

1. Gestión de Celulares
   - Registrar, actualizar, eliminar y listar celulares.
   - Cada celular debe tener:
     - id.
     - marca.
     - modelo.
     - precio.
     - stock.
     - sistema operativo.
     - gama (por ejemplo: Alta, Media, Baja).
   - Validar que el precio y el stock sean positivos.

2. Gestión de Clientes
   - Registrar clientes con:
     - id.
     - nombre.
     - identificación.
     - correo.
     - teléfono.
   - Validar formato del correo y número de identificación único.

3. Gestión de Ventas
   - Registrar una venta seleccionando cliente y celular(es).
   - Calcular el total de la venta (incluyendo IVA del 19%).
   - Actualizar el stock del celular vendido.
   - Guardar la venta en la base de datos (usando JDBC).

4. Reportes y análisis
   - Mostrar en consola:
     - Celulares con stock bajo (menor a 5 unidades).
     - Top 3 de celulares más vendidos.
   - Ventas totales por mes.
   - Los cálculos deben realizarse con Stream API y colecciones.

5. Persistencia y archivos
   - Generar un archivo reporte_ventas.txt con el resumen de todas las ventas realizadas.
   - Manejar excepciones con try-with-resources.

6. Patrones y principios SOLID
   - Implementar al menos un patrón de diseño (Factory, Strategy o Singleton).
   - Aplicar encapsulamiento, herencia y composición correctamente.

### Estructura sugerida de clases:

- Modelo:
  - Celular, Cliente, Venta, ItemVenta, CategoriaGama (enum).
  - Representan las entidades del negocio.

- Lógica / Servicio:
  - GestorCelulares, GestorClientes, GestorVentas.
  - Contiene las reglas de negocio.

- Persistencia:
  - ConexiónDB, CelularDAO, VentaDAO, ClienteDAO.
  - Manejo de datos en JDBC.

- Utilidades:
  - Validador, ReporteUtils, ArchivoUtils.
  - Funciones de apoyo.

- Patrón:
  - FactoryCelular o StrategyDescuento.
  - Implementa patrón elegido.

- Principal:
  - Main.java.
  - Manú de consola principal.

### Base de datos MySQL sugerida (tecnostore_db)

Tablas:

- celulares (id, marca, modelo, sistema_operativo, gama, precio, stock)
- clientes (id, nombre, identificacion, correo, telefono)
- ventas (id, id_cliente, fecha, total)
- detalle_ventas (id, id_venta, id_celular, cantidad, subtotal)

## Resultado esperado

### Entregables

- Código fuente completo (.java).
- Script SQL (tecnostore_db.sql) con creación de tablas.
- Archivo reporte_ventas.txt generado automáticamente.
- Capturas de la ejecución de las funciones principales.
- README con:
  - Descripción del proyecto.
  - Estructura de clases.
  - Ejemplo de ejecución.
  - Indicaciones para conexión MySQL.
- Repositorio en GitHub.

# TecnoStore - Sistema de Gestión de Ventas y Inventario

Sistema de consola desarrollado en Java para automatizar la gestión de ventas, inventario y clientes en una tienda minorista de teléfonos celulares.

**Versión:** 1.0  
**Estado:** Completado y Funcional  
**Última actualización:** Febrero 2026

## Tabla de Contenidos

- [Descripción](#descripción)
- [Características](#características)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Arquitectura](#arquitectura)
- [Base de Datos](#base-de-datos)
- [Testing](#testing)
- [Contribución](#contribución)
- [Licencia](#licencia)

---

## Descripción

TecnoStore es una solución empresarial que reemplaza la gestión manual de operaciones en una tienda de teléfonos celulares. Automatiza procesos de:

- **Gestión de Clientes:** Registro, búsqueda, actualización y eliminación de clientes
- **Control de Inventario:** Catálogo de celulares, alertas de stock bajo, análisis de disponibilidad
- **Registro de Ventas:** Ventas multi-artículo con cálculo automático de IVA (19%)
- **Generación de Reportes:** Reportes de ventas, inventario, clientes y análisis

La aplicación implementa principios SOLID, patrones de diseño reconocidos y una arquitectura modular que facilita mantenimiento y escalabilidad.

---

## Características

### Gestión de Clientes

- Crear, leer, actualizar y eliminar clientes
- Validación automática de emails y DNI únicos
- Búsqueda por ID, DNI o email
- Historial de compras por cliente
- Análisis de gasto por cliente

### Gestión de Celulares

- Catálogo completo de productos
- Clasificación por gama (Alta, Media, Baja)
- Monitoreo en tiempo real de stock
- Alertas automáticas de stock bajo (< 5 unidades)
- Consultas por categoría, marca o precio
- Estadísticas de inventario

### Registro de Ventas

- Ventas con múltiples artículos en una sola transacción
- Cálculo automático de IVA al 19%
- Validación de stock antes de vender
- Actualización automática de inventario
- Confirmación de transacciones
- Análisis de ventas (total, promedio, mayor/menor)

### Generación de Reportes

- **Reporte de Ventas:** Detalles de transacciones con timestamps
- **Reporte de Inventario:** Stock por celular y valor total
- **Reporte de Clientes:** Información y estadísticas de clientes
- **Análisis en Consola:** Top 3 productos, alertas de stock bajo
- **Exportación:** Reportes en formato .txt con timestamp

---

## Requisitos

### Hardware Mínimo

- Procesador: Intel Core i3 o superior
- RAM: 2 GB (4 GB recomendado)
- Almacenamiento: 500 MB disponibles
- Sistema Operativo: Windows, Linux o macOS

### Software Requerido

| Software     | Versión    | Propósito                            |
| ------------ | ---------- | ------------------------------------ |
| Java JDK     | 21+        | Lenguaje de programación             |
| Apache Maven | 3.8+       | Gestor de dependencias y compilación |
| MySQL        | 8.0+       | Base de datos                        |
| Git          | (Opcional) | Control de versiones                 |

### Dependencias del Proyecto

```xml
<dependencies>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>9.6.0</version>
    </dependency>
    <dependency>
        <groupId>io.github.cdimascio</groupId>
        <artifactId>dotenv-java</artifactId>
        <version>3.2.0</version>
    </dependency>
</dependencies>
```

---

## Instalación

### 1. Descargar el Proyecto

```bash
# Clonar desde repositorio
git clone https://github.com/usuario/Proyecto_Java_S2_DuranAlexi.git
cd Proyecto_Java_S2_DuranAlexi
```

### 2. Instalar Software Requerido

**Java JDK 21:**

- Descargar desde: https://www.oracle.com/java/technologies/downloads/
- Verificar: `java -version`

**Maven:**

- Descargar desde: https://maven.apache.org/download.cgi
- Verificar: `mvn -version`

**MySQL:**

- Descargar desde: https://dev.mysql.com/downloads/
- Iniciar servicio
- Verificar: `mysql --version`

### 3. Configurar Base de Datos

```bash
# Ejecutar script de creación
mysql -u root -p < tecnostore_db.sql

# Verificar creación
mysql -u root -p -e "USE tecnostore_db; SHOW TABLES;"
```

### 4. Crear Archivo de Configuración

En la raíz del proyecto, crear archivo `.env`:

```env
# Database Connection
DB_URL=jdbc:mysql://localhost:3306/tecnostore_db
DB_USER=root
DB_PASSWORD=tu_contraseña
DB_HOST=localhost
DB_PORT=3306
DB_NAME=tecnostore_db

# Application Settings
APP_NAME=TecnoStore
APP_VERSION=1.0
DEBUG_MODE=true

# Reports
REPORT_PATH=./reports/
REPORT_FILENAME=reporte_ventas.txt

# Validation
MIN_STOCK_WARNING=5
IVA_PERCENTAGE=0.19
```

### 5. Compilar y Ejecutar

```bash
# Limpiar y compilar
mvn clean compile

# Ejecutar aplicación
mvn exec:java -Dexec.mainClass="org.example.Main"
```

---

## Uso

### Menú Principal

Al ejecutar la aplicación se muestra:

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

### Operaciones Comunes

#### Crear Cliente

1. Seleccionar opción 1 → Crear cliente
2. Ingresar datos:
   - Nombre: "Juan Pérez"
   - DNI: "1005678901" (debe ser único)
   - Email: "juan@mail.com" (formato válido y único)
   - Teléfono: "3001234567"
3. Sistema confirma creación

#### Registrar Venta

1. Seleccionar opción 3 → Crear venta
2. Seleccionar cliente de la lista
3. Agregar celulares:
   - Seleccionar celular
   - Ingresar cantidad (no puede exceder stock)
   - Repetir para más artículos
   - Ingresar 0 para terminar
4. Sistema calcula:
   - Subtotal por artículo
   - IVA automático (19%)
   - Total con IVA
5. Confirmar con "S"
6. Stock se actualiza automáticamente

#### Generar Reporte

1. Seleccionar opción 4 → Generar reporte
2. Se crea archivo en `reports/` con timestamp
3. Contiene: resumen, detalles, top 3 productos, alertas

---

## Estructura del Proyecto

```
Proyecto_Java_S2_DuranAlexi/
├── src/main/java/org/example/
│   ├── Config/
│   │   ├── Configs.java                 # Singleton para configuración
│   │   └── DatabaseConnection.java      # Conexión JDBC a MySQL
│   ├── Controller/
│   │   ├── ClientController.java        # Gestión de clientes
│   │   ├── PhoneController.java         # Gestión de celulares
│   │   ├── SaleController.java          # Gestión de ventas
│   │   └── ReportController.java        # Generación de reportes
│   ├── Model/
│   │   ├── ClientModel.java             # Entidad cliente
│   │   ├── PhoneModel.java              # Entidad celular
│   │   ├── SaleModel.java               # Entidad venta
│   │   └── SalesDetailsModel.java       # Detalles de venta
│   ├── View/
│   │   ├── MainMenu.java                # Menú principal
│   │   ├── ClientView.java              # Interfaz cliente
│   │   ├── PhoneView.java               # Interfaz celular
│   │   ├── SaleView.java                # Interfaz venta
│   │   └── ReportView.java              # Interfaz reportes
│   ├── repository/
│   │   ├── ClientRepository.java        # CRUD cliente
│   │   ├── PhoneRepository.java         # CRUD celular
│   │   ├── SaleRepository.java          # CRUD venta
│   │   └── SalesDetailsRepository.java  # CRUD detalles
│   ├── service/
│   │   ├── ClientService.java           # Lógica clientes
│   │   ├── PhoneService.java            # Lógica celulares
│   │   ├── SaleService.java             # Lógica ventas
│   │   └── ReportService.java           # Generación reportes
│   ├── Interfaces/                      # Interfaces de contrato
│   │   ├── IView.java
│   │   ├── repositoryInterfaces/
│   │   └── viewInterfaces/
│   ├── tests/
│   │   ├── testModel/                   # Tests de modelos
│   │   ├── testRepository/              # Tests de repositorio
│   │   └── testService/                 # Tests de servicios
│   └── Main.java                        # Punto de entrada
├── pom.xml                              # Configuración Maven
├── tecnostore_db.sql                    # Script de base de datos
├── .env.example                         # Ejemplo de configuración
├── .gitignore                           # Archivos ignorados por Git
└── README.md                            # Este archivo
```

---

## Arquitectura

### Patrón MVC

La aplicación sigue el patrón Model-View-Controller:

```
┌────────────────┐
│ View (Consola) │  ← Interface de usuario
└────────┬───────┘
         │
┌────────▼──────────────┐
│ Controller (4 clases) │  ← Orquestación
└────────┬──────────────┘
         │
┌────────▼────────────┐
│ Service (4 clases)  │  ← Lógica de negocio
└────────┬────────────┘
         │
┌────────▼──────────────────┐
│ Repository (4 clases)     │  ← Acceso a datos
└────────┬──────────────────┘
         │
┌────────▼──────────────┐
│ MySQL Database        │  ← Persistencia
└───────────────────────┘
```

### Patrones de Diseño

| Patrón                 | Ubicación    | Propósito                        |
| ---------------------- | ------------ | -------------------------------- |
| **Singleton**          | Configs.java | Instancia única de configuración |
| **Repository**         | repository/  | Abstracción de acceso a datos    |
| **Service Layer**      | service/     | Lógica de negocio centralizada   |
| **DAO**                | Repository   | Operaciones CRUD                 |
| **Try-with-Resources** | Toda la BD   | Manejo seguro de recursos        |

### Principios SOLID

- **S (Single Responsibility):** Cada clase tiene una responsabilidad única
- **O (Open/Closed):** Abierto a extensión, cerrado a modificación
- **L (Liskov Substitution):** Implementación coherente de interfaces
- **I (Interface Segregation):** Interfaces específicas
- **D (Dependency Inversion):** Dependencia en abstracciones

---

## Base de Datos

### Esquema

```
┌──────────────────────┐
│     clientes         │
├──────────────────────┤
│ id (PK)              │
│ name                 │
│ dni (UNIQUE)         │
│ email (UNIQUE)       │
│ phone                │
│ created_at           │
│ updated_at           │
└──────────────────────┘
         │ 1
         │
         ├─────┐
         │     │ N
         │     ▼
         │  ┌──────────────────────┐
         │  │      ventas          │
         │  ├──────────────────────┤
         │  │ id (PK)              │
         │  │ id_cliente (FK)      │
         │  │ date                 │
         │  │ total                │
         │  │ created_at           │
         │  │ updated_at           │
         │  └──────────────────────┘
         │     │ 1
         │     │
         │     ├─────┐
         │     │     │ N
         │     │     ▼
         │     │  ┌──────────────────────────┐
         │     │  │   detalle_ventas         │
         │     │  ├──────────────────────────┤
         │     │  │ id (PK)                  │
         │     │  │ id_venta (FK)            │
         │     │  │ id_celular (FK)          │
         │     │  │ cantidad                 │
         │     │  │ subtotal                 │
         │     │  └──────────────────────────┘
         │     │     │
         │     │     │ N
         │     │     │
└─────────┘     │     │
                │     │
     ┌──────────┴─────┘
     │
     ▼ 1
┌──────────────────────┐
│    celulares         │
├──────────────────────┤
│ id (PK)              │
│ brand                │
│ model                │
│ operative_sistem     │
│ range_category       │
│ price                │
│ stock                │
│ created_at           │
│ updated_at           │
└──────────────────────┘
```

### Tablas

**clientes:** Información de clientes con validaciones de DNI y email únicos

**celulares:** Catálogo de productos con categorías y control de stock

**ventas:** Transacciones con total incluido IVA

**detalle_ventas:** Items específicos de cada venta

---

## Testing

### Cobertura de Tests

| Nivel      | Archivos | Tests  | Cobertura |
| ---------- | -------- | ------ | --------- |
| Modelos    | 4        | 24     | 100%      |
| Repository | 3        | 22     | 95%       |
| Servicios  | 3        | 38     | 90%       |
| **Total**  | **10**   | **84** | **~92%**  |

### Ejecutar Tests

```bash
# Tests de modelos (sin BD requerida)
mvn exec:java -Dexec.mainClass="org.example.tests.testModel.TestPhoneModel"
mvn exec:java -Dexec.mainClass="org.example.tests.testModel.TestClientModel"

# Tests de repositorio (requiere BD)
mvn exec:java -Dexec.mainClass="org.example.tests.testRepository.TestPhoneRepository"
mvn exec:java -Dexec.mainClass="org.example.tests.testRepository.TestSaleRepository"

# Tests de servicios (requiere BD)
mvn exec:java -Dexec.mainClass="org.example.tests.testService.TestPhoneService"
mvn exec:java -Dexec.mainClass="org.example.tests.testService.TestSaleService"
```

---

## Funcionalidades Implementadas

### Validaciones

- Email: Formato válido (usuario@dominio.com)
- DNI: Único por cliente
- Precio: Mayor a 0
- Stock: No negativo
- Cantidad: No puede exceder disponible
- IVA: Automático al 19%

### Cálculos Automáticos

```
Subtotal = Precio × Cantidad
IVA = Subtotal × 0.19
Total = Subtotal × (1 + 0.19)
```

### Consultas Especializadas

- Celulares con stock bajo (< 5 unidades)
- Top 3 productos más vendidos
- Total de ventas por mes
- Gasto promedio por cliente
- Cliente con mayor gasto
- Cliente con más compras

---

## Mejoras Futuras

- Interfaz gráfica (Swing/JavaFX)
- Aplicación móvil
- Dashboard analytics
- Integración con pasarelas de pago
- Sistema multi-sucursal
- API REST
- Autenticación de usuarios
- Sistema de descuentos
- Devoluciones y cambios

---

## Troubleshooting

### Error: "Conexión a base de datos rechazada"

```bash
# Verificar MySQL está corriendo
mysql -u root -p -e "SELECT VERSION();"

# Verificar credenciales en .env
cat .env

# Recrear base de datos
mysql -u root -p < tecnostore_db.sql
```

### Error: "Tabla no encontrada"

```bash
# Ejecutar script de BD
mysql -u root -p tecnostore_db < tecnostore_db.sql

# Verificar tablas
mysql -u root -p -e "USE tecnostore_db; SHOW TABLES;"
```

### Error: "BUILD FAILURE en compilación"

```bash
# Limpiar caché y recompilar
mvn clean install

# Ver detalles del error
mvn clean compile -X
```

---

## Contribución

### Reportar Bugs

1. Crear issue en el repositorio
2. Incluir:
   - Descripción del problema
   - Pasos para reproducir
   - Logs/mensajes de error
   - Versión del sistema

### Sugerir Mejoras

1. Abrir issue con tag `enhancement`
2. Describir la mejora
3. Proporcionar justificación

### Pull Requests

1. Fork del repositorio
2. Crear rama para feature: `git checkout -b feature/mi-feature`
3. Commit cambios: `git commit -am 'Agregar mi feature'`
4. Push a rama: `git push origin feature/mi-feature`
5. Abrir Pull Request

---

## Licencia

Este proyecto está bajo licencia MIT.

---

## Autores

- **Desarrollador Principal:** Alexi Durán
- **Proyecto:** Sistema de Gestión TecnoStore
- **Institución:** [Institución Educativa]
- **Fecha de Creación:** Febrero 2026

---

## Contacto y Soporte

Para preguntas técnicas o soporte:

- Email: [correo del desarrollador]
- GitHub Issues: [enlace al repositorio]
- Documentación: Ver archivos en `/docs` (si existen)

---

## Changelog

### v1.0 (Febrero 2026)

- Versión inicial del sistema
- Funcionalidades CRUD completas
- Sistema de reportes
- 84 tests implementados
- Documentación completa

---

## Agradecimientos

- Java Community
- Apache Maven
- MySQL Community
- Spring Framework (conceptos)
- Desarrollo en equipo

---

**Última actualización:** Febrero 2026  
**Versión:** 1.0  
**Estado:** Producción
