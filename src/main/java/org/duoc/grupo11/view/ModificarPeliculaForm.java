package org.duoc.grupo11.view;

import org.duoc.grupo11.dao.PeliculaDAO;
import org.duoc.grupo11.model.Pelicula;

import javax.swing.*;
import java.awt.*;

import static org.duoc.grupo11.constants.DialogosConstantes.VACIO;
import static org.duoc.grupo11.constants.EtiquetasConstantes.*;
import static org.duoc.grupo11.model.Genero.*;

public class ModificarPeliculaForm extends JFrame {

    public static final String TITULO_VENTANA_MODIFICAR_PELICULA = "Modificar Película";
    public static final int WIDTH1 = 400;
    public static final int HEIGHT1 = 300;

    private JTextField txtId, txtTitulo, txtDirector, txtDuracion, txtAnio;
    private JComboBox<String> cmbGenero;
    private JButton btnBuscar, btnActualizar, btnLimpiar;
    private PeliculaDAO peliculaDAO;

    public ModificarPeliculaForm() {
        setTitle(TITULO_VENTANA_MODIFICAR_PELICULA);
        setSize(WIDTH1, HEIGHT1);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        peliculaDAO = new PeliculaDAO();
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel(ID_ETIQUETA));
        txtId = new JTextField();
        panel.add(txtId);

        btnBuscar = new JButton(BUSCAR);
        add(btnBuscar);
        add(new JLabel());

        panel.add(new JLabel(TITULO_ETIQUETA));
        txtTitulo = new JTextField();
        panel.add(txtTitulo);

        panel.add(new JLabel(DIRECTOR_ETIQUETA));
        txtDirector = new JTextField();
        panel.add(txtDirector);

        panel.add(new JLabel(ANIO_ETIQUETA));
        txtAnio = new JTextField();
        panel.add(txtAnio);

        panel.add(new JLabel(DURACION_MIN_ETIQUETA));
        txtDuracion = new JTextField();
        panel.add(txtDuracion);

        panel.add(new JLabel(GENERO_ETIQUETA));
        String[] generos = {COMEDIA.getNombre(),
                DRAMA.getNombre(),
                ACCION.getNombre(),
                TERROR.getNombre(),
                CIENCIA_FICCION.getNombre(),
                ROMANCE.getNombre()
        };
        cmbGenero = new JComboBox<>(generos);
        panel.add(cmbGenero);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnActualizar = new JButton(ACTUALIZAR);
        btnLimpiar = new JButton(LIMPIAR);


        btnBuscar.addActionListener(e -> buscarPelicula());
        btnActualizar.addActionListener(e -> actualizarPelicula());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panelBotones.add(btnActualizar);
        panelBotones.add(btnLimpiar);

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void limpiarCampos() {
        txtTitulo.setText(VACIO);
        txtDirector.setText(VACIO);
        txtAnio.setText(VACIO);
        txtDuracion.setText(VACIO);
        cmbGenero.setSelectedIndex(0);
        txtTitulo.requestFocus();
    }

    private void actualizarPelicula() {
        if (txtTitulo.getText().isEmpty() || txtDirector.getText().isEmpty() ||
                cmbGenero.getSelectedItem().toString().isEmpty() || txtDuracion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }
        Pelicula p = new Pelicula(
                Integer.parseInt(txtId.getText()),
                txtTitulo.getText(),
                txtDirector.getText(),
                Integer.parseInt(txtAnio.getText()),
                Integer.parseInt(txtDuracion.getText()),
                cmbGenero.getSelectedItem().toString()
        );
        if (peliculaDAO.actualizarPelicula(p)) {
            JOptionPane.showMessageDialog(this, "Película actualizada con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar.");
        }
    }

    private void buscarPelicula() {
        int id = Integer.parseInt(txtId.getText());
        Pelicula p = peliculaDAO.buscarPorId(id);
        if (p != null) {
            txtTitulo.setText(p.getTitulo());
            txtDirector.setText(p.getDirector());
            txtDuracion.setText(String.valueOf(p.getDuracion()));
        } else {
            JOptionPane.showMessageDialog(this, "Película no encontrada.");
        }
    }
}
