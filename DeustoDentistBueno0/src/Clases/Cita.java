package Clases;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Cita {
		private String dniPaciente;
		private String nombrePaciente;
		private String nombreDentista;
		private Date fecha; 
		SimpleDateFormat sdfFecha = new SimpleDateFormat("dd-MM-yyyy");
		private Date hora;
		SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
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
		public Date getHora() {
			return hora;
		}
		public void setHora(Date hora) {
			this.hora = hora;
		}

}
