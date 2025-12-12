package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana que permite visualizar los estudiantes y su perfil.
 * Muestra una lista de estudiantes y permite seleccionar uno para ver su perfil.
 * También permite cargar los estudiantes desde un archivo.
 */
public class VentanaEstudiantes extends JFrame {
    private JList<String> listaEstudiantes; // Lista de estudiantes
    private JTextArea perfilEstudiante; // Área para mostrar el perfil del estudiante seleccionado
    private EstudianteRepo estudianteRepo; // Repositorio para gestionar los estudiantes

    /**
     * Constructor para inicializar la ventana de estudiantes.
     * Configura la interfaz gráfica con la lista de estudiantes y el perfil.
     */
    public VentanaEstudiantes(EstudianteRepo estudianteRepo) {
        this.estudianteRepo = estudianteRepo;
        
        setTitle("Estudiantes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel para mostrar la lista de estudiantes
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Lista de estudiantes (con los nombres de los estudiantes)
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Estudiante estudiante : estudianteRepo.listarEstudiantes()) {
            model.addElement(estudiante.getNombre()); // Agregamos los nombres de los estudiantes
        }
        listaEstudiantes = new JList<>(model);
        listaEstudiantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Panel para el perfil
        perfilEstudiante = new JTextArea();
        perfilEstudiante.setEditable(false); // Solo lectura

        // Añadir componentes al panel
        panel.add(new JScrollPane(listaEstudiantes), BorderLayout.WEST);
        panel.add(new JScrollPane(perfilEstudiante), BorderLayout.CENTER);

        // Acción al seleccionar un estudiante de la lista
        listaEstudiantes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Para evitar doble actualización al seleccionar
                String nombreSeleccionado = listaEstudiantes.getSelectedValue();
                Estudiante estudianteSeleccionado = estudianteRepo.buscarEstudiantePorRUT(nombreSeleccionado); // O usar otro criterio de búsqueda
                if (estudianteSeleccionado != null) {
                    perfilEstudiante.setText(estudianteSeleccionado.getPerfil()); // Mostramos el perfil
                }
            }
        });

        // Botón para cargar los estudiantes desde el archivo
        JButton btnCargarEstudiantes = new JButton("Cargar Estudiantes");
        btnCargarEstudiantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamamos al método para cargar los estudiantes desde el archivo
                estudianteRepo.cargarEstudiantesDesdeArchivo("ruta/a/estudiantes.txt");
                model.clear(); // Limpiar la lista
                for (Estudiante estudiante : estudianteRepo.listarEstudiantes()) {
                    model.addElement(estudiante.getNombre()); // Volver a agregar los estudiantes a la lista
                }
            }
        });

        // Añadir el botón al panel
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCargarEstudiantes);

        // Añadir todo al JFrame
        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }
}
