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
			System.out.println("Menu Admin (1)");
			System.out.println("Menu Usuario (2)");
			System.out.print("Ingrese su opcion: ");
			teclado = scan.nextInt();
			
			if (teclado != 1 && teclado != 2) {                          //Agregue este if pq o si no me da toc (CHENTE)
				System.out.println("Ingrese una opcion valida.");
				System.out.println("");
			}

			
		} while (teclado != 1 && teclado != 2);
		
		//menu de Usuario
		if (teclado == 2) {         //Cambie a 2 el usuario porque eso sale arriba.
			System.out.println("Entro");
			File arch = new File("archivos/experimentos.txt");
			Scanner lector = new Scanner(arch);
			int contadorExperimientos = 0;   //Cambie de "Experminetos" a Experimentos (CHENTE)
			
			//lei todo el archivo para saber la cantidad de experimentos que hay y de ahi hacer la lista
			while (lector.hasNextLine()) {
				contadorExperimientos += 1;
				lector.nextLine();
			}
			lector.close();
			String[] expermientos = new String[contadorExperimientos];
			String[] descripcionExp = new String[contadorExperimientos];  //Nueva lista para tener la descripcion de los experimentos (CHENTE)
			
			// aca agrego los nombre a una lista 
			Scanner leer = new Scanner(arch);
			for (int i = 0; i < contadorExperimientos;i++) {
				String linea = leer.nextLine();
				String[] partes = linea.split(";");
				
				String descripcionExperimentos = partes[1];
				String id = partes[0];
				expermientos[i] = id;
				descripcionExp[i] = descripcionExperimentos;
				
				
			}
			// 1) mostrar los experimentos
			for (int i = 0; i < contadorExperimientos; i++) {
			    System.out.println(expermientos[i] + " " + descripcionExp[i]);
			}
			
			//pregunto cual experimento quiere ver
			Scanner leer2 = new Scanner(System.in);
			int expVer = 0;
			boolean seguirViendo = true;  //Va a servir para ver si parar el codigo o si quiere seguir viendo mas experimentos (CHENTE)
			// con esta respuesta debes leer el arch de predicciones y de ahi ir comparando cuales 
			//son los TP,FN,FP,TN
			
			while (seguirViendo == true) {
				do {                    //Va a servir para que de una opcion valida. (CHENTE)
					System.out.print("¿Cual experimento desea ver? (1,2,3,4) : ");
					expVer = leer2.nextInt();
					if (expVer != 1 && expVer != 2 && expVer != 3 && expVer != 4) {
						System.out.println("Ingrese una opcion valida.");
						System.out.println("");
					}
					
				} while (expVer != 1 && expVer != 2 && expVer != 3 && expVer != 4);
				
				//Leer archivo de predicciones (CHENTE)
				File archPredicciones = new File("archivos/predicciones.txt");
				Scanner lectorPredicciones = new Scanner(archPredicciones);
				
				int TP = 0, FP = 0, TN= 0, FN= 0; //Valores a calcular del experimento seleccionado (CHENTE)
				int allTP = 0, allFP = 0, allTN = 0, allFN = 0;
				double[] accuracies = new double[contadorExperimientos+1]; //Sirve para calcular el promedio(CHENTE)
				
				int contador = 0; //Contador que sirve para calcular el promedio de acurracies
				int aux = 1;
				
				
				while (lectorPredicciones.hasNextLine()) {
					String linea = lectorPredicciones.nextLine();
					String[] partes = linea.split(";");
					String[] separadorExp = partes[0].split("p"); //Sirve para tener directamente el numero del experimento (CHENTE)
					
					
					
					int idExp = Integer.valueOf(separadorExp[1]);
					int real = Integer.valueOf(partes[1]);
					int predicho = Integer.valueOf(partes[2]);
					
					if (idExp == expVer) { //Ver los valores de TP,FN,FP,TN (CHENTE)
						if (real == 1 && predicho == 1) TP++;
				        else if (real == 0 && predicho == 1) FP++;
				        else if (real == 0 && predicho == 0) TN++;
				        else if (real == 1 && predicho == 0) FN++;
					
					}
					if (idExp == aux) {
						if (real == 1 && predicho == 1) allTP++;
				        else if (real == 0 && predicho == 1) allFP++;
				        else if (real == 0 && predicho == 0) allTN++;
				        else if (real == 1 && predicho == 0) allFN++;
					}
					else if (aux != idExp) {
						aux += 1;
						accuracies[contador] = (double)(allTP + allTN) / (allTP + allFP + allTN + allFN);
						contador += 1;
						allTP = 0; allFP = 0; allTN = 0; allFN = 0;
						System.out.println(accuracies[contador-1]);
					}
					
					
					
				}
                lectorPredicciones.close();
                
                
                
				//Mostar la matriz de confusion de experimentos (CHENTE)
				
				System.out.println("Matriz de confusion para experimento "+ expVer + ":");
				System.out.println("TP = " + TP + " | FP = " + FP);
				System.out.println("TN = " + TN + " | FN = " + FN);
				
				//Ver las metricas del experimento (CHENTE)
				double Acurracy = 0, Precision = 0, Recall = 0, F1Score = 0, allAcurracy = 0;
				
				//Formulas para las metricas del experimento (CHENTE)
				Acurracy = (double)(TP + TN) / (TP + FP + TN + FN);
				Precision = (double)TP / (TP + FP);
				Recall = (double)TP / (TP + FN);
				F1Score = 2 * (double)(Precision * Recall) / (Precision + Recall);
				
				for(int i = 0;i<5;i++) {
					allAcurracy += accuracies[i];
				}
				allAcurracy = (double) allAcurracy/4;
				
				
				//FALTA ARREGLAR ESTA PARTE DE ARRIBA (CHENTE)
				
				//Printear metricas del experimento (CHENTE)
				System.out.println("Metricas del experimento: ");
				System.out.println("Acurracy = " + Acurracy);
				System.out.println("Precision = " + Precision);
				System.out.println("Recall = " + Recall);
				System.out.println("F1-Score = " + F1Score);
				
				//LOS PRINTS NO SON CORRECTOS FALTAN ARREGLARLOS (CHENTE)
				
				//Printear el promedio de todos los modelos (CHENTE)
				System.out.println("Promedio de acurracy en todos los modelos = "+ allAcurracy);
				
				//Preguntar si quiere seguir viendo experimentos al usuario (CHENTE)
				Scanner menuUsuario = new Scanner(System.in);
				String seguirViendoString = "";
				do {
					System.out.print("¿Desea seguir viendo experimentos? (si o no) : ");
					seguirViendoString = menuUsuario.nextLine();
					seguirViendoString = seguirViendoString.toLowerCase();
					seguirViendoString = seguirViendoString.strip();
					
					if (!seguirViendoString.equals("si") && !seguirViendoString.equals("no")) { //El equals es para comparar strings
							System.out.println("Ingrese un si o un no");
							System.out.println("");
					}
				} while (!seguirViendoString.equals("si") && !seguirViendoString.equals("no"));
				menuUsuario.close();
				if (seguirViendoString.equals("no")) {
					seguirViendo = false;
				}
				else seguirViendo = true;
				
			}
			
			
			
			
			
			
			
			
			
			leer.close();
			lector.close();
		}
		
		if (teclado == 1) {
			//verifico por si hay un experimento que tenga valor 0 y no se contabilice en usuario 
			File arch = new File("archivos/experimentos.txt");
			Scanner lector = new Scanner(arch);
			int contador = 0; 
			while(lector.hasNextLine()) {
				contador += 1;
				lector.nextLine();
				}
			//fila = experimentos columnas = metricas (Accuracy,Precision,Recall,F1-Score)
			double[][] matriz = new double[contador][4];
			
			File archMetricas = new File("archivos/predicciones.txt");
			Scanner lectorMetricas = new Scanner(archMetricas);
			
			int TP = 0;
			int FP = 0;
			int TN = 0;
			int FN = 0;
			
			
			while (lectorMetricas.hasNextLine()) {
				String linea = lectorMetricas.nextLine();
				String[] partes = linea.split(";");
				String exp = partes[0];
				int real = Integer.valueOf(partes[1]);
				int predicho = Integer.valueOf(partes[2]);
				
				if (real == 1 && predicho == 1) TP++;
				else if (real == 0 && predicho == 1) FP++;
				else if (real == 0 && predicho == 0) TN++;
				else if (real == 1 && predicho == 0) FN++;
				
				
				
			}
			
			
			
			lector.close();
			
			
			 
			
			
			
		}
		
	

	}

}
