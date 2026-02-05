DROP DATABASE IF EXISTS tecnostore_db;

CREATE DATABASE tecnostore_db;

USE tecnostore_db;

-- Phones Table
CREATE TABLE celulares (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    brand VARCHAR(50) NOT NULL UNIQUE,
    model VARCHAR(255) NOT NULL,
    operative_sistem VARCHAR(100) NOT NULL,
    range_category VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Customers Table
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    dni VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Vales Table
CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES clientes (id)
);

-- Sale Details Table
CREATE TABLE detalle_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_celular INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_venta) REFERENCES ventas (id),
    FOREIGN KEY (id_celular) REFERENCES celulares (id)
);

-- Phones insertion
INSERT INTO
    celulares (
        brand,
        model,
        operative_sistem,
        range_category,
        price,
        stock
    )
VALUES (
        'Samsung',
        'Galaxy S23',
        'Android',
        'Alta',
        4200.00,
        15
    ),
    (
        'Apple',
        'iPhone 14',
        'iOS',
        'Alta',
        5200.00,
        10
    ),
    (
        'Xiaomi',
        'Redmi Note 12',
        'Android',
        'Media',
        1800.00,
        30
    ),
    (
        'Motorola',
        'Moto G54',
        'Android',
        'Media',
        1600.00,
        25
    ),
    (
        'Huawei',
        'P40 Lite',
        'Android',
        'Media',
        2000.00,
        12
    );

-- Customers insertion
INSERT INTO
    clientes (name, dni, email, phone)
VALUES (
        'Carlos Perez',
        '1002345678',
        'carlos@mail.com',
        '3001234567'
    ),
    (
        'Ana Torres',
        '1003456789',
        'ana@mail.com',
        '3019876543'
    ),
    (
        'Luis Gomez',
        '1004567890',
        'luis@mail.com',
        '3024567890'
    ),
    (
        'Maria Lopez',
        '1005678901',
        'maria@mail.com',
        '3036547891'
    );

-- Sales insertion
INSERT INTO
    ventas (id_cliente, total)
VALUES (1, 4200.00),
    (2, 3600.00),
    (3, 5200.00);

-- Sale Details insertion
INSERT INTO
    detalle_ventas (
        id_venta,
        id_celular,
        cantidad,
        subtotal
    )
VALUES (1, 1, 1, 4200.00),
    (2, 3, 2, 3600.00),
    (3, 2, 1, 5200.00);

-- Test data retrieval
SELECT * FROM celulares;

SELECT * FROM clientes;

SELECT * FROM ventas;

SELECT * FROM datelle_ventas;

-- Test data retrieval with joins
SELECT v.id AS venta_id, c.name AS cliente_name, v.date, v.total
FROM ventas v
    JOIN clientes c ON v.id_cliente = c.id;

SELECT
    dv.id AS detalle_id,
    v.id AS venta_id,
    cel.brand AS celular_brand,
    cel.model AS celular_model,
    dv.cantidad,
    dv.subtotal
FROM
    detalle_ventas dv
    JOIN ventas v ON dv.id_venta = v.id
    JOIN celulares cel ON dv.id_celular = cel.id;

-- End of tecnostore_db.sql