package Clases;

import java.util.Date;

/**
 * clase paciente donde se especifica una seria de parametros
 * @author irene
 *
 */
public class Paciente extends Persona{
	
	private String direccion;

	/**
	 * contructor vacio
	 */
	public Paciente() {
	
	}
	
	/**
	 * contructor con los siguientes parametros
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param fechaNacimiento
	 * @param telefono
	 * @param genero
	 * @param direccion
	 */
	public Paciente(String dni, String nombre, String apellido, Date fechaNacimiento, int telefono, String genero,String direccion) {
		super(dni,nombre,apellido,fechaNacimiento,telefono,genero);
		this.direccion = direccion;
	}

	/**
	 * metodo  getDireccion
	 * @return direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	 /**
	  * metodo set direccion
	  * @param direccion 
	  */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public String toString() {
		return "Paciente ["
				+ "Dni=" + getDni() + ", Nombre=" + getNombre() + ", Apellido=" + getApellido()
				+ ", Fecha Nacimiento=" + getFechaNacimiento() + ", Telefono=" + getTelefono() + ", Direccion=" + getDireccion()
				+ ", Genero=" + getGenero() + "]";
	}
	
}