package Clases;

public class Paciente extends Persona{
	
	private String direccion;
	

	public Paciente(String direccion) {
		super();
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
}