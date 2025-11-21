package main;

import java.util.Scanner;

public class Funciones {

    
    public static Usuario login() /** fue creado para hacer login y revisar si esta dentro del sistema*/{
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