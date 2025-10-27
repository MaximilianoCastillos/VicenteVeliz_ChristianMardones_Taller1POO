package main;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Funciones.cargarUsuarios();
		Funciones.cargarProyectos();
		Funciones.cargarTareas();
	}

}
