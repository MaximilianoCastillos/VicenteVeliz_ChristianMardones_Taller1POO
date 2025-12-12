package main;

/**
 * Representa un curso dentro de un sistema de certificación. Contiene información sobre el curso, 
 * como su código NRC, nombre, semestre, créditos, área y prerrequisitos.
 */
public class Curso {
    private String nrc; // Código único del curso (NRC)
    private String nombre; // Nombre del curso
    private int semestre; // Semestre en el que se imparte
    private int creditos; // Número de créditos
    private String area; // Área de la certificación a la que pertenece
    private String prerrequisitos; // Cursos prerrequisito

    /**
     * Constructor para crear un curso con los parámetros proporcionados.
     */
    public Curso(String nrc, String nombre, int semestre, int creditos, String area, String prerrequisitos) {
        this.nrc = nrc;
        this.nombre = nombre;
        this.semestre = semestre;
        this.creditos = creditos;
        this.area = area;
        this.prerrequisitos = prerrequisitos;
    }

    /**
     * Obtiene el NRC del curso.
     */
    public String getNrc() {
        return nrc;
    }

    /**
     * Establece el NRC del curso.
     */
    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    /**
     * Obtiene el nombre del curso.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del curso.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el semestre en el que se imparte el curso.
     */
    public int getSemestre() {
        return semestre;
    }

    /**
     * Establece el semestre en el que se imparte el curso.
     */
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    /**
     * Obtiene la cantidad de créditos del curso.
     */
    public int getCreditos() {
        return creditos;
    }

    /**
     * Establece la cantidad de créditos del curso.
     */
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    /**
     * Obtiene el área a la que pertenece el curso.
     */
    public String getArea() {
        return area;
    }

    /**
     * Establece el área del curso.
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * Obtiene los prerrequisitos del curso.
     */
    public String getPrerrequisitos() {
        return prerrequisitos;
    }

    /**
     * Establece los prerrequisitos del curso.
     */
    public void setPrerrequisitos(String prerrequisitos) {
        this.prerrequisitos = prerrequisitos;
    }

    /**
     * Muestra toda la información del curso.
     */
    public String mostrarInformacion() {
        return "NRC: " + nrc + "\n" +
               "Nombre: " + nombre + "\n" +
               "Semestre: " + semestre + "\n" +
               "Créditos: " + creditos + "\n" +
               "Área: " + area + "\n" +
               "Prerrequisitos: " + prerrequisitos;
    }
}
