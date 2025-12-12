package main;

import java.util.List;

/**
 * Estrategia para calcular el promedio general de un estudiante.
 * Implementa la interfaz `EstrategiaPromedio`.
 */
public class PromedioGeneral implements EstrategiaPromedio {

    /**
     * Calcula el promedio de las notas de un estudiante.
     * Si el estudiante no tiene notas, retorna 0.
     */
    @Override
    public double calcularPromedio(Estudiante e) {
        // Obtener la lista de notas del estudiante
        List<Nota> notas = e.getNotas();

        // Si el estudiante no tiene notas, retornamos 0
        if (notas.isEmpty()) {
            return 0.0;
        }

        // Calcular la suma total de las calificaciones
        double sumaCalificaciones = 0.0;
        for (Nota nota : notas) {
            sumaCalificaciones += nota.getCalificacion();
        }

        // Calcular y retornar el promedio
        return sumaCalificaciones / notas.size(); // Promedio de todas las calificaciones
    }
}
