package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Inventario;

public class InventarioTest {

	private Inventario inventario;
	private int codigoProducto = 111;
	private int newCodigoProducto = 222;
	private String nombreProducto = "Nombre";
	private String newNombreProducto = "Nuevo Nombre";
	private int cantidad = 2;
	private int newCantidad = 7;

	@Before
	public void setUp() throws Exception {
		inventario = new Inventario(codigoProducto, nombreProducto, cantidad);
	}

//	@After
//	public void tearDown() throws Exception {
//	}

	// GETTERS:

	@Test
	public void testGetCodigoProducto() {
		assertEquals(codigoProducto, inventario.getCodigoProducto());
	}

	@Test
	public void testGetNombreProducto() {
		assertEquals(nombreProducto, inventario.getNombreProducto());
	}

	@Test
	public void testGetCantidad() {
		assertEquals(cantidad, inventario.getCantidad());
	}

	// SETTERS:

	@Test
	public void testSetCodigoProducto() {
		inventario.setCodigoProducto(newCodigoProducto);
		assertEquals(newCodigoProducto, inventario.getCodigoProducto());
	}

	@Test
	public void testSetNombreProducto() {
		inventario.setNombreProducto(newNombreProducto);
		assertEquals(newNombreProducto, inventario.getNombreProducto());
	}

	@Test
	public void testSetCantidad() {
		inventario.setCantidad(newCantidad);
		assertEquals(newCantidad, inventario.getCantidad());
	}
}
