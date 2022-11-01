package Clases;

import java.text.SimpleDateFormat;

public class Dentista extends Persona{
	
	private float salario;
	
	public Dentista() {
		
	}
	
	public Dentista(String dni, String nombre, String apellido, SimpleDateFormat sdfFecha, int telefono, String genero,float salario) {
		super(dni,nombre,apellido,sdfFecha,telefono,genero);
		this.salario = salario;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	
}