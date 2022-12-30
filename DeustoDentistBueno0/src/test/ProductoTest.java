package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Producto;

public class ProductoTest {

	private Producto producto;
	private int codigo = 111;
	private int newCodigo = 222;
	private String nombre = "Nombre";
	private String newNombre = "New nombre";
	private String descripcion = "Descripcion";
	private String newDescripcion = "New descripcion";
	private float precio = 10;
	private float newPrecio = 11;
	private int cantidad = 2;
	private int newCantidad = 3;

	@Before
	public void setUp() throws Exception {
		producto = new Producto(codigo, nombre, descripcion, precio, cantidad);
	}

//	@After
//	public void tearDown() throws Exception {
//	}

	// GETTERS:

	@Test
	public void testGetCodigo() {
		assertEquals(codigo, producto.getCodigo());
	}

	@Test
	public void testGetNombre() {
		assertEquals(nombre, producto.getNombre());
	}

	@Test
	public void testGetDescripcion() {
		assertEquals(descripcion, producto.getDescripcion());
	}

	@Test
	public void testGetPrecio() {
		assertEquals(precio, producto.getPrecio(), 0);
	}
	
	@Test
	public void testGetCantidad() {
		assertEquals(cantidad, producto.getCantidad(), 0);
	}

	// SETTERS:

	@Test
	public void testSetCodigo() {
		producto.setCodigo(newCodigo);
		assertEquals(newCodigo, producto.getCodigo());
	}

	@Test
	public void testSetNombre() {
		producto.setNombre(newNombre);
		assertEquals(newNombre, producto.getNombre());
	}

	@Test
	public void testSetDescripcion() {
		producto.setDescripcion(newDescripcion);
		assertEquals(newDescripcion, producto.getDescripcion());
	}

	@Test
	public void testSetPrecio() {
		producto.setPrecio(newPrecio);
		assertEquals(newPrecio, producto.getPrecio(), 0);
	}
	
	@Test
	public void testSetCantidad() {
		producto.setCantidad(newCantidad);
		assertEquals(newCantidad, producto.getCantidad());
	}


}
