package main;

import java.util.Scanner;
/**
 * La clase Funciones contiene métodos auxiliares para el manejo de usuarios en el sistema,
 * como el inicio de sesión (login). 
 */

public class Funciones {
	 /**
     * Permite al usuario iniciar sesión en el sistema.
     * Solicita al usuario su nombre de usuario y contraseña, luego verifica si estos
     * coinciden con algún usuario registrado en el repositorio.
     *
     * @return El objeto Usuario correspondiente al usuario que ha iniciado sesión.
     *         Si las credenciales no son correctas, retorna null.
     */
	

    
    public static Usuario login() {
        Scanner scan = new Scanner(System.in); 

        System.out.print("Usuario: ");
        String usuario = scan.nextLine();

        System.out.print("Contraseña: ");
        String contra = scan.nextLine();

        Repositorio repo = Repositorio.getInstance();
        Usuario user = null;

        /** Buscar usuario en la lista cargada del repositorio*/
        for (Usuario u : repo.getUsuarios()) {
            if (u.getUsername().equalsIgnoreCase(usuario) && u.getContraseña().equalsIgnoreCase(contra)) {
                user = u;
                break;
            }
        }

        if (user != null) {
            System.out.println("Bienvenido, " + user.getUsername() + "!");
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }

        return user;
    }
}