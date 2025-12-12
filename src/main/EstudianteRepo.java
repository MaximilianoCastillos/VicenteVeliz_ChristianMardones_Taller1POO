package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Repositorio que gestiona los estudiantes en el sistema.
 * Permite cargar estudiantes desde un archivo, listar todos los estudiantes y buscar estudiantes por su RUT.
 */
public class EstudianteRepo {
    private List<Estudiante> estudiantes;

    /**
     * Inicializa el repositorio de estudiantes.
     */
    public EstudianteRepo() {
        estudiantes = new ArrayList<>();
    }

    /**
     * Carga los estudiantes desde un archivo.
     * Lee cada línea del archivo y crea un estudiante con los datos obtenidos.
     */
    public void cargarEstudiantesDesdeArchivo(String ruta) {
        try {
            Scanner scanner = new Scanner(new File(ruta));
            
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");
                
                String rut = datos[0];
                String nombre = datos[1];
                String carrera = datos[2];
                int semestre = Integer.parseInt(datos[3]);
                String correo = datos[4];
                String password = datos[5];
                
                Estudiante estudiante = new Estudiante(rut, nombre, carrera, semestre, correo, password);
                estudiantes.add(estudiante);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Devuelve la lista de todos los estudiantes.
     */
    public List<Estudiante> listarEstudiantes() {
        return estudiantes;
    }

    /**
     * Busca un estudiante por su RUT.
     * Si encuentra un estudiante con el RUT proporcionado, lo devuelve.
     */
    public Estudiante buscarEstudiantePorRUT(String rut) {
        for (Estudiante e : estudiantes) {
            if (e.getRUT().equals(rut)) {
                return e;
            }
        }
        return null;
    }
}
