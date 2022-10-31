package Clases;

public class Dentista extends Persona{
	
	private float salario;
	
	public Dentista() {
		
	}
	
	public Dentista(float salario) {
		super();
		this.salario = salario;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	
}