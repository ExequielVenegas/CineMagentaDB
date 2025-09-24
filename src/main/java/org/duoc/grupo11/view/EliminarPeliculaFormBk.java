package org.duoc.grupo11.view;

import org.duoc.grupo11.dao.PeliculaDAO;

import javax.swing.*;
import java.awt.*;

import static org.duoc.grupo11.constants.DialogosConstantes.VACIO;
import static org.duoc.grupo11.constants.EtiquetasConstantes.*;

public class EliminarPeliculaFormBk extends JFrame {

    public static final String ELIMINAR_PELICULA = "Eliminar Película";
    public static final int WIDTH1 = 300;
    public static final int HEIGHT1 = 150;
    public static final String DESEA_ELIMINAR_ESTA_PELICULA = "¿Seguro que desea eliminar esta película?";
    public static final String PELICULA_ELIMINADA = "Película eliminada.";
    public static final String NO_SE_PUDO_ELIMINAR_VERIFIQUE_EL_ID = "No se pudo eliminar. Verifique el ID.";

    private JTextField txtId;
    private JButton btnEliminar, btnLimpiar;
    private PeliculaDAO peliculaDAO = new PeliculaDAO();

    public EliminarPeliculaFormBk() {
        setTitle(ELIMINAR_PELICULA);
        setSize(WIDTH1, HEIGHT1);
        setLayout(new GridLayout(3, 2));

        add(new JLabel(ID_ETIQUETA));
        txtId = new JTextField();
        add(txtId);

        btnEliminar = new JButton(ELIMINAR);
        btnLimpiar = new JButton(LIMPIAR);
        add(btnEliminar);
        add(btnLimpiar);

        btnEliminar.addActionListener(e -> {
            //TODO CONTROLAR EXCEPCION
            int id = Integer.parseInt(txtId.getText());
            int confirm = JOptionPane.showConfirmDialog(this, DESEA_ELIMINAR_ESTA_PELICULA);
            if (confirm == JOptionPane.YES_OPTION) {
                if (peliculaDAO.eliminarPelicula(id)) {
                    JOptionPane.showMessageDialog(this, PELICULA_ELIMINADA);
                } else {
                    JOptionPane.showMessageDialog(this, NO_SE_PUDO_ELIMINAR_VERIFIQUE_EL_ID);
                }
            }
        });

        btnLimpiar.addActionListener(e -> txtId.setText(VACIO));

        setVisible(true);
    }
}
