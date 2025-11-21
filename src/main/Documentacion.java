package main;

/**
 * La clase Documentacion representa una tarea de tipo "Documentación" que extiende
 * la clase Tarea. Esta clase agrega la funcionalidad para manejar la prioridad
 * de la tarea y permite ser visitada por un visitante según el patrón de diseño Visitor.
 */

public class Documentacion extends Tarea /** clase documentacion es una subclase que extiende de tarea */{
	private String prioridad;
	
	 /**
     * Constructor de la clase Documentacion.
     * Inicializa un objeto de tipo Documentacion con los valores proporcionados.
     *
     * @param id          El ID de la tarea.
     * @param tipo        El tipo de tarea (en este caso, "Documentación").
     * @param descripcion Descripción de la tarea.
     * @param estado      El estado de la tarea (ej. "pendiente", "en progreso").
     * @param complejidad La complejidad de la tarea.
     * @param fecha       La fecha de la tarea.
     */

	public Documentacion(String id, String tipo, String descripcion, String estado, String complejidad, String fecha) {
		super(id, tipo, descripcion, estado, complejidad, fecha);
	}
	
	/**
     * Método que representa la acción asociada a una tarea de tipo Documentación.
     * Actualmente no tiene implementación específica, pero puede ser sobreescrito
     * por subclases o definido más adelante.
     */

	@Override
	public void Accion() {
		
	}
	
	 /**
     * Obtiene la prioridad de la tarea de documentación.
     *
     * @return La prioridad de la tarea de documentación.
     */
	
	public String getPrioridad() {
		return prioridad;
	}
	
	 /**
     * Método que permite que un visitante (Visitor) visite este objeto Documentacion.
     * Implementa el patrón de diseño Visitor para permitir realizar diferentes
     * acciones sobre el objeto sin modificar la clase del mismo.
     *
     * @param v El visitante que realizará la acción sobre esta tarea de documentación.
     */

	@Override
	public void accept(TareaVisitor v)/** acepta al visitor en caso de que pidan algo en especifico*/ {
		v.visit(this);		
	}

}
