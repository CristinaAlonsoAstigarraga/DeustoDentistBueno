package Clases;

public class Dentista extends Persona{
	
	private float salario;
	
	public Dentista() {
		
	}
	
	public Dentista(String dni, String nombre, String apellido, String fechaNacimiento, int telefono, String genero,float salario) {
		super(dni,nombre,apellido,fechaNacimiento,telefono,genero);
		this.salario = salario;
	}
	
	@Override
	public String toString() {
		return "Dentista [Dni=" + getDni() + ", Nombre=" + getNombre() + ", Apellido=" + getApellido()
				+ ", FechaNacimiento=" + getFechaNacimiento() + ", Telefono=" + getTelefono()
				+ ", Genero=" + getGenero() + ", Salario=" + getSalario()+"]";
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	
}