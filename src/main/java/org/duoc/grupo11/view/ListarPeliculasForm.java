package org.duoc.grupo11.view;

import org.duoc.grupo11.dao.PeliculaDAO;
import org.duoc.grupo11.model.Pelicula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarPeliculasForm extends JFrame {
    private JTable tablaPeliculas;
    private JComboBox<String> comboGenero;
    private JTextField txtAnioInicio, txtAnioFin;
    private JButton btnBuscar, btnLimpiar;
    private PeliculaDAO peliculaDAO;

    public ListarPeliculasForm() {
        peliculaDAO = new PeliculaDAO();

        setTitle("Listado de Películas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelFiltros = new JPanel(new FlowLayout());

        panelFiltros.add(new JLabel("Género:"));
        comboGenero = new JComboBox<>(new String[]{"Todos", "Acción", "Drama", "Comedia", "Terror"});
        panelFiltros.add(comboGenero);

        panelFiltros.add(new JLabel("Año Inicio:"));
        txtAnioInicio = new JTextField(5);
        panelFiltros.add(txtAnioInicio);

        panelFiltros.add(new JLabel("Año Fin:"));
        txtAnioFin = new JTextField(5);
        panelFiltros.add(txtAnioFin);

        btnBuscar = new JButton("Buscar");
        panelFiltros.add(btnBuscar);

        tablaPeliculas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaPeliculas);

        btnLimpiar = new JButton("Limpiar");
        panelFiltros.add(btnLimpiar);

        getContentPane().add(panelFiltros, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> {
            String genero = (String) comboGenero.getSelectedItem();
            int anioInicio = 0;
            int anioFin = 0;

            try {
                if (!txtAnioInicio.getText().trim().isEmpty()) {
                    anioInicio = Integer.parseInt(txtAnioInicio.getText().trim());
                }
                if (!txtAnioFin.getText().trim().isEmpty()) {
                    anioFin = Integer.parseInt(txtAnioFin.getText().trim());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese años válidos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Pelicula> resultados = peliculaDAO.buscarPeliculas(genero, anioInicio, anioFin);
            actualizarTabla(resultados);
        });

        btnLimpiar.addActionListener(e -> {
            comboGenero.setSelectedIndex(0);
            txtAnioInicio.setText("");
            txtAnioFin.setText("");
            actualizarTabla(peliculaDAO.listarTodasPeliculas());
        });

        actualizarTabla(peliculaDAO.listarTodasPeliculas());
    }

    private void actualizarTabla(List<Pelicula> peliculas) {
        String[] columnas = {"ID", "Título", "Género", "Año"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Pelicula p : peliculas) {
            Object[] fila = {p.getId(), p.getTitulo(), p.getGenero(), p.getAnio()};
            modelo.addRow(fila);
        }

        tablaPeliculas.setModel(modelo);
    }
}
