package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

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
	    System.out.println("1) Ver lista completa de proyectos y tareas");
	    System.out.println("2) Agregar o eliminar un proyecto");
	    System.out.println("3) Agregar o eliminar una tarea de un proyecto");
	    System.out.println("4) Asignar prioridades");
	    System.out.println("5) Generar reportes de proyectos");
	    System.out.println("6) Salir");
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
		
		int eleccion = 0;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println();
		System.out.println("¿Desea agregar o eliminar un proyecto?");
		System.out.println("1) Agregar");
		System.out.println("2) Eliminar");
		System.out.println("3) Salir");
		while (eleccion < 1 || eleccion > 3) {
			try {
				System.out.print("Ingrese su respuesta: ");
				eleccion = input.nextInt();
				if (eleccion < 1 || eleccion > 3) {
					System.out.println("Escoga una opcion correcta. ");
					System.out.println("");
				}
			} catch (Exception e) {
				System.out.println("Error!: " + e);
				System.out.println("Ingrese un valor valido. ");
				System.out.println("");
				input.nextLine();
				// TODO: handle exception
			}
		}
		
		switch (eleccion) {
		case 1: //Agregar un proyecto
			input.nextLine();
			
			System.out.println();
			System.out.println("Ingrese el nombre del proyecto: "); //Ingresar datos del proyecto
			String nombre = input.nextLine();
			System.out.println("Ingrese el responsable de este proyecto: ");
			String usuario = input.nextLine();
			
			Repositorio repo = Repositorio.getInstance(); //Obtener instancia de los repositorios
			
			ArrayList<Usuario> usuarios = (ArrayList<Usuario>) repo.getUsuarios(); 
			
			boolean usuarioEncontrado = false;
			
			Usuario usuarioFinal = null;
			
			for (Usuario u : usuarios) { // Encontrar los usuarios por lista
				if (usuario.equals(u.getUsername())) {
					usuarioEncontrado = true;
					usuarioFinal = u;
					break;
				}
			}
			
			if (!usuarioEncontrado) { //Si no se encuentran se crea un nuevo usuario no archivado
				usuarioFinal = new UsuariosNoArchivados(usuario, null, null);
			}
			
			ArrayList<Proyecto> nuevosProyectos = (ArrayList<Proyecto>) repo.getProyectos(); // Cargar lista de proyectos
			
			String ultimaid = nuevosProyectos.getLast().getId(); // Obtener la ultima id
			
			 if (ultimaid.contains("00")) { // Si contiene 00 hace esto
				 String[] partes = ultimaid.split("R00"); // Obtener la ultima id
				 int id = Integer.valueOf(partes[1]) + 1; //Agregarle un valor mas a la ID
				 String idString = "PR" + String.format("%03d", id); // Se pone "PR" y dos ceros mas junto al id
				 
				 Proyecto p = new Proyecto(idString, nombre); //Se crea el proyecto
				 p.setUsuario(usuarioFinal);
				 nuevosProyectos.add(p);
				 repo.setProyectos(nuevosProyectos);
				 System.out.println("Proyecto agregado con exito! ");
				 System.out.println();
			 }
			 else if (ultimaid.contains("0")) { // Misma cosa que la de arriba pero en caso de que haya un solo cero
				 String[] partes = ultimaid.split("R0");
				 int id = Integer.valueOf(partes[1]) + 1;
				 String idString = "PR" + String.format("%03d", id);
				 
				 Proyecto p = new Proyecto(idString, nombre);
				 p.setUsuario(usuarioFinal);
				 nuevosProyectos.add(p);
				 repo.setProyectos(nuevosProyectos);
				 System.out.println("Proyecto agregado con exito! ");
				 System.out.println();
			}
			 else { //Limite de proyectos
				 System.out.println("No se pueden añadir mas proyectos!"); 
			 }
			break;
		
		case 2: // Eliminar un proyecto
			input.nextLine();
			
			System.out.println();
			
			Repositorio repo1 = Repositorio.getInstance();
			ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) repo1.getProyectos(); //Obtener lista de proyectos
			ArrayList<Tarea> tareas = (ArrayList<Tarea>) repo1.getTareas(); //Obtener lista de tareas
			
			System.out.println("¿Que proyecto desesa eliminar?");
			System.out.print("Ingresa el id del proyecto: ");
			int idProyectoAEliminar = input.nextInt();
			String idString = "PR" + String.format("%03d", idProyectoAEliminar); //Se obtiene el string para que de igual con el string de getId
			
			boolean proyectoEliminado = false;
			
			for (Proyecto p : proyectos) { //Recorrer lista de proyectos
				if (p.getId().equals(idString)) {
					ArrayList<Tarea> tareasProyecto = (ArrayList<Tarea>) p.getListaTarea(); //se obtiene la lista de tareas en el proyecto
					
					tareas.removeAll(tareasProyecto); // Se remueven las tareas de proyecto en la lista principal de tareas
					proyectos.remove(p); // Se remueve el proyecto de lista de proyectos
					
					repo1.setProyectos(proyectos); // Se agrega la nueva lista
					repo1.setTareas(tareas);
					
					System.out.println("¡Proyecto eliminado con extio!");
					proyectoEliminado = true;
					break;
				}
			}
			
			if (!proyectoEliminado) {
				System.out.println("No se ha podida encontrar ese proyecto. ");
			}
			break;
		
		case 3:
			break;
			
		default:
			break;
		}
	}
	
	public void gestionarTareas() {
		
		int eleccion = 0;
		
		Repositorio repo = Repositorio.getInstance();
		
		
		Scanner input = new Scanner(System.in);
		
		System.out.println();
		System.out.println("¿Desea agregar o eliminar una tarea?");
		System.out.println("1) Agregar");
		System.out.println("2) Eliminar");
		System.out.println("3) Salir");
		while (eleccion < 1 || eleccion > 3) {
			try {
				System.out.print("Ingrese su respuesta: ");
				eleccion = input.nextInt();
				if (eleccion < 1 || eleccion > 3) {
					System.out.println("Escoga una opcion correcta. ");
					System.out.println("");
				}
			} catch (Exception e) {
				System.out.println("Error!: " + e);
				System.out.println("Ingrese un valor valido. ");
				System.out.println("");
				input.nextLine();
				// TODO: handle exception
			}
		}
		
		switch (eleccion) {
		case 1:
			input.nextLine();
			
			System.out.println();
			System.out.println("¿A que proyecto desea agregar la tarea?");
			System.out.print("Ingrese el ID del proyecto: ");
			int idProyecto = input.nextInt();
			input.nextLine();
			
			
			
			System.out.println();
			System.out.print("¿De que tipo es su tarea?: ");
			String tipoTarea = "";
			while (!tipoTarea.equalsIgnoreCase("bug") && !tipoTarea.equalsIgnoreCase("feature") && !tipoTarea.equalsIgnoreCase("documentacion")) {
				tipoTarea = input.nextLine().strip();
				
				if (!tipoTarea.equalsIgnoreCase("bug") && !tipoTarea.equalsIgnoreCase("feature") && !tipoTarea.equalsIgnoreCase("documentacion")) {
					System.out.println("Ingrese un tipo de tarea valido. ");
					System.out.print("¿De que tipo es su tarea?: ");
				}
			}
			
			System.out.print("Ingrese una descripcion: ");
			String descripcionTarea = input.nextLine();
			
			String estadoTarea = "";
			
			while (!estadoTarea.equals("pendiente") && !estadoTarea.equals("enprogreso") && !estadoTarea.equals("completada")) {
				System.out.print("Ingrese el estado inicial de su tarea: ");
				estadoTarea = input.nextLine().strip().toLowerCase();
				
				if (!estadoTarea.equals("pendiente") && !estadoTarea.equals("enprogreso") && !estadoTarea.equals("completada")) {
					System.out.println("Ingrese un tipo de estado inicial valido");
					System.out.println();
				}
			}
			
			System.out.print("Ingrese a un responsable: ");
			String usuario = input.nextLine();
			
			ArrayList<Usuario> usuarios = (ArrayList<Usuario>) repo.getUsuarios(); 
			
			boolean usuarioEncontrado = false;
			
			Usuario usuarioFinal = null;
			
			for (Usuario u : usuarios) { // Encontrar los usuarios por lista
				if (usuario.equals(u.getUsername())) {
					usuarioEncontrado = true;
					usuarioFinal = u;
					break;
				}
			}
			
			if (!usuarioEncontrado) { //Si no se encuentran se crea un nuevo usuario no archivado
				usuarioFinal = new UsuariosNoArchivados(usuario, null, null);
			}
			
			ArrayList<Tarea> ultimasTareas = (ArrayList<Tarea>) repo.getTareas();
			ArrayList<Proyecto> nuevoProyectos = (ArrayList<Proyecto>) repo.getProyectos();
			
			String ultimoIdTareas = ultimasTareas.getLast().getId();
			
			if (ultimoIdTareas.contains("00")) { // Si contiene 00 hace esto
				 String[] partes = ultimoIdTareas.split("T00"); // Obtener la ultima id
				 int id = Integer.valueOf(partes[1]) + 1; //Agregarle un valor mas a la ID
				 String idString = "T" + String.format("%03d", id); // Se pone "T" y dos ceros mas junto al id
				 
				 String complejidad = "No definido";
				 
				 Tarea nuevaTarea = null;
				 
				 if (tipoTarea.equalsIgnoreCase("bug")) {
					 nuevaTarea = new Bug(idString, tipoTarea, descripcionTarea, estadoTarea, complejidad, null);
				 }
				 else if (tipoTarea.equalsIgnoreCase("feature")) {
					 nuevaTarea = new Feature(idString, tipoTarea, descripcionTarea, estadoTarea, complejidad, null);
				 }
				 else {
					 nuevaTarea = new Documentacion(idString, tipoTarea, descripcionTarea, estadoTarea, complejidad, null);
				 }
				 
				 nuevaTarea.setUsuario(usuarioFinal);
				 
				 String idProyectoString = "PR" + String.format("%03d", idProyecto); //Se obtiene el string para que de igual con el string de getId
				 
				 for (Proyecto p : nuevoProyectos) { //Recorrer lista de proyectos
						if (p.getId().equals(idProyectoString)) {
							ArrayList<Tarea> listaTareaProyecto = (ArrayList<Tarea>) p.getListaTarea();
							int indice = nuevoProyectos.indexOf(p);
							nuevaTarea.setProyecto(p);
							listaTareaProyecto.add(nuevaTarea);
							p.setListaTarea(listaTareaProyecto);
							nuevoProyectos.set(indice, p);
							ultimasTareas.add(nuevaTarea);
							System.out.println("¡Tarea agregada con exito!");
							break;
						}
					}
				 repo.setProyectos(nuevoProyectos);
				 repo.setTareas(ultimasTareas);
			 }
			 else if (ultimoIdTareas.contains("0")) { // Misma cosa que la de arriba pero en caso de que haya un solo cero
				 String[] partes = ultimoIdTareas.split("T0");
				 int id = Integer.valueOf(partes[1]) + 1;
				 String idString = "T" + String.format("%03d", id);

				 String complejidad = "No definido";
				 
				 Tarea nuevaTarea = null;
				 
				 if (tipoTarea.equalsIgnoreCase("bug")) {
					 nuevaTarea = new Bug(idString, tipoTarea, descripcionTarea, estadoTarea, complejidad, null);
				 }
				 else if (tipoTarea.equalsIgnoreCase("feature")) {
					 nuevaTarea = new Feature(idString, tipoTarea, descripcionTarea, estadoTarea, complejidad, null);
				 }
				 else {
					 nuevaTarea = new Documentacion(idString, tipoTarea, descripcionTarea, estadoTarea, complejidad, null);
				 }
				 
				 nuevaTarea.setUsuario(usuarioFinal);
				 
				 String idProyectoString = "PR" + String.format("%03d", idProyecto); //Se obtiene el string para que de igual con el string de getId
				 
				 for (Proyecto p : nuevoProyectos) { //Recorrer lista de proyectos
						if (p.getId().equals(idProyectoString)) {
							ArrayList<Tarea> listaTareaProyecto = (ArrayList<Tarea>) p.getListaTarea();
							int indice = nuevoProyectos.indexOf(p);
							nuevaTarea.setProyecto(p);
							listaTareaProyecto.add(nuevaTarea);
							p.setListaTarea(listaTareaProyecto);
							nuevoProyectos.set(indice, p);
							ultimasTareas.add(nuevaTarea);
							System.out.println("¡Tarea agregada con exito!");
							break;
						}
					}
				 repo.setProyectos(nuevoProyectos);
				 repo.setTareas(ultimasTareas);
			}
			 else { //Limite de proyectos
				 System.out.println("No se pueden añadir mas tareas!"); 
			 }
			break;
		
		case 2:
			input.nextLine();
			ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) repo.getProyectos();
			ArrayList<Tarea> tareas = (ArrayList<Tarea>) repo.getTareas();
			
			System.out.println();
			System.out.println("¿A que proyecto desea eliminar la tarea?");
			System.out.print("Ingrese el ID del proyecto: ");
			int idProyecto1 = input.nextInt();
			
			
			 String idProyectoString = "PR" + String.format("%03d", idProyecto1); //Se obtiene el string para que de igual con el string de getId
			 
			 for (Proyecto p : proyectos) { //Recorrer lista de proyectos
				 if (idProyectoString.equals(p.getId())) {
					 ArrayList<Tarea> tareasProyecto = (ArrayList<Tarea>) p.getListaTarea();
					 int index = proyectos.indexOf(p);
					 System.out.println("¿Que tarea desea eliminar?");
					 for (Tarea t : tareasProyecto) {
						 System.out.println(t.toString());
					 }
					System.out.println();
					System.out.print("Selecciona la tarea a eliminar mediante el id: ");
					int tareaId = input.nextInt();
					String tareaIdString = "T" + String.format("%03d", tareaId);
					
					boolean tareaEncontrada = false;
					
					for (Tarea t : tareasProyecto) {
						if (tareaIdString.equals(t.getId())) {
							tareasProyecto.remove(t);
							p.setListaTarea(tareasProyecto);
							proyectos.set(index, p);
							tareas.remove(t);
							repo.setProyectos(proyectos);
							repo.setTareas(tareas);
							tareaEncontrada = true;
							System.out.println("¡Tarea eliminada con exito!");
							break;
						}
					}
					if (!tareaEncontrada) {
						System.out.println("No se encontro tal tarea. ");
					}
					break;
				 }
			 }
		default:
			break;
		}
	}
}