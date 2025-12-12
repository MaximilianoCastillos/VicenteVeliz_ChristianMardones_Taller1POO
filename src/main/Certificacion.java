package main;

/**
 * Representa una certificación con nombre, descripción, requisitos y validez.
 */
public class Certificacion {
    private String nombre;
    private String descripcion;
    private int requisitos;  // Número de créditos o requisitos
    private int validez;     // Días de validez o algún otro valor relevante

    /**
     * Constructor para crear una certificación.
     */
    public Certificacion(String nombre, String descripcion, int requisitos, int validez) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.requisitos = requisitos;
        this.validez = validez;
    }

    /**
     * Obtiene el nombre de la certificación.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la descripción de la certificación.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene los requisitos de la certificación (por ejemplo, número de créditos).
     */
    public int getRequisitos() {
        return requisitos;
    }

    /**
     * Obtiene la validez de la certificación (en días o años).
     */
    public int getValidez() {
        return validez;
    }

    /**
     * Establece el nombre de la certificación.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la descripción de la certificación.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Establece los requisitos de la certificación.
     */
    public void setRequisitos(int requisitos) {
        this.requisitos = requisitos;
    }

    /**
     * Establece la validez de la certificación.
     */
    public void setValidez(int validez) {
        this.validez = validez;
    }

    /**
     * Muestra toda la información de la certificación.
     */
    public String mostrarInformacion() {
        return 
               "Nombre: " + nombre + "\n" +
               "Descripción: " + descripcion + "\n" +
               "Requisitos de créditos: " + requisitos + "\n" +
               "Validez (años): " + validez;
    }

    /**
     * Verifica si un estudiante cumple con los requisitos de la certificación.
     */
    public boolean cumpleRequisitos(double d) {
        return d >= requisitos; // El estudiante debe tener al menos los créditos requeridos
    }
}
