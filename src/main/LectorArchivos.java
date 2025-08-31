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
		lector.close();	
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
		lector.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void LeePrediciones() {
		try {
			File archivosExperimento = new File("archivos/predicciones.txt");
			Scanner lector = new Scanner(archivosExperimento);
			
			while (lector.hasNextLine()) {
				
				String linea = lector.nextLine();
				System.out.println(linea);
				
			}
		lector.close();	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void LeerVerificaciones() {
		try {
			File archivosVerificaciones = new File("archivos/verificacion_docente_confusiones.csv");
			Scanner lector = new Scanner(archivosVerificaciones);
			
			while (lector.hasNextLine()) {
				
				String linea = lector.nextLine();
				System.out.println(linea);
				
			}
		lector.close();	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		
	}
}

