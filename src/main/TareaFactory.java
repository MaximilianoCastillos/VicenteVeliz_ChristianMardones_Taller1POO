package main;

public class TareaFactory /** encargada de crear los objetos de tipo Bug,Fetaure,Documentacion*/ {
	
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
            // por si es que trae un tipo no previsto.
            return new Documentacion(id, tipo, descripcion, estado, complejidad, fecha);
		}
	}
	
}
