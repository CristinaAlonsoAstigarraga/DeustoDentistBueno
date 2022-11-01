package test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Dentista;
import Clases.Persona;

public class DentistaTest {

	private Dentista dentista;
	private float salario = 2330;
	private float newSalario = 1500;
	private String nom="Alberto";
	private String apellid="Martinez";
	private String gen="masculino";
	private String dni="54332356q";
	

	@Before
	public void setUp() throws Exception {
		
		dentista = new Dentista(dni,nom,apellid,null,0,gen,salario);
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
