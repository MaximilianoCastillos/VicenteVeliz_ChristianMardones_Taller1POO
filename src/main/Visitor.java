package main;

/**
 * Interfaz para implementar el patrón de diseño Visitor.
 * Define un método para visitar una certificación y realizar acciones sobre ella.
 */
public interface Visitor {
    
    /**
     * Método para visitar una certificación.
     * Este método será implementado para realizar acciones específicas en la certificación.
     */
    void visitar(Certificacion certificacion);
}
