package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana que permite al coordinador gestionar estudiantes y certificaciones.
 * El coordinador puede inscribir estudiantes en certificaciones, ver perfiles y mostrar estadísticas.
 */
public class VentanaCoordinador extends JFrame {
    private EstudianteRepo estudianteRepo; // Repositorio de estudiantes
    private CertificacionRepo certificacionRepo; // Repositorio de certificaciones
    private JList<String> listaEstudiantes; // Lista de estudiantes
    private JList<String> listaCertificaciones; // Lista de certificaciones
    private JTextArea areaDetalles; // Área de texto para mostrar detalles del estudiante o certificación

    /**
     * Constructor de la ventana del coordinador.
     * Configura la interfaz gráfica con listas de estudiantes, certificaciones y botones de acción.
     */
    public VentanaCoordinador(EstudianteRepo estudianteRepo, CertificacionRepo certificacionRepo) {
        this.estudianteRepo = estudianteRepo;
        this.certificacionRepo = certificacionRepo;
        
        setTitle("Coordinador");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Lista de estudiantes
        DefaultListModel<String> modeloEstudiantes = new DefaultListModel<>();
        for (Estudiante estudiante : estudianteRepo.listarEstudiantes()) {
            modeloEstudiantes.addElement(estudiante.getNombre());
        }
        listaEstudiantes = new JList<>(modeloEstudiantes);
        listaEstudiantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Lista de certificaciones
        DefaultListModel<String> modeloCertificaciones = new DefaultListModel<>();
        for (Certificacion certificacion : certificacionRepo.listarCertificaciones()) {
            modeloCertificaciones.addElement(certificacion.getNombre());
        }
        listaCertificaciones = new JList<>(modeloCertificaciones);
        listaCertificaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Crear área de texto para mostrar detalles
        areaDetalles = new JTextArea();
        areaDetalles.setEditable(false); // Solo lectura

        // Agregar los componentes al panel
        panel.add(new JScrollPane(listaEstudiantes), BorderLayout.WEST);
        panel.add(new JScrollPane(listaCertificaciones), BorderLayout.EAST);
        panel.add(new JScrollPane(areaDetalles), BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        
        JButton btnGestionarCertificaciones = new JButton("Gestionar Certificaciones");
        btnGestionarCertificaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionarCertificaciones();
            }
        });

        JButton btnVerPerfilEstudiante = new JButton("Ver Perfil Estudiante");
        btnVerPerfilEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verPerfilEstudiante();
            }
        });

        JButton btnInscribirEstudiante = new JButton("Inscribir Estudiante");
        btnInscribirEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inscribirEstudiante();
            }
        });

        JButton btnMostrarEstadisticas = new JButton("Mostrar Estadísticas");
        btnMostrarEstadisticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEstadisticas();
            }
        });

        panelBotones.add(btnGestionarCertificaciones);
        panelBotones.add(btnVerPerfilEstudiante);
        panelBotones.add(btnInscribirEstudiante);
        panelBotones.add(btnMostrarEstadisticas);
        
        // Agregar todo al JFrame
        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Abre una nueva ventana para gestionar las certificaciones (crear/modificar/eliminar).
     */
    private void gestionarCertificaciones() {
        JOptionPane.showMessageDialog(this, "Gestionar certificaciones (Crear/Modificar)");
    }

    /**
     * Muestra el perfil del estudiante seleccionado en la lista.
     */
    private void verPerfilEstudiante() {
        String estudianteSeleccionado = listaEstudiantes.getSelectedValue();
        if (estudianteSeleccionado != null) {
            Estudiante estudiante = estudianteRepo.buscarEstudiantePorRUT(estudianteSeleccionado);
            if (estudiante != null) {
                areaDetalles.setText(estudiante.getPerfil()); // Mostrar el perfil del estudiante
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un estudiante.");
        }
    }

    /**
     * Inscribe al estudiante seleccionado en la certificación seleccionada.
     */
    private void inscribirEstudiante() {
        String estudianteSeleccionado = listaEstudiantes.getSelectedValue();
        String certificacionSeleccionada = listaCertificaciones.getSelectedValue();

        if (estudianteSeleccionado != null && certificacionSeleccionada != null) {
            Estudiante estudiante = estudianteRepo.buscarEstudiantePorRUT(estudianteSeleccionado);
            Certificacion certificacion = certificacionRepo.buscarCertificacionPorNombre(certificacionSeleccionada);

            if (estudiante != null && certificacion != null) {
                if (estudiante.inscribirseEnCertificacion(certificacion)) {
                    JOptionPane.showMessageDialog(this, "Estudiante inscrito exitosamente en la certificación.");
                } else {
                    JOptionPane.showMessageDialog(this, "El estudiante ya está inscrito en esta certificación.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo inscribir al estudiante en la certificación.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un estudiante y una certificación.");
        }
    }

    /**
     * Muestra las estadísticas de la certificación seleccionada.
     */
    private void mostrarEstadisticas() {
        String certificacionSeleccionada = listaCertificaciones.getSelectedValue();
        if (certificacionSeleccionada != null) {
            Certificacion certificacion = certificacionRepo.buscarCertificacionPorNombre(certificacionSeleccionada);
            if (certificacion != null) {
                // Mostrar la cantidad de estudiantes inscritos
                int inscritos = certificacionRepo.obtenerCantidadEstudiantesInscritos(certificacion);
                areaDetalles.setText("Estadísticas para la certificación " + certificacion.getNombre() + ":\n");
                areaDetalles.append("Estudiantes inscritos: " + inscritos + "\n");

                // Calcular y mostrar el progreso de cada estudiante
                for (Estudiante estudiante : estudianteRepo.listarEstudiantes()) {
                    VisitorProgreso visitor = new VisitorProgreso(estudiante);
                    visitor.visitar(certificacion);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una certificación.");
        }
    }
}
