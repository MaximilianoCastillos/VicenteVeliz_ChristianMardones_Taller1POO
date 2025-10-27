package main;

public class Colaborador extends Usuario {

	public Colaborador(String username, String contraseña) {
		super(username, contraseña, "Colaborador");
	}

	@Override
	public void mostrarPermisos() {
        System.out.println(getUsername() + " puede ver y actualizar sus propias tareas.");

		
	}

}
