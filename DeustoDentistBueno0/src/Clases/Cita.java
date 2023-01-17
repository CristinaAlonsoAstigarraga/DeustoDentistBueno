package Clases;
import java.util.Date;

/**
 * clase Cita donde se gestiona toda la informacionde una cita de una paciente
 * 
 * @author irene
 *
 */
public class Cita {
	private String dniPaciente;
	private String nombrePaciente;
	private String nombreDentista;
	private Date fecha; 
	private TipoCita tipo;
	private int id;
	 
	/**
	 * constructor vacio
	 */
	public Cita() {
		
	}
	
	/**
	 * Constructo cita con parametros
	 * @param dniPaciente dni del paciente
	 * @param nombrePaciente nombre del paciente
	 * @param nombreDentista nombre del dentista
	 * @param fecha fecha de la cita
	 * @param tipo tipo de la cita
	 */
	public Cita(String dniPaciente, String nombrePaciente, String nombreDentista, Date fecha,TipoCita tipo) {
		super();
		this.dniPaciente = dniPaciente;
		this.nombrePaciente = nombrePaciente;
		this.nombreDentista = nombreDentista;
		this.fecha = fecha;
		this.tipo=tipo;
	}
	/**
	 * Constructor con parametros
	 * @param id id de la cita
	 * @param dniPaciente dni del paciente
	 * @param nombrePaciente nombre del paciente
	 * @param nombreDentista nombre del dentista
	 * @param fecha fecha de la cita
	 * @param tipo tipo de la cita
	 */
	public Cita(int id,String dniPaciente, String nombrePaciente, String nombreDentista, Date fecha,TipoCita tipo) {
		super();
		this.id=id;
		this.dniPaciente = dniPaciente;
		this.nombrePaciente = nombrePaciente;
		this.nombreDentista = nombreDentista;
		this.fecha = fecha;
		this.tipo=tipo;
	}
	
	/**
	 * Metodo get Id de la cita
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * metodo set id de la cita
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * metodo get dni del paciente
	 * @return dni
	 */
	public String getDniPaciente() {
		return dniPaciente;
	}
	/**
	 * metodo set dni del papciente
	 * @param dniPaciente
	 */
	
	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}
	/**
	 * metodo get nombre del paciente
	 * @return nombrePaciente
	 */
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	/**
	 * metodo set nombre del paciente
	 * @param nombrePaciente
	 */
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	/**
	 * metodo get Nombre del dentista
	 * @return nombbreDentista
	 */
	public String getNombreDentista() {
		return nombreDentista;
	}
	/**
	 * metodo set Nombre del dentista
	 * @param nombreDentista
	 */
	public void setNombreDentista(String nombreDentista) {
		this.nombreDentista = nombreDentista;
	}

	/**
	 * metodo get fecha
	 * @return fecha
	 */
	public Date getFecha() {
		return fecha;
	}
/**
 * metodo set fecha
 * @param fecha
 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * metodo set tipo
	 * @param tipo
	 */
	public void setTipo(TipoCita tipo) {
		this.tipo = tipo;
	}
	/**
	 * metodo getTipo
	 * @return tipo
	 */
	public TipoCita getTipo(){
		return tipo;
	}

	@Override
	public String toString() {
		return "Cita [dniPaciente=" + dniPaciente + ", nombrePaciente=" + nombrePaciente + ", nombreDentista="
				+ nombreDentista + ", fyh=" + fecha + ", tipo=" + tipo + "]";
	}		
}
