package main;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * La clase Colaborador representa a un usuario con rol de colaborador en el sistema.
 * Los colaboradores pueden ver y actualizar sus propias tareas, así como interactuar
 * con las tareas de sus proyectos y aplicar un patrón de diseño Visitor sobre sus tareas.
 */
public class Colaborador extends Usuario {

    /**
     * Constructor de la clase Colaborador.
     * Inicializa un objeto colaborador con el nombre de usuario, contraseña y rol.
     *
     * @param username    El nombre de usuario del colaborador.
     * @param contraseña  La contraseña del colaborador.
     */
    public Colaborador(String username, String contraseña) {
        super(username, contraseña, "Colaborador");
    }

    /**
     * Muestra los permisos del colaborador. Un colaborador puede ver y actualizar
     * sus propias tareas.
     */
    @Override
    public void mostrarPermisos() {
        System.out.println(getUsername() + " puede ver y actualizar sus propias tareas.");
    }

    /**
     * Muestra el menú de opciones para el colaborador. El menú incluye opciones para
     * ver tareas, cambiar el estado de tareas, aplicar un Visitor a las tareas y salir.
     */
    public void menuColaborador() {
        System.out.println("\n--- MENÚ COLABORADOR ---");
        System.out.println("1) Ver mis tareas");
        System.out.println("2) Ver tareas de mis proyectos");
        System.out.println("3) Cambiar estado de una tarea");
        System.out.println("4) Aplicar visitor");
        System.out.println("5) Salir");
        System.out.print("Elija una opción: ");
    }

    /**
     * Muestra las tareas asignadas al colaborador. Si el colaborador no tiene tareas
     * asignadas, se muestra un mensaje indicando que no tiene tareas.
     */
    public void verTareas() {
        Repositorio repo = Repositorio.getInstance();
        boolean confirmar = false;

        for (Tarea t : repo.getTareas()) {
            if (t.getUsuario() != null && t.getUsuario().getUsername().equals(this.getUsername())) {
                System.out.println(t.toString());
                confirmar = true;
            }
        }

        if (!confirmar) {
            System.out.println("No tiene tareas asignadas: ");
        }
    }

    /**
     * Permite al colaborador cambiar el estado de una de sus tareas.
     * El colaborador selecciona una tarea de la lista de tareas asignadas y luego
     * ingresa un nuevo estado.
     */
    public void cambiarEstado() {
        Scanner scan = new Scanner(System.in);
        Repositorio repo = Repositorio.getInstance();
        System.out.println("--- Cambiar estado ---");
        ArrayList<Tarea> tareas = new ArrayList<>();

        for (Tarea t : repo.getTareas()) {
            if (t.getUsuario() != null && t.getUsuario().getUsername().equals(this.getUsername())) {
                tareas.add(t);
            }
        }

        if (tareas.isEmpty()) {
            System.out.println("No tiene tareas.");
        }

        for (int i = 0; i < tareas.size(); i++) {
            System.out.println((i + 1) + ": " + tareas.get(i).toString());
        }

        System.out.print("Seleccione tarea que quiere ver: ");
        int opcion = scan.nextInt();

        if (opcion < 1 || opcion > tareas.size()) {
            System.out.println("Opción inválida");
            return;
        }

        Tarea eleccion = tareas.get(opcion - 1);
        System.out.print("Nuevo estado: ");
        scan.nextLine(); // Limpiar buffer
        String nuevo = scan.nextLine();

        eleccion.setEstado(nuevo);
        System.out.println("Estado actualizado.");
    }

    /**
     * Muestra las tareas de los proyectos en los que el colaborador está involucrado.
     * Si el colaborador no está en ningún proyecto, muestra un mensaje indicando esto.
     */
    public void tareasProyectos() {
        Repositorio repo = Repositorio.getInstance();

        System.out.println("--- Mis tareas de proyectos ---");

        ArrayList<Proyecto> misProyectos = new ArrayList<>();

        for (Proyecto p : repo.getProyectos()) {
            for (Tarea t : p.getListaTarea()) {
                if (t.getUsuario() != null && t.getUsuario().getUsername().equals(this.getUsername())) {
                    if (!misProyectos.contains(p)) {
                        misProyectos.add(p);
                    }
                }
            }
        }

        if (misProyectos.isEmpty()) {
            System.out.println("No estás en ningún proyecto.");
            return;
        }

        for (Proyecto pr : misProyectos) {
            System.out.println("\nProyecto: " + pr.getId() + " | " + pr.getNombre());
            System.out.println("Tareas asociadas:");

            for (Tarea t : pr.getListaTarea()) {
                System.out.println(" - " + t.toString());
            }
        }
    }

    /**
     * Aplica el patrón de diseño Visitor a las tareas del colaborador.
     * El visitante realiza una acción sobre cada tarea asignada al colaborador.
     */
    public void aplicarVisitor() {
        Repositorio repo = Repositorio.getInstance();
        TareaVisitor visitor = new AccionVisitor();

        System.out.println("--- Aplicando visitor ---");
        boolean tiene = false;

        for (Tarea t : repo.getTareas()) {
            if (t.getUsuario() != null && t.getUsuario().getUsername().equals(this.getUsername())) {
                System.out.println("\nTarea: " + t.getId() + " (" + t.getTipo() + ")");
                t.accept(visitor); // Aplica el Visitor a la tarea
                tiene = true;
            }
        }

        if (!tiene) {
            System.out.println("No tienes tareas para aplicar Visitor.");
        }
    }
}
