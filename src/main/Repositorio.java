package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Esta clase servirá como Singleton
public class Repositorio {
	
	private static Repositorio instance;
	
	private List<Usuario> usuarios = new ArrayList<>();
	private List<Proyecto> proyectos = new ArrayList<>();
	private List<Tarea> tareas = new ArrayList<>();
    
    private String base = "archivos" + File.separator;
    private File fUsuarios = new File(base + "usuarios.txt");
    private File fProyectos = new File(base + "proyectos.txt");
    private File fTareas = new File(base + "tareas.txt");
    
    private Repositorio() {}
    
    public static Repositorio getInstance() {
        if (instance == null) {
            instance = new Repositorio();
        }
        return instance;
    }
    
    public List<Usuario> getUsuarios() { 
    	return usuarios; 
    }
   
    public List<Proyecto> getProyectos() {
    	return proyectos; 
    }
    
    public List<Tarea> getTareas() { 
    	return tareas;
    }
    
    public void cargarUsuarios() throws FileNotFoundException {
        usuarios.clear();
        try (Scanner input = new Scanner(fUsuarios)) {
            while (input.hasNextLine()) {
                String linea = input.nextLine();
                if (linea == null || linea.trim().isEmpty()) {
                    // Si la linea esta vacía, no hace nada y pasa a la siguiente
                } else {
                    String[] p = linea.split("\\|");
                    if (p.length >= 3) {
                        String user = p[0].trim();
                        String pass = p[1].trim();
                        String rol = p[2].trim();
                        
                        Usuario u = rol.equalsIgnoreCase("Administrador")
                                ? new Administrador(user, pass)
                                : new Colaborador(user, pass);
                        usuarios.add(u);
                    }
                }
            }
        }
    }
    
    public void cargarProyectos() throws FileNotFoundException {
        proyectos.clear();
        try (Scanner input = new Scanner(fProyectos)) {
            while (input.hasNextLine()) {
                String linea = input.nextLine();
                if (linea == null || linea.trim().isEmpty()) {
                    // No hace nada
                } else {
                    String[] p = linea.split("\\|");
                    if (p.length >= 3) {
                        Proyecto pr = new Proyecto(p[0].trim(), p[1].trim());
                        String responsable = p[2].trim();

                        // Asignar usuario responsable si existe
                        
                        Usuario encontrado = null;
                        
                        for (Usuario u : usuarios) {
                            if (u.getUsername().equalsIgnoreCase(responsable)) {
                                encontrado = u;
                                break;
                            }
                        }
                        
                        if (encontrado != null) {
                        	pr.setUsuario(encontrado);
                        }
                        else {
                        	pr.setUsuario(new UsuariosNoArchivados(responsable, null, null));
                        }
                        proyectos.add(pr);
                    }
                }
            }
        }
    }
    
    public void cargarTareas() throws FileNotFoundException {
        tareas.clear();
        try (Scanner input = new Scanner(fTareas)) {
            while (input.hasNextLine()) {
                String linea = input.nextLine();
                if (linea == null || linea.trim().isEmpty()) {
                    // No hace nada
                } else {
                    String[] partes = linea.split("\\|");
                    if (partes.length >= 8) {
                        String idProyecto   = partes[0].trim();
                        String id           = partes[1].trim();
                        String tipo         = partes[2].trim();
                        String descripcion  = partes[3].trim();
                        String estado       = partes[4].trim();
                        String responsable  = partes[5].trim();
                        String complejidad  = partes[6].trim();
                        String fecha        = partes[7].trim();

                        Tarea t = TareaFactory.crear(tipo, id, descripcion, estado, complejidad, fecha);

                        // Vincular proyecto
                        for (Proyecto pr : proyectos) {
                            if (pr.getId().equals(idProyecto)) {
                                t.setProyecto(pr);
                                pr.getListaTarea().add(t);
                                break;
                            }
                        }

                        // Vincular usuario
                        for (Usuario u : usuarios) {
                            if (u.getUsername().equalsIgnoreCase(responsable)) {
                                t.setUsuario(u);
                                break;
                            }
                        }

                        tareas.add(t);
                    }
                }
            }
        }
    }
    
    public void guardarReporte(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            FileWriter escritor = new FileWriter(archivo);

            for (Proyecto pr : proyectos) {
                escritor.write("Proyecto " + pr.getId() + " - " + pr.getNombre() +
                        " (Resp: " + (pr.getUsuario() != null ? pr.getUsuario().getUsername() : "N/A") + ")\n");

                for (Tarea t : pr.getListaTarea()) {
                    escritor.write("  - [" + t.getTipo() + "] " + t.getId() + " :: " + t.getDescripcion() +
                            " | " + t.getEstado() + " | " + t.getComplejidad() + " | " + t.getFecha() +
                            " | Resp: " + (t.getUsuario() != null ? t.getUsuario().getUsername() : "N/A") + "\n");
                }

                escritor.write("\n");
            }

            escritor.close();
            System.out.println("Reporte guardado correctamente en " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al guardar el reporte: " + e.getMessage());
        }
    }
}
