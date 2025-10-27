package main;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Funciones.cargarUsuarios();
		Funciones.cargarProyectos();
		Funciones.cargarTareas();
		
		Usuario revisar = null;
		Scanner scan = new Scanner(System.in);
		Scanner eleccion = new Scanner(System.in);


		
		
		boolean seguirPrograma = true;
		
		while (seguirPrograma) {
			while (revisar == null) {
				revisar = Funciones.login();
				
			}
			System.out.println("Su rol es " + revisar.getRol());
			
			
			if (revisar.getRol().equalsIgnoreCase("Administrador")) {
				Administrador admin = (Administrador) revisar;
				
				admin.menuAdmin();
				int opcion = 0;
				
				System.out.print("eliga una opcion: ");
				opcion = eleccion.nextInt();
				
				switch (opcion) {
					case 1 :	
						break;
						
					case 2 :
						break;
					
					case 3:
						revisar = null;
						break;
						
					default:
						System.out.println(" ");
						System.out.println("------------");
						System.out.println("opcion invalida");
						System.out.println("------------");
						System.out.println(" ");


						
				}
				
			}
			
			if (revisar.getRol().equalsIgnoreCase("Colaborador")) {
				Colaborador cola = (Colaborador) revisar;
				cola.menuColaborador();
				int opcion = 0;
				
				switch (opcion) {
					case 1 :	
						break;
					
					case 2 :
						break;
				
					case 3:
						break;
					
					default:
						System.out.println("opcion invalida");
						
				}
			}
			
			
		}
	}
	
	

}
