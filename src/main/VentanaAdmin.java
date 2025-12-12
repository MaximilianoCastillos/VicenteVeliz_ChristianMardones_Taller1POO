package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana de administración donde el administrador puede crear, modificar, eliminar usuarios y restablecer contraseñas.
 */
public class VentanaAdmin extends JFrame {
    private UsuarioRepo usuarioRepo; // Repositorio de usuarios

    /**
     * Constructor para inicializar la ventana de administración.
     * Configura los botones para gestionar usuarios y sus acciones.
     */
    public VentanaAdmin(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;

        setTitle("Administrador");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Botones para administrar usuarios
        JButton btnCrearUsuario = new JButton("Crear Usuario");
        JButton btnModificarUsuario = new JButton("Modificar Usuario");
        JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
        JButton btnRestablecerContraseña = new JButton("Restablecer Contraseña");

        // Acciones de los botones
        btnCrearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuario();
            }
        });

        btnModificarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarUsuario();
            }
        });

        btnEliminarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

        btnRestablecerContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restablecerContraseña();
            }
        });

        // Añadir los botones al panel
        panel.add(btnCrearUsuario);
        panel.add(btnModificarUsuario);
        panel.add(btnEliminarUsuario);
        panel.add(btnRestablecerContraseña);
        
        // Añadir el panel al JFrame
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Crea un nuevo usuario e lo agrega al repositorio.
     */
    private void crearUsuario() {
        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        String password = JOptionPane.showInputDialog("Ingrese la contraseña:");
        String rol = JOptionPane.showInputDialog("Ingrese el rol (admin, coordinador, estudiante):");

        // Crear el usuario utilizando UsuarioFactory
        Usuario nuevoUsuario = UsuarioFactory.crearUsuario(rol, username, password, "rut", "nombre", "carrera", 5, "correo@dominio.com");

        // Verificar si el usuario ya existe en el repositorio
        Usuario usuarioExistente = usuarioRepo.buscarUsuarioPorUsername(username);
        if (usuarioExistente != null) {
            JOptionPane.showMessageDialog(this, "El usuario ya existe en el sistema.");
        } else {
            // Agregar el usuario al repositorio
            usuarioRepo.agregarUsuario(nuevoUsuario);

            // Verificación
            if (usuarioRepo.buscarUsuarioPorUsername(username) != null) {
                JOptionPane.showMessageDialog(this, "Usuario creado correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Hubo un error al crear el usuario");
            }
        }
    }

    /**
     * Modifica los datos de un usuario existente.
     */
    private void modificarUsuario() {
        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario a modificar:");
        Usuario usuario = usuarioRepo.buscarUsuarioPorUsername(username);

        if (usuario != null) {
            String nuevoUsername = JOptionPane.showInputDialog("Nuevo nombre de usuario:");
            String nuevaPassword = JOptionPane.showInputDialog("Nueva contraseña:");
            String nuevoRol = JOptionPane.showInputDialog("Nuevo rol:");

            // Modificar los datos del usuario
            usuario.setUsername(nuevoUsername);
            usuario.setPassword(nuevaPassword);
            usuario.setRol(nuevoRol);

            JOptionPane.showMessageDialog(this, "Usuario modificado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado");
        }
    }

    /**
     * Elimina un usuario del sistema.
     */
    private void eliminarUsuario() {
        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario a eliminar:");
        Usuario usuario = usuarioRepo.buscarUsuarioPorUsername(username);

        if (usuario != null) {
            usuarioRepo.eliminarUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado");
        }
    }

    /**
     * Restablece la contraseña de un usuario.
     */
    private void restablecerContraseña() {
        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        Usuario usuario = usuarioRepo.buscarUsuarioPorUsername(username);

        if (usuario != null) {
            String nuevaPassword = JOptionPane.showInputDialog("Ingrese la nueva contraseña:");
            usuario.setPassword(nuevaPassword);
            JOptionPane.showMessageDialog(this, "Contraseña restablecida correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado");
        }
    }
}
