package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		File arch = new File("archivos/usuarios.txt");
		File arch2 = new File("archivos/registros.txt");
		
		Scanner scan = new Scanner(arch);
		Scanner scan2 = new Scanner(arch2);

		
		String[] usuario = new String[300];
		String[] contras = new String[300];
		
		
		String[] ides = new String[300];
		String[] fechas = new String[300];
		String[] actividades = new String[300];
		int[] horas = new int[300];
		
		int[] contador = new int[300]; 

		
		int j = 0;
		int i = 0;
		
		
		try {
			while (scan.hasNextLine()) {
				String linea = scan.nextLine();
				String[] partes = linea.split(";");
				String nombre = partes[0];
				String contraseña = partes[1]; 
				
				usuario[j] = nombre;
				contras[j] = contraseña;
				
				j++;
				
				
				
			}
			
			while (scan2.hasNextLine()) {
			    String linea2 = scan2.nextLine();
			    String[] partes2 = linea2.split(";"); 
			    String id = partes2[0];
			    String fecha = partes2[1];
			    
			    int hora = Integer.parseInt(partes2[2]);
			    String actividad = partes2[3];
			    boolean confirmar = false;
			    
			   
			    
			    for (int l = 0; l < i; l++) {
			        if (actividad.equals(actividades[l])) {
			            confirmar = true;
			            contador[l] += 1;
			            break;
			        }
			    }

			    if (!confirmar) {
			        ides[i] = id; 
			        fechas[i] = fecha;
			        actividades[i] = actividad;
			        horas[i] = hora;  
			        contador[i] = 1;
			        i++;
			    }
			}
			
			
		} catch (Exception e) {

		}
		
		
				
		
		
		
		
		
		
		boolean seguirViendo = true;
		
		Scanner teclado = new Scanner(System.in);
		
		while (seguirViendo) {
			
				try {
					System.out.println("1) Menu de Usuarios");
					System.out.println("2) Menu de Analisis");
					System.out.println("3) Salir");
					System.out.println("ingrese opcion");
					
					int opcionUsuario = teclado.nextInt();
					
					switch (opcionUsuario) {
					
					
						case 1:
							System.out.println("menu usuario");
							
							
							
							
							
							
							
						case 2:
							System.out.println("menu analisis");
							System.out.println("-----------");
							
							System.out.println("1) Actividad más realizada");
							System.out.println("2) Actividad más realizada por cada usuario");
							System.out.println("3) Usuario con mayor procastinacion");
							System.out.println("4) Ver todas las actividades");
							System.out.println("5) Salir");
							
							int analisis = teclado.nextInt();
							
							switch (analisis) {
								case 1:
									String mayor = "";
									int maximo = 0;
									
									for (int v = 0; v < i ; v++) {
										if (contador[v] > maximo) {
											mayor = actividades[v];
											maximo = contador[v];
										}
									
										
										
									
									}
									System.out.println("el mayor es " + mayor);
									System.out.println(" ");
									
								case 2:
								    
								    for (int d = 0; d < j; d++) {  
								        String persona = usuario[d];  
								        int maximoHoras = 0;          
								        String actividadMax = "";     
								        
								       
								        for (int r = 0; r < i; r++) {  
								            if (ides[r].equalsIgnoreCase(persona)) {  
								                if (horas[r] > maximoHoras) { 
								                    maximoHoras = horas[r];  
								                    actividadMax = actividades[r];  
								                }
								            }
								        }
								        
								       
								        System.out.println( persona +  " "+  actividadMax + " con " + maximoHoras + " horas.");
								    }
								    break;
									
									
									
									

									
							
							
							}
							
							
							

							
					}
					
					
				} catch (Exception e) {
					System.out.println("aweoanao");
				}
				


				
				
				
				
				
				
		
			 
		}
		
		
		
		
		
	}

}
