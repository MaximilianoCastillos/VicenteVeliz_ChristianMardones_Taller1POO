package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un estudiante en el sistema. Hereda de `Usuario` y gestiona información como su RUT, nombre, carrera, semestre,
 * correo, notas y certificaciones a las que está inscrito.
 */
public class Estudiante extends Usuario {
    private String rut;
    private String nombre;
    private String carrera;
    private int semestre;
    private String correo;
    private List<Nota> notas; // Lista de notas del estudiante
    private List<Certificacion> certificacionesInscritas; // Lista de certificaciones en las que el estudiante está inscrito

    /**
     * Constructor para crear un estudiante.
     */
    public Estudiante(String rut, String nombre, String carrera, int semestre, String correo, String password) {
        super(nombre, password, "Estudiante"); // El rol es siempre "Estudiante"
        this.rut = rut;
        this.nombre = nombre;
        this.carrera = carrera;
        this.semestre = semestre;
        this.correo = correo;
        this.notas = new ArrayList<>(); // Inicializa la lista de notas vacía
        this.certificacionesInscritas = new ArrayList<>(); // Inicializa la lista de certificaciones inscritas vacía
    }

    /**
     * Obtiene el RUT del estudiante.
     */
    public String getRUT() {
        return this.rut;
    }

    /**
     * Obtiene el nombre del estudiante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la carrera del estudiante.
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * Obtiene el semestre en el que está el estudiante.
     */
    public int getSemestre() {
        return semestre;
    }

    /**
     * Obtiene el correo del estudiante.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Obtiene la lista de notas del estudiante.
     */
    public List<Nota> getNotas() {
        return notas;
    }

    /**
     * Obtiene la lista de certificaciones en las que el estudiante está inscrito.
     */
    public List<Certificacion> getCertificacionesInscritas() {
        return certificacionesInscritas;
    }

    /**
     * Obtiene el perfil completo del estudiante.
     */
    public String getPerfil() {
        return "Nombre: " + this.nombre + "\n" +
               "RUT: " + this.rut + "\n" +
               "Carrera: " + this.carrera + "\n" +
               "Semestre: " + this.semestre + "\n" +
               "Correo: " + this.correo;
    }

    /**
     * Agrega una nota a la lista de notas del estudiante.
     */
    public void agregarNota(Nota nota) {
        this.notas.add(nota);
    }

    /**
     * Calcula el promedio de las notas del estudiante.
     */
    public double calcularPromedio() {
        if (notas.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (Nota nota : notas) {
            total += nota.getCalificacion();
        }

        return total / notas.size(); // Promedio de las notas
    }

    /**
     * Verifica si el estudiante está inscrito en una certificación.
     */
    public boolean isInscrito(Certificacion certificacion) {
        return certificacionesInscritas.contains(certificacion);
    }

    /**
     * Inscribe al estudiante en una certificación.
     */
    public boolean inscribirseEnCertificacion(Certificacion certificacion) {
        if (!isInscrito(certificacion)) {
            certificacionesInscritas.add(certificacion);
            return true;
        } else {
            return false; // Ya está inscrito en la certificación
        }
    }

    /**
     * Desinscribe al estudiante de una certificación.
     */
    public void desinscribirseDeCertificacion(Certificacion certificacion) {
        certificacionesInscritas.remove(certificacion);
    }
}
