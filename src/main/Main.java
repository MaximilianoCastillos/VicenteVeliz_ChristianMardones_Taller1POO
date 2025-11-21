package main;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Repositorio repo = Repositorio.getInstance();
        repo.cargarUsuarios();
        repo.cargarProyectos();
        repo.cargarTareas();

        Usuario revisar = null;
        Scanner scan = new Scanner(System.in);
        Scanner eleccion = new Scanner(System.in);

        boolean seguirPrograma = true;

        while (seguirPrograma)
        /** mantiene activa el programa hasta que se desee salir */{
            while (revisar == null) {
                revisar = Funciones.login();
            }
            if (revisar.getRol().equalsIgnoreCase("Administrador"))
            /** en caso que el usuario sea administrador entra por aca */ {
                Administrador admin = (Administrador) revisar;

                admin.menuAdmin();
                /** muestra el menu constantemente */
                int opcion = 0;

                System.out.print("Elija una opcion: ");
                
                try {
                    opcion = eleccion.nextInt(); // Intenta leer una opción numérica

                    switch (opcion)
                    /** vee que opcion se eligio y revisa que este dentro de los limites */ {
                        case 1:
                            admin.listaProyectoTareas();
                            break;
                        case 2:
                            admin.gestionerProyectos();
                            break;
                        case 3:
                            admin.gestionarTareas();
                            break;
                        case 4:
                            admin.asignarPrioridades();
                            break;
                        case 5:
                            admin.generarReporte();
                            break;
                        case 6:
                            revisar = null;
                            break;

                        default:
                            System.out.println(" ");
                            System.out.println("------------");
                            System.out.println("Opcion invalida");
                            System.out.println("------------");
                            System.out.println(" ");
                    }
                } catch (IllegalArgumentException e) {
                    
                    System.out.println("Entrada inválida, por favor ingrese un número.");
                    eleccion.nextLine(); 
                }
            }
            if (revisar.getRol().equalsIgnoreCase("Colaborador"))
            /** entra aca en caso que el usuario sea colaborador */ {
                Colaborador cola = (Colaborador) revisar;
                cola.menuColaborador();
                int opcion = 0;

                try {
                    opcion = eleccion.nextInt(); // Intenta leer una opción numérica

                    switch (opcion)
                    /** vee que opcion se eligio y revisa que este dentro de los limites */ {
                        case 1:
                            cola.verTareas();
                            break;

                        case 2:
                            cola.tareasProyectos();
                            break;

                        case 3:
                            cola.cambiarEstado();
                            break;
                        case 4:
                            cola.aplicarVisitor();
                            break;

                        case 5:
                            revisar = null;
                            break;

                        default:
                            System.out.println(" ");
                            System.out.println("------------");
                            System.out.println("Opcion invalida");
                            System.out.println("------------");
                            System.out.println(" ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida, por favor ingrese un número.");
                    eleccion.nextLine(); 
                }
            }
        }
    }
}
