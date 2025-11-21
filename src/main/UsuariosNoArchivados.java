package main;
/**
 * La clase `UsuariosNoArchivados` representa a los usuarios que no están almacenados
 * en el archivo de usuarios. Estos usuarios no están  en el sistema,
 * pero aún así se comportan como usuarios con un nombre de usuario, contraseña y rol.
 * Esta clase extiende de `Usuario` y permite manejar usuarios no registrados en el repositorio.
 */
public class UsuariosNoArchivados extends Usuario { 

	public UsuariosNoArchivados(String username, String contraseña, String rol) {
		super(username, contraseña, rol);
	} 

	@Override
	/**
     * Método que muestra los permisos del usuario no archivado.
     * Este método está vacío porque los usuarios no archivados no tienen permisos definidos
     * en este contexto específico. Puede ser sobrecargado o modificado según el comportamiento
     * deseado para estos usuarios.
     */
	public void mostrarPermisos() {
		
	}
}
