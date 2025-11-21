package main;
/**
 * La clase  Tarea  es abstratca ya que es una tarea general en el sistema.
 * Las subclases de esta clase son Bug, Feature y Documentacion,
 *  Una tarea tiene un ID, tipo, descripción, estado, usuario responsable,
 * complejidad y fecha de creación. Además, está asociada a un proyecto.
 */
public abstract class Tarea {
	private Proyecto proyecto;
	private String id;
	private String tipo;
	private String descripcion;
	private String estado;
	private Usuario usuario;
	private String complejidad;
	private String fecha;
	
	/**
     * Constructor de la clase Tarea.
     * Inicializa una tarea con los valores proporcionados.
     *
     * @param id          El ID de la tarea.
     * @param tipo        El tipo de tarea (Bug, Feature, Documentacion, etc.).
     * @param descripcion Descripción de la tarea.
     * @param estado      El estado de la tarea (ej. "pendiente", "en progreso").
     * @param complejidad La complejidad de la tarea.
     * @param fecha       La fecha de la tarea.
     */
	public Tarea(String id, String tipo, String descripcion, String estado, String complejidad, String fecha) {
		this.id = id;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.complejidad = complejidad;
		this.fecha = fecha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComplejidad() {
		return complejidad;
	}

	public void setComplejidad(String complejidad) {
		this.complejidad = complejidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	 /**
     * Obtiene el nombre de usuario del responsable de la tarea.
     * Si no tiene un responsable asignado, devuelve "Sin responsable".
     *
     * @return El nombre de usuario del responsable o "Sin responsable".
     */
	public String getUsuarioUsername() {
		if (this.usuario != null) {
			return this.usuario.getUsername();
		}
		else {
			return "Sin responsable";
		}
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public abstract void Accion();
	public abstract void accept(TareaVisitor v);

	@Override
	public String toString() {
		return id + "|" + tipo + "|" + descripcion + "|" + estado + "|"
				+ "Usuario =" + usuario.getUsername() + "|" + complejidad + "|" + fecha;
	}
	
	
	
	
	
	

}
