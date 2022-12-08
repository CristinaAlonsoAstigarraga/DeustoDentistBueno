package Clases;

import java.util.Date;

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

	public Persona(String dni, String nombre, String apellido, Date fechaNacimiento, int telefono, String genero) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.genero = genero;
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public int getTelefono() {
		return telefono;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
}
