package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Repositorio que gestiona los usuarios en el sistema.
 * Permite cargar usuarios desde un archivo, listar todos los usuarios, buscar un usuario por nombre de usuario,
 * agregar nuevos usuarios y eliminar usuarios.
 */
public class UsuarioRepo {
    private List<Usuario> usuarios;

    /**
     * Constructor para inicializar el repositorio de usuarios.
     */
    public UsuarioRepo() {
        usuarios = new ArrayList<>();
    }

    /**
     * Carga los usuarios desde un archivo.
     * El archivo debe contener los datos de los usuarios separados por ";".
     */
    public void cargarUsuariosDesdeArchivo(String ruta) {
        try {
            Scanner scanner = new Scanner(new File(ruta));

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");

                if (datos[2].equalsIgnoreCase("admin")) {
                    usuarios.add(new Administrador(datos[0], datos[1], "Admin"));
                } else if (datos[2].equalsIgnoreCase("coordinador")) {
                    usuarios.add(new Coordinador(datos[0], datos[1], "Coordinador"));
                } else if (datos[2].equalsIgnoreCase("estudiante")) {
                    String rut = datos[3];
                    String nombre = datos[4];
                    String carrera = datos[5];
                    int semestre = Integer.parseInt(datos[6]);
                    String correo = datos[7];
                    usuarios.add(new Estudiante(rut, nombre, carrera, semestre, correo, datos[1]));
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Lista todos los usuarios registrados en el repositorio.
     */
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * @return El usuario que coincide con el nombre, o null si no se encuentra.
     */
    public Usuario buscarUsuarioPorUsername(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Agrega un nuevo usuario al repositorio.
     * Verifica que el usuario no exista previamente.
     */
    public void agregarUsuario(Usuario usuario) {
        if (buscarUsuarioPorUsername(usuario.getUsername()) == null) {
            usuarios.add(usuario);
        } else {
            System.out.println("El usuario ya existe.");
        }
    }

    /**
     * Elimina un usuario del repositorio.
     */
    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }
}
