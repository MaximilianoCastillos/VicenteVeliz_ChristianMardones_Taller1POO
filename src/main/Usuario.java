package main;

/**
 * Clase abstracta que representa un usuario genérico del sistema.
 * Define los atributos comunes de los usuarios, como el nombre de usuario, la contraseña y el rol.
 */
public abstract class Usuario {
    protected String username;
    protected String password;
    protected String rol;

    /**
     * Constructor para crear un usuario.
     */
    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    /**
     * Obtiene el nombre de usuario.
     */
    public String getUsername() { 
        return username;
    }

    /**
     * Obtiene la contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Obtiene el rol del usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Establece la contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Establece el rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
}
