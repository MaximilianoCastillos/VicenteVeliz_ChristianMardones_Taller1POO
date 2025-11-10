package main;

public class Feature extends Tarea {
	private String prioridad;

	public Feature(String id, String tipo, String descripcion, String estado, String complejidad, String fecha) {
		super(id, tipo, descripcion, estado, complejidad, fecha);

	}

	@Override
	public void Accion() {
		// TODO Auto-generated method stub
		
	}
	
	public String getPrioridad() {
		return prioridad;
	}

}
