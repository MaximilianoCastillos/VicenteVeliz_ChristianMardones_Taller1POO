package main;
/**
 * La clase abstracta `Usuario` representa un usuario genérico en el sistema.
 * Las subclases de `Usuario`, como `Colaborador` y `Administrador`, heredan de esta clase
 * y definen roles específicos y permisos asociados.
 */

public abstract class Usuario {
	private String username;
	private String contraseña;
	private String rol;
	
	 /**
     * Constructor de la clase `Usuario`.
     * Inicializa un usuario con su nombre de usuario, contraseña y rol.
     *
     * @param username   El nombre de usuario del usuario.
     * @param contraseña La contraseña del usuario.
     * @param rol        El rol del usuario (ej. "Administrador", "Colaborador").
     */
	
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
	  /**
     * Método abstracto que debe ser implementado por las subclases.
     * Este método es el que se encarga de mostrar los permisos específicos del usuario,
     * dependiendo de su rol (Administrador o Colaborador).
     */
    public abstract void mostrarPermisos();
}
