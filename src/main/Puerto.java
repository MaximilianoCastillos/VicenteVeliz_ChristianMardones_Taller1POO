package main;

public class Puerto {
	private String numPuerto;
	private String estado;
	private Vulnerabilidad vulnerabilidad;
	
	public Puerto(String numPuerto, String estado) {	
		this.numPuerto = numPuerto;
		this.estado = estado;
		this.vulnerabilidad = null;
	}

	public String getNumPuerto() {
		return numPuerto;
	}

	public void setNumPuerto(String numPuerto) {
		this.numPuerto = numPuerto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Vulnerabilidad getVulnerabilidad() {
		return vulnerabilidad;
	}

	public void setVulnerabilidad(Vulnerabilidad vulnerabilidad) {
		this.vulnerabilidad = vulnerabilidad;
	}
	
	
	
	

}
