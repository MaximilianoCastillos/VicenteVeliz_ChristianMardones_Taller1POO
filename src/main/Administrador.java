package main;

import java.util.List;

/**
 * Clase que representa a un usuario con rol de Administrador. Permite gestionar otros usuarios.
 */
public class Administrador extends Usuario {

    /**
     * Constructor para crear un administrador.
     */
    public Administrador(String username, String password, String rol) {
        super(username, password, rol);
    }

    /**
     * Crea un nuevo usuario (Administrador, Coordinador, Estudiante).
     */
    public Usuario crearUsuario(String tipo, String username, String password, String rut, String nombre, String carrera, int semestre, String correo) {
        return UsuarioFactory.crearUsuario(tipo, username, password, rut, nombre, carrera, semestre, correo);
    }

    /**
     * Modifica los detalles de un usuario.
     */
    public void modificarUsuario(Usuario usuario, String nuevoUsername, String nuevaPassword, String nuevoRol) {
        usuario.setUsername(nuevoUsername);
        usuario.setPassword(nuevaPassword);
        usuario.setRol(nuevoRol);
    }

    /**
     * Elimina un usuario del sistema.
     */
    public void eliminarUsuario(Usuario usuario, List<Usuario> usuarios) {
        usuarios.remove(usuario);
    }

    /**
     * Restablece la contraseña de un usuario.
     */
    public void restablecerContraseña(Usuario usuario, String nuevaPassword) {
        usuario.setPassword(nuevaPassword);
    }
}