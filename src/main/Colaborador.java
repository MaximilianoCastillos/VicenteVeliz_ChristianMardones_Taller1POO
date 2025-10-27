package main;

public class Colaborador extends Usuario {

	public Colaborador(String username, String contraseña) {
		super(username, contraseña, "Colaborador");
	}

	@Override
	public void mostrarPermisos() {
        System.out.println(getUsername() + " puede ver y actualizar sus propias tareas.");

		
	}
	
	public static void menuColaborador() {
		System.out.println("\n--- MENÚ COLABORADOR ---");
        System.out.println("1) Ver mis tareas");
        System.out.println("2) Salir");
        System.out.print("Elija una opción: ");
	}

}
