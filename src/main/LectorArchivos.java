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
				String[] partes = linea.split(";");
				
				String id = (partes[0]);
				String descripcion = partes[1];
				
				
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
				String metricas = linea;
				
				
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
				String [] partes = linea.split(";");
				
				String id2 = partes[0];
				int valor1 = Integer.valueOf(partes[1]);
				
				
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
			
			String linea = lector.nextLine();
			
			
			
			
			while (lector.hasNextLine()) {
				
				String linea2 = lector.nextLine();
				String[] partes = linea2.split(",");
				
				String experimento = partes[0];
				int tp = Integer.valueOf(partes[1]);
				int fp = Integer.valueOf(partes[2]);
				int tn = Integer.valueOf(partes[3]);
				int fn = Integer.valueOf(partes[4]);
				
				
				
				
				
				
			}
		lector.close();	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		
	}
}

