# MANUAL DE USUARIO - TECNOSTORE

**Versión:** 1.0  
**Fecha:** Febrero 2026  
**Dirigido a:** Gerentes, Vendedores y Personal Administrativo

---

## TABLA DE CONTENIDOS

1. [Inicio Rápido](#inicio-rápido)
2. [Pantalla Principal](#pantalla-principal)
3. [Gestión de Clientes](#gestión-de-clientes)
4. [Gestión de Celulares](#gestión-de-celulares)
5. [Registro de Ventas](#registro-de-ventas)
6. [Generación de Reportes](#generación-de-reportes)
7. [Consultas Frecuentes](#consultas-frecuentes)
8. [Resolución de Problemas](#resolución-de-problemas)

---

## INICIO RÁPIDO

### Paso 1: Ejecutar la Aplicación

La aplicación se inicia automáticamente al abrir el programa TecnoStore.

Si necesita iniciar manualmente:

1. Abrir terminal/consola
2. Navegar a la carpeta del proyecto
3. Ejecutar: `mvn exec:java -Dexec.mainClass="org.example.Main"`

### Paso 2: Pantalla de Bienvenida

```
=======================================================
        Bienvenido a Tecno Store System
=======================================================

Versión 1.0
Sistema de gestión de ventas, inventario y reportes

=======================================================
```

Una vez iniciado, verá el menú principal.

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

### Navegación

- Ingrese el número (1-5) de la opción deseada
- Presione Enter
- El sistema le dirigirá al módulo seleccionado

---

## GESTIÓN DE CLIENTES

### Submenú de Clientes

Al seleccionar opción 1 del menú principal:

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

### Crear Cliente (Opción 1)

**Pasos:**

1. Selecciona opción 1
2. Sistema muestra formulario:

```
============================================================
                   Registro de Cliente
============================================================
Nombre:
```

3. Ingrese nombre completo del cliente
   - Ejemplo: "Juan Pérez García"
   - Requisito: Mínimo 3 caracteres

```
C.C:
```

4. Ingrese número de cédula o identificación
   - Ejemplo: "1005678901"
   - Requisito: Debe ser único, no puede repetirse

```
Email:
```

5. Ingrese correo electrónico
   - Ejemplo: "juan.perez@email.com"
   - Requisito: Formato válido (usuario@dominio.com)
   - Requisito: Debe ser único

```
Numero de telefono:
```

6. Ingrese número telefónico
   - Ejemplo: "3001234567"
   - Requisito: No puede estar vacío

7. Sistema valida y confirma:

```
✓ Cliente creado exitosamente
```

**Posibles Errores:**

| Error                                          | Causa            | Solución                         |
| ---------------------------------------------- | ---------------- | -------------------------------- |
| "El nombre es requerido"                       | Campo vacío      | Ingrese nombre válido            |
| "Ya existe un cliente con esta identificación" | DNI duplicado    | Use DNI diferente                |
| "El formato del email no es válido"            | Email incorrecto | Use formato: usuario@dominio.com |
| "Ya existe un cliente con este email"          | Email duplicado  | Use email diferente              |
| "El teléfono es requerido"                     | Campo vacío      | Ingrese teléfono válido          |

### Ver Todos los Clientes (Opción 2)

**Pasos:**

1. Selecciona opción 2
2. Sistema muestra listado:

```
=======================================================
                    CLIENTES REGISTRADOS
=======================================================
Total de clientes: 5

Clientes: (5)
=======================================================
    Id: 1
    Nombre: Carlos Perez
    C.c: 1002345678
    Email: carlos@mail.com
    Telefono: 3001234567
    F. de creacion: 2026-02-05
    F. de actualizacion: 2026-02-05

=======================================================
    Id: 2
    Nombre: Ana Torres
    C.c: 1003456789
    Email: ana@mail.com
    Telefono: 3019876543
    F. de creacion: 2026-02-05
    F. de actualizacion: 2026-02-05

=======================================================
[Más clientes...]
```

3. Sistema muestra todos los clientes registrados
4. Presione Enter para volver

### Buscar Cliente por ID (Opción 3)

**Pasos:**

1. Selecciona opción 3
2. Sistema solicita:

```
Introduzca el id:
```

3. Ingrese número del cliente (Ej: "1")
4. Sistema muestra información:

```
=======================================================
    Id: 1
    Nombre: Carlos Perez
    C.c: 1002345678
    Email: carlos@mail.com
    Telefono: 3001234567
    F. de creacion: 2026-02-05
    F. de actualizacion: 2026-02-05
=======================================================
```

### Buscar Cliente por DNI (Opción 4)

**Pasos:**

1. Selecciona opción 4
2. Sistema solicita:

```
Introduzca el DNI:
```

3. Ingrese número de identificación (Ej: "1002345678")
4. Sistema muestra información del cliente con ese DNI

### Actualizar Cliente (Opción 5)

**Pasos:**

1. Selecciona opción 5
2. Sistema solicita ID del cliente:

```
Introduzca el id:
```

3. Ingrese ID (Ej: "1")
4. Sistema muestra datos actuales:

```
Datos actuales:
  Nombre: Carlos Perez
  C.C: 1002345678
  Email: carlos@mail.com
  Telefono: 3001234567
```

5. Sistema solicita nuevos datos (igual que crear cliente)
6. Ingrese datos nuevos
7. Sistema confirma:

```
✓ Usuario actualizado exitosamente.
```

### Eliminar Cliente (Opción 6)

**Pasos:**

1. Selecciona opción 6
2. Sistema solicita ID:

```
Introduzca el id:
```

3. Ingrese ID (Ej: "1")
4. Sistema muestra datos del cliente:

```
¿Deseas eliminar el siguiente cliente?
  Nombre: Carlos Perez
  C.C: 1002345678
  (S/N):
```

5. Ingrese "S" para confirmar o "N" para cancelar
6. Sistema confirma:

```
✓ Usuario eliminado exitosamente
```

---

## GESTIÓN DE CELULARES

### Submenú de Celulares

Al seleccionar opción 2 del menú principal:

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

### Crear Celular (Opción 1)

**Pasos:**

1. Selecciona opción 1
2. Sistema muestra formulario:

```
============================================================
                 Registro de Celular
============================================================
Marca:
```

3. Ingrese marca (Ej: "Samsung")

```
Modelo:
```

4. Ingrese modelo (Ej: "Galaxy S23")

```
Sistema operativo:
```

5. Ingrese SO (Ej: "Android")

```
Gama (Alta/Media/Baja):
```

6. Ingrese categoría (Ej: "Alta")

```
Precio:
```

7. Ingrese precio en pesos (Ej: "4200.00")
   - Requisito: Mayor a 0

```
Stock actual:
```

8. Ingrese cantidad en stock (Ej: "15")
   - Requisito: No negativo

9. Sistema confirma:

```
✓ Celular creado exitosamente
```

### Ver Todos los Celulares (Opción 2)

**Muestra:**

```
============================================================
                    CATÁLOGO DE CELULARES
============================================================
Total de celulares: 5

=======================================================
    Id: 1
    Marca: Samsung
    Modelo: Galaxy S23
    SO: Android
    Gama: Alta
    Precio: 4200.0
    Stock: 15
    Creado: 2026-02-05
    Actualizado: 2026-02-05

=======================================================
    Id: 2
    Marca: Apple
    Modelo: iPhone 14
    SO: iOS
    Gama: Alta
    Precio: 5200.0
    Stock: 10
    ...
```

### Buscar Celular por ID (Opción 3)

**Pasos:**

1. Selecciona opción 3
2. Ingresa ID del celular
3. Sistema muestra información detallada

### Ver Celulares por Categoría (Opción 4)

**Pasos:**

1. Selecciona opción 4
2. Sistema muestra opciones:

```
¿Cuál es la categoría?
1. Alta
2. Media
3. Baja
Selecciona:
```

3. Ingrese número (1-3)
4. Sistema muestra celulares de esa categoría

### Actualizar Celular (Opción 5)

Similar a actualizar cliente. Sistema solicita ID y nuevos datos.

### Eliminar Celular (Opción 6)

Similar a eliminar cliente. Sistema solicita confirmación.

---

## REGISTRO DE VENTAS

### Submenú de Ventas

Al seleccionar opción 3 del menú principal:

```
=======================================================
                Gestionar Ventas
=======================================================
Elija una opción:
    1. Crear venta.
    2. Ver todos los ventas.
    3. Buscar venta por ID.
    4. Buscar venta por Cliente.
    5. Estadísticas de ventas.
    6. Top 3 celulares más vendidos.
    7. Volver al menú principal.

=======================================================
```

### Crear Venta (Opción 1)

Este es el proceso más importante. Siga cuidadosamente:

**Fase 1: Seleccionar Cliente**

```
============================================================
               REGISTRAR NUEVA VENTA
============================================================

--- SELECCIONAR CLIENTE ---

Clientes disponibles:
1. Carlos Perez (ID: 1, DNI: 1002345678)
2. Ana Torres (ID: 2, DNI: 1003456789)
3. Luis Gomez (ID: 3, DNI: 1004567890)
4. Maria Lopez (ID: 4, DNI: 1005678901)

Selecciona el número del cliente:
```

Ingrese número del cliente (1-4):

```
✓ Cliente seleccionado: Carlos Perez
```

**Fase 2: Seleccionar Celulares**

```
--- SELECCIONAR CELULARES ---

Celulares disponibles:
1. Samsung Galaxy S23 - Precio: $4200.00 (Stock: 15)
2. Apple iPhone 14 - Precio: $5200.00 (Stock: 10)
3. Xiaomi Redmi Note 12 - Precio: $1800.00 (Stock: 30)
4. Motorola Moto G54 - Precio: $1600.00 (Stock: 25)
5. Huawei P40 Lite - Precio: $2000.00 (Stock: 12)

Selecciona el número del celular (0 para terminar):
```

Ingrese número del celular:

```
¿Cuántas unidades? (máx: 15):
```

Ingrese cantidad:

```
✓ Añadido: Samsung Galaxy S23 x 1
```

Puede agregar más celulares. Cuando termine, ingrese "0":

```
Selecciona el número del celular (0 para terminar): 0
```

**Fase 3: Confirmación y Cálculo**

```
--- CONFIRMAR VENTA ---

Detalles de la venta:
Cliente: Carlos Perez

Artículos:
  - Samsung Galaxy S23 x 1: $4,200.00
  - Xiaomi Redmi Note 12 x 2: $3,600.00

Subtotal: $7,800.00
IVA (19%): $1,482.00
Total: $9,282.00

¿Confirmar venta? (S/N):
```

Ingrese "S" para confirmar o "N" para cancelar:

```
✓ Venta registrada exitosamente
Total a pagar: $9,282.00
```

**Notas Importantes:**

- El IVA se calcula automáticamente al 19%
- El stock se reduce automáticamente
- No puede haber celulares duplicados en una venta
- No puede vender más cantidad que el stock disponible

### Ver Todas las Ventas (Opción 2)

Muestra lista de todas las ventas con totales.

### Buscar Venta por ID (Opción 3)

Permite buscar una venta específica por ID.

### Buscar Ventas por Cliente (Opción 4)

Ingrese ID del cliente y sistema muestra todas sus compras.

### Estadísticas de Ventas (Opción 5)

```
============================================================
               ANÁLISIS DE VENTAS
============================================================

--- INFORMACIÓN GENERAL ---
Total de ventas realizadas: 4
Total de artículos vendidos: 4

--- INFORMACIÓN FINANCIERA ---
Total de ventas (sin IVA): $36,134.45
IVA recaudado (19%): $6,865.55
Total de ventas (con IVA): $43,000.00
Promedio por venta: $10,750.00
Promedio de artículos por venta: 1.00

--- ESTADÍSTICAS DE CLIENTES ---
Cliente con más compras: Carlos Perez (ID: 1)
Cliente que más ha gastado: Carlos Perez (ID: 1)
Gasto promedio por cliente: $14,333.33

============================================================
```

### Top 3 Celulares (Opción 6)

```
============================================================
      TOP 3 CELULARES MÁS VENDIDOS
============================================================

1. Xiaomi Redmi Note 12
   Cantidad vendida: 2 unidades
   Ingresos totales: $3,600.00

2. Samsung Galaxy S23
   Cantidad vendida: 1 unidad
   Ingresos totales: $4,200.00

3. Apple iPhone 14
   Cantidad vendida: 1 unidad
   Ingresos totales: $5,200.00

============================================================
```

---

## GENERACIÓN DE REPORTES

### Submenú de Reportes

Al seleccionar opción 4 del menú principal:

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

### Generar Reporte de Ventas (Opción 1)

**Pasos:**

1. Selecciona opción 1
2. Sistema muestra:

```
============================================================
            GENERANDO REPORTE DE VENTAS
============================================================

✓ Reporte generado exitosamente
  Ubicación: reports/reporte_ventas_2026-02-06_12-13-10.txt

  El reporte incluye:
  • Resumen general de ventas
  • Información financiera (totales, promedios, IVA)
  • Detalles de cada venta
  • Celulares con stock bajo
  • Top 3 celulares más vendidos
  • Ventas por mes
```

3. Archivo se crea automáticamente en carpeta `reports/`

### Ver Total de Ventas (Opción 2)

Muestra resumen de ventas en consola:

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

### Generar Reporte de Inventario (Opción 3)

Crea archivo `reporte_inventario.txt` con:

- Cantidad de celulares
- Stock total
- Listado detallado

### Generar Reporte de Clientes (Opción 4)

Crea archivo `reporte_clientes.txt` con:

- Total de clientes
- Información de cada cliente
- Número de compras
- Total gastado

### Ver Resumen de Ventas (Opción 5)

Muestra análisis en consola (similar a opción 2).

### Ver Top 3 Más Vendidos (Opción 6)

Muestra ranking de celulares más vendidos.

---

## CONSULTAS FRECUENTES

### P: ¿Cuál es el porcentaje de IVA?

R: El IVA es de 19% y se calcula automáticamente al registrar una venta.

Ejemplo: Si compra $1,000 en celulares, pagará $1,190 (incluido IVA).

### P: ¿Cómo sé qué celulares tienen stock bajo?

R: El sistema alerta automáticamente cuando el stock es menor a 5 unidades.

También puede generar un reporte que incluye esta información.

### P: ¿Puedo agregar el mismo celular dos veces en una venta?

R: No, el sistema no lo permite. Debe agregar la cantidad total en una sola línea.

### P: ¿Qué pasa si me equivoco al registrar una venta?

R: Puede buscar la venta por ID y revisar los detalles. Si necesita cambios, contacte al administrador del sistema.

### P: ¿Dónde se guardan los reportes?

R: En la carpeta `reports/` del proyecto. Están en formato `.txt`.

Nombre: `reporte_ventas_YYYY-MM-DD_HH-MM-SS.txt`

### P: ¿Puedo eliminar un cliente que tiene compras?

R: Sí, pero se recomienda hacerlo con cuidado. El sistema lo permite pero afectará los reportes históricos.

### P: ¿El email debe tener un formato específico?

R: Sí, debe ser: `usuario@dominio.com`

Ejemplos válidos:

- carlos@mail.com
- juan.perez@empresa.co
- ana_torres@domain.com

### P: ¿Hay un límite de clientes o celulares?

R: No, el sistema puede manejar miles de registros sin problema.

---

## RESOLUCIÓN DE PROBLEMAS

### Problema: "Error de conexión a base de datos"

**Causa:** MySQL no está funcionando o credenciales incorrectas.

**Solución:**

1. Verificar que MySQL está corriendo
2. Revisar archivo `.env`:
   - DB_URL correcto
   - DB_USER correcto
   - DB_PASSWORD correcto
3. Verificar que base de datos existe

```bash
mysql -u root -p -e "SHOW DATABASES;"
```

### Problema: "Tabla no encontrada"

**Causa:** Base de datos no está configurada.

**Solución:**

1. Ejecutar script de BD:

```bash
mysql -u root -p < tecnostore_db.sql
```

2. Verificar tablas:

```bash
mysql -u root -p -e "USE tecnostore_db; SHOW TABLES;"
```

### Problema: No puedo ingresar número (pide número en campo de texto)

**Causa:** Formato de entrada incorrecto.

**Solución:**

- Ingrese solo el número (sin espacios)
- Presione Enter
- No use caracteres especiales

### Problema: Email rechazado aunque parece válido

**Causa:** Formato no coincide con patrón requerido.

**Solución:**

Asegúrese que email sea: `usuario@dominio.com`

- Debe tener @
- Debe tener punto (.)
- Sin espacios
- Sin caracteres especiales (excepto: +, \_, .)

### Problema: No aparecen los celulares en la venta

**Causa:** Catálogo vacío o sin stock.

**Solución:**

1. Ir a "Gestionar Celulares"
2. Crear al menos un celular
3. Asegurar que tiene stock > 0

---

**Fin del Manual de Usuario**

Para soporte técnico: consulte al administrador del sistema.

Para documentación técnica detallada: consulte GUIA_TECNICA_DETALLADA.md
