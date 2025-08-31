package main;

import java.io.File;
import java.util.Scanner;

public class LectorArchivos {
	public void LeerExperimentos() {
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
	public void LeerMetricas() {
		try {
			File archivosMetricas = new File("archvios/metricas.txt");
			Scanner lector = new Scanner(archivosMetricas);
			
			while (lector.hasNextLine()) {
				
				String linea = lector.nextLine();
				System.out.println(linea);
				
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
