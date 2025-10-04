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
-- CREATE DATABASE Cine_DB;

-- Usar la base de datos
USE Cine_DB;

-- Crear la tabla Cartelera
DROP TABLE cartelera;
CREATE TABLE Cartelera (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    director VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    duracion INT NOT NULL,
    genero ENUM('Comedia', 'Drama', 'Accion', 'Terror', 'Ciencia Ficcion', 'Romance') NOT NULL
);

INSERT INTO Cartelera (titulo, director, anio, duracion, genero) VALUES
('Pulp Fiction', 'Quentin Tarantino', 1994, 154, 'Accion'),
('El Padrino', 'Francis Ford Coppola', 1972, 175, 'Drama'),
('La La Land', 'Damien Chazelle', 2016, 128, 'Romance'),
('Parásitos', 'Bong Joon Ho', 2019, 132, 'Drama'),
('Interestelar', 'Christopher Nolan', 2014, 169, 'Ciencia Ficcion'),
('El Señor de los Anillos: La Comunidad del Anillo', 'Peter Jackson', 2001, 178, 'Accion'),
('Titanic', 'James Cameron', 1997, 195, 'Romance'),
('Forrest Gump', 'Robert Zemeckis', 1994, 142, 'Drama'),
('Gladiador', 'Ridley Scott', 2000, 155, 'Accion'),
('El Resplandor', 'Stanley Kubrick', 1980, 146, 'Terror'),
('El Club de la Pelea', 'David Fincher', 1999, 139, 'Accion'),
('El Origen', 'Christopher Nolan', 2010, 148, 'Ciencia Ficcion'),
('La Matriz', 'Lana Wachowski', 1999, 136, 'Ciencia Ficcion'),
('El Silencio de los Corderos', 'Jonathan Demme', 1991, 118, 'Terror'),
('Volver al Futuro', 'Robert Zemeckis', 1985, 116, 'Ciencia Ficcion'),
('Toy Story', 'John Lasseter', 1995, 81, 'Comedia'),
('Vengadores: Endgame', 'Hermanos Russo', 2019, 181, 'Accion'),
('Búsqueda Implacable', 'Pierre Morel', 2008, 93, 'Accion'),
('Mad Max: Furia en el Camino', 'George Miller', 2015, 120, 'Accion'),
('El Mago de Oz', 'Victor Fleming', 1939, 102, 'Romance'),
('La Sirenita', 'Ron Clements', 1989, 83, 'Romance'),
('Joker', 'Todd Phillips', 2019, 122, 'Drama'),
('El Gran Lebowski', 'Hermanos Coen', 1998, 117, 'Comedia'),
('E.T. el Extraterrestre', 'Steven Spielberg', 1982, 115, 'Ciencia Ficcion'),
('El Caballero de la Noche', 'Christopher Nolan', 2008, 152, 'Accion'),
('Harry Potter y la Piedra Filosofal', 'Chris Columbus', 2001, 152, 'Accion'),
('Los Goonies', 'Richard Donner', 1985, 114, 'Comedia'),
('Pesadilla en la Calle Elm', 'Wes Craven', 1984, 91, 'Terror'),
('El Conjuro', 'James Wan', 2013, 112, 'Terror'),
('Whiplash', 'Damien Chazelle', 2014, 106, 'Drama'),
('Hereditary', 'Ari Aster', 2018, 127, 'Terror'),
('Coco', 'Lee Unkrich', 2017, 105, 'Drama'),
('El Rey León', 'Roger Allers', 1994, 88, 'Drama'),
('Intensamente', 'Pete Docter', 2015, 95, 'Comedia'),
('Wall-E', 'Andrew Stanton', 2008, 98, 'Ciencia Ficcion'),
('Ratatouille', 'Brad Bird', 2007, 111, 'Comedia'),
('Buscando a Nemo', 'Andrew Stanton', 2003, 100, 'Comedia'),
('Monsters Inc.', 'Pete Docter', 2001, 92, 'Comedia'),
('Los Increíbles', 'Brad Bird', 2004, 115, 'Accion'),
('Up', 'Pete Docter', 2009, 96, 'Drama'),
('El Viaje de Chihiro', 'Hayao Miyazaki', 2001, 125, 'Romance'),
('El Castillo Ambulante', 'Hayao Miyazaki', 2004, 119, 'Romance'),
('Princesa Mononoke', 'Hayao Miyazaki', 1997, 134, 'Accion'),
('Akira', 'Katsuhiro Otomo', 1988, 124, 'Ciencia Ficcion'),
('El Laberinto del Fauno', 'Guillermo del Toro', 2006, 118, 'Drama'),
('El Orfanato', 'J.A. Bayona', 2007, 105, 'Terror'),
('Roma', 'Alfonso Cuarón', 2018, 135, 'Drama'),
('Y tu mamá también', 'Alfonso Cuarón', 2001, 106, 'Drama'),
('Los Siete Samuráis', 'Akira Kurosawa', 1954, 207, 'Accion');
```

(Reemplaza `tu_usuario` y `tu_contraseña` con tus credenciales de MySQL.)
