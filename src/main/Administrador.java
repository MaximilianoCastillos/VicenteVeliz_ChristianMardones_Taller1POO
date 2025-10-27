package main;

public class Administrador extends Usuario {

	public Administrador(String username, String contraseña) {
		super(username, contraseña, "Administrador");
	}

	@Override
	public void mostrarPermisos() {
        System.out.println(getUsername() + " puede crear, modificar y eliminar proyectos y tareas.");

	}

}
