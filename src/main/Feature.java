package main;

public class Feature extends Tarea/** la clase feature es una subclase que extiende de tarea */ {
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

	@Override
	public void accept(TareaVisitor v) {
		v.visit(this);		
	}

}
