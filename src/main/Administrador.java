package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * Clase Administrador que extiende de Usuario. El Administrador tiene  permisos
 * especiales para crear, modificar y eliminar proyectos y tareas dentro de la
 * aplicación.
 */

public class Administrador extends Usuario {
	
	 /**
     * Constructor para inicializar un Administrador con su nombre de usuario,
     * contraseña y rol.
     *
     * @param username    El nombre de usuario del administrador.
     * @param contraseña  La contraseña del administrador.
     */

	public Administrador(String username, String contraseña) {
		super(username, contraseña, "Administrador");
	}

	@Override
	public void mostrarPermisos() {
        System.out.println(getUsername() + " puede crear, modificar y eliminar proyectos y tareas.");

	}
	
	  /**
     * Muestra el menú principal de opciones para el Administrador.
     * El menú incluye opciones para gestionar proyectos, tareas y generar reportes.
     */
	
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
	
	
	/**
     * Obtiene y muestra la lista de proyectos y las tareas asociadas a cada uno.
     * Si la lista de proyectos está vacía, se muestra un mensaje indicando que
     * no hay proyectos disponibles.
     */
	public void listaProyectoTareas() {   /***/
		System.out.println("Proyectos: " + "\n");
		Repositorio repo = Repositorio.getInstance();   
		ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) repo.getProyectos(); 
		
		if (proyectos.isEmpty()) { 
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
	
	 /**
     * Permite gestionar proyectos. El administrador puede elegir entre agregar o
     * eliminar un proyecto.
     */
	
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
		
		/**Agregar un proyecto*/
		 /**Ingresar datos del proyecto*/
		/**Obtener instancia de los repositorios*/
		switch (eleccion) {
		case 1: 
			input.nextLine();
			
			System.out.println();
			System.out.println("Ingrese el nombre del proyecto: ");
			String nombre = input.nextLine();
			System.out.println("Ingrese el responsable de este proyecto: ");
			String usuario = input.nextLine();
			
			Repositorio repo = Repositorio.getInstance(); 
			
			ArrayList<Usuario> usuarios = (ArrayList<Usuario>) repo.getUsuarios(); 
			
			boolean usuarioEncontrado = false;
			
			Usuario usuarioFinal = null;
			
			
			// Encontrar los usuarios por lista
			for (Usuario u : usuarios) { 
				if (usuario.equals(u.getUsername())) {
					usuarioEncontrado = true;
					usuarioFinal = u;
					break;
				}
			}
			
			/**Si no se encuentran se crea un nuevo usuario no archivado*/
			if (!usuarioEncontrado) { 
				usuarioFinal = new UsuariosNoArchivados(usuario, null, null);
			}
			
			/** Cargar lista de proyectos*/
			ArrayList<Proyecto> nuevosProyectos = (ArrayList<Proyecto>) repo.getProyectos(); 
			
			String ultimaid = nuevosProyectos.getLast().getId(); /**Obtener la ultima id*/
			
			/** Si contiene 00 hace esto*/
			/**  Obtener la ultima id*/
			/** Agregarle un valor mas a la ID*/
			/** Se pone "PR" y dos ceros mas junto al id*/
			 /** Se crea el proyecto*/
			 if (ultimaid.contains("00")) { 
				 String[] partes = ultimaid.split("R00"); 
				 int id = Integer.valueOf(partes[1]) + 1; 
				 String idString = "PR" + String.format("%03d", id); 
				 
				 Proyecto p = new Proyecto(idString, nombre);
				 p.setUsuario(usuarioFinal);
				 nuevosProyectos.add(p);
				 repo.setProyectos(nuevosProyectos);
				 System.out.println("Proyecto agregado con exito! ");
				 System.out.println();
			 }
			 /**  Misma cosa que la de arriba pero en caso de que haya un solo cero*/
			 else if (ultimaid.contains("0")) { 
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
			 /** Limite de proyectos*/
			 else { 
				 System.out.println("No se pueden añadir mas proyectos!"); 
			 }
			break;
			/** Eliminar un proyecto*/
		case 2: 
			input.nextLine();
			
			System.out.println();
			
			/** Obtener lista de proyectos*/
			/** Obtener lista de tareas*/
			/**Se obtiene el string para que de igual con el string de getId*/
			/**  Recorrer lista de proyectos */
			Repositorio repo1 = Repositorio.getInstance();
			ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) repo1.getProyectos(); 
			ArrayList<Tarea> tareas = (ArrayList<Tarea>) repo1.getTareas(); 
			
			System.out.println("¿Que proyecto desesa eliminar?");
			System.out.print("Ingresa el id del proyecto: ");
			int idProyectoAEliminar = input.nextInt();
			String idString = "PR" + String.format("%03d", idProyectoAEliminar); 
			
			boolean proyectoEliminado = false;
			
			for (Proyecto p : proyectos) { 
				
				/**se obtiene la lista de tareas en el proyecto*/
				/** Se remueven las tareas de proyecto en la lista principal de tareas*/
				/**  Se remueve el proyecto de lista de proyectos*/
				if (p.getId().equals(idString)) {
					ArrayList<Tarea> tareasProyecto = (ArrayList<Tarea>) p.getListaTarea(); 
					
					tareas.removeAll(tareasProyecto); 
					proyectos.remove(p); 
					
					/**Se agrega la nueva lista*/
					repo1.setProyectos(proyectos); 
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
	
	/**
     * Permite gestionar tareas. El administrador puede agregar o eliminar tareas
     * dentro de los proyectos.
     */
	
	public void gestionarTareas() {
		
		int eleccion = 0;
		
		Repositorio repo = Repositorio.getInstance();
		
		
		Scanner input = new Scanner(System.in);
		
		/** control de error*/
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
			
			if (tipoTarea.equalsIgnoreCase("bug")) {
				tipoTarea = "Bug";
			}
			
			else if (tipoTarea.equalsIgnoreCase("feature")) {
				tipoTarea = "Feature";
			}
			
			else {
				tipoTarea = "Documentacion";
			}
			
			System.out.print("Ingrese una descripcion: ");
			String descripcionTarea = input.nextLine();
			
			// === FECHA CON LOCALDATE
		    java.time.LocalDate fechaLD = null;
		    while (fechaLD == null) {
		        System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
		        String fechaTexto = input.nextLine().trim();

		        try {
		            fechaLD = java.time.LocalDate.parse(fechaTexto);
		        } catch (java.time.format.DateTimeParseException e) {
		            System.out.println("Formato inválido. Ejemplo: 2025-08-01");
		        }
		    }
		    String fechaTarea = fechaLD.toString();
			
			String estadoTarea = "";
			
			while (!estadoTarea.equals("pendiente") && !estadoTarea.equals("enprogreso") && !estadoTarea.equals("completada")) {
				System.out.print("Ingrese el estado inicial de su tarea: ");
				estadoTarea = input.nextLine().strip().toLowerCase();
				estadoTarea = estadoTarea.replace(" ", "");
				
				if (!estadoTarea.equals("pendiente") && !estadoTarea.equals("enprogreso") && !estadoTarea.equals("completada")) {
					System.out.println("Ingrese un tipo de estado inicial valido");
					System.out.println();
				}
			}
			
			if (estadoTarea.equals("pendiente")) {
				estadoTarea = "Pendiente";
			}
			
			else if (estadoTarea.equals("enprogreso")) {
				estadoTarea = "En Progreso";
			}
			
			else {
				estadoTarea = "Completada";
			}
			
			System.out.print("Ingrese a un responsable: ");
			String usuario = input.nextLine();
			
			ArrayList<Usuario> usuarios = (ArrayList<Usuario>) repo.getUsuarios(); 
			
			boolean usuarioEncontrado = false;
			
			Usuario usuarioFinal = null;
			
			
			for (Usuario u : usuarios) { 
				if (usuario.equals(u.getUsername())) {
					usuarioEncontrado = true;
					usuarioFinal = u;
					break;
				}
			}
			
			
			if (!usuarioEncontrado) { 
				usuarioFinal = new UsuariosNoArchivados(usuario, null, null);
			}
			
			ArrayList<Tarea> ultimasTareas = (ArrayList<Tarea>) repo.getTareas();
			ArrayList<Proyecto> nuevoProyectos = (ArrayList<Proyecto>) repo.getProyectos();
			
			String ultimoIdTareas = ultimasTareas.getLast().getId();
			
			
			if (ultimoIdTareas.contains("00")) { 
				 String[] partes = ultimoIdTareas.split("T00"); 
				 int id = Integer.valueOf(partes[1]) + 1;
				 String idString = "T" + String.format("%03d", id); 
				 
				 String complejidad = "No definido";
				 
				 Tarea nuevaTarea = null;
				 
				 if (tipoTarea.equalsIgnoreCase("bug")) {
					 nuevaTarea = new Bug(idString, tipoTarea, descripcionTarea, estadoTarea, complejidad, fechaTarea);
				 }
				 else if (tipoTarea.equalsIgnoreCase("feature")) {
					 nuevaTarea = new Feature(idString, tipoTarea, descripcionTarea, estadoTarea, complejidad, fechaTarea);
				 }
				 else {
					 nuevaTarea = new Documentacion(idString, tipoTarea, descripcionTarea, estadoTarea, complejidad, fechaTarea);
				 }
				 
				 nuevaTarea.setUsuario(usuarioFinal);
				 /**Se obtiene el string para que de igual con el string de getId*/
				 String idProyectoString = "PR" + String.format("%03d", idProyecto); 
				 /**Recorrer lista de proyectos*/
				 for (Proyecto p : nuevoProyectos) { 
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
			/** Misma cosa que la de arriba pero en caso de que haya un solo cero*/
			 else if (ultimoIdTareas.contains("0")) { 
				 String[] partes = ultimoIdTareas.split("T0");
				 int id = Integer.valueOf(partes[1]) + 1;
				 String idString = "T" + String.format("%03d", id);

				 String complejidad = "No definido";
				 
				 Tarea nuevaTarea = null;
				 
				 if (tipoTarea.equalsIgnoreCase("bug")) {
					 nuevaTarea = new Bug(idString, tipoTarea, descripcionTarea, estadoTarea, complejidad, fechaTarea);
				 }
				 else if (tipoTarea.equalsIgnoreCase("feature")) {
					 nuevaTarea = new Feature(idString, tipoTarea, descripcionTarea, estadoTarea, complejidad, fechaTarea);
				 }
				 else {
					 nuevaTarea = new Documentacion(idString, tipoTarea, descripcionTarea, estadoTarea, complejidad, fechaTarea);
				 }
				 
				 nuevaTarea.setUsuario(usuarioFinal);
				 
				 /**Se obtiene el string para que de igual con el string de getId*/
				 String idProyectoString = "PR" + String.format("%03d", idProyecto); 
				 
				 /**Recorrer lista de proyectos*/
				 for (Proyecto p : nuevoProyectos) { 
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
			/**Limite de proyectos*/
			 else { 
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
			
			 /**Se obtiene el string para que de igual con el string de getId*/
			 String idProyectoString = "PR" + String.format("%03d", idProyecto1);
			 /**Recorrer lista de proyectos*/
			 for (Proyecto p : proyectos) { 
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
	public void asignarPrioridades() {
	    Repositorio repo = Repositorio.getInstance();
	    Scanner input = new Scanner(System.in);

	    System.out.println("\n=== ASIGNAR PRIORIDADES A TAREAS ===");

	    /** 1) Seleccionar proyecto*/
	    System.out.print("Ingrese el ID numérico del proyecto (ej: 1 para PR001): ");
	    int idNum = input.nextInt();
	    input.nextLine(); // limpiar ENTER

	    String idProyecto = "PR" + String.format("%03d", idNum);

	    Proyecto proyectoElegido = null;
	    /** recorrer proyectos y compara*/
	    for (Proyecto p : repo.getProyectos()) { 
	        if (p.getId().equalsIgnoreCase(idProyecto)) {
	            proyectoElegido = p;
	            break;
	        }
	    }

	    if (proyectoElegido == null) {
	        System.out.println("No se encontro ese proyecto.");
	        return;
	    }

	    if (proyectoElegido.getListaTarea().isEmpty()) {
	        System.out.println("El proyecto no tiene tareas para ordenar.");
	        return;
	    }

	    /** 2) Menú de estrategia*/
	    System.out.println("\nProyecto encontrado: " + proyectoElegido.getNombre());
	    System.out.println("¿Cómo desea priorizar las tareas?");
	    System.out.println("1) Por fecha");
	    System.out.println("2) Por tipo (impacto)");
	    System.out.println("3) Por complejidad");
	    System.out.print("Opción: ");

	    int opcion = input.nextInt();
	    input.nextLine();

	    
	    /** 3) Crear la estrategia correcta (tus clases reales)*/
	    PrioridadStrategy estrategia = null;

	    switch (opcion) {
	        case 1:
	            estrategia = new PrioridadPorFecha();
	            break;
	        case 2:
	            estrategia = new PrioridadPorImpacto();
	            break;
	        case 3:
	            estrategia = new PrioridadPorComplejidad();
	            break;
	        default:
	            System.out.println("Opción inválida.");
	            return;
	    }

	    /** 4) Aplicar la estrategia*/
	    ArrayList<Tarea> tareasOrdenadas = new ArrayList<>(
	            estrategia.ordenar(proyectoElegido.getListaTarea())
	    );

	    /** 5) Actualizar el proyecto con las tareas ordenadas*/
	    proyectoElegido.setListaTarea(tareasOrdenadas);

	    System.out.println("\n Tareas ordenadas exitosamente:");
	    for (Tarea t : tareasOrdenadas) { /** recorre tareas*/
	        System.out.println(t);
	    }

	    System.out.println();
	}
	public void generarReporte() {
	    Repositorio repo = Repositorio.getInstance();
	    Scanner input = new Scanner(System.in);

	    System.out.println("\n=== GENERAR REPORTE DE PROYECTOS ===");

	    System.out.print("Ingrese el nombre del archivo (sin extensión): ");
	    String nombre = input.nextLine().trim();

	    if (nombre.isEmpty()) /** revisa que no sea vacio */ {
	        System.out.println("Nombre inválido.");
	        return;
	    }

	    /** El archivo se guardará como archivos/<nombre>.txt*/
	    String ruta = "archivos" + File.separator + nombre + ".txt";

	    repo.guardarReporte(ruta);

	    System.out.println("\n Reporte generado en: " + ruta + "\n");
	}
}