package Clases;

import java.util.Date;

/**
 * clase persona con atributos
 * @author irene
 *
 */
public class Persona {

	private String dni;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private int telefono;
	private String genero;
	
	/**
	 * Constructor por defecto
	 */
	public Persona() {
	
	}
	/**
	 * constructor persona con parametros
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param fechaNacimiento
	 * @param telefono
	 * @param genero
	 */
	public Persona(String dni, String nombre, String apellido, Date fechaNacimiento, int telefono, String genero) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.genero = genero;
	}
	
	/**
	 * metodo get dni
	 * @return
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * metodo set dni
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * metodo getNombre
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * metodo set Nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * metodo get Apellido
	 * @return
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * metodo set apellido
	 * @param apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * metodo get fecha nacimiento
	 * @return
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * metodo get Telefono
	 * @return
	 */
	public int getTelefono() {
		return telefono;
	}
	/**
	 * metodo set Fecha Nacimiento
	 * @param fechaNacimiento
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * metodo set Telefono
	 * @param telefono
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	/**
	 * metodo get Genero
	 * @return
	 */
	public String getGenero() {
		return genero;
	}
	/**
	 * metodo setGenero
	 * @param genero
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
}
