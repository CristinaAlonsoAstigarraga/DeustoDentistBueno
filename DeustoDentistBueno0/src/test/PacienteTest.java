package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Paciente;

public class PacienteTest {

	private Paciente paciente;
	private String dni = "11111111A";
	private String genero = "femenino";
	private String nombre = "nombre";
	private String newNombre = "New Nombre";
	private String apellido = "apellido";
	private String newApellido = "New Apellido";
	private String direccion = "Direccion";
	private String NewDireccion = "Nueva Direccion";

	@Before
	public void setUp() throws Exception {
		paciente = new Paciente(dni, nombre, apellido, null, 0, genero, direccion);
	}

//	@After
//	public void tearDown() throws Exception {
//	}

	// GETTER:

	@Test
	public void testGetDireccion() {
		assertEquals(direccion, paciente.getDireccion());
	}

	// SETTER:

	@Test
	public void testSetDireccion() {
		paciente.setDireccion(NewDireccion);
		assertEquals(NewDireccion, paciente.getDireccion());
	}

}
