package Clases;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Persona {

	private String dni;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	SimpleDateFormat sdfFecha = new SimpleDateFormat("dd-MM-yyyy");
	private int telefono;
	private String genero;
	
	public Persona() {
		super();
	}

	public Persona(String dni, String nombre, String apellido, SimpleDateFormat sdfFecha, int telefono, String genero) {
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
	public void setFechaNacimiento(String fecha) {
		try{
			this.fechaNacimiento = (Date) sdfFecha.parse(fecha);
		}catch(ParseException e) {
			this.fechaNacimiento = new Date(System.currentTimeMillis());
		}
	}
	public int getTelefono() {
		return telefono;
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
