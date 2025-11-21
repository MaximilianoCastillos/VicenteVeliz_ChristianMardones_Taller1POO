package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Colaborador extends Usuario /**clase colaborador es uno de los usuarios  que extiende de Usuario */ {

	public Colaborador(String username, String contraseña) {
		super(username, contraseña, "Colaborador");
	}

	@Override
	public void mostrarPermisos() {
        System.out.println(getUsername() + " puede ver y actualizar sus propias tareas.");

		
	}
	
	public  void menuColaborador() {
		System.out.println("\n--- MENÚ COLABORADOR ---");
	    System.out.println("1) Ver mis tareas");
	    System.out.println("2) Ver tareas de mis proyectos");
	    System.out.println("3) Cambiar estado de una tarea");
	    System.out.println("4) aplicar visitor");
	    System.out.println("5) Salir");
	    System.out.print("Elija una opción: ");
	}

	public void verTareas() {
		Repositorio repo = Repositorio.getInstance();
		boolean confirmar = false;

		for (Tarea t : repo.getTareas()) /** recorre las tareas y las compara usando como "pivote" la letra t que es la que en si recorre por cada una  */ {
			if (t.getUsuario() != null && t.getUsuario().getUsername().equals(this.getUsername())) {
				System.out.println(t.toString());
				confirmar = true;
			}
		}

		if (confirmar == false)/** revisa si es no tiene tareas*/ {
			System.out.println("No tiene tareas asignadas: ");
		}
	}

	public void cambiarEstado() {
		Scanner scan = new Scanner(System.in);
		Repositorio repo = Repositorio.getInstance();
		System.out.println("--- cambiar estado ---");
		ArrayList<Tarea> tareas = new ArrayList<>();

		for (Tarea t : repo.getTareas()) {
			if (t.getUsuario() != null && t.getUsuario().getUsername().equals(this.getUsername())) {
				tareas.add(t);
			}
		}

		if (tareas.isEmpty()) {
			System.out.println(" no tiene tareas");
		}

		for (int i = 0; i < tareas.size(); i++) {
			System.out.println((i + 1) + ": " + tareas.get(i).toString());
		}

		System.out.print("Seleccione tareas que quiere ver : ");
		int opcion = scan.nextInt();

		if (opcion < 1 || opcion > tareas.size()) {
			System.out.println("Opción inválida");
			return;
		}

		Tarea eleccion  = tareas.get(opcion - 1);

		System.out.print("Nuevo estado: ");
		scan.nextLine();
		String nuevo = scan.nextLine();

		eleccion.setEstado(nuevo);    
		System.out.println("actualizado ");



	}

	public void tareasProyectos() {
		Repositorio repo = Repositorio.getInstance();

		System.out.println("--- mis tareas de proyecto  ---");

		ArrayList<Proyecto> misProyectos = new ArrayList<>();

		for (Proyecto p : repo.getProyectos())  {
			for (Tarea t : p.getListaTarea()) {
				if (t.getUsuario() != null && t.getUsuario().getUsername().equals(this.getUsername())) {
					
					if (!misProyectos.contains(p)) {
						misProyectos.add(p);
					}
				}
			}
		}
		
		if (misProyectos.isEmpty()) /** verifica que no este vacio*/{
	        System.out.println("no estas en ningun proyecto");
	        return;
	    }
		
		 for (Proyecto pr : misProyectos) /** muestra los proyectos*/ {
		        System.out.println("\nProyecto: " + pr.getId() + " | " + pr.getNombre());
		        System.out.println("Tareas asociadas:");

		        for (Tarea t : pr.getListaTarea())/** muestras las tareas de los proyectos*/ {
		            System.out.println(" - " + t.toString());
		        }
		    }
	}
	
	
	public void aplicarVisitor() {
		 Repositorio repo = Repositorio.getInstance();
		    TareaVisitor visitor = new AccionVisitor();

		    System.out.println("--- aplicando visitor---");
		    boolean tiene = false;

		    
		    for (Tarea t : repo.getTareas()) {
		        if (t.getUsuario() != null &&
		            t.getUsuario().getUsername().equals(this.getUsername())) {

		            System.out.println("\nTarea: " + t.getId() + " (" + t.getTipo() + ")");
		            t.accept(visitor);  
		            tiene = true;
		        }
		    }

		    if (!tiene) {
		        System.out.println("No tienes tareas para aplicar Visitor.");
		    }
	}

}
