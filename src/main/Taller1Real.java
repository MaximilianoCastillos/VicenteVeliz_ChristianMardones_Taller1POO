package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Taller1Real {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		boolean seguirViendo = true;
		
		while (seguirViendo) {
			int teclado = 0;
			//hasta que tire un valor correcto 
			do {
				System.out.println("Menu Admin (1)");
				System.out.println("Menu Usuario (2)");
				System.out.println("Salir (3)");
				System.out.print("Ingrese su opcion: ");
				teclado = input.nextInt();
				
				if (teclado != 1 && teclado != 2 && teclado != 3) {
					System.out.println("Ingrese una opcion valida.");
					System.out.println("");
				}

				
			} while (teclado != 1 && teclado != 2 && teclado != 3);
			
			input.nextLine();
			
			if (teclado == 2) {
				boolean seguirViendoUsuario = true; //
				
				while (seguirViendoUsuario) {
					System.out.println("1) Ver lista de experimentos");
					System.out.println("2) Mostrar matriz de confusión de un experimento");
					System.out.println("3) Ver métricas de un experimento");
					System.out.println("4) Ver promedio de Accuracy de todos los modelos");
					System.out.println("5) Salir menu de usuario");
					
					System.out.print("Ingrese una Opcion: ");
					teclado = input.nextInt();
					
					if (teclado == 1) {
						File arch = new File("archivos/experimentos.txt");
						Scanner scan = new Scanner(arch);
						int contador = 0;
						
						while (scan.hasNextLine()) {
							contador += 1;
							scan.nextLine();
						}
						
						
						String[] experimentos = new String[contador];
						String[] descripcionExp = new String[contador];
						
						
						Scanner leerArchivo = new Scanner(arch);
						
						
						for (int i = 0; i < contador; i++) {
							String linea = leerArchivo.nextLine();
							String[] partes = linea.split(";");
							String id = partes[0];
							String texto = partes[1];
							experimentos[i] = id;
							descripcionExp[i] = texto;	
						}
						
						for (int i = 0; i < contador;i++) {
							System.out.println(experimentos[i] +  ": " + descripcionExp[i]);
							
						}
						scan.close();
						System.out.println("-----------");
					}
					if (teclado == 2) {
						input.nextLine(); //
						//Contar cuantos experimentos hay
						File archExperimentos = new File("archivos/experimentos.txt");
						Scanner inputExp = new Scanner(archExperimentos);
						
						int contadorExperimentos = 0;
						
						while (inputExp.hasNextLine()) {
							contadorExperimentos++;
							inputExp.nextLine();
						}
						inputExp.close();
						
						//Crear listas
						
						int[] TP = new int[contadorExperimentos];
					    int[] FP = new int[contadorExperimentos];
					    int[] TN = new int[contadorExperimentos];
					    int[] FN = new int[contadorExperimentos];
						
					    //Leer las predicciones
					    
						File archPredicciones = new File("archivos/predicciones.txt"); //
						Scanner inputPredicciones = new Scanner(archPredicciones); //
						
						
						while (inputPredicciones.hasNextLine()) {
							String linea = inputPredicciones.nextLine();
					        String[] partes = linea.split(";");
					        String[] separadorExp = partes[0].split("p");
					        
					        int idExp = Integer.valueOf(separadorExp[1]) - 1;
					        
					        int real = Integer.valueOf(partes[1]);
					        int predicho = Integer.valueOf(partes[2]);
					        
					        if (real == 1 && predicho == 1) TP[idExp]++;
					        else if (real == 0 && predicho == 1) FP[idExp]++;
					        else if (real == 0 && predicho == 0) TN[idExp]++;
					        else if (real == 1 && predicho == 0) FN[idExp]++;
					        
						}
						inputPredicciones.close();
						
						//Imprimir
						
						for (int i = 0; i < contadorExperimentos; i++) {
					        System.out.println("Matriz de confusión - Experimento " + (i+1));
					        System.out.println("TP = " + TP[i] + " | FP = " + FP[i]);
					        System.out.println("TN = " + TN[i] + " | FN = " + FN[i]);
					        System.out.println("-----------------");
						}
						
					}
					if (teclado == 3) {
						//Contar cuantos experimentos hay
						File archExperimentos = new File("archivos/experimentos.txt");
						Scanner inputExp = new Scanner(archExperimentos);
						
						int contadorExperimentos = 0;
						
						while (inputExp.hasNextLine()) {
							contadorExperimentos++;
							inputExp.nextLine();
						}
						inputExp.close();

						//Crear listas
						
						int[] TP = new int[contadorExperimentos];
					    int[] FP = new int[contadorExperimentos];
					    int[] TN = new int[contadorExperimentos];
					    int[] FN = new int[contadorExperimentos];
						
					    //Leer las predicciones
					    
						File archPredicciones = new File("archivos/predicciones.txt"); //
						Scanner inputPredicciones = new Scanner(archPredicciones); //
						
						
						while (inputPredicciones.hasNextLine()) {
							String linea = inputPredicciones.nextLine();
					        String[] partes = linea.split(";");
					        String[] separadorExp = partes[0].split("p");
					        
					        int idExp = Integer.valueOf(separadorExp[1]) - 1;
					        
					        int real = Integer.valueOf(partes[1]);
					        int predicho = Integer.valueOf(partes[2]);
					        
					        if (real == 1 && predicho == 1) TP[idExp]++;
					        else if (real == 0 && predicho == 1) FP[idExp]++;
					        else if (real == 0 && predicho == 0) TN[idExp]++;
					        else if (real == 1 && predicho == 0) FN[idExp]++;
					        
						}
						inputPredicciones.close();
						
						try {
							for (int i = 0; i < contadorExperimentos;i++ ) {
								
								
								double accuracy = (double)(TP[i] + TN[i])/(TP[i] + FP[i] + TN[i] + FN[i]);
								double precision = (double)(TP[i]) / (TP[i] + FP[i]);
								double recall = (double)(TP[i] + FN[i]);
								double f1Score = (double)(2*(precision * recall) / (precision + recall));
								
								System.out.println("Métricas - Experimento " + (i+1));
						        System.out.println("Accuracy = " + accuracy);
						        System.out.println("Precision = " + precision);
						        System.out.println("Recall = " + recall);
						        System.out.println("F1-Score = " + f1Score);
						        System.out.println("-----------------");
									
							}
								
						} catch (Exception e) {
							System.out.println(e);
						}
	
					}
					if (teclado == 4) {
						
					}
					if (teclado == 5) {
						
					}
					
				}
			}
		}
		
	}
}
