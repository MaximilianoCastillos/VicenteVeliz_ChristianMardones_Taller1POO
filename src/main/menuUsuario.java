package main;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class menuUsuario {
	
	private static Scanner input = new Scanner(System.in);
	
	//Print menu de usuario
	public static void printMenu() {
		System.out.println(" ");
		System.out.println("1) Ver lista de PCs");
		System.out.println("2) Scanear un pc");
		System.out.println("3) Ver total de puertos abiertos ");
		System.out.println("4) Ordenar PCs segun ip");
		System.out.println("5) Salir");
		System.out.print("Ingrese una opción: ");
	}
	
	//Ver lista de PCS
	public static void verListaPCS() throws FileNotFoundException {
		System.out.println("-------- LISTA DE PC'S -------- ");
		for (PC p : Funciones.listaPCs) {
			System.out.println("PC " + p.getId() + "|" + "IP = " + p.getIp() + "|" +   "SO = " + p.getSo());
		}
		System.out.println("");
	}
	//Escanear PCS
	public static void escanerPC() {
		System.out.println("--------- ESCANEAR PC'S --------- ");
		for (PC p : Funciones.listaPCs) {
			System.out.println("PC " + p.getId() + "|" + "IP = " + p.getIp() + "|" +   "SO = " + p.getSo());
		}
		System.out.println("");
		System.out.print("Seleccione un PC mediante su ID: ");
		String idSeleccionado = input.nextLine().trim();
		
		//Seleccion de ID
		PC pcSeleccionado = null;
		for (PC p : Funciones.listaPCs) {
			if (p.getId().equalsIgnoreCase(idSeleccionado)) {
				pcSeleccionado = p;
				break;
			}
		}
		
		if (pcSeleccionado == null) {
			System.out.println("No se encontro nigun PC");
			return;
		}
		//Seleccion de ID
		
		//Mostar la informacion
		System.out.println("--- INFORMACIÓN DEL PC ---");
        System.out.println("ID: " + pcSeleccionado.getId());
        System.out.println("IP: " + pcSeleccionado.getIp());
        System.out.println("SO: " + pcSeleccionado.getSo());
        System.out.println("Puertos asociados:");
        
        int contadorVulenrabilidad = 0;
        int puertosAbiertos = 0;
        String detallePuertos = "";
        
        for (Puerto puerto : pcSeleccionado.getPuertos()) {
            System.out.println("- Puerto " + puerto.getNumPuerto() + " (" + puerto.getEstado() + ")");
            detallePuertos = detallePuertos + "- Puerto " + puerto.getNumPuerto() + " (" + puerto.getEstado() + ")\n";

            if (puerto.getEstado().equalsIgnoreCase("abierto")) {
                puertosAbiertos++;
            }

            Vulnerabilidad v = puerto.getVulnerabilidad();
            if (v != null) {
                contadorVulenrabilidad++;
                System.out.println(v.getNombre() + ": " + v.getDescripcion());
                detallePuertos = detallePuertos +" " + v.getNombre() + ": " + v.getDescripcion() + "\n";
            }
        }
        
        //Calcular riesgos
        String riesgo = "";
        if (contadorVulenrabilidad <= 1) {
            riesgo = "Bajo";
        } else if (contadorVulenrabilidad == 2) {
            riesgo = "Medio";
        } else {
            riesgo = "Alto";
        }

        System.out.println("Nivel de riesgo del PC: " + riesgo);
        System.out.println("Total de puertos abiertos: " + puertosAbiertos);
        
        //Crear archivo de reporte
        String reporte = "====================================\n";
        reporte += "PC: " + pcSeleccionado.getId() + " | IP: " + pcSeleccionado.getIp() + " | SO: " + pcSeleccionado.getSo() + "\n";
        reporte += "Nivel de riesgo: " + riesgo + "\n";
        reporte += "Puertos:\n" + detallePuertos;
        reporte += "====================================\n\n";
        
        try {
            FileWriter fw = new FileWriter("archivos/reportes.txt", true);
            fw.write(reporte);
            fw.close();
            System.out.println("Escaneo completado. Reporte guardado en archivos/reportes.txt");
        } catch (IOException e) {
            System.out.println("Error al guardar el reporte: " + e.getMessage());
        }
	}
	//Lista de puertos abiertos
	public static void puertosAbiertos() {
		System.out.println("------ PUERTOS ABIERTOS EN LA RED ------");
		
		int puertosAbiertos = 0;
		
		for (PC pc : Funciones.listaPCs) {
			for (Puerto puerto : pc.getPuertos()) {
				if (puerto.getEstado() != null && puerto.getEstado().strip().equalsIgnoreCase("abierto")) {
					puertosAbiertos++;
					
					String vulnerabilidad = "Ninguna";
					String descripcionVulnerabilidad = "";
					
					if (puerto.getVulnerabilidad() != null) {
						vulnerabilidad = puerto.getVulnerabilidad().getNombre();
						descripcionVulnerabilidad = puerto.getVulnerabilidad().getDescripcion();
					}
					
					//Imprimir puertos
					System.out.println("PC: " + pc.getId() + " | IP: " + pc.getIp()
                    + " | Puerto: " + puerto.getNumPuerto()
                    + " | Vulnerabilidad: " + vulnerabilidad);
					
					if (!descripcionVulnerabilidad.equals("")) {
	                    System.out.println("    Descripción: " + descripcionVulnerabilidad);
					}
				}
			}
		}
		if (puertosAbiertos == 0) {
			System.out.println("No se encontraron puertos conectados.");
		}
		System.out.println("");
		System.out.println("Total de puertos abiertos en la red: " + puertosAbiertos);
		System.out.println("");
	}
	//Ordenar PCS por ips
	public static void ordenarPCsPorClaseIP() {
	    System.out.println("------ ORDENANDO PCs POR CLASE DE IP ------");

	    List<PC> claseA = new ArrayList<>();
	    List<PC> claseB = new ArrayList<>();
	    List<PC> claseC = new ArrayList<>();
	    List<PC> desconocida = new ArrayList<>();

	    for (PC pc : Funciones.listaPCs) {
	        String clase = Funciones.obtenerClaseIP(pc.getIp());

	        if (clase.equals("A")) {
	            claseA.add(pc);
	        } else if (clase.equals("B")) {
	            claseB.add(pc);
	        } else if (clase.equals("C")) {
	            claseC.add(pc);
	        } else {
	            desconocida.add(pc);
	        }
	    }

	    // Mostrar ordenados por clase
	    System.out.println("\nClase A:");
	    if (claseA.isEmpty()) System.out.println("   (Sin PCs)");
	    for (PC pc : claseA) {
	        System.out.println("   PC " + pc.getId() + " | IP: " + pc.getIp() + " | SO: " + pc.getSo());
	    }

	    System.out.println("\nClase B:");
	    if (claseB.isEmpty()) System.out.println("   (Sin PCs)");
	    for (PC pc : claseB) {
	        System.out.println("   PC " + pc.getId() + " | IP: " + pc.getIp() + " | SO: " + pc.getSo());
	    }

	    System.out.println("\nClase C:");
	    if (claseC.isEmpty()) System.out.println("   (Sin PCs)");
	    for (PC pc : claseC) {
	        System.out.println("   PC " + pc.getId() + " | IP: " + pc.getIp() + " | SO: " + pc.getSo());
	    }

	    if (!desconocida.isEmpty()) {
	        System.out.println("\nClases desconocidas:");
	        for (PC pc : desconocida) {
	            System.out.println("   PC " + pc.getId() + " | IP: " + pc.getIp());
	        }
	    }

	    System.out.println("\nPCs mostrados por clase de IP.");
	    System.out.println("");
	}
}
