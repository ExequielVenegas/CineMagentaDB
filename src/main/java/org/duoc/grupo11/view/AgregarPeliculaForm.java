package org.duoc.grupo11.view;

import org.duoc.grupo11.dao.PeliculaDAO;
import org.duoc.grupo11.model.Pelicula;

import javax.swing.*;
import java.awt.*;

public class AgregarPeliculaForm extends JFrame {


    public static final String AGREGAR_NUEVA_PELÍCULA = "Agregar Nueva Película";
    public static final int WIDTH1 = 400;
    public static final int HEIGHT1 = 350;
    public static final String TITULO = "Título:";
    public static final String DIRECTOR = "Director:";
    public static final String ANIO = "Año:";
    public static final String DURACION_MIN = "Duración (min):";
    public static final String GENERO = "Género:";
    public static final String COMEDIA = "Comedia";
    public static final String DRAMA = "Drama";
    public static final String ACCIÓN = "Acción";
    public static final String TERROR = "Terror";
    public static final String CIENCIA_FICCION = "Ciencia Ficción";
    public static final String ROMANCE = "Romance";
    public static final String AVENTURA = "Aventura";
    public static final String ANIMACION = "Animación";
    public static final String GUARDAR = "💾 Guardar";
    public static final String LIMPIAR = "🧹 Limpiar";
    public static final String CANCELAR = "❌ Cancelar";
    public static final String PELICULA_AGREGADA_EXITOSAMENTE = "Película agregada exitosamente!";
    public static final String ERROR_ANIO_Y_DURACION_INVALIDOS = "Error: Año y duración deben ser números válidos";
    public static final String ERROR1 = "Error";
    public static final String TITULO_ES_OBLIGATORIO = "El título es obligatorio";
    public static final String DIRECTOR_ES_OBLIGATORIO = "El director es obligatorio";
    public static final String ANIO_ES_OBLIGATORIO = "El año es obligatorio";
    public static final String DURACION_ES_OBLIGATORIA = "La duración es obligatoria";
    public static final String EL_ANIO_DEBE_SER_ENTRE_1888_Y = "El año debe ser entre 1888 y ";
    public static final String EL_ANIO_DEBE_SER_UN_NUMERO_VALIDO = "El año debe ser un número válido";
    public static final String LA_DURACION_DEBE_SER_UN_NUMERO_VALIDO = "La duración debe ser un número válido";
    public static final String VACIO = "";
    public static final String DURACIÓN_DEBE_SER_ENTRE_MINUTOS = "La duración debe ser entre 1 y 500 minutos";

    private JTextField txtTitulo, txtDirector, txtAnio, txtDuracion;
    private JComboBox<String> cmbGenero;
    private JButton btnGuardar, btnLimpiar, btnCancelar;
    private PeliculaDAO peliculaDAO;

    public AgregarPeliculaForm() {
        setTitle(AGREGAR_NUEVA_PELÍCULA);
        setSize(WIDTH1, HEIGHT1);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        peliculaDAO = new PeliculaDAO();
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel(TITULO));
        txtTitulo = new JTextField();
        panel.add(txtTitulo);

        panel.add(new JLabel(DIRECTOR));
        txtDirector = new JTextField();
        panel.add(txtDirector);

        panel.add(new JLabel(ANIO));
        txtAnio = new JTextField();
        panel.add(txtAnio);

        panel.add(new JLabel(DURACION_MIN));
        txtDuracion = new JTextField();
        panel.add(txtDuracion);

        panel.add(new JLabel(GENERO));
        String[] generos = {COMEDIA, DRAMA, ACCIÓN, TERROR, CIENCIA_FICCION, ROMANCE, AVENTURA, ANIMACION};
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

            if (peliculaDAO.crearPelicula(pelicula)) {
                JOptionPane.showMessageDialog(this, PELICULA_AGREGADA_EXITOSAMENTE);
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, ERROR_ANIO_Y_DURACION_INVALIDOS, ERROR1, JOptionPane.ERROR_MESSAGE);
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
            int año = Integer.parseInt(txtAnio.getText().trim());
            if (año < 1888 || año > java.time.Year.now().getValue() + 1) {
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
                JOptionPane.showMessageDialog(this, DURACIÓN_DEBE_SER_ENTRE_MINUTOS, ERROR1, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError(LA_DURACION_DEBE_SER_UN_NUMERO_VALIDO, txtDuracion);
            return false;
        }

        return true;
    }

    private void mostrarError(String mensaje, JTextField campo) {
        JOptionPane.showMessageDialog(this, mensaje, ERROR1, JOptionPane.ERROR_MESSAGE);
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