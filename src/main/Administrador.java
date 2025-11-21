package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Clase Administrador que extiende de Usuario. El Administrador tiene permisos
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

    /**
     * Método que muestra los permisos del Administrador. Los administradores
     * pueden crear, modificar y eliminar proyectos y tareas.
     */
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
    public void listaProyectoTareas() {
        System.out.println("Proyectos: " + "\n");
        Repositorio repo = Repositorio.getInstance();
        ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) repo.getProyectos();

        if (proyectos.isEmpty()) {
            System.out.println("Lista de proyectos esta vacia. ");
            return;
        } else {
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
                input.nextLine();  // Limpiar buffer
            }
        }

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

            ArrayList<Proyecto> nuevosProyectos = (ArrayList<Proyecto>) repo.getProyectos();
            String ultimaid = nuevosProyectos.getLast().getId(); // Obtener la última ID de proyecto

            if (ultimaid.contains("00")) {
                String[] partes = ultimaid.split("R00");
                int id = Integer.valueOf(partes[1]) + 1;
                String idString = "PR" + String.format("%03d", id);
                Proyecto p = new Proyecto(idString, nombre);
                p.setUsuario(usuarioFinal);
                nuevosProyectos.add(p);
                repo.setProyectos(nuevosProyectos);
                System.out.println("Proyecto agregado con exito! ");
            } else if (ultimaid.contains("0")) { // Similar para ID con un solo "0"
                String[] partes = ultimaid.split("R0");
                int id = Integer.valueOf(partes[1]) + 1;
                String idString = "PR" + String.format("%03d", id);
                Proyecto p = new Proyecto(idString, nombre);
                p.setUsuario(usuarioFinal);
                nuevosProyectos.add(p);
                repo.setProyectos(nuevosProyectos);
                System.out.println("Proyecto agregado con exito! ");
            } else { 
                System.out.println("No se pueden añadir mas proyectos!");
            }
            break;

        case 2:
            input.nextLine();
            System.out.println();
            Repositorio repo1 = Repositorio.getInstance();
            ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) repo1.getProyectos();
            ArrayList<Tarea> tareas = (ArrayList<Tarea>) repo1.getTareas();
            System.out.println("¿Que proyecto desesa eliminar?");
            System.out.print("Ingresa el id del proyecto: ");
            int idProyectoAEliminar = input.nextInt();
            String idString = "PR" + String.format("%03d", idProyectoAEliminar);

            boolean proyectoEliminado = false;
            for (Proyecto p : proyectos) {
                if (p.getId().equals(idString)) {
                    ArrayList<Tarea> tareasProyecto = (ArrayList<Tarea>) p.getListaTarea();
                    tareas.removeAll(tareasProyecto); 
                    proyectos.remove(p); 
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
           
            break;
        case 2:
            
            break;
        default:
            break;
        }
    }

    /**
     * Asigna prioridades a las tareas de un proyecto.
     * Permite al administrador elegir cómo priorizar las tareas (por fecha, impacto
     * o complejidad).
     */
    public void asignarPrioridades() {
        Repositorio repo = Repositorio.getInstance();
        Scanner input = new Scanner(System.in);

        System.out.println("\n=== ASIGNAR PRIORIDADES A TAREAS ===");

       
        System.out.print("Ingrese el ID numérico del proyecto (ej: 1 para PR001): ");
        int idNum = input.nextInt();
        input.nextLine(); // limpiar ENTER

        String idProyecto = "PR" + String.format("%03d", idNum);

        Proyecto proyectoElegido = null;

        
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

       
        System.out.println("\nProyecto encontrado: " + proyectoElegido.getNombre());
        System.out.println("¿Cómo desea priorizar las tareas?");
        System.out.println("1) Por fecha");
        System.out.println("2) Por tipo (impacto)");
        System.out.println("3) Por complejidad");
        System.out.print("Opción: ");

        int opcion = input.nextInt();
        input.nextLine();

        
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

        
        ArrayList<Tarea> tareasOrdenadas = new ArrayList<>(estrategia.ordenar(proyectoElegido.getListaTarea()));

        
        proyectoElegido.setListaTarea(tareasOrdenadas);

        System.out.println("\n Tareas ordenadas exitosamente:");
        for (Tarea t : tareasOrdenadas) {
            System.out.println(t);
        }

        System.out.println();
    }

    /**
     * Genera un reporte de los proyectos y lo guarda en un archivo de texto.
     * El archivo se guarda en el directorio "archivos".
     */
    public void generarReporte() {
        Repositorio repo = Repositorio.getInstance();
        Scanner input = new Scanner(System.in);

        System.out.println("\n=== GENERAR REPORTE DE PROYECTOS ===");

       
        System.out.print("Ingrese el nombre del archivo (sin extensión): ");
        String nombre = input.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("Nombre inválido.");
            return;
        }

        
        String ruta = "archivos" + File.separator + nombre + ".txt";
        repo.guardarReporte(ruta);

        System.out.println("\n Reporte generado en: " + ruta + "\n");
    }
}
