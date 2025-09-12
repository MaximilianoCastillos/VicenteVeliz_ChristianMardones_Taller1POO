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
				System.out.println();
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
			
			if (teclado == 3) {
				seguirViendo = false;
			}
			
			
			if (teclado == 1) {
				boolean seguirViendoAdmin = true;
				while (seguirViendoAdmin) {
					System.out.println();
					System.out.println("MENU ADMIN");
					System.out.println("1) Ver la matriz completa de métricas");
					System.out.println("2) Identificar el experimento con mejor F1-Score ");
					System.out.println("3) Calcular promedio global de cada métrica");
					System.out.println("4) Comparar dos experimentos lado a lado");
					System.out.println("5) (EXTRA) Comparar CSV con matriz de confusión generada ");
					System.out.println("6) Salir");
					System.out.print("Ingrese una opcion: ");
					
					teclado = input.nextInt();
					
					
					if (teclado == 1) {
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
							System.out.println(" ");
							System.out.println("--------- MATRIZ DE METRICAS ---------");
							System.out.println("EXPERIMENTOS | ACCURACY | PRECISION | RECALL | F1-SCORE");
							System.out.println("---------------------------------------------------");
							for (int i = 0; i < contadorExperimentos;i++ ) {
								
								double accuracy = (double)(TP[i] + TN[i])/(TP[i] + FP[i] + TN[i] + FN[i]);
								double precision = (double)(TP[i]) / (TP[i] + FP[i]);
								double recall = (double)(TP[i] + FN[i]);
								double f1Score = (double)(2*(precision * recall) / (precision + recall));
								
								accuracy = Math.round(accuracy * 100.0) / 100.0;
								precision = Math.round(precision * 100.0) / 100.0;
								recall = Math.round(recall * 100.0) / 100.0;
								f1Score = Math.round(f1Score * 100.0) / 100.0;
								
								System.out.println("Exp "+(i+1) + "        |  " + accuracy + "    |  " + precision + "     |  " + recall + "  |  " + f1Score  );
								
							}
								
						} catch (Exception e) {
							System.out.println(e);
						}
						System.out.println("---------------------------------------------------");
						System.out.println(" ");
					}
					if (teclado == 2) {
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
						
						int id = 0;
						double mayor = -1;
						
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
								
								if (f1Score > mayor ) {
									mayor = f1Score;
									id = i + 1;
								}
								
							}
							
							System.out.println("el ID del F1-Score mayor es: " + id + " con un valor de: " + Math.round(mayor * 100.0) / 100.0);
								
						} catch (Exception e) {
							System.out.println(e);
						}
						
						System.out.println("---------------------------------------------------");
						System.out.println(" ");				}
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
						
						double promedioAccurracy = 0, promedioPrecision = 0, promedioRecall = 0, promedioF1Score = 0;
						
						try {
							for (int i = 0; i < contadorExperimentos;i++ ) {
								
								double accuracy = (double)(TP[i] + TN[i])/(TP[i] + FP[i] + TN[i] + FN[i]);
								promedioAccurracy += accuracy;
								double precision = (double)(TP[i]) / (TP[i] + FP[i]);
								promedioPrecision += precision;
								double recall = (double)(TP[i] + FN[i]);
								promedioRecall += recall;
								double f1Score = (double)(2*(precision * recall) / (precision + recall));
								promedioF1Score += f1Score;
								
							}
								
						} catch (Exception e) {
							System.out.println(e);
						}
						
						promedioAccurracy = (double) promedioAccurracy/contadorExperimentos;
						promedioAccurracy = Math.round(promedioAccurracy * 100.0) / 100.0;
						promedioPrecision = (double) promedioPrecision/contadorExperimentos;
						promedioPrecision = Math.round(promedioPrecision * 100.0) / 100.0;
						promedioRecall = (double) promedioRecall/contadorExperimentos;
						promedioRecall = Math.round(promedioRecall * 100.0) / 100.0;
						promedioF1Score = (double) promedioF1Score/contadorExperimentos;
						promedioF1Score = Math.round(promedioF1Score * 100.0) / 100.0;
						
						System.out.println(" ");
						System.out.println("----------- PROMEDIO GLOBAL -----------");
						System.out.println("Promedio global de Accuracy = "+ promedioAccurracy);
						System.out.println("Promedio global de Precision = "+ promedioPrecision);
						System.out.println("Promedio global de Recall = "+ promedioRecall);
						System.out.println("Promedio global de F1-Score = "+ promedioF1Score);
						System.out.println("---------------------------------------");
						System.out.println(" ");
						
						
						
						
					}
					if (teclado == 4) {
						File archExperimentos = new File("archivos/experimentos.txt");
						Scanner lectorExperimentos = new Scanner (archExperimentos);
						int contador = 0;
						
						
						while (lectorExperimentos.hasNextLine()) {
							contador += 1;
							lectorExperimentos.nextLine();
						}
						lectorExperimentos.close();
						
						
						int[] TP = new int[contador];
					    int[] FP = new int[contador];
					    int[] TN = new int[contador];
					    int[] FN = new int[contador];
					    
					    File archPredicciones = new File("archivos/predicciones.txt");
					    Scanner lectorPredicciones = new Scanner(archPredicciones);
						
					    while (lectorPredicciones.hasNextLine()) {
					        String linea = lectorPredicciones.nextLine();
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
					    lectorPredicciones.close();
					    
						Scanner lector = new Scanner(System.in);
					    
					    int experimento1;
					    int experimento2;
					    
					    do {
					    	System.out.print("ingrese primer experimento: ");
						    experimento1 = lector.nextInt() -1;
						    System.out.print("ingrese segundo experimento: ");
						    experimento2 = lector.nextInt() - 1;
							
					    	
					    	
						} while (experimento1 < 0|| experimento1 >= contador || experimento2 < 0|| experimento2 >= contador);
						
					    System.out.println(" ");
				        System.out.println("Métrica       | Exp" + (experimento1+1) + " | Exp" + (experimento2+1));
				        System.out.println("-------------------------------------------");
				        
				        double accuracy = (double)(TP[experimento1] + TN[experimento1])/(TP[experimento1] + FP[experimento1] + TN[experimento1] + FN[experimento1]);
				        double precision = (double)(TP[experimento1])/(TP[experimento1] + FP[experimento1]);
				        double recall = (double)(TP[experimento1])/(TP[experimento1] + FN[experimento1]);
				        double f1Score = 2 * (precision * recall) / (precision + recall);
				        
				        double accuracy2 = (double)(TP[experimento2] + TN[experimento2])/(TP[experimento2] + FP[experimento2] + TN[experimento2] + FN[experimento2]);
				        double precision2 = (double)(TP[experimento2])/(TP[experimento2] + FP[experimento2]);
				        double recall2 = (double)(TP[experimento2])/(TP[experimento2] + FN[experimento2]);
				        double f1Score2 = 2 * (precision2 * recall2) / (precision2 + recall2);
				        
				        System.out.println("Accuracy      | " + accuracy + " | " + accuracy2);
				        System.out.println("Precision     | " + precision + " | " + precision2);
				        System.out.println("Recall        | " + recall + " | " + recall2);
				        System.out.println("F1-Score      | " + f1Score + " | " + f1Score2);
				        
						
					}
					if (teclado == 5) {
						//Contar cuantos experimentos hay
					    File archExperimentos = new File("archivos/experimentos.txt");
					    Scanner inputExp = new Scanner(archExperimentos);
					    int contadorExperimentos = 0;
					    while (inputExp.hasNextLine()) {
					        contadorExperimentos++;
					        inputExp.nextLine();
					    }
					    inputExp.close();

					    //Crear listas para matriz
					    int[] TP = new int[contadorExperimentos];
					    int[] FP = new int[contadorExperimentos];
					    int[] TN = new int[contadorExperimentos];
					    int[] FN = new int[contadorExperimentos];

					    //Leer las predicciones para calcular la matriz
					    File archPredicciones = new File("archivos/predicciones.txt");
					    Scanner inputPredicciones = new Scanner(archPredicciones);
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

					    //Leer el CSV de verificación
					    File archCSV = new File("archivos/verificacion_docente_confusiones.csv");
					    Scanner lectorCSV = new Scanner(archCSV);
					    lectorCSV.nextLine();

					    int index = 0;
					    boolean todoBien = true;

					    while (lectorCSV.hasNextLine()) {
					        String linea = lectorCSV.nextLine();
					        String[] partes = linea.split(",");
					        
					        String exp = partes[0];
					        int tpOficial = Integer.valueOf(partes[1]);
					        int fpOficial = Integer.valueOf(partes[2]);
					        int tnOficial = Integer.valueOf(partes[3]);
					        int fnOficial = Integer.valueOf(partes[4]);

					        if (tpOficial == TP[index] && fpOficial == FP[index] && tnOficial == TN[index] && fnOficial == FN[index]) {
					            System.out.println(" ");
					        	System.out.println(exp + " Coincide con la verificación oficial.");
					        } else {
					            System.out.println(exp + "No coincide con la verificación oficial.");
					            System.out.println("Calculos: TP = " + TP[index] + ", FP = " + FP[index] + ", TN = "+ TN[index] + ", FN = "+ FN[index] );
					            System.out.println("Oficial: TP = " + tpOficial + ", FP = " + fpOficial + ", TN = "+ tnOficial + ", FN = "+ fnOficial);
					            todoBien = false;
					        }

					        index++;
					    }
					    lectorCSV.close();

					    if (todoBien) {
					    	System.out.println(" ");
					    	System.out.println("Todas las matrices coinciden con el archivo de verificacion.");
					    } 
					    else {
					    	System.out.println(" ");
					    	System.out.println("Existen diferencias con la verificación oficial.");
					    }
					    System.out.println(" ");
				
				}
				if (teclado == 6) {
					seguirViendoAdmin = false;
				}
			}
		}
				
			if (teclado == 2) {
				boolean seguirViendoUsuario = true; 
				
				while (seguirViendoUsuario) {
					System.out.println();
					System.out.println("MENU USUARIO");
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
						
						
						Scanner usuario = new Scanner(System.in);
						
						int mostrar;
						
						 do {
							System.out.print("ingrese experimento a ver: ");
							mostrar = usuario.nextInt();
								
						    	
						    	
							} while (mostrar <= 0|| mostrar >= contadorExperimentos || mostrar < 0|| mostrar >= contadorExperimentos);
						 
						
						
						for (int i = 0; i < 1; i++) {
					        System.out.println(" ");
							System.out.println("Matriz de confusión - Experimento " + (mostrar));
					        System.out.println("TP = " + TP[mostrar-1] + " | FP = " + FP[mostrar-1]);
					        System.out.println("TN = " + TN[mostrar -1 ] + " | FN = " + FN[mostrar-1]);
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
						
						double promedio = 0;
						
						
						try {
							for (int i = 0; i < contadorExperimentos;i++ ) {
								
								double accuracy = (double)(TP[i] + TN[i])/(TP[i] + FP[i] + TN[i] + FN[i]);
								
								promedio += accuracy;
							
							}
								
						} catch (Exception e) {
							System.out.println(e);
						}
						
						promedio = (double) promedio/contadorExperimentos;
						
						System.out.println(" ");
						System.out.println("------------------");
						System.out.println("El promedio de accuracy de todos los experimentos es = " + promedio);
						System.out.println("------------------");
						System.out.println(" ");
						
						
					}
					if (teclado == 5) {
						
						seguirViendoUsuario = false;						
						
					}													
				}
			}
		}
		input.close();
	}
}