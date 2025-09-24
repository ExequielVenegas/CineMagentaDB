package org.duoc.grupo11.constants;

public class DatabaseConstantes {

    public static final String URL = "jdbc:mysql://localhost:3306/Cine_DB";
    public static final String USER = "root";
    public static final String PASSWORD = "admin";
    public static final String DRIVER_DB = "com.mysql.cj.jdbc.Driver";

    public static final String ID = "id";
    public static final String TITULO = "titulo";
    public static final String DIRECTOR = "director";
    public static final String ANIO = "anio";
    public static final String DURACION = "duracion";
    public static final String GENERO = "genero";

    public static final String INSERT_PELICULA_SQL = "INSERT INTO Cartelera (titulo, director, anio, duracion, genero) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_SQL = "SELECT * FROM Cartelera ORDER BY titulo";
    public static final String UPDATE_SQL = "UPDATE Cartelera SET titulo=?, director=?, anio=?, duracion=?, genero=? WHERE id=?";
    public static final String DELETE_SQL = "DELETE FROM Cartelera WHERE id=?";
    public static final String SELECT_WHERE_ID_COUNT_SQL = "SELECT COUNT(*) FROM Cartelera WHERE id = ?";
    public static final String SELECT_WHERE_TITULO_SQL = "SELECT * FROM Cartelera WHERE titulo LIKE ? ORDER BY titulo";
    public static final String SELECT_WHERE_ID_SQL = "SELECT * FROM peliculas WHERE id = ?";


}
