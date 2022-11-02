package Clases;

public class Historial {
	private String dni;
	private String nombre;
	private String desc;
	
	public Historial(String dni, String nombre, String desc) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.desc = desc;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
	

}
