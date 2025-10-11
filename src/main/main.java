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
        	eleccion = input.nextInt();
        	
        	if (eleccion == 1) {
        		Usuario usuarioActual = null;
                while (usuarioActual == null) {
                    usuarioActual = Funciones.login();
                }
                
                
                if (usuarioActual.getRol().equalsIgnoreCase("Admin")) { //Menu Admin
                	boolean seguirViendoAdmin = true;
                	while (seguirViendoAdmin) {
                		Funciones.menuAdmin();
                		eleccion = input.nextInt();
                    	input.nextLine();
                    	if (eleccion == 1) {
                    		Funciones.listaPC();
                    	}
                    	if (eleccion == 2) {
                    		Funciones.subMenu();
                    		eleccion = input.nextInt();
                    		boolean subMenu = true;
                    		while(subMenu) {
                    			if (eleccion == 1) {
                                	Funciones.agregarPc();

                        		}
                        		if (eleccion == 2) {
                        			Funciones.eliminar();
                        		}
                        		if (eleccion == 3) {
                        			subMenu = false;
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
                		eleccion = input.nextInt();
                		input.nextLine();
                		
                		if (eleccion == 1) {
                			menuUsuario.verListaPCS();
                		}
                		if (eleccion == 2) {
                			menuUsuario.escanerPC();
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
