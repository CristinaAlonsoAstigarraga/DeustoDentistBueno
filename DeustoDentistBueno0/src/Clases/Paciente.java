package Clases;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Paciente extends Persona{
	
	private String direccion;

	
	public Paciente() {
	
	}
	

	public Paciente(String dni, String nombre, String apellido, SimpleDateFormat sdfFecha, int telefono, String genero,String direccion) {
		super(dni,nombre,apellido,sdfFecha,telefono,genero);
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
}