package main;

/**
 * La clase Bug representa una tarea de tipo "Bug" que extiende la clase Tarea.
 * Esta clase agrega la funcionalidad para manejar la prioridad de la tarea y
 * permite ser visitada por un visitante según el patrón de diseño Visitor.
 */

public class Bug extends Tarea /** clase bug es una  subclase que extiende de tarea */ {
	private String prioridad;
	
	/**
     * Constructor de la clase Bug.
     * Inicializa un objeto Bug con los valores proporcionados.
     *
     * @param id          El ID de la tarea.
     * @param tipo        El tipo de tarea (en este caso, "Bug").
     * @param descripcion Descripción de la tarea.
     * @param estado      El estado de la tarea (ej. "pendiente", "en progreso").
     * @param complejidad La complejidad de la tarea.
     * @param fecha       La fecha de la tarea.
     */
	

	public Bug(String id, String tipo, String descripcion, String estado, String complejidad, String fecha) {
		super(id, tipo, descripcion, estado, complejidad, fecha);

	}

	@Override
	 /**
     * Método que representa la acción asociada a un bug.
     * En este caso, no realiza ninguna acción concreta ya que la lógica
     * debe ser definida por las subclases o el contexto de uso.
     */
	public void Accion() /** viene de tarea ya que extiende de ella*/ {
		
	}
	
	 /**
     * Obtiene la prioridad del bug.
     *
     * @return La prioridad del bug.
     */
	public String getPrioridad() {
		return prioridad;
	}
	
	 /**
     * Método que permite que un visitante (Visitor) visite este objeto Bug.
     * Implementa el patrón de diseño Visitor para permitir realizar diferentes
     * acciones sobre el objeto sin modificar la clase del mismo.
     *
     * @param v El visitante que realizará la acción sobre este Bug.
     */

	@Override
	public void accept(TareaVisitor v)/** acepta al visitor */ {
		v.visit(this);		
	}

}
