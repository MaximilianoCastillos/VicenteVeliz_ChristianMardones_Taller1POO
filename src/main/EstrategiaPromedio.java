package main;

/**
 * Interfaz para calcular el promedio de un estudiante.
 * Las clases que implementen esta interfaz deben definir la lógica de cálculo del promedio.
 */
public interface EstrategiaPromedio {
    
    /**
     * Método para calcular el promedio de un estudiante.
     * @param e El estudiante del cual se calculará el promedio.
     * @return El promedio calculado.
     */
    double calcularPromedio(Estudiante e);
}
