package Clases;

/**
 * Clase inventario la cual se utiliza para especificar los productos que se tienen en clinica
 * 
 * @author irene
 *
 */
public class Inventario {
	private int codigoProducto;
	private String nombreProducto;
	private int cantidad;

	/**
	 * constructor vacio
	 */
	public Inventario() {
		
	}
	
	/**
	 * constructor con los siguientes parametros
	 * @param codigoProducto codigo del producto
	 * @param nombreProducto nombre del producto
	 * @param cantidad cantidad del producto
	 */
	public Inventario(int codigoProducto, String nombreProducto, int cantidad) {
		super();
		this.codigoProducto = codigoProducto;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Inventario [codigoProducto=" + codigoProducto + ", nombreProducto=" + nombreProducto + ", cantidad="
				+ cantidad + "]";
	}
	
	
	

}
