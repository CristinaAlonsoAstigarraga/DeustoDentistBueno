package Clases;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import BD.BD;

/**
 * clase producto con atributos
 * @author irene
 *
 */
public class Producto {
	
	private int codigo;
	private String nombre;
	private String descripcion;
	private float precio; 
	private int cantidad; 	//Cantidad del producto en el inventario (número de unidades del producto en la clínica)
	private int contador = 0;
	
	Connection con = BD.initBD("BaseDatos.db");
	
	/**
	 * contructor vacio
	 */
	public Producto() {
		
	}
	/**
	 * contructor  producto con parametros
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * @param precio
	 * @param cantidad
	 */
	public Producto(int codigo, String nombre, String descripcion, float precio, int cantidad) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	/**
	 * metodo get contador
	 * @return
	 */
	public int getContador() {
		return contador;
	}
	/**
	 * metodo set contador
	 * @param contador
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}
	/**
	 * metodo get codigo
	 * @return
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * metodo set Codigo
	 * @param codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * metodo get Nombre
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * metodo set Nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * metodo get Descripcion
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * metodo set descripcion
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "["+ codigo+", "+nombre + ", " + precio + "€]";
	}
	
	/**
	 * metodo combinaciones de procutos 
	 * @param resultado
	 * @param elementos
	 * @param DineroDisponible
	 * @param DineroSobreanteMax
	 * @param provisional
	 * @param repetidos
	 */
	private static void combinacionesProductos(List<List<Producto>> resultado,
			List<Producto> elementos, 
			double DineroDisponible, 
			double DineroSobreanteMax, 
			List<Producto> provisional, int repetidos) {
		if (DineroDisponible < 0) {
			return;
		} else if (DineroDisponible < DineroSobreanteMax) {
			provisional.sort((Producto p1, Producto p2) -> Integer.compare(p1.getCodigo(), p2.getCodigo()));
			if (!resultado.contains(provisional)) {
				resultado.add(new ArrayList<>(provisional));
			} else {
				repetidos++;
			}
		} else {
			for (Producto p : elementos) {
				provisional.add(p);
				combinacionesProductos(resultado, elementos, DineroDisponible-p.getPrecio(), DineroSobreanteMax, provisional, repetidos);
				provisional.remove(provisional.size()-1);
			}
		}
		
		}
		
		public static List<List<Producto>> combinacionesProductos(List<Producto> elementos, double DineroDisponible, double DineroSobrante, int repetidos){
			List<List<Producto>> resultado = new ArrayList<>();
			combinacionesProductos(resultado, elementos, DineroDisponible, DineroSobrante, new ArrayList<>(), repetidos);
			return resultado;		
		}

}
