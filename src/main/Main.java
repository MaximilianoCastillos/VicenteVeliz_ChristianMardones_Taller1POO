package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
			
			while (scan2.hasNextLine()) {				//Lee archivo de registros		
			    String linea2 = scan2.nextLine();
			    String[] partes2 = linea2.split(";");

			    ides[i] = partes2[0];
			    fechas[i] = partes2[1];
			    horas[i] = Integer.parseInt(partes2[2]);
			    actividades[i] = partes2[3];

			    i++;
			}
			
			
		} catch (Exception e) {
			System.out.println("error al leer archivo");

		}
				
		
		boolean seguirViendo = true;
		
		Scanner teclado = new Scanner(System.in);
		
	while (seguirViendo) {							//menu principal y debemos elegir si nos metemos a "menu usuario" o "menu de analisis" y si no salir 
			
				try {
					
					
					
					boolean ver = true;
					int opcionUsuario;
					do {
						System.out.println("1) Menu de Usuarios");
						System.out.println("2) Menu de Analisis");
						System.out.println("3) Salir");
						System.out.print("Ingrese opcion: ");
						opcionUsuario = teclado.nextInt();
						
						
					} while (opcionUsuario < 0 || opcionUsuario > 4 );
					
					switch (opcionUsuario) {
					
					
						case 1: //Entra al menu de usuarios
							
							teclado.nextLine();
							String usuarioLogin;
							int usuarioLoginIndice = 0;
							int usuarioEleccion = 0;
							
							while (true) { //Login de usuarios
								System.out.print("Usuario: ");
								usuarioLogin = teclado.nextLine().strip();
								System.out.print("\n" +"Contraseña: ");
								String contraseñaLogin = teclado.nextLine().strip();
								
								boolean accesoCorrecto = false;

								for (int k = 0; k < j; k++) {
								    if (usuarioLogin.equals(usuario[k]) && contraseñaLogin.equals(contras[k])) {
								        accesoCorrecto = true;
								        usuarioLoginIndice = k;
								        break;
								    }
								}

								if (accesoCorrecto) {
								    break;
								} else {
								    System.out.println("Usuario o Contraseña incorrectos.");
								    System.out.println();
								}
							}
							
							System.out.println("Acceso concedido. ");
							System.out.println("Bienvenid@ " + usuarioLogin);
							
							while (usuarioEleccion != 5) {
								System.out.print("Que deseas realizar?\r\n"
										+ "\r\n"
										+ "1) Registrar actividad.\r\n"
										+ "2) Modificar actividad.\r\n"
										+ "3) Eliminar actividad.\r\n"
										+ "4) Cambiar contraseña.\r\n"
										+ "5) Salir.\r\n"
										+ "Ingrese eleccion: ");
								try {
									usuarioEleccion = teclado.nextInt();
								}
								catch (Exception e){
									System.out.println("solo valores numericos");
									teclado.nextLine();
									usuarioEleccion = -1;
								}
								
								switch (usuarioEleccion) {
								case 1: //Registrar actividad
									
									if (i < 300) {
								        teclado.nextLine();

								        System.out.print("Ingrese fecha: ");
								        String nuevaFecha = teclado.nextLine().strip();
								        int nuevasHoras = -1;
								        boolean horasValidas = false;
								        
								        while (!horasValidas) {
								            System.out.print("Ingrese horas validas: ");
								            try {
								                nuevasHoras = teclado.nextInt();

								                if (nuevasHoras >= 0) {
								                    horasValidas = true;
								                } else {
								                    System.out.println("Las horas no pueden ser negativas.");
								                }

								            } catch (Exception e) {
								                System.out.println("Debe ingresar solo numeros.");
								                teclado.nextLine();
								            }
								        }
								        
								        teclado.nextLine();

								        System.out.print("Ingrese actividad: ");
								        String nuevaActividad = teclado.nextLine().strip();

								        ides[i] = usuario[usuarioLoginIndice];
								        fechas[i] = nuevaFecha;
								        horas[i] = nuevasHoras;
								        actividades[i] = nuevaActividad;

								        i++;

								        System.out.println("Actividad registrada con exito!");
								        
								        guardarRegistros(ides, fechas, horas, actividades, i);
								    } else {
								        System.out.println("No se pueden registrar mas actividades.");
								    }
								    break;
								
								case 2: //Modificar actividad
									int[] indicesMostrados = new int[300];
									int cantidadMostrada = 0;

									System.out.println("Cual actividad deseas modificar?");
									System.out.println("0) Regresar.");

									//Mostrar actividades del usuario logeado
									for (int r = 0; r < i; r++) {
									    if (ides[r].equalsIgnoreCase(usuario[usuarioLoginIndice])) {
									        System.out.println((cantidadMostrada + 1) + ") " +
									                ides[r] + ";" +
									                fechas[r] + ";" +
									                horas[r] + ";" +
									                actividades[r]);

									        indicesMostrados[cantidadMostrada] = r;
									        cantidadMostrada++;
									    }
									}

									if (cantidadMostrada == 0) {
									    System.out.println("No tienes actividades registradas.");
									    break;
									}

									System.out.print("Ingrese eleccion: ");
									int eleccionModificar;
									
									try {
									    eleccionModificar = teclado.nextInt();

										
									}
									catch (Exception e){
										System.out.println("valores numericos");
										teclado.nextLine();
										break;
									}
									

									if (eleccionModificar == 0) {
									    break;
									}

									if (eleccionModificar < 1 || eleccionModificar > cantidadMostrada) {
									    System.out.println("Opcion invalida.");
									    break;
									}

									// índice dentro de los arreglos
									int indiceReal = indicesMostrados[eleccionModificar - 1];

									System.out.println("Que deseas modificar?");
									System.out.println("0) Regresar.");
									System.out.println("1) Fecha");
									System.out.println("2) Duracion");
									System.out.println("3) Tipo de actividad");
									System.out.print("Ingrese eleccion: ");
									
									int campoModificar;
									try {
										campoModificar = teclado.nextInt();

									}
									catch (Exception e){
										System.out.println("ingrese valores numericos pao ");
										teclado.nextLine();
										break;
									}

									switch (campoModificar) {
									    case 0:
									        break;

									    case 1:
									        System.out.print("Ingrese nueva fecha: ");
									        fechas[indiceReal] = teclado.nextLine().strip();
									        System.out.println("Fecha modificada con exito!");
									        guardarRegistros(ides, fechas, horas, actividades, i);
									        break;

									    case 2:
									        System.out.print("Ingrese nueva duracion: ");
									        
									        try {
										        horas[indiceReal] = teclado.nextInt();

									        }
									        catch(Exception e){
									        	System.out.println("ingrese valores numericos");
									            teclado.nextLine();
									            break;
									        }
									        teclado.nextLine();
									        System.out.println("Duracion modificada con exito!");
									        guardarRegistros(ides, fechas, horas, actividades, i);
									        break;

									    case 3:
									        System.out.print("Ingrese nuevo tipo de actividad: ");
									        actividades[indiceReal] = teclado.nextLine().strip();
									        System.out.println("Actividad modificada con exito!");
									        guardarRegistros(ides, fechas, horas, actividades, i);
									        break;

									    default:
									        System.out.println("Opcion invalida.");
									        break;
									}

									break;
								
								case 3: //Eliminar actividad
									int[] indicesMostradosEliminar = new int[300];
									int cantidadMostradaEliminar = 0;

									System.out.println("Cual actividad deseas eliminar?");
									System.out.println("0) Regresar.");

									// Mostrar solo actividades del usuario logeado
									for (int r = 0; r < i; r++) {
									    if (ides[r].equalsIgnoreCase(usuario[usuarioLoginIndice])) {
									        System.out.println((cantidadMostradaEliminar + 1) + ") " +
									                ides[r] + ";" +
									                fechas[r] + ";" +
									                horas[r] + ";" +
									                actividades[r]);

									        indicesMostradosEliminar[cantidadMostradaEliminar] = r;
									        cantidadMostradaEliminar++;
									    }
									}

									if (cantidadMostradaEliminar == 0) {
									    System.out.println("No tienes actividades registradas.");
									    break;
									}

									System.out.print("Ingrese eleccion: ");
									int eleccionEliminar = -1;
									try {
										eleccionEliminar = teclado.nextInt();
									}catch(Exception e){
										System.out.println("te falla??");
										teclado.nextLine();
										eleccionEliminar = -1;
										
									}
									

									if (eleccionEliminar == 0) {
									    break;
									}
									
									if (eleccionEliminar == -1) {
									    break;
									}

									if (eleccionEliminar < 1 || eleccionEliminar > cantidadMostradaEliminar) {
									    System.out.println("Opcion invalida.");
									    break;
									}

									int indiceRealEliminar = indicesMostradosEliminar[eleccionEliminar - 1];

									// Correr todo a la izquierda desde el indice eliminado
									for (int k = indiceRealEliminar; k < i - 1; k++) {
									    ides[k] = ides[k + 1];
									    fechas[k] = fechas[k + 1];
									    horas[k] = horas[k + 1];
									    actividades[k] = actividades[k + 1];
									}

									// Limpiar la ultima posicion
									ides[i - 1] = null;
									fechas[i - 1] = null;
									horas[i - 1] = 0;
									actividades[i - 1] = null;

									// Reducir cantidad de registros
									i--;

									System.out.println("Actividad eliminada con exito");
									guardarRegistros(ides, fechas, horas, actividades, i);

									break;
								
								case 4:// Cambiar contraseña
									teclado.nextLine();

									System.out.print("Ingrese nueva contraseña: ");
									String nuevaContra = teclado.nextLine().strip();

									contras[usuarioLoginIndice] = nuevaContra;

									guardarUsuarios(usuario, contras, j);

									System.out.println("Contraseña cambiada con exito!");
									break;
								
								case 5: //Salir
									usuarioEleccion = 5;
									break;

								default:
									break;
								}
							}
							
							break;
							
					case 2:											// "menu de analisis"  
						int analisis = 0;
						
						while (analisis != 5) {
							System.out.println("Menu analisis");
							System.out.println("-----------");
							
							System.out.println("1) Actividad más realizada");
							System.out.println("2) Actividad más realizada por cada usuario");
							System.out.println("3) Usuario con mayor procastinacion");
							System.out.println("4) Ver todas las actividades");
							System.out.println("5) Salir");
							System.out.print("Ingrese una opcion: ");
							
							try {
								analisis = teclado.nextInt();
							} catch (Exception e) {
								System.out.println("anda a costarte");
								teclado.nextLine();
								analisis = 0;
							}
							
							switch (analisis) {
								case 1:
									String[] actividadesUnicas = new String[300];
								    int[] repeticiones = new int[300];
								    int cantidadUnicas = 0;

								    for (int r = 0; r < i; r++) {
								        String act = actividades[r];
								        boolean encontrada = false;

								        for (int k = 0; k < cantidadUnicas; k++) {
								            if (act.equalsIgnoreCase(actividadesUnicas[k])) {
								                repeticiones[k]++;
								                encontrada = true;
								                break;
								            }
								        }

								        if (!encontrada) {
								            actividadesUnicas[cantidadUnicas] = act;
								            repeticiones[cantidadUnicas] = 1;
								            cantidadUnicas++;
								        }
								    }

								    String mayor = "";
								    int maximo = 0;

								    for (int k = 0; k < cantidadUnicas; k++) {
								        if (repeticiones[k] > maximo) {
								            maximo = repeticiones[k];
								            mayor = actividadesUnicas[k];
								        }
								    }
								    
								    System.out.println();
								    System.out.println("La actividad mas realizada es: " + mayor + " con " + maximo + " veces.");
								    System.out.println();
								    break;
									
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
								        System.out.println();
								    }
								    break;
							
								case 3:
									int maxHorasTotal = 0;
									String usuarioMayor = "";

									for (int d = 0; d < j; d++) {

									    int sumaHoras = 0;

									    for (int r = 0; r < i; r++) {

									        if (ides[r].equalsIgnoreCase(usuario[d])) {

									            sumaHoras += horas[r];

									        }

									    }

									    if (sumaHoras > maxHorasTotal) {

									        maxHorasTotal = sumaHoras;
									        usuarioMayor = usuario[d];

									    }

									}

									System.out.println("Usuario con mayor procrastinacion: " + usuarioMayor  + " con "+ maxHorasTotal + " horas.");
									System.out.println();
									break;
									
								case 4:
									for (int r = 0; r < i; r++) {
									    System.out.println(ides[r] + " " + fechas[r] + " " + horas[r] + " " + actividades[r]);
									    System.out.println();
									}

									break;
								
								case 5:
									analisis = 5;
									break;
							}
						}
						break;
					
					case 3:
						seguirViendo = false;
						
					
						
					}					
					
				} catch (Exception e) {
					System.out.println("enserio???");
					System.out.println("hola");
					teclado.nextLine();
					
				}				 
		}
		
	}
	
	public static void guardarRegistros(String[] ides, String[] fechas,int[] horas, String[] actividades, int cantidad) { //Funcion para guardar registros en el archivo
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/registros.txt"));

			for (int r = 0; r < cantidad; r++) {

				bw.write( ides[r] + ";" + fechas[r] + ";" +horas[r] + ";" +actividades[r]);

				bw.newLine();
			}

			bw.close();

			System.out.println("Registros guardados correctamente.");

		} catch (IOException e) {
			System.out.println("Error al guardar registros.");
		}
	}
	public static void guardarUsuarios(String[] usuario, String[] contras, int cantidad) { //Funcion para guardar usuarios en el archivo (se usa para cambiar contraseña)
	    try {
	        BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/usuarios.txt"));

	        for (int k = 0; k < cantidad; k++) {
	            bw.write(usuario[k] + ";" + contras[k]);
	            bw.newLine();
	        }

	        bw.close();
	        System.out.println("Usuarios guardados correctamente.");

	    } catch (IOException e) {
	        System.out.println("Error al guardar usuarios.");
	    }
	}
}
