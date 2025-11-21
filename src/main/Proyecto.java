package main;

import java.util.ArrayList;
import java.util.List;

public class Proyecto /** esta clase agrupa las tareas por ejemplo cada vez que se carga una tarea desde un archivo se agrega a su respectivo proyecto, tamien nos 
permite mostrar las tareas organizadas por proyecto, entonces la clase proyecto es la estructura completa del sistema*/{
	private String id;
	private String nombre;
	private Usuario usuario;
	private List<Tarea> listaTarea = new ArrayList<>();
	
	public Proyecto(String id, String nombre) {	
		this.id = id;
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

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
