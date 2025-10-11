//Integrante 1: Vicente Veliz | Rut : 22.012.230-1 | Carrera: Ingeneria Civil en Computacion e Informatica
//Integrante 2: Christian Mardones | Rut: 22.026.428-9 | Carrera: Ingeneria Civil en Computacion e Informatica
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class main {
	public static void main(String[] args) throws FileNotFoundException {
		Funciones.cargarUsuarios();
		Funciones.cargarVulnerabilidades();
        Funciones.cargarPc();
        Funciones.cargarPuertos();
		
        boolean seguirViendo = true;
        Scanner input = new Scanner(System.in);
        int eleccion = 0;
        
        while (seguirViendo) {
        	Funciones.printMenuMain();
        	
        	while (eleccion < 1 || eleccion > 2) {
        		try {
        			System.out.print("Ingrese eleccion: ");
        			eleccion = input.nextInt();
        			
        			input.nextLine();
        			
        			if (eleccion < 1 || eleccion > 2) {
        				System.out.println("");
        				System.out.println("Ingrese una opcion valida.");
        				System.out.println("");
        				
        			}
    			} catch (Exception e) {
    				System.out.println("");
    				System.out.println("Error: " + e);
    				System.out.println("Ingrese un valor valido.");
    				System.out.println("");
    				input.nextLine();
    			}
        	}
        	
        	if (eleccion == 1) {
        		Usuario usuarioActual = null;
                while (usuarioActual == null) {
                    usuarioActual = Funciones.login();
                }
                
                
                if (usuarioActual.getRol().equalsIgnoreCase("Admin")) { //Menu Admin
                	boolean seguirViendoAdmin = true;
                	while (seguirViendoAdmin) {
                		Funciones.menuAdmin();
                		
                		try {
                			eleccion = input.nextInt();
                        	input.nextLine();
                        	
                        	if (eleccion < 1 || eleccion > 4 ) {
                        		System.out.println("");
                        		System.out.println("Ingrese una opcion valida. ");
                        		System.out.println("");
                        	}
                		} catch (Exception e) {
							System.out.println("");
							System.out.println("Error: " + e);
							System.out.println("Ingrese un valor valido.");
							System.out.println("");
							input.nextLine();
							eleccion = 0;
						}
                    	
                    	
                    	
                    	if (eleccion == 1) {
                    		Funciones.listaPC();
                    	}
                    	if (eleccion == 2) {
                    		boolean subMenu = true;
                    		while(subMenu) {
                    			Funciones.subMenu();
                    			try {
                        			eleccion = input.nextInt();
                                	input.nextLine();
                                	
                                	if (eleccion < 1 || eleccion > 3 ) {
                                		System.out.println("");
                                		System.out.println("Ingrese una opcion valida. ");
                                		System.out.println("");
                                	}
                        		} catch (Exception e) {
        							System.out.println("");
        							System.out.println("Error: " + e);
        							System.out.println("Ingrese un valor valido.");
        							System.out.println("");
        							input.nextLine();
        							eleccion = 0;
        						}
                    			
                        		if (eleccion == 1) {
                                	Funciones.agregarPc();
                                	eleccion = 0;

                        		}
                        		if (eleccion == 2) {
                        			Funciones.eliminar();
                        			eleccion = 0;
                        		}
                        		if (eleccion == 3) {
                        			subMenu = false;
                        			eleccion = 0;
                        		}
                    			
                    		}
                    		
                    	}
                    	if (eleccion == 3) {
    						Funciones.clasificarRiesgo();

                    	}
                    	if (eleccion == 4) {
                    		seguirViendoAdmin = false;
                    	}
                	}
                }
                else { //Menu Usuario
                	boolean seguirViendoUsuario = true;
                	while (seguirViendoUsuario) {
                		menuUsuario.printMenu();
                		try {
                			eleccion = input.nextInt();
                        	input.nextLine();
                        	
                        	if (eleccion < 1 || eleccion > 5 ) {
                        		System.out.println("");
                        		System.out.println("Ingrese una opcion valida. ");
                        		System.out.println("");
                        	}
                		} catch (Exception e) {
							System.out.println("");
							System.out.println("Error: " + e);
							System.out.println("Ingrese un valor valido.");
							System.out.println("");
							input.nextLine();
							eleccion = 0;
						}
                		
                		if (eleccion == 1) {
                			menuUsuario.verListaPCS();
                		}
                		if (eleccion == 2) {
                			menuUsuario.escanerPC(usuarioActual);
                		}
                		if (eleccion == 3) {
                			menuUsuario.puertosAbiertos();
                		}
                		if (eleccion == 4) {
                			menuUsuario.ordenarPCsPorClaseIP();
                		}
                		if (eleccion == 5) {
                			seguirViendoUsuario = false;
                		}
                	}
                }
        	}
        	if (eleccion == 2) {
        		break;
        	}
        }
	}
}
