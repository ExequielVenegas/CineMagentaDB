package org.duoc.grupo11.view;

import org.duoc.grupo11.dao.PeliculaDAO;
import org.duoc.grupo11.model.Pelicula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

import static org.duoc.grupo11.constants.DialogosConstantes.MIN;
import static org.duoc.grupo11.constants.EtiquetasConstantes.*;

public class ListarPeliculasForm extends JFrame {
    public static final String TITULO_VENTANA_LISTAR_PELICULAS = "Listado de Películas";
    public static final int WIDTH1 = 800;
    public static final int HEIGHT1 = 400;

    private JTable table;
    private DefaultTableModel tableModel;
    private PeliculaDAO peliculaDAO;

    public ListarPeliculasForm() {
        setTitle(TITULO_VENTANA_LISTAR_PELICULAS);
        setSize(WIDTH1, HEIGHT1);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        peliculaDAO = new PeliculaDAO();
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        String[] columnNames = {ID_COLUMNA, TITULO_COLUMNA, DIRECTOR_COLUMNA, ANIO_COLUMNA, DURACION_COLUMNA, GENERO_COLUMNA};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnActualizar = new JButton(ACTUALIZAR);
        JButton btnCerrar = new JButton(CERRAR);

        btnActualizar.addActionListener(e -> cargarDatos());
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnActualizar);
        panelBotones.add(btnCerrar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarDatos() {
        tableModel.setRowCount(0);
        List<Pelicula> peliculas = peliculaDAO.listarTodasPeliculas().stream()
                .sorted(Comparator.comparingInt(Pelicula::getId))
                .toList();

        for (Pelicula pelicula : peliculas) {
            Object[] row = {
                    pelicula.getId(),
                    pelicula.getTitulo(),
                    pelicula.getDirector(),
                    pelicula.getAnio(),
                    pelicula.getDuracion() + MIN,
                    pelicula.getGenero()
            };
            tableModel.addRow(row);
        }
    }
}