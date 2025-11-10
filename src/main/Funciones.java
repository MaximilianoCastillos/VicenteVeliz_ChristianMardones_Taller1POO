package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Funciones {
	private static List<Usuario> listaUsuarios = new ArrayList<>();
	private static List<Proyecto> listaProyectos = new ArrayList<>();
	private  static List<Tarea> listaTareas = new ArrayList<>();
	
	public static Usuario login() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Usuario: ");
		String usuario = scan.nextLine();
		System.out.println("Contraseña: ");
		String contra = scan.nextLine();		
		
		Usuario user = null;
		
		for (Usuario u : listaUsuarios) {
			if (u.getUsername().equalsIgnoreCase(usuario) && u.getContraseña().equalsIgnoreCase(contra)) {
				user = u;
				break;
			}
		}
		if (user != null) {
			System.out.println("Buena " + user.getUsername());
		}
		if (user == null) {
			System.out.println("User o contra incorrecta ");
		}
		
		return user;
	}
	
	public static void cargarUsuarios() throws FileNotFoundException {
		File arch = new File("archivos/usuarios.txt");
		Scanner scan = new Scanner(arch);
		
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split("\\|");
			String user = partes[0];
			String contra = partes[1];
			String rl = partes[2];
			
			Usuario u = null;
			
			if (rl.equalsIgnoreCase("Administrador")) {
				u = new Administrador(user, contra);
			}
			
			if (rl.equalsIgnoreCase("Colaborador")) {
				u = new Colaborador(user, contra);
			}
			listaUsuarios.add(u);
		}
		scan.close();
	}
	
	public static void cargarProyectos() throws FileNotFoundException {
		File arch = new File("archivos/proyectos.txt");
		Scanner scan = new Scanner(arch);
		
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split("\\|");
			
			String identificador = partes[0];
			String nom = partes[1];
			String responsable = partes[2];
			
			Proyecto p = new Proyecto(identificador, nom);
			
			for (Usuario user : listaUsuarios) {
				if (user.getUsername().equalsIgnoreCase(responsable)) {
					p.setUsuario(user);
					break;

				}
			}
			listaProyectos.add(p);
		}
	}
	
	public static void cargarTareas() throws FileNotFoundException {
		File arch = new File("archivos/tareas.txt");
		Scanner scan = new Scanner(arch);
		
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split("\\|");
			
			String proyecto = partes[0];
			String id = partes[1];
			String tipo = partes[2];
			String descripcion = partes[3];
			String estado = partes[4];
			String responsable = partes[5];
			String complejidad = partes[6];
			String fecha = partes[7];
			
			Tarea t = null;
			
			if (tipo.equalsIgnoreCase("Bug")) {
				t = new Bug(id, tipo, descripcion, estado, complejidad, fecha);
			}
			if (tipo.equalsIgnoreCase("Feature")) {
	            t = new Feature(id, tipo, descripcion, estado, complejidad, fecha);
			}
			if(tipo.equalsIgnoreCase("Documentacion")) {
				t = new Documentacion(id, tipo, descripcion, estado, complejidad, fecha);
			}	
			
			for (Proyecto p : listaProyectos) {
				if (p.getId().equalsIgnoreCase(proyecto)) {
					t.setProyecto(p);
					p.getListaTarea().add(t);
					break;
				}
			}
			for (Usuario u : listaUsuarios) {
				if (u.getUsername().equalsIgnoreCase(responsable)) {
					t.setUsuario(u);
					break;
				}
			}
			listaTareas.add(t);
		}
	}
}
