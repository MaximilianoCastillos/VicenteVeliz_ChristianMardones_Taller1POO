package main;

import java.util.ArrayList;

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
	
	public void listaProyectoTareas() {
		System.out.println("Proyectos: " + "\n");
		Repositorio repo = Repositorio.getInstance(); //Obtiene la instancia del repositorio
		ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) repo.getProyectos(); //Llama al repositorio para obtener lista de proyectos
		
		if (proyectos.isEmpty()) { //Si la lista esta vacia retorna un mensaje y se termina la funcion
			System.out.println("Lista de proyectos esta vacia. ");
			return;
		}
		
		else {
			for (Proyecto p : proyectos) {
				System.out.println();
				System.out.println(p.getId() + "|" + p.getNombre() + "|" + p.getUsuarioUsername());
				System.out.println("Tareas asociadas: ");
				ArrayList<Tarea> tareas = (ArrayList<Tarea>) p.getListaTarea();
				for (Tarea t : tareas) {
					System.out.println(t.toString());
				}
			}
		}
	}
	
	public void gestionerProyectos() {
		
	}
	
}