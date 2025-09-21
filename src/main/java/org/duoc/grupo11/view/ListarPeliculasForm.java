package org.duoc.grupo11.view;

import org.duoc.grupo11.dao.PeliculaDAO;
import org.duoc.grupo11.model.Pelicula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarPeliculasForm extends JFrame {
    public static final String LISTADO_DE_PELICULAS = "Listado de Películas";
    public static final int WIDTH1 = 800;
    public static final int HEIGHT1 = 400;
    public static final String ACTUALIZAR = "🔄 Actualizar";
    public static final String CERRAR = "❌ Cerrar";
    public static final String ID = "ID";
    public static final String TITULO = "Título";
    public static final String DIRECTOR = "Director";
    public static final String ANIO = "Año";
    public static final String DURACION = "Duración";
    public static final String GENERO = "Género";
    public static final String MIN = " min";

    private JTable table;
    private DefaultTableModel tableModel;
    private PeliculaDAO peliculaDAO;

    public ListarPeliculasForm() {
        setTitle(LISTADO_DE_PELICULAS);
        setSize(WIDTH1, HEIGHT1);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        peliculaDAO = new PeliculaDAO();
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        String[] columnNames = {ID, TITULO, DIRECTOR, ANIO, DURACION, GENERO};
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
        List<Pelicula> peliculas = peliculaDAO.obtenerTodasPeliculas();

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