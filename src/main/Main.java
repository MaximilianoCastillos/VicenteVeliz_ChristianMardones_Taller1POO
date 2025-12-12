package main;

import javax.swing.SwingUtilities;

/**
 * Clase principal que inicia el sistema. Se encarga de inicializar la instancia del sistema
 * y abrir la ventana de inicio de sesión (`LoginWindow`).
 */
public class Main {

    /**
     * Método principal que inicializa el sistema y abre la ventana de Login.
     */
    public static void main(String[] args) {
        // Inicializar la instancia del sistema para cargar los datos
        Sistema.getInstancia();
        
        // Iniciar la ventana de Login
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginWindow();
            }
        });
    }
}
