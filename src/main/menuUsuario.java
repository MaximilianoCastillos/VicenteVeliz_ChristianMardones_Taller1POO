package main;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class menuUsuario {
	
	Scanner input = new Scanner(System.in);
	
	public static void printMenu() {
		System.out.println(" ");
		System.out.println("1) Ver lista de PCs");
		System.out.println("2) Scanear un pc");
		System.out.println("3) Ver total de puertos abiertos ");
		System.out.println("4) Ordenar PCs segun ip");
		System.out.println("5) Salir");
		System.out.print("Ingrese una opción: ");
	}
	
	public static void verListaPCS() throws FileNotFoundException {
		System.out.println("-------- LISTA DE PC'S -------- ");
		for (PC p : Funciones.listaPCs) {
			System.out.println("PC " + p.getId() + "|" + "IP = " + p.getIp() + "|" +   "SO = " + p.getSo());
		}
		System.out.println("");
	}
	public static void escanerPC() {
		
		System.out.println("--------- ESCANEAR PC'S --------- ");
		for (PC p : Funciones.listaPCs) {
			System.out.println("PC " + p.getId() + "|" + "IP = " + p.getIp() + "|" +   "SO = " + p.getSo());
		}
		System.out.println("");
		System.out.print("Seleccione un PC mediante su ID");
	}
}
