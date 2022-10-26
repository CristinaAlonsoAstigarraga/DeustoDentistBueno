package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Dentista;
import Clases.Persona;

public class DentistaTest {

	private Dentista dentista;
	private float salario = 1000;
	private float newSalario = 1500;

	@Before
	public void setUp() throws Exception {
		dentista = new Dentista(salario);
	}

//	@After
//	public void tearDown() throws Exception {
//	}

	// GETTER:

	@Test
	public void testGetSalario() {
		assertEquals(salario, dentista.getSalario(), 0);
	}

	// SETTER:

	@Test
	public void testSetSalario() {
		dentista.setSalario(newSalario);
		assertEquals(newSalario, dentista.getSalario(), 0);
	}

}
