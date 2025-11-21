package main;

/**
 * La clase Feature representa una tarea de tipo "Feature" que extiende la clase Tarea.
 * Esta clase se agrega para poder manejar la prioridad de la tarea y
 * permite ser visitada por un visitante según el patrón de diseño Visitor.
 */

public class Feature extends Tarea/** la clase feature es una subclase que extiende de tarea */ {
	private String prioridad;
	 /**
     * Constructor de la clase Feature.
     * Inicializa un objeto de tipo Feature con los valores proporcionados.
     *
     * @param id          El ID de la tarea.
     * @param tipo        El tipo de tarea (en este caso, "Feature").
     * @param descripcion Descripción de la tarea.
     * @param estado      El estado de la tarea (ej. "pendiente", "en progreso").
     * @param complejidad La complejidad de la tarea.
     * @param fecha       La fecha de la tarea.
     */

	public Feature(String id, String tipo, String descripcion, String estado, String complejidad, String fecha) {
		super(id, tipo, descripcion, estado, complejidad, fecha);

	}
	
	 /**
     * Método que representa la acción asociada a una tarea de tipo Feature.
     * Actualmente no tiene implementación específica, pero puede ser sobreescrito
     * por subclases o definido más adelante.
     */

	@Override
	public void Accion() {
		// TODO Auto-generated method stub
		
	}
	
	 /**
     * Obtiene la prioridad de la tarea de tipo Feature.
     *
     * @return La prioridad de la tarea de tipo Feature.
     */
	
	public String getPrioridad() {
		return prioridad;
	}
	/**
     * Método que permite que un visitante (Visitor) visite este objeto Feature.
     * Implementa el patrón de diseño Visitor para permitir realizar diferentes
     * acciones sobre el objeto sin modificar la clase del mismo.
     *
     * @param v El visitante que realizará la acción sobre esta tarea de tipo Feature.
     */
	@Override
	public void accept(TareaVisitor v) {
		v.visit(this);		
	}

}
