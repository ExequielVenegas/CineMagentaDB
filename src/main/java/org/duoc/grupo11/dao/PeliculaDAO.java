package org.duoc.grupo11.dao;

import org.duoc.grupo11.database.ConexionDB;
import org.duoc.grupo11.model.Pelicula;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.duoc.grupo11.constants.DatabaseConstants.*;
import static org.duoc.grupo11.constants.DialogesConstants.*;

public class PeliculaDAO {

    public boolean crearPelicula(Pelicula pelicula) {
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            pstmt.setString(1, pelicula.getTitulo());
            pstmt.setString(2, pelicula.getDirector());
            pstmt.setInt(3, pelicula.getAnio());
            pstmt.setInt(4, pelicula.getDuracion());
            pstmt.setString(5, pelicula.getGenero());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ERROR_AL_CREAR_PELICULA + e.getMessage());
            return false;
        }
    }

    public List<Pelicula> obtenerTodasPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_SQL)) {

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setId(rs.getInt(ID));
                pelicula.setTitulo(rs.getString(TITULO));
                pelicula.setDirector(rs.getString(DIRECTOR));
                pelicula.setAnio(rs.getInt(ANIO));
                pelicula.setDuracion(rs.getInt(DURACION));
                pelicula.setGenero(rs.getString(GENERO));
                peliculas.add(pelicula);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ERROR_AL_OBTENER_PELICULAS + e.getMessage());
        }
        return peliculas;
    }

    public boolean actualizarPelicula(Pelicula pelicula) {
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL)) {

            pstmt.setString(1, pelicula.getTitulo());
            pstmt.setString(2, pelicula.getDirector());
            pstmt.setInt(3, pelicula.getAnio());
            pstmt.setInt(4, pelicula.getDuracion());
            pstmt.setString(5, pelicula.getGenero());
            pstmt.setInt(6, pelicula.getId());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ERROR_AL_ACTUALIZAR_PELICULA + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPelicula(int id) {
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ERROR_AL_ELIMINAR_PELICULA + e.getMessage());
            return false;
        }
    }

    public boolean existePeliculaConId(int id) {

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_WHERE_ID_SQL)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ERROR_AL_VERIFICAR_ID + e.getMessage());
        }
        return false;
    }

    public List<Pelicula> buscarPorTitulo(String titulo) {
        List<Pelicula> peliculas = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_WHERE_TITULO_SQL)) {

            pstmt.setString(1, "%" + titulo + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setId(rs.getInt(ID));
                pelicula.setTitulo(rs.getString(TITULO));
                pelicula.setDirector(rs.getString(DIRECTOR));
                pelicula.setAnio(rs.getInt(ANIO));
                pelicula.setDuracion(rs.getInt(DURACION));
                pelicula.setGenero(rs.getString(GENERO));
                peliculas.add(pelicula);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ERROR_AL_BUSCAR_PELICULAS + e.getMessage());
        }
        return peliculas;
    }
}