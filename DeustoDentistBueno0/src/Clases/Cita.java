package Clases;
import java.util.Date;

public class Cita {
	private String dniPaciente;
	private String nombrePaciente;
	private String nombreDentista;
	private Date fecha; 
	private TipoCita tipo;
	private int id;
	 
	public Cita() {
		
	}
	
	public Cita(String dniPaciente, String nombrePaciente, String nombreDentista, Date fecha,TipoCita tipo) {
		super();
		this.dniPaciente = dniPaciente;
		this.nombrePaciente = nombrePaciente;
		this.nombreDentista = nombreDentista;
		this.fecha = fecha;
		this.tipo=tipo;
	}
	//contructor con atributo id de la tabla
	public Cita(int id,String dniPaciente, String nombrePaciente, String nombreDentista, Date fecha,TipoCita tipo) {
		super();
		this.id=id;
		this.dniPaciente = dniPaciente;
		this.nombrePaciente = nombrePaciente;
		this.nombreDentista = nombreDentista;
		this.fecha = fecha;
		this.tipo=tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDniPaciente() {
		return dniPaciente;
	}
	
	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	public String getNombreDentista() {
		return nombreDentista;
	}
	public void setNombreDentista(String nombreDentista) {
		this.nombreDentista = nombreDentista;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setTipo(TipoCita tipo) {
		this.tipo = tipo;
	}

	public TipoCita getTipo(){
		return tipo;
	}

	@Override
	public String toString() {
		return "Cita [dniPaciente=" + dniPaciente + ", nombrePaciente=" + nombrePaciente + ", nombreDentista="
				+ nombreDentista + ", fyh=" + fecha + ", tipo=" + tipo + "]";
	}		
}
