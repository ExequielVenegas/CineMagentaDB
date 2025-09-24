package org.duoc.grupo11.view;

import org.duoc.grupo11.constants.DialogosConstantes;
import org.duoc.grupo11.dao.PeliculaDAO;
import org.duoc.grupo11.model.Pelicula;

import javax.swing.*;
import java.awt.*;

import static org.duoc.grupo11.constants.DialogosConstantes.*;
import static org.duoc.grupo11.constants.EtiquetasConstantes.*;
import static org.duoc.grupo11.model.Genero.*;

public class AgregarPeliculaForm extends JFrame {


    public static final String TITULO_VENTANA_AGREGAR_PELÍCULA = "Agregar Nueva Película";
    public static final int WIDTH1 = 400;
    public static final int HEIGHT1 = 350;

    private JTextField txtTitulo, txtDirector, txtAnio, txtDuracion;
    private JComboBox<String> cmbGenero;
    private JButton btnGuardar, btnLimpiar, btnCancelar;
    private PeliculaDAO peliculaDAO;

    public AgregarPeliculaForm() {
        setTitle(TITULO_VENTANA_AGREGAR_PELÍCULA);
        setSize(WIDTH1, HEIGHT1);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        peliculaDAO = new PeliculaDAO();
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
        btnGuardar = new JButton(GUARDAR);
        btnLimpiar = new JButton(LIMPIAR);
        btnCancelar = new JButton(CANCELAR);

        btnGuardar.addActionListener(e -> guardarPelicula());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void guardarPelicula() {
        if (!validarCampos()) return;

        try {
            Pelicula pelicula = new Pelicula();
            pelicula.setTitulo(txtTitulo.getText().trim());
            pelicula.setDirector(txtDirector.getText().trim());
            pelicula.setAnio(Integer.parseInt(txtAnio.getText().trim()));
            pelicula.setDuracion(Integer.parseInt(txtDuracion.getText().trim()));
            pelicula.setGenero((String) cmbGenero.getSelectedItem());

            if (peliculaDAO.insertarPelicula(pelicula)) {
                JOptionPane.showMessageDialog(this, PELICULA_AGREGADA_EXITOSAMENTE);
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, ERROR_ANIO_Y_DURACION_INVALIDOS, DialogosConstantes.ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        if (txtTitulo.getText().trim().isEmpty()) {
            mostrarError(TITULO_ES_OBLIGATORIO, txtTitulo);
            return false;
        }
        if (txtDirector.getText().trim().isEmpty()) {
            mostrarError(DIRECTOR_ES_OBLIGATORIO, txtDirector);
            return false;
        }
        if (txtAnio.getText().trim().isEmpty()) {
            mostrarError(ANIO_ES_OBLIGATORIO, txtAnio);
            return false;
        }
        if (txtDuracion.getText().trim().isEmpty()) {
            mostrarError(DURACION_ES_OBLIGATORIA, txtDuracion);
            return false;
        }

        try {
            int anio = Integer.parseInt(txtAnio.getText().trim());
            if (anio < 1888 || anio > java.time.Year.now().getValue() + 1) {
                JOptionPane.showMessageDialog(this, EL_ANIO_DEBE_SER_ENTRE_1888_Y + (java.time.Year.now().getValue() + 1), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError(EL_ANIO_DEBE_SER_UN_NUMERO_VALIDO, txtAnio);
            return false;
        }

        try {
            int duracion = Integer.parseInt(txtDuracion.getText().trim());
            if (duracion <= 0 || duracion > 500) {
                JOptionPane.showMessageDialog(this, DURACION_DEBE_SER_ENTRE_MINUTOS, DialogosConstantes.ERROR, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError(LA_DURACION_DEBE_SER_UN_NUMERO_VALIDO, txtDuracion);
            return false;
        }

        return true;
    }

    private void mostrarError(String mensaje, JTextField campo) {
        JOptionPane.showMessageDialog(this, mensaje, DialogosConstantes.ERROR, JOptionPane.ERROR_MESSAGE);
        campo.requestFocus();
    }

    private void limpiarCampos() {
        txtTitulo.setText(VACIO);
        txtDirector.setText(VACIO);
        txtAnio.setText(VACIO);
        txtDuracion.setText(VACIO);
        cmbGenero.setSelectedIndex(0);
        txtTitulo.requestFocus();
    }
}