package test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import Clases.Cita;
import Clases.TipoCita;

public class CitaTest {

	private Cita cita;
	private String dniPaciente = "11111111A";
	private String newDniPaciente = "22222222B";
	private String nombrePaciente = "Nombre Paciente";
	private String newNombrePaciente = "New Nombre Paciente";
	private String nombreDentista = "Nombre Dentista";
	private String newNombreDentista = "New Nombre Dentista";
 
	
	//Comprobar cómo hay que hacer estos test y hacerlos.
	private Date fecha;
	SimpleDateFormat sdfFecha = new SimpleDateFormat("dd-MM-yyyy");
//	private String hora;
//	SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

	@Before
	public void setUp() throws Exception {
		cita = new Cita(dniPaciente, nombrePaciente, nombreDentista, fecha, TipoCita.EMPASTE);
	}

//	@After
//	public void tearDown() throws Exception {
//	}

	// GETTERS:

	@Test
	public void testGetDniPaciente() {
		assertEquals(dniPaciente, cita.getDniPaciente());
	}

	@Test
	public void testGetNombrePaciente() {
		assertEquals(nombrePaciente, cita.getNombrePaciente());
	}

	@Test
	public void testGetNombreDentista() {
		assertEquals(nombreDentista, cita.getNombreDentista());
	}

	// SETTERS:

	@Test
	public void testSetDniPaciente() {
		cita.setDniPaciente(newDniPaciente);
		assertEquals(newDniPaciente, cita.getDniPaciente());
	}

	@Test
	public void testSetNombrePaciente() {
		cita.setNombrePaciente(newNombrePaciente);
		assertEquals(newNombrePaciente, cita.getNombrePaciente());
	}

	@Test
	public void testSetNombreDentista() {
		cita.setNombreDentista(newNombreDentista);
		assertEquals(newNombreDentista, cita.getNombreDentista());
	}

}
