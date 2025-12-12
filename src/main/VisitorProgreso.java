package main;

/**
 * Implementa el patrón de diseño Visitor para calcular y mostrar el progreso de un estudiante en una certificación.
 * Verifica si el estudiante está inscrito en la certificación y calcula su progreso según su promedio.
 */
public class VisitorProgreso implements Visitor {
    private Estudiante estudiante;

    /**
     * Constructor que recibe un estudiante para realizar el seguimiento de su progreso.
     */
    public VisitorProgreso(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * Visita una certificación para verificar si el estudiante está inscrito y calcular su progreso.
     */
    @Override
    public void visitar(Certificacion certificacion) {
        // Verificar si el estudiante está inscrito en la certificación
        if (estudiante.isInscrito(certificacion)) {
            // Si el estudiante está inscrito, calcular su progreso
            double promedio = estudiante.calcularPromedio();  // Usamos el promedio de las notas del estudiante

            // Mostrar el progreso en función del promedio
            System.out.println("Progreso del estudiante " + estudiante.getNombre() + " en la certificación " + certificacion.getNombre() + ":");

            if (promedio >= 60) {
                System.out.println("¡El estudiante ha completado la certificación! Progreso: " + promedio + "%");
            } else {
                System.out.println("El estudiante aún no ha completado la certificación. Progreso: " + promedio + "%");
            }
        } else {
            // Si el estudiante no está inscrito en la certificación
            System.out.println("El estudiante " + estudiante.getNombre() + " no está inscrito en la certificación " + certificacion.getNombre());
        }
    }
}
