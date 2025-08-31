package main;

import java.io.File;
import java.util.Scanner;

public class Taller_1 {

	public static void main(String[] args) {
		try {
			File archivosExperimento = new File("archivos/experimentos.txt");
			Scanner lector = new Scanner(archivosExperimento);
			
			while (lector.hasNextLine()) {
				
				String linea = lector.nextLine();
				System.out.println(linea);
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		

	}

}
