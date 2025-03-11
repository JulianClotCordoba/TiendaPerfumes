-- Eliminar base de datos si existe
DROP DATABASE IF EXISTS perfumesdb;

-- Crear base de datos
CREATE DATABASE perfumesdb;
USE perfumesdb;

-- Tabla Marcas
CREATE TABLE IF NOT EXISTS marca (
    id_marca BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    pais_origen VARCHAR(100)
);

-- Tabla Familias Olfativas
CREATE TABLE IF NOT EXISTS familia_olfativa (
    id_familia BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    descripcion TEXT
);

-- Tabla Productos
CREATE TABLE IF NOT EXISTS producto (
    id_producto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    descuento DECIMAL(3,2) DEFAULT 0.00,
    imagen_url VARCHAR(512),
    en_oferta BOOLEAN DEFAULT false,
    rating INT CHECK (rating BETWEEN 0 AND 5),
    tiene_opciones BOOLEAN DEFAULT false,
    genero ENUM('HOMBRE', 'MUJER', 'UNISEX') NOT NULL,
    id_marca BIGINT,
    id_familia BIGINT,
    FOREIGN KEY (id_marca) REFERENCES marca(id_marca),
    FOREIGN KEY (id_familia) REFERENCES familia_olfativa(id_familia)
);

-- Insertar Marcas
INSERT INTO marca (nombre, pais_origen) VALUES
('Chanel', 'Francia'),
('Dior', 'Francia'),
('Versace', 'Italia'),
('Guess', 'Estados Unidos');

-- Insertar Familias Olfativas
INSERT INTO familia_olfativa (nombre, descripcion) VALUES
('Floral', 'Notas dominantes de flores'),
('Oriental', 'Mezcla especiada y cálida'),
('Cítrico', 'Aromas frescos y ácidos'),
('Amaderado', 'Base de maderas nobles');

-- Insertar Productos
INSERT INTO producto (
    nombre, 
    descripcion, 
    precio, 
    descuento, 
    imagen_url, 
    en_oferta, 
    rating, 
    tiene_opciones, 
    genero, 
    id_marca, 
    id_familia
) VALUES
('Jazmín Nocturno', 'Fragancia floral intensa', 120.00, 0.15, 
 'https://luxurious-fragrances.com/wp-content/uploads/2020/09/parfum-rp-paris-jasmin-de-nuit.jpg', true, 4, false, 'MUJER', 1, 1),

('Cítrico Marino', 'Notas frescas de bergamota', 95.50, 0.00, 
 'https://api-assets.wikiparfum.com/_resized/dsxrj7nycj837kpxeb5bmak9chvwliywlcb3o9asxkkadg9yedn0q8qbjuhl-w500-q85.jpg', false, 5, true, 'HOMBRE', 2, 3),

('Seducción Oriental', 'Mezcla especiada con vainilla', 150.00, 0.20, 
 'https://zermatcostarica.com/wp-content/uploads/2020/08/zfc-badGirl-seduccion-dama.jpg', true, 5, false, 'UNISEX', 3, 2),

('Guess Noir', 'Frescura urbana', 75.00, 0.10, 
 'https://m.media-amazon.com/images/I/61i5Syw9pFL._AC_UF1000,1000_QL80_.jpg', true, 4, true, 'UNISEX', 4, 4);

-- Verificar datos
SELECT * FROM marca;
SELECT * FROM familia_olfativa;
SELECT * FROM producto;
SELECT 
    p.id_producto,
    p.nombre AS producto,
    p.genero,
    m.nombre AS marca,
    f.nombre AS familia_olfativa,
    p.precio
FROM producto p
JOIN marca m ON p.id_marca = m.id_marca
JOIN familia_olfativa f ON p.id_familia = f.id_familia;

-- Verificar relaciones
SELECT 
    p.nombre AS producto,
    m.nombre AS marca,
    f.nombre AS familia
FROM producto p
LEFT JOIN marca m ON p.id_marca = m.id_marca
LEFT JOIN familia_olfativa f ON p.id_familia = f.id_familia;