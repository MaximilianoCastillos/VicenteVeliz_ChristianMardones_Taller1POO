package main;

public class Usuario {
	private String username;
	private String contraseña;
	private String rol;
	
	public Usuario(String username, String contraseña, String rol) {
		this.username = username;
		this.contraseña = contraseña;
		this.rol = rol;
	}

	public String getUsername() {
		return username;
	}

	public String getContraseña() {
		return contraseña;
	}

	public String getRol() {
		return rol;
	}
}
