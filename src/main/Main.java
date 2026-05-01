//Nombre: Vicente Alonso Veliz Veliz | Rut: 22.012.230-1 | Carrera: Ingeneria Civil en Informatica
//Nombre: Christian Jovanny Mardones Lopez | Rut: 22.026.428-9 | Carrera: Ingeneria Civil en Informatica
package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static ArrayList<Pokemon> pokedex = new ArrayList<>();
	public static ArrayList<Habitat> habitats = new ArrayList<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		leerPokedex();
		
	}
	
	public static void leerHabitat() throws FileNotFoundException {
		File arch = new File("habitats/pokedex.txt");
		Scanner scan = new Scanner(arch);
		
		while(scan.hasNextLine()) {
			String nombre = scan.nextLine();
			
			Habitat h = new Habitat(nombre);
			
		}
	}
	
	public static void leerPokedex() throws FileNotFoundException {
		File arch = new File("archivos/pokedex.txt");
		Scanner scan = new Scanner(arch);
		
		while(scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String nombrePokemon = partes[0];
			String habitat = partes[1];
			double aparicion = Double.valueOf(partes[2]);
			int vida = Integer.valueOf(partes[3]);
			int ataque = Integer.valueOf(partes[4]);
			int defensa = Integer.valueOf(partes[5]);
			int ataqueEspecial = Integer.valueOf(partes[6]);
			int defensaEspecial = Integer.valueOf(partes[7]);
			int velocidad = Integer.valueOf(partes[8]);
			String tipo = partes[9];
			
			Pokemon p = new Pokemon(nombrePokemon,habitat,aparicion,vida,ataque,defensa,ataqueEspecial,defensaEspecial,velocidad,tipo);
			pokedex.add(p);
						
			
			
		}		
		
	}
}
	