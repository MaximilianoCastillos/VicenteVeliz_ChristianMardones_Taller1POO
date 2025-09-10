package main;

import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

public class Taller_1 {

	public static void main(String[] args) throws Exception {
		//Pregunta si accedera al menu admin o usuarío
		Scanner scan = new Scanner(System.in);
		
		
		int teclado = 0;
		//hasta que tire un valor correcto 
		do {
			System.out.println("menu Admin(1)");
			System.out.println("menu Usuario(2)");
			System.out.print("ingrese su opcion: ");
			teclado = scan.nextInt();

			
		} while (teclado != 1 && teclado != 2);
		
		//menu de Usuario
		if (teclado == 1) {
			System.out.println("entro");
			File arch = new File("archivos/experimentos.txt");
			Scanner lector = new Scanner(arch);
			int contadorExpermientos = 0;
			
			//lei todo el archivo para saber la cantidad de experimentos que hay y de ahi hacer la lista
			while (lector.hasNextLine()) {
				contadorExpermientos += 1;
				lector.nextLine();
			}
			lector.close();
			String[] expermientos = new String[contadorExpermientos];
			// aca agrego los nombre a una lista 
			Scanner leer = new Scanner(arch);
			for (int i = 0; i < contadorExpermientos;i++) {
				String linea = leer.nextLine();
				String[] partes = linea.split(";");
				
				String id = partes[0];
				expermientos[i] = id;
				
			}
			// 1) mostrar los experimentos
			for (int i = 0; i < contadorExpermientos; i++) {
			    System.out.println(expermientos[i]);
			}
			
			//pregunto cual experimento quiere ver
			System.out.print("cual experimento desea ver???: ");
			Scanner leer2 = new Scanner(System.in);
			String expVer = leer2.nextLine();
			// con esta respuesta debes leer el arch de predicciones y de ahi ir comparando cuales 
			//son los TP,FN,FP,TN
			
			
			
			
			
			
			
			
			
			
			leer.close();
			lector.close();
		}
		
	

	}

}
