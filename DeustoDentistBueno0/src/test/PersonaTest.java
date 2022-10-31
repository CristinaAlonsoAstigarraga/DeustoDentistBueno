package test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

//import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Persona;

public class PersonaTest {

	private Persona persona;
	private String dni = "11111111A";
	private String newDni = "22222222B";
	private String nombre = "nombre";
	private String newNombre = "New Nombre";
	private String apellido = "apellido";
	private String newApellido = "New Apellido";
	// Comprobar test de getFecha() y setFecha()
	private String er = "[0-9]{2}-[0-9]{2}-[0-9]{4}";
	SimpleDateFormat sdfFecha = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sdfNewFecha = new SimpleDateFormat("dd-MM-yyyy");
	private int telefono = 609609609;
	private int newTelefono = 600600600;
	private String genero = "femenino";
	private String newGenero = "masculino";

	@Before
	public void setUp() throws Exception {
		persona = new Persona(dni, nombre, apellido, sdfFecha, telefono, genero);
	}

//	@After
//	public void tearDown() throws Exception {
//	}

	// GETTERS:

	@Test
	public void testGetDni() {
		assertEquals(dni, persona.getDni());
	}

	@Test
	public void testGetNombre() {
		assertEquals(nombre, persona.getNombre());
	}

	@Test
	public void testGetApellido() {
		assertEquals(apellido, persona.getApellido());
	}

	// Comprobar
	@Test
	public void testGetFechaNacimiento() {
		assertEquals(sdfFecha, persona.getFechaNacimiento());
	}

	@Test
	public void testGetTelefono() {
		assertEquals(telefono, persona.getTelefono());
	}

	@Test
	public void testGetGenero() {
		assertEquals(genero, persona.getGenero());
	}

	// SETTERS:
	/*
	 * En todos: 1. Inicializamos una persona (línea 25) 2. Cambiamos el valor del
	 * nombre, dni, ... 3. Comprobamos que es el mismo que se ha cambiado
	 */

	@Test
	public void testSetDni() {
		persona.setDni(newDni);
		assertEquals(newDni, persona.getDni());
	}

	@Test
	public void testSetNombre() {
		persona.setNombre(newNombre);
		assertEquals(newNombre, persona.getNombre());
	}

	@Test
	public void testSetApellido() {
		persona.setApellido(newApellido);
		assertEquals(newApellido, persona.getApellido());
	}

	// Comprobar
	/*@Test
	public void testSetFechaNacimiento() {
		persona.setFechaNacimiento(fechaNacimiento);
		assertEquals(sdfNewFecha, persona.getFechaNacimiento());
	}
*/
	@Test
	public void testSetTelefono() {
		persona.setTelefono(newTelefono);
		assertEquals(newTelefono, persona.getTelefono());
	}

	@Test
	public void testSetGenero() {
		persona.setGenero(newGenero);
		assertEquals(newGenero, persona.getGenero());
	}
}
