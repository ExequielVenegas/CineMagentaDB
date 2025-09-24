package org.duoc.grupo11.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.duoc.grupo11.constants.DatabaseConstantes.*;
import static org.duoc.grupo11.constants.DialogosConstantes.DRIVER_NO_ENCONTRADO;

public class ConexionDB {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER_DB);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException(DRIVER_NO_ENCONTRADO + e.getMessage());
        }
    }
}