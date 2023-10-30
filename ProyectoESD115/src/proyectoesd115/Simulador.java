
package proyectoesd115;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Simulador {
 // Componentes de la GUI.
    private JFrame frame;                         // Marco principal de la aplicación.
    private JPanel panel;                         // Panel para contener otros componentes.
    private JTextArea areaTexto;                  // Área para mostrar información sobre objetos.
    private JTextField campoId, campoTamaño;      // Campos de texto para mostrar ID y obtener el tamaño del objeto.
    private JRadioButton enUsoRadio, noEnUsoRadio;// Botones de opción para determinar el estado de uso del objeto.
    private ButtonGroup radioButtonGroup;         // Grupo para contener los botones de opción.
    private JButton btnAgregar, btnMostrar, btnRecolectar; // Botones para funciones específicas.

    private Memoria memoria;                      // Objeto de clase Memoria para gestionar la memoria.

    // Constructor que inicializa la memoria y configura la GUI.
    public Simulador() {
        memoria = new Memoria();
        configurarUI();
    }

    // Método para configurar el marco y los componentes principales de la GUI.
    private void configurarUI() {
        frame = new JFrame("Gestión de Memoria - Simulador");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setIconImage(new ImageIcon("D:/Proyectos Java/ProyectoESD115/path/escoba.png").getImage());
        frame.setResizable(false);

        panel = new JPanel(new FlowLayout());
        configurarComponentes();

        frame.add(panel);
        frame.setVisible(true);
    }

    // Método para configurar e inicializar componentes individuales de la GUI.
    private void configurarComponentes() {
        areaTexto = new JTextArea(10, 35);
        areaTexto.setEditable(false);
        
        campoId = new JTextField(5);
        campoId.setText(Integer.toString(Objeto.contId));
        campoId.setEditable(false);
        
        campoTamaño = new JTextField(5);

        enUsoRadio = new JRadioButton("En Uso", true);
        noEnUsoRadio = new JRadioButton("No en Uso");
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(enUsoRadio);
        radioButtonGroup.add(noEnUsoRadio);

        btnAgregar = new JButton("Agregar Objeto");
        btnAgregar.addActionListener(e -> agregarObjeto());

        btnMostrar = new JButton("Mostrar Objetos");
        btnMostrar.addActionListener(e -> mostrarObjetos());

        btnRecolectar = new JButton("Recolectar Basura");
        btnRecolectar.addActionListener(e -> recolectarBasura());

        // Añadir componentes al panel principal.
        panel.add(new JLabel("Tarea:"));
        panel.add(campoId);
        panel.add(new JLabel("Tamaño:"));
        panel.add(campoTamaño);
        panel.add(new JLabel("Kb"));
        panel.add(enUsoRadio);
        panel.add(noEnUsoRadio);
        panel.add(new JScrollPane(areaTexto));
        panel.add(btnAgregar);
        panel.add(btnMostrar);
        panel.add(btnRecolectar);
    }

    // Método para agregar un objeto a la memoria.
    private void agregarObjeto() {
        try {
            int tamaño = Integer.parseInt(campoTamaño.getText());
            boolean enUso = enUsoRadio.isSelected();
            memoria.asignarObjeto(new Objeto(tamaño, enUso));

            Objeto.contId++;
            campoId.setText(Integer.toString(Objeto.contId));

            campoTamaño.setText("");
            JOptionPane.showMessageDialog(null, "Se ha agregado la tarea");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un tamaño válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para mostrar todos los objetos en la memoria.
    private void mostrarObjetos() {
        StringBuilder sb = new StringBuilder();
        Nodo actual = memoria.cabeza;
        while (actual != null) {
            sb.append("Tarea " + actual.objeto.id + " - Tamaño kb: " + actual.objeto.tamaño + " - En uso: " + actual.objeto.enUso + "\n");
            actual = actual.siguiente;
        }
        areaTexto.setText(sb.toString());
        JOptionPane.showMessageDialog(null, "Se muestran todas las tareas...");
    }

    // Método para eliminar objetos que no están en uso.
    private void recolectarBasura() {
        memoria.recolectarBasura();
        if (memoria.objetosEliminados.size() > 0) {
            JOptionPane.showMessageDialog(null, "Se han eliminado las tareas fuera de uso, para liberar memoria...");
        } else {
            JOptionPane.showMessageDialog(null, "No había tareas fuera de uso para eliminar.");
        }
        mostrarObjetos();
    }

    // Método principal para ejecutar la aplicación.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Simulador());
    }
   
}






