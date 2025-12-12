package main;

/**
 * Clase que representa el sistema central del proyecto. 
 * Utiliza el patrón Singleton para asegurar que solo haya una instancia del sistema.
 * Maneja los repositorios de estudiantes, certificaciones y usuarios.
 */
public class Sistema {
    private static Sistema instancia;
    private EstudianteRepo estudianteRepo;
    private CertificacionRepo certificacionRepo; // Repositorio de certificaciones
    private UsuarioRepo usuarioRepo; // Repositorio de usuarios

    /**
     * Constructor privado para inicializar los repositorios y cargar los datos.
     */
    private Sistema() {
        estudianteRepo = new EstudianteRepo();
        certificacionRepo = new CertificacionRepo();
        usuarioRepo = new UsuarioRepo();
        
        // Llamar a cargar los datos cuando se instancie el sistema
        cargarDatos();
    }

    /**
     * Obtiene la instancia única del sistema (patrón Singleton).
     */
    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    /**
     * Obtiene el repositorio de estudiantes.
     */
    public EstudianteRepo getEstudianteRepo() {
        return estudianteRepo;
    }

    /**
     * Obtiene el repositorio de certificaciones.
     */
    public CertificacionRepo getCertificacionRepo() {
        return certificacionRepo;
    }

    /**
     * Obtiene el repositorio de usuarios.
     */
    public UsuarioRepo getUsuarioRepo() {
        return usuarioRepo;
    }

    /**
     * Carga los datos de los archivos correspondientes.
     * Asigna las rutas a los archivos .txt y carga los datos de usuarios, estudiantes y certificaciones.
     */
    private void cargarDatos() {
        // Asigna las rutas a los archivos .txt
        String rutaUsuarios = "archivos/usuarios.txt";
        String rutaEstudiantes = "archivos/estudiantes.txt";
        String rutaCertificaciones = "archivos/certificaciones.txt";
        
        // Cargar los datos desde los archivos
        usuarioRepo.cargarUsuariosDesdeArchivo(rutaUsuarios);
        estudianteRepo.cargarEstudiantesDesdeArchivo(rutaEstudiantes);
        certificacionRepo.cargarCertificacionesDesdeArchivo(rutaCertificaciones);
    }
}
