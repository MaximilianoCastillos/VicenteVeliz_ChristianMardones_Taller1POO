package main;

public class Bug extends Tarea /** clase bug es una  subclase que extiende de tarea */ {
	private String prioridad;
	

	public Bug(String id, String tipo, String descripcion, String estado, String complejidad, String fecha) {
		super(id, tipo, descripcion, estado, complejidad, fecha);

	}

	@Override
	public void Accion() /** viene de tarea ya que extiende de ella*/ {
		
	}
	
	public String getPrioridad() {
		return prioridad;
	}

	@Override
	public void accept(TareaVisitor v)/** acepta al visitor */ {
		v.visit(this);		
	}

}
