package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Repositorio que gestiona las certificaciones del sistema. Permite cargar certificaciones desde un archivo,
 * agregar nuevas certificaciones, listar las certificaciones y buscar certificaciones por nombre.
 */
public class CertificacionRepo {
    private List<Certificacion> certificaciones;

    /**
     * Constructor que inicializa la lista de certificaciones.
     */
    public CertificacionRepo() {
        certificaciones = new ArrayList<>();
    }

    /**
     * Carga las certificaciones desde un archivo.
     * El archivo debe contener los datos de las certificaciones separados por ";".
     */
    public void cargarCertificacionesDesdeArchivo(String ruta) {
        try {
            // Usamos Scanner para leer el archivo
            Scanner scanner = new Scanner(new File(ruta));

            // Leer cada línea del archivo
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                // Separar los campos usando ";" como delimitador
                String[] datos = linea.split(";");

                // Crear una certificación con los datos completos
                String nombre = datos[0]; // Nombre de la certificación
                String descripcion = datos[1]; // Descripción (texto largo)
                int requisitos = Integer.parseInt(datos[2]);  // Convierte requisitos a int (número)
                int validez = Integer.parseInt(datos[3]);  // Convierte validez a int (número)

                // Crear la certificación y agregarla a la lista
                certificaciones.add(new Certificacion(nombre, descripcion, requisitos, validez));
            }

            // Cerrar el scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir los datos a número: " + e.getMessage());
        }
    }

    /**
     * Agrega una nueva certificación al repositorio.
     */
    public void agregarCertificacion(Certificacion certificacion) {
        certificaciones.add(certificacion);
    }

    /**
     * Lista todas las certificaciones.
     */
    public List<Certificacion> listarCertificaciones() {
        return certificaciones;
    }

    /**
     * Busca una certificación por su nombre.
     */
    public Certificacion buscarCertificacionPorNombre(String nombre) {
        for (Certificacion c : certificaciones) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Obtiene la cantidad de estudiantes inscritos en una certificación.
     */
    public int obtenerCantidadEstudiantesInscritos(Certificacion certificacion) {
        int inscritos = 0;
        for (Estudiante estudiante : Sistema.getInstancia().getEstudianteRepo().listarEstudiantes()) {
            if (estudiante.isInscrito(certificacion)) {
                inscritos++;
            }
        }
        return inscritos;
    }
}
