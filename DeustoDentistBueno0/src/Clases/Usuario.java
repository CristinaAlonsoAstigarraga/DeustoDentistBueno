package Clases;

/**
 * Clase Usuario con atributos
 * @author irene
 *
 */
public class Usuario {
	String nombre;
	String nick;
	String contrasenia;
	String rol;
	
	
	/**
	 * contructor vacio
	 */
	public Usuario() {
		
	}
	
	/**
	 * contructor con parametros
	 * @param nombre
	 * @param nick
	 * @param contrasenia
	 * @param rol
	 */
	public Usuario(String nombre, String nick, String contrasenia, String rol) {
		super();
		this.nombre = nombre;
		this.nick = nick;
		this.contrasenia = contrasenia;
		this.rol = rol;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

}
