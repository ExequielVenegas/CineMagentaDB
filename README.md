# CineDB

Proyecto en Java que implementa una base de datos para gestionar información de películas y funciones de cine. Utiliza Maven como herramienta de construcción.


## Requisitos

Java 8 o superior, Maven, MySQL.

## Instalación

1. Clona el repositorio:  
   git clone https://github.com/ExequielVenegas/CineDB.git

2. Ingresa al directorio del proyecto:  
   cd CineDB

3. Compila el proyecto con Maven:  
   mvn clean install

## Conexión a la Base de Datos

Crea una base de datos llamada `cinedb` en MySQL:  

```
-- Crear la base de datos
CREATE DATABASE Cine_DB;

-- Usar la base de datos
USE Cine_DB;

-- Crear la tabla Cartelera
CREATE TABLE Cartelera (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    director VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    duracion INT NOT NULL,
    genero ENUM('Comedia', 'Drama', 'Accion', 'Terror', 'Ciencia Ficcion', 'Romance') NOT NULL
);

```

(Reemplaza `tu_usuario` y `tu_contraseña` con tus credenciales de MySQL.)
