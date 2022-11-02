package Clases;

public class Paciente extends Persona{
	
	private String direccion;

	
	public Paciente() {
	
	}
	

	public Paciente(String dni, String nombre, String apellido, String fechaNacimiento, int telefono, String genero,String direccion) {
		super(dni,nombre,apellido,fechaNacimiento,telefono,genero);
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
}