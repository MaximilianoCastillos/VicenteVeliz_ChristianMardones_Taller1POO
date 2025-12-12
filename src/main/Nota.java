package main;

/**
 * Representa una nota asociada a una asignatura cursada por un estudiante.
 * Contiene información sobre la asignatura, la calificación obtenida, el estado de la asignatura y el semestre.
 */
public class Nota {
    private String codigoAsignatura;
    private double calificacion;
    private String estado; // Aprobada, Reprobada, Cursando
    private String semestre;

    /**
     * Constructor para crear una nota con los parámetros proporcionados.
     */
    public Nota(String codigoAsignatura, double calificacion, String estado, String semestre) {
        this.codigoAsignatura = codigoAsignatura;
        this.calificacion = calificacion;
        this.estado = estado;
        this.semestre = semestre;
    }

    /**
     * Obtiene la calificación de la nota.
     */
    public double getCalificacion() {
        return this.calificacion;
    }

    /**
     * Obtiene el estado de la asignatura (Aprobada, Reprobada, Cursando).
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     * Obtiene el semestre en el que se cursó la asignatura.
     */
    public String getSemestre() {
        return this.semestre;
    }
}
