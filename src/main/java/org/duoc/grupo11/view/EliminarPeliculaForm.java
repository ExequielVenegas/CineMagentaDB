package org.duoc.grupo11.view;

import org.duoc.grupo11.dao.PeliculaDAO;
import org.duoc.grupo11.model.Pelicula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

import static org.duoc.grupo11.constants.EtiquetasConstantes.*;

public class EliminarPeliculaForm extends JFrame {

    private static final String TITULO_VENTANA_ELIMINAR_PELICULA = "Eliminar Películas";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final String BUSCAR_POR_TITULO = "Buscar por título:";
    private static final String MIN = " min";

    private JTextField txtTitulo;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JTable tabla;
    private DefaultTableModel modelo;
    private PeliculaDAO peliculaDAO;

    public EliminarPeliculaForm() {
        setTitle(TITULO_VENTANA_ELIMINAR_PELICULA);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        peliculaDAO = new PeliculaDAO();
        initComponents();
        cargarResultados(true);
    }

    private void initComponents() {
        String[] columnas = {ID_COLUMNA, TITULO_COLUMNA, DIRECTOR_COLUMNA, ANIO_COLUMNA, DURACION_COLUMNA, GENERO_COLUMNA};

        // Panel superior: buscador
        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuscar.add(new JLabel(BUSCAR_POR_TITULO));
        txtTitulo = new JTextField(30);
        panelBuscar.add(txtTitulo);
        btnBuscar = new JButton(BUSCAR);
        btnLimpiar = new JButton(LIMPIAR);
        panelBuscar.add(btnBuscar);
        panelBuscar.add(btnLimpiar);
        add(panelBuscar, BorderLayout.NORTH);


        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Integer.class;
                return String.class;
            }
        };
        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // Panel inferior: botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnEliminar = new JButton(ELIMINAR);
        btnCancelar = new JButton(CANCELAR);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // Listeners
        btnBuscar.addActionListener(e -> cargarResultados(false));
        btnLimpiar.addActionListener(e -> limpiarResultado());
        btnCancelar.addActionListener(e -> dispose());
        btnEliminar.addActionListener(e -> eliminarResultado());
    }

    private void eliminarResultado() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una película para eliminar.");
            return;
        }

        int id = (int) modelo.getValueAt(filaSeleccionada, 0); // columna 0 = ID
        String titulo = modelo.getValueAt(filaSeleccionada, 1).toString();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea eliminar la película \"" + titulo + "\"?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean ok = peliculaDAO.eliminarPelicula(id);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Película eliminada correctamente.");
                modelo.removeRow(filaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar la película.");
            }
        }
    }

    private void limpiarResultado() {
        txtTitulo.setText("");
        cargarResultados(false);
    }

    private void cargarResultados(boolean inicial) {
        modelo.setRowCount(0);
        String filtro = (txtTitulo.getText() == null) ? "" : txtTitulo.getText().trim().toLowerCase();

        List<Pelicula> peliculas = peliculaDAO.listarTodasPeliculas().stream()
                .sorted(Comparator.comparingInt(Pelicula::getId))
                .toList();

        if (peliculas == null) return;

        for (Pelicula p : peliculas) {
            if (inicial || filtro.isEmpty() || p.getTitulo().toLowerCase().contains(filtro)) {
                Object[] row = new Object[]{
                        p.getId(),
                        p.getTitulo(),
                        p.getDirector(),
                        p.getAnio(),
                        p.getDuracion() + MIN,
                        p.getGenero()
                };
                modelo.addRow(row);
            }
        }
    }
}
