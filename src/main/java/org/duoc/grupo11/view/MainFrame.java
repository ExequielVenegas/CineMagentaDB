package org.duoc.grupo11.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static final String TITULO_SISTEMA_DE_GESTION_CINE_MAGENTA = "Sistema de Gestión - Cine Magenta";
    public static final int WIDTH1 = 800;
    public static final int HEIGHT1 = 600;
    public static final String AGREGAR = "➕ Agregar";
    public static final String LISTAR = "📋 Listar";
    public static final String BUSCAR = "🔍 Buscar";
    public static final String SALIR = "🚪 Salir";
    public static final String BIENVENIDA_SISTEMA = "Bienvenido al Sistema de Gestión del Cine Magenta";
    public static final String FONT = "Arial";
    public static final int FONT_SIZE = 24;

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
        JButton btnSalir = new JButton(SALIR);

        btnAgregar.addActionListener(e -> new AgregarPeliculaForm().setVisible(true));
        btnListar.addActionListener(e -> new ListarPeliculasForm().setVisible(true));
        btnBuscar.addActionListener(e -> new BuscarPeliculasForm().setVisible(true));
        btnSalir.addActionListener(e -> System.exit(0));

        toolBar.add(btnAgregar);
        toolBar.add(btnListar);
        toolBar.add(btnBuscar);
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