package main;

import java.util.Scanner;

public class Taller1Real {

	public static void main(String[] args) {
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
				boolean seguirViendoUsuario = true;
				
				while (seguirViendoUsuario) {
					System.out.println("1) Ver lista de experimentos");
					System.out.println("2) Mostrar matriz de confusión de un experimento");
					System.out.println("3) Ver métricas de un experimento");
					System.out.println("4) Ver promedio de Accuracy de todos los modelos");
					System.out.println("5) Salir menu de usuario");
					
					teclado = input.nextInt();
					
					if (teclado == 1) {
						
					}
					if (teclado == 2) {
						
					}
					if (teclado == 3) {
						System.out.println("prueba");
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
