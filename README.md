# Proyecto_Java_S1_DuranAlexi

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
