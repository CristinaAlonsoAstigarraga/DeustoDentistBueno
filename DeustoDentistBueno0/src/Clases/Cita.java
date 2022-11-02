package Clases;
import java.text.SimpleDateFormat;

public class Cita {
		private String dniPaciente;
		private String nombrePaciente;
		private String nombreDentista;
		private String fecha; 
		SimpleDateFormat sdfFecha = new SimpleDateFormat("dd-MM-yyyy");
		private TipoCita tipo;
		 
		public Cita() {
			
		}
		
		public Cita(String dniPaciente, String nombrePaciente, String nombreDentista, String fecha,TipoCita tipo) {
			super();
			this.dniPaciente = dniPaciente;
			this.nombrePaciente = nombrePaciente;
			this.nombreDentista = nombreDentista;
			this.fecha = fecha;
			this.tipo=tipo;
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
		
		public String getFecha() {
			return fecha;
		}

		@Override
		public String toString() {
			return "Cita [dniPaciente=" + dniPaciente + ", nombrePaciente=" + nombrePaciente + ", nombreDentista="
					+ nombreDentista + ", fecha=" + fecha + ", tipo=" + tipo + "]";
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public TipoCita getTipo(){
			return tipo;
		}
		
		
		
}
