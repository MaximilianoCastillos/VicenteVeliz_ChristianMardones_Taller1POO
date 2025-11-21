package main;

public class UsuariosNoArchivados extends Usuario { /**Aqui van los usuarios que no estan dentro del archivo usuarios*/

	public UsuariosNoArchivados(String username, String contraseña, String rol) {
		super(username, contraseña, rol);
	} 

	@Override
	public void mostrarPermisos() {
		
	}
}
