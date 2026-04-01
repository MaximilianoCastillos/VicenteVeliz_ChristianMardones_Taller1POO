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

		
		String[] usuario = new String[300];     //guarda nombres de usuarios
		String[] contras = new String[300];     // guarda las contraseñas
		
		
		String[] ides = new String[300];		//guarda id de las personas
		String[] fechas = new String[300];		// guarda fechas
		String[] actividades = new String[300];	// las actividades que hacen los ususarios
		int[] horas = new int[300];				//horas que dedicaron a las actividades
		
		int[] contador = new int[300]; 			//guarda la cantidad que fue mas realizada y la use para responder el item 1 del menu de analisis

		
		int j = 0;								//lo use para que se vaya moviendo el indice cada vez que guardaba actividades, ides y esas cosas chaval
		int i = 0;
		
		
		try {
			while (scan.hasNextLine()) {						//lee el archivo
				String linea = scan.nextLine();					//lee cada linea
				String[] partes = linea.split(";");				//separo la linea en partes
				String nombre = partes[0];						//valores
				String contraseña = partes[1]; 					//valores
				usuario[j] = nombre;							//valores
				contras[j] = contraseña;						//valores
				
				j++;											//se mueve el indice para ir agregandolo a la lista
							
			}
			
		while (scan2.hasNextLine()) {							//lee el archivo
			    String linea2 = scan2.nextLine();
			    String[] partes2 = linea2.split(";"); 
			    String id = partes2[0];
			    String fecha = partes2[1];
			    
			    int hora = Integer.parseInt(partes2[2]);
			    String actividad = partes2[3];
			    boolean confirmar = false;						//confirma si esta en lista de actividades
			    
			    for (int l = 0; l < i; l++) {					// este for hace que revise la lista de actividades para ver si la actividad ya 
			        if (actividad.equals(actividades[l])) {		//se repite y pueda ir sumando "1" a la lista de contador en ese indice
			            confirmar = true;
			            contador[l] += 1;
			            break;
			        }
			    }

			    if (!confirmar) {								// aqui en caso que la actividad no este en la lista se agrega normal a la lista
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
		
	while (seguirViendo) {							//menu principal y debemos elegir si nos metemos a "menu usuario" o "menu de analisis" y si no salir 
			
				try {
					System.out.println("1) Menu de Usuarios");
					System.out.println("2) Menu de Analisis");
					System.out.println("3) Salir");
					System.out.println("ingrese opcion");
					
					int opcionUsuario = teclado.nextInt();			//preguntamos que opcion desea elegir
					
					switch (opcionUsuario) {
					
					
						case 1:
							System.out.println("menu usuario");		// "menu de usuario" aca no puse anda, (aca debes avanzar con lo que te dije en clases) 
							
							
					case 2:											// "menu de analisis" aca respondi 2 nomas falta por terminar 
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
					System.out.println("hola");
				}
								 
		}
		
	}

}
