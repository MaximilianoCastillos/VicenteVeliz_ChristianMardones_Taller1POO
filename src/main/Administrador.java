package main;

public class Administrador extends Usuario {

	public Administrador(String username, String contraseña) {
		super(username, contraseña, "Administrador");
	}

	@Override
	public void mostrarPermisos() {
        System.out.println(getUsername() + " puede crear, modificar y eliminar proyectos y tareas.");

	}
	
	public static void menuAdmin() {
		System.out.println("\n--- MENÚ ADMINISTRADOR ---");
	    System.out.println("1) Ver proyectos");
	    System.out.println("2) Ver tareas");
	    System.out.println("3) Salir");
	    System.out.print("Elija una opción: ");
		
	}
	
	
	
	

}
