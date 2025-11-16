package main;

public class Bug extends Tarea {
	private String prioridad;
	

	public Bug(String id, String tipo, String descripcion, String estado, String complejidad, String fecha) {
		super(id, tipo, descripcion, estado, complejidad, fecha);

	}

	@Override
	public void Accion() {
		
	}
	
	public String getPrioridad() {
		return prioridad;
	}

	@Override
	public void accept(TareaVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
