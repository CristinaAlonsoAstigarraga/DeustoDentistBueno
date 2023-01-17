package Clases;

import java.util.Date;

/**
 * Clase dentista donde se especifica los atributos de un dentista
 * se utiliza para tener todos los datos de los dentistass que componen la app
 * @author irene
 *
 */
public class Dentista extends Persona{
	
	private float salario;
	
	/**
	 * constructor vacio
	 */
	public Dentista() {
		
	}
	
	/**
	 * contructor con los siguientes parametros
	 * @param dni dni del dentista
	 * @param nombre nombre del dentista
	 * @param apellido apellidos del dentista
	 * @param fechaNacimiento fecha de nacimiento del dentista
	 * @param telefono telefono del dentista
	 * @param genero genero del dentista
	 * @param salario salario del  dentista
	 */
	public Dentista(String dni, String nombre, String apellido, Date fechaNacimiento, int telefono, String genero,float salario) {
		super(dni,nombre,apellido,fechaNacimiento,telefono,genero);
		this.salario = salario;
	}
	
	/**
	 * metodo toString donde se visualiza cadena del obejto dentista
	 */
	@Override
	public String toString() {
		return "Dentista [Dni=" + getDni() + ", Nombre=" + getNombre() + ", Apellido=" + getApellido()
				+ ", FechaNacimiento=" + getFechaNacimiento() + ", Telefono=" + getTelefono()
				+ ", Genero=" + getGenero() + ", Salario=" + getSalario()+"]";
	}
	/**
	 * metodo get de salario
	 * @return salario (de tipo float)
	 */
	public float getSalario() {
		return salario;
	}

	/**
	 * metodo set de salario
	 * @param salario
	 */
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	
}