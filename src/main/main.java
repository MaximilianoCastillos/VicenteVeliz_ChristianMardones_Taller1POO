package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class main {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Funciones.cargarVulnerabilidades();
        Funciones.cargarPc();
        Funciones.cargarPuertos();
		boolean seguirViendo = true;
		Scanner scan = new Scanner(System.in);
		int input;
		while (seguirViendo) {
			System.out.println("1) Menu Admin");
			System.out.println("2) Menu Usuario");
			System.out.println("3) salir");

			System.out.print("ingrese que desea ver: ");
			input = scan.nextInt();
			
			if (input == 1) {
				boolean seguirViendoAdmin = true;
				while (seguirViendoAdmin) {
					System.out.println(" ");
					System.out.println("1) ver lista completa de PCs: ");
					System.out.println("2) AGREGAR O ELIMINAR: ");
					System.out.println("3) clasificar PCs segun Nivel: ");
					System.out.println("4) salir: ");
					input = scan.nextInt();
					if (input == 1) {
						Funciones.listaPC();
						
					}
					if (input == 2) {
						boolean verSubMenu = true;
						while (verSubMenu) {
							System.out.println("1) Agregar un nuevo PC");
                            System.out.println("2) Eliminar un PC por ID");
                            System.out.println("3) Volver al Menú Admin");
                            System.out.print("Ingrese una opción: ");
                            input = scan.nextInt();
                            System.out.println(" ");
                            
                            
                            
                            if (input == 1) {
                            	Funciones.agregarPc();
                            }
                            if (input == 2 ) {
                            	Funciones.eliminar();
                            }
                            if (input == 3) {
                            	verSubMenu = false;
                            }

						}
						
					}
					if (input == 3) {
						Funciones.clasificarRiesgo();
					}
					if (input == 4) {
						seguirViendoAdmin = false;
					}
					


				}	
			}
			if (input == 2) {
				boolean seguirViendoUsuario = true;
				while (seguirViendoUsuario) {
					menuUsuario.printMenu();
					input = scan.nextInt();
					
					if (input == 1) {
						menuUsuario.verListaPCS();
					}
					if (input == 2) {
					}
				}
			}
		}
	}
	
	//metodos
	
	
}
