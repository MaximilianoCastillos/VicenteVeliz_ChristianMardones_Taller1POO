package main;

public class Vulnerabilidad {
	private String puerto;
	private String nombre;
	private String descripcion;
	public Vulnerabilidad(String puerto, String nombre, String descripcion) {
		this.puerto = puerto;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	public String getPuerto() {
		return puerto;
	}
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
