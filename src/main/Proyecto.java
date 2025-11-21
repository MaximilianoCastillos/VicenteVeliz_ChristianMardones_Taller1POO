package main;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Proyecto representa un proyecto en el sistema. Un proyecto tiene un ID, un nombre,
 * un usuario responsable y una lista de tareas asociadas. Cada vez que se carga una tarea,
 * se agrega a su respectivo proyecto.
 * 
 * Esta clase también proporciona métodos para gestionar las tareas y el responsable del proyecto.
 */

public class Proyecto {
	private String id;
	private String nombre;
	private Usuario usuario;
	private List<Tarea> listaTarea = new ArrayList<>();
	
	 /**
     * Constructor de la clase Proyecto.
     * Inicializa un proyecto con un ID y un nombre.
     * 
     * @param id El identificador único del proyecto.
     * @param nombre El nombre del proyecto.
     */
	
	public Proyecto(String id, String nombre) {	
		this.id = id;
		this.nombre = nombre;
	}
	
	/**
     * Obtiene el ID del proyecto.
     * 
     * @return El ID del proyecto.
     */

	public String getId() {
		return id;
	}
	  /**
     * Establece el ID del proyecto.
     * 
     * @param id El nuevo ID del proyecto.
     */

	public void setId(String id) {
		this.id = id;
	}
	 /**
     * Obtiene el nombre del proyecto.
     * 
     * @return El nombre del proyecto.
     */

	public String getNombre() {
		return nombre;
	}
	 /**
     * Establece el nombre del proyecto.
     * 
     * @param nombre El nuevo nombre del proyecto.
     */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
     * Obtiene la lista de tareas asociadas al proyecto.
     * 
     * @return La lista de tareas del proyecto.
     */

	public List<Tarea> getListaTarea() {
		return listaTarea;
	}

	public void setListaTarea(List<Tarea> listaTarea) {
		this.listaTarea = listaTarea;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
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
}
