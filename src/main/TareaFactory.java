package main;

/**
 * La clase TareaFactory es responsable de crear objetos de tipo `Tarea`.
 * Dependiendo del tipo de tarea proporcionado (Bug, Feature, Documentación), 
 * la clase crea una instancia correspondiente de la subclase apropiada (`Bug`, `Feature`, `Documentacion`).
 */

public class TareaFactory  {
	/**
     * Crea una tarea basada en el tipo proporcionado.
     * Si el tipo es "bug", "feature" o "documentación", se crea una instancia
     * de la subclase correspondiente. Si el tipo no es reconocido, se crea una tarea
     * de tipo `Documentacion` por defecto.
     * 
     * @param tipo        El tipo de tarea a crear por ejemplo  "bug", "feature", "documentación"
     * @param id          El ID de la tarea.
     * @param descripcion La descripción de la tarea.
     * @param estado      El estado de la tarea (ej. "pendiente", "en progreso").
     * @param complejidad La complejidad de la tarea (ej. "alta", "media", "baja").
     * @param fecha       La fecha de la tarea.
     * @return Una instancia de la subclase correspondiente de `Tarea` (Bug, Feature, Documentación).
     */
	
	public static Tarea crear(String tipo, String id, String descripcion, String estado, String complejidad, String fecha) {
		String t = tipo == null ? "" : tipo.trim().toLowerCase();
		
		switch (t) {
		case "bug":
            return new Bug(id, "Bug", descripcion, estado, complejidad, fecha);
        case "feature":
            return new Feature(id, "Feature", descripcion, estado, complejidad, fecha);
        case "documentacion":
        case "documentación":
            return new Documentacion(id, "Documentación", descripcion, estado, complejidad, fecha);
        default:
           
            return new Documentacion(id, tipo, descripcion, estado, complejidad, fecha);
		}
	}
	
}
