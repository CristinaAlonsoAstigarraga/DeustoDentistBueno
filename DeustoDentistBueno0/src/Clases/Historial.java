package Clases;

/**
 * Clase historial donde se especifica sus parametros
 * Se usa para llevar uns eguiemiento de los pacientes
 * @author irene
 *
 */
public class Historial {
	private String dni;
	private String nombre;
	private String desc;
	
	/**
	 * constructor con los diguientes parametros
	 * @param dni dni del paciente
	 * @param nombre nombre del paciente
	 * @param desc descripcion de las observacciones tomadas del paciente
	 */
	public Historial(String dni, String nombre, String desc) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.desc = desc;
	}
	
	

	/**
	 * metodo toString donde se visualiza cadena del obejto historial
	 */
	@Override
	public String toString() {
		return "Historial [dni=" + dni + ", nombre=" + nombre + ", desc=" + desc + "]";
	}

	/**
	 * metodo get dni
	 * @return dni
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
	 * metodo det nombre
	 * @return nombre
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
	 * metodso get Descripcion
	 * @return desc
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * metodo set Descripcion
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
