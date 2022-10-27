package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//mirar mismo nombre en diferentes clases

public class BD {
	
	/**
	 * Método que realiza la conexión con la base de datos
	 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
	 * @return Devuelve la conexión a la base de datos
	 */
	public static Connection initBD(String nombreBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
					
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void closeBD(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void crearTablaPaciente(Connection con) {
		//mirar int
		String sql = "CREATE TABLE IF NOT EXISTS Paciente(\r\n"
				+ "dni VARCHAR(10) PRIMARY KEY, \r\n"
				+ "nom VARCHAR(25),\r\n"
				+ "apellidos VARCHAR(30), \r\n"
				+ "fechaNacimiento Date, \r\n"
				+ "dir VARCHAR(30), \r\n"
				+ "telf INETEGR, \r\n"
				+ "gen VARCHAR(10)\r\n"
				+ ")";
		
		//String sql = "CREATE TABLE IF NOT EXISTS Paciente (dni String, nom String,apellidos String, fechaNacimiento String, dir String, telf int, gen String)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void crearTablaDentista(Connection con) {
		//falta enum mirar int 
		String sql = "CREATE TABLE IF NOT EXISTS Dentista (\r\n"
				+ "dni  VARCHAR(10) PRIMARY KEY, \r\n"
				+ "nom  VARCHAR(25),\r\n"
				+ "apellidos  VARCHAR(25), \r\n"
				+ "fechaNacimiento Date, \r\n"
				+ "dir  VARCHAR(35), \r\n"
				+ "telf INTEGER, \r\n"
				+ "gen VARCHAR(10)\r\n"
				+ ")\r\n"
				+ "";
		
		//String sql = "CREATE TABLE IF NOT EXISTS Dentista (dni String, nom String,apellidos String, fechaNacimiento String, dir String, telf int, gen String)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void crearTablaProducto(Connection con) {
		//mirar int 
		String sql = "CREATE TABLE IF NOT EXISTS Producto (\r\n"
				+ "cod_p INTEGER PRIMARY KEY, \r\n"
				+ "nom VARCHAR(20), \r\n"
				+ "desc VARCHAR(40), \r\n"
				+ "precio INTEGER)";
		
		//String sql = "CREATE TABLE IF NOT EXISTS Producto (cod int, nom String, desc String, precio int)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void crearTablaInventario(Connection con, int cod, String nom) {
		//mirar int mirar foreign producto
		String sql = "CREATE TABLE IF NOT EXISTS Inventario ("+cod+", "+nom+", cant int)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
