package main;

public class Documentacion extends Tarea {
	private String prioridad;

	public Documentacion(String id, String tipo, String descripcion, String estado, String complejidad, String fecha) {
		super(id, tipo, descripcion, estado, complejidad, fecha);
	}

	@Override
	public void Accion() {
		
	}
	
	public String getPrioridad() {
		return prioridad;
	}

}
