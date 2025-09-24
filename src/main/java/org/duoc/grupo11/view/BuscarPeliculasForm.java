package org.duoc.grupo11.view;


import org.duoc.grupo11.dao.PeliculaDAO;
import org.duoc.grupo11.model.Pelicula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static org.duoc.grupo11.constants.DialogosConstantes.MIN;
import static org.duoc.grupo11.constants.EtiquetasConstantes.*;

public class BuscarPeliculasForm extends JFrame {
    public static final String TITULO_VENTAMA_BUSCAR_PELICULAS = "Buscar Películas";
    public static final int WIDTH1 = 800;
    public static final int HEIGHT1 = 400;

    public static final String BUSCAR_POR_TITULO = "Buscar por título:";
    public static final String TERMINO_DE_BUSQUEDA = "Ingrese un término de búsqueda";
    public static final String NO_SE_ENCONTRARON_PELICULA = "No se encontraron películas con ese título";

    private JTextField txtBusqueda;
    private JTable table;
    private DefaultTableModel tableModel;
    private PeliculaDAO peliculaDAO;

    public BuscarPeliculasForm() {
        setTitle(TITULO_VENTAMA_BUSCAR_PELICULAS);
        setSize(WIDTH1, HEIGHT1);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        peliculaDAO = new PeliculaDAO();
        initComponents();
    }

    private void initComponents() {
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBusqueda.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelBusqueda.add(new JLabel(BUSCAR_POR_TITULO));
        txtBusqueda = new JTextField(20);
        panelBusqueda.add(txtBusqueda);

        JButton btnBuscar = new JButton(BUSCAR);
        btnBuscar.addActionListener(e -> buscarPeliculas());
        panelBusqueda.add(btnBuscar);

        String[] columnNames = {ID_COLUMNA, TITULO_COLUMNA, DIRECTOR_COLUMNA, ANIO_COLUMNA, DURACION_COLUMNA, GENERO_COLUMNA};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnCerrar = new JButton(CERRAR);
        btnCerrar.addActionListener(e -> dispose());
        panelBotones.add(btnCerrar);

        add(panelBusqueda, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void buscarPeliculas() {
        String busqueda = txtBusqueda.getText().trim();
        if (busqueda.isEmpty()) {
            JOptionPane.showMessageDialog(this, TERMINO_DE_BUSQUEDA);
            return;
        }

        tableModel.setRowCount(0);
        List<Pelicula> peliculas = peliculaDAO.buscarPorTitulo(busqueda);

        if (peliculas.isEmpty()) {
            JOptionPane.showMessageDialog(this, NO_SE_ENCONTRARON_PELICULA);
            return;
        }

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