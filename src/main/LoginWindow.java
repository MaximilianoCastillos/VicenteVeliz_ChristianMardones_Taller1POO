package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana de inicio de sesión para autenticar al usuario.
 * Permite ingresar el nombre de usuario y la contraseña, y valida si los datos coinciden con los registrados.
 */
public class LoginWindow extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    /**
     * Constructor para crear la ventana de login.
     * Inicializa los componentes de la interfaz gráfica (campos de texto y botón).
     */
    public LoginWindow() {
        setTitle("Login");
        setLayout(new FlowLayout());
        setSize(300, 150);

        // Crear los campos de texto y el botón
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");

        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                
                // Verificar usuario y rol
                Usuario usuario = Sistema.getInstancia().getUsuarioRepo().buscarUsuarioPorUsername(username);
                if (usuario != null && usuario.getPassword().equals(password)) {
                    // Dependiendo del rol, abrir la ventana correspondiente
                    if (usuario.getRol().equals("Admin")) {
                        // Si es Admin, abrir la ventana Admin
                        new VentanaAdmin(Sistema.getInstancia().getUsuarioRepo()); // Pasar el repositorio de usuarios
                    } else if (usuario.getRol().equals("Coordinador")) {
                        // Si es Coordinador, abrir la ventana Coordinador
                        EstudianteRepo estudianteRepo = Sistema.getInstancia().getEstudianteRepo();
                        CertificacionRepo certificacionRepo = Sistema.getInstancia().getCertificacionRepo();
                        new VentanaCoordinador(estudianteRepo, certificacionRepo);
                    } else if (usuario.getRol().equals("Estudiante")) {
                        // Si es Estudiante, abrir la ventana Estudiantes
                        new VentanaEstudiantes(Sistema.getInstancia().getEstudianteRepo());
                    }
                    dispose(); // Cerrar la ventana de login después de iniciar sesión
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });

        // Añadir los componentes al panel
        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Método principal que inicia la ventana de login.
     */
    public static void main(String[] args) {
        // Iniciar la ventana de Login
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginWindow();
            }
        });
    }
}
