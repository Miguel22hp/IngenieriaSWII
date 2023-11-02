package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JTextField campoTexto;
    private JLabel etiqueta;
    private JButton botonGuardar;
    private JTextArea areaTexto;

    public View() {
        super("Mi Aplicación de Introducción y Recuperación de Datos");

        // Configura el diseño del marco
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Crea y configura los componentes
        etiqueta = new JLabel("Introduce un dato:");
        campoTexto = new JTextField(20);
        botonGuardar = new JButton("Guardar");
        areaTexto = new JTextArea(10, 30);

        // Agrega un ActionListener al botón para guardar datos
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dato = campoTexto.getText();
                areaTexto.append(dato + "\n");
                campoTexto.setText(""); // Limpia el campo de texto
            }
        });

        // Agrega los componentes a la ventana
        add(etiqueta);
        add(campoTexto);
        add(botonGuardar);
        add(areaTexto);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View app = new View();
            app.setVisible(true);
        });
    }
}
