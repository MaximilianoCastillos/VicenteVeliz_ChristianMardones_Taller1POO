package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class Funciones {
    
	private static List<Vulnerabilidad> listaVulnerabilidades = new ArrayList<>();
	public static List<PC> listaPCs = new ArrayList<>();
	public static List<Usuario> listaUsuarios = new ArrayList<>();
	
	public static void menuAdmin() {
		System.out.println(" ");
		System.out.println("1) ver lista completa de PCs: ");
		System.out.println("2) AGREGAR O ELIMINAR: ");
		System.out.println("3) clasificar PCs segun Nivel: ");
		System.out.println("4) salir: ");
	}
	public static void subMenu() {
        System.out.println(" ");
		System.out.println("1) Agregar un nuevo PC");
        System.out.println("2) Eliminar un PC por ID");
        System.out.println("3) Volver al Menú Admin");
        System.out.println("Ingrese una opción: ");
        
	}
	
	
	//item 1--------------------------------------------------------------------------------------------------------------------------------------------
	public static void cargarUsuarios() throws FileNotFoundException {
		File archivo = new File("archivos/usuarios.txt");
		Scanner lector = new Scanner(archivo);
		
		while(lector.hasNextLine()) {
			String linea = lector.nextLine();
			String[] partes = linea.split(";");
			String usuario = partes[0];
			String contraseña = partes[1];
			String rol = partes[2];
			
			Usuario p = new Usuario(usuario, contraseña, rol);
			
			listaUsuarios.add(p);
		}
		lector.close();
	}
	
	public static void cargarVulnerabilidades() throws FileNotFoundException {
		File archivo = new File("archivos/vulnerabilidades.txt"); 
		Scanner lector = new Scanner(archivo);
		
		while (lector.hasNextLine()) {
			String linea = lector.nextLine();
			String[] partes = linea.split("\\|");
			String puerto = partes[0];
			String nombre = partes[1];
			String descripcion = partes[2];
			
			Vulnerabilidad vulnerabilidad = new Vulnerabilidad(puerto, nombre, descripcion);
			listaVulnerabilidades.add(vulnerabilidad);
		}
		lector.close();
		
	}
	
	public static void cargarPc() throws FileNotFoundException {
		File archivo = new File("archivos/pcs.txt"); 
		Scanner lector = new Scanner(archivo);
		
		while (lector.hasNextLine()) {
			String linea = lector.nextLine().trim();
			String[] partes = linea.split("\\|");
			
			if (partes.length == 3) {
				String ID = partes[0];
				String ip = partes[1];
				String sistemaOperativo = partes[2];
				
				PC pc = new PC(ID, ip, sistemaOperativo);
				listaPCs.add(pc);
			}
			
			
		}
		lector.close();
	}
	
	public static void cargarPuertos() throws FileNotFoundException {
		File archivo = new File("archivos/puertos.txt"); 
		Scanner lector = new Scanner(archivo);
		
		while (lector.hasNextLine()) {
			String linea = lector.nextLine();
			String[] partes = linea.split("\\|");
			String ID = partes[0];
			String numero = partes[1];
			String estado = partes[2];
			
			Puerto p = new Puerto(ID, estado);
			
			Vulnerabilidad vulnEncontrada = null;
			for (Vulnerabilidad v : listaVulnerabilidades) {
				if (v.getPuerto().equals(numero)) { 
                    vulnEncontrada = v;
                    break; 
                }
			}
			 if (vulnEncontrada != null) {
	                p.setVulnerabilidad(vulnEncontrada); 
            }
			
			 PC pcEncontrado = null;
	            for (PC pc : listaPCs) {
	                if (pc.getId().equalsIgnoreCase(ID)) {
	                    pcEncontrado = pc;
	                    break; 
	                }
	            }

	            if (pcEncontrado != null) {
	                pcEncontrado.agregarPuerto(p); 
	            }
			
		}
		lector.close();
	}
	
	public static void listaPC() {
		for (PC pc : listaPCs) {
			String puertosInfo = "";
			boolean esPrimerPuerto = true;
			
			for (Puerto puerto : pc.getPuertos()) {
				
				 if (esPrimerPuerto == false) {
		                puertosInfo = puertosInfo + " | "; 
		            }
				Vulnerabilidad vuln = puerto.getVulnerabilidad();
				String vulnNombre = "nada";
				if (vuln != null) {
					vulnNombre = vuln.getNombre();
					
				}
				puertosInfo = puertosInfo + puerto.getNumPuerto();
	         
	            puertosInfo = puertosInfo + puerto.getEstado().toUpperCase();
	            
	            
	            esPrimerPuerto = false; 

			}
		 if (puertosInfo.isEmpty()) {
                puertosInfo = "NINGUNO";
            }
            System.out.println(pc.getId()+" " + pc.getIp() +" "+ pc.getSo() +" " + " "+puertosInfo);
            System.out.println("-----------------------");
		}
	}
	//item 1(Admin)--------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	//item 2(Admin)--------------------------------------------------------------------------------------------------------------------------------------------
	public static void agregarPc() {
        Scanner scan = new Scanner(System.in);
		System.out.println("agregar pc");
		System.out.println("Ingrese id del pc: ");
		String id = scan.nextLine();
		
		System.out.println("ingrese ip del pc: ");
		String ip = scan.nextLine();
		
		System.out.println("ingrese sistema operativo: ");
		String sistemaOperativo = scan.nextLine();
		
		PC nuevoPC = new PC(id, ip, sistemaOperativo);
		listaPCs.add(nuevoPC);
		
		System.out.println("Pc agregado");
	}
	
	public static void eliminar() {
		Scanner scan = new Scanner(System.in); 
        System.out.print("Ingrese el ID del PC que desea eliminar: ");
        String eliminar = scan.nextLine();
        boolean encontrado = false;
        
        for (int i = 0; i < listaPCs.size(); i++) {
            if (listaPCs.get(i).getId().equalsIgnoreCase(eliminar)) {
                listaPCs.remove(i);
                encontrado = true;
                break; 
            }
        }
        if (encontrado) {
        	System.out.println("pc con id " + eliminar + " fue eliminado");
        }
        if (encontrado == false) {
        	System.out.println("pc no encontrado");
        }
	}
	//item 2(Admin)--------------------------------------------------------------------------------------------------------------------------------------------
	
	//item 3(Admin)-------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static void clasificarRiesgo() {
		System.out.println("clasificados segun el riesgo: ");
		for (PC pc : listaPCs) {
			int contador = 0;
			for (Puerto puerto : pc.getPuertos()) {
				if (puerto.getVulnerabilidad() != null) {
					contador += 1;
				}
				
			}
			String nivel = "";
			if (contador == 0) {
				nivel = "bajo";
			}
			if (contador <= 2) {
				nivel = "medio";
			}
			if (contador > 2) {
				nivel = "alto";
				
			}
			System.out.println("pc: " + pc.getId() + " " + pc.getIp()  );
			System.out.println("vulnerabilidades en total son " + contador);
			System.out.println("el nivel es " + nivel);
			System.out.println(" ");
		}
	}
	public static String obtenerClaseIP(String ip) {
	    try {
	        // separar la IP por puntos
	        String[] partes = ip.split("\\.");
	        int primerNumero = Integer.parseInt(partes[0]);

	        if (primerNumero >= 0 && primerNumero <= 127) {
	            return "A";
	        } else if (primerNumero >= 128 && primerNumero <= 191) {
	            return "B";
	        } else if (primerNumero >= 192 && primerNumero <= 223) {
	            return "C";
	        } else {
	            return "Desconocida";
	        }

	    } catch (Exception e) {
	        return "Desconocida";
	    }
	}
	//item 3(Admin)-------------------------------------------------------------------------------------------------------------------------------------------------
	public static String decodificarContraseña(String texto) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = md.digest(texto.getBytes());
			return Base64.getEncoder().encodeToString(hashBytes);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Usuario login() {
	    Scanner scan = new Scanner(System.in);
	    System.out.println("===== INICIO DE SESIÓN =====");

	    System.out.print("Usuario: ");
	    String nombreIngresado = scan.nextLine().trim();

	    System.out.print("Contraseña: ");
	    String passIngresada = scan.nextLine().trim();

	    // Hashear la contraseña ingresada
	    String hashIngresado = decodificarContraseña(passIngresada);

	    // Buscar usuario en la lista
	    for (Usuario u : listaUsuarios) {
	        if (u.getUsername().equalsIgnoreCase(nombreIngresado) && u.getContraseña().equals(hashIngresado)) {
	            System.out.println("Ingresado " + u.getUsername() + " (" + u.getRol() + ")");
	            return u;
	        }
	    }

	    System.out.println("Usuario o contraseña incorrectos.");
	    return null;
	}
	
	public static void printMenuMain() {
		System.out.println("1) Ingresar al Sistema");
		System.out.println("2) Salir");
	}
	 
}