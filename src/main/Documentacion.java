package main;

public class Documentacion extends Tarea /** clase documentacion es una subclase que extiende de tarea */{
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

	@Override
	public void accept(TareaVisitor v)/** acepta al visitor en caso de que pidan algo en especifico*/ {
		v.visit(this);		
	}

}
