package main;

/**
 * Fábrica de usuarios que crea instancias de diferentes tipos de usuarios
 * (Administrador, Coordinador, Estudiante) basándose en el tipo especificado.
 */
public class UsuarioFactory {

    /**
     * Crea un nuevo usuario según el tipo especificado.
     * Dependiendo del tipo, se crea un Administrador, Coordinador o Estudiante.
     */
    public static Usuario crearUsuario(String tipo, String username, String password, String rut, String nombre, String carrera, int semestre, String correo) {
        if (tipo.equalsIgnoreCase("admin")) {
            return new Administrador(username, password, "Admin");
        } else if (tipo.equalsIgnoreCase("coordinador")) {
            return new Coordinador(username, password, "Coordinador");
        } else if (tipo.equalsIgnoreCase("estudiante")) {
            // Aquí estamos creando el Estudiante con los parámetros correctos
            return new Estudiante(rut, nombre, carrera, semestre, correo, password);  // Corregido
        }
        return null; // Si el tipo no es reconocido, retorna null
    }
}
