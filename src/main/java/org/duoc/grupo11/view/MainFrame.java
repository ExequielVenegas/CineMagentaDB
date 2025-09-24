package org.duoc.grupo11.view;

import org.duoc.grupo11.dao.PeliculaDAO;

import javax.swing.*;
import java.awt.*;

import static org.duoc.grupo11.constants.EtiquetasConstantes.*;

public class MainFrame extends JFrame {
    public static final String TITULO_SISTEMA_DE_GESTION_CINE_MAGENTA = "Sistema de Gestión - Cine Magenta";
    public static final int WIDTH1 = 800;
    public static final int HEIGHT1 = 600;
    public static final String BIENVENIDA_SISTEMA = "Bienvenido al Sistema de Gestión del Cine Magenta";
    public static final String FONT = "Arial";
    public static final int FONT_SIZE = 24;
    public static final String MODIFICAR_PELICULA = "Modificar Película";
    public static final String ELIMINAR_PELICULA = "Eliminar Película";
    public static final String EXPORTAR_A_CSV = "Exportar a CSV";

    private JToolBar toolBar;

    public MainFrame() {
        setTitle(TITULO_SISTEMA_DE_GESTION_CINE_MAGENTA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH1, HEIGHT1);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton btnAgregar = new JButton(AGREGAR);
        JButton btnListar = new JButton(LISTAR);
        JButton btnBuscar = new JButton(BUSCAR);
        JButton btnModificar = new JButton(MODIFICAR);
        JButton btnEliminar = new JButton(ELIMINAR);
        JButton btnExportar = new JButton(EXPORTAR_CSV);
        JButton btnSalir = new JButton(SALIR);

        btnAgregar.addActionListener(e -> new AgregarPeliculaForm().setVisible(true));
        btnListar.addActionListener(e -> new ListarPeliculasForm().setVisible(true));
        btnBuscar.addActionListener(e -> new BuscarPeliculasForm().setVisible(true));
        btnModificar.addActionListener(e -> new ModificarPeliculaForm().setVisible(true));
        btnEliminar.addActionListener(e -> new EliminarPeliculaForm().setVisible(true));

        btnExportar.addActionListener(e -> {
            String ruta = "peliculas.csv";
            PeliculaDAO dao = new PeliculaDAO();
            if (dao.exportarCSV(ruta)) {
                JOptionPane.showMessageDialog(this, "Exportado a " + ruta);
            } else {
                JOptionPane.showMessageDialog(this, "Error al exportar.");
            }
        });

        btnSalir.addActionListener(e -> System.exit(0));

        toolBar.add(btnAgregar);
        toolBar.add(btnListar);
        toolBar.add(btnBuscar);
        toolBar.add(btnModificar);
        toolBar.add(btnEliminar);
        toolBar.add(btnExportar);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(btnSalir);

        JLabel lblWelcome = new JLabel(BIENVENIDA_SISTEMA, SwingConstants.CENTER);
        lblWelcome.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));

        add(toolBar, BorderLayout.NORTH);
        add(lblWelcome, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}