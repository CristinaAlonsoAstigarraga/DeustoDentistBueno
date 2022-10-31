package BD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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
		String sql = "CREATE TABLE IF NOT EXISTS Inventario (\r\n"
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \r\n"
				+ "cod_p INTEGER, \r\n"
				+ "nom VARCHAR(20), \r\n"
				+ "cantidad INTEGER, \r\n"
				+ "FOREIGN KEY (cod_p) REFERENCES Producto(cod_p) ON DELETE CASCADE, \r\n"
				+ "FOREIGN KEY (nom) REFERENCES Producto(nom) ON DELETE CASCADE)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void crearTablaCita(Connection con, int cod, String nom) {
		//mirar int mirar foreign producto
		String sql = "CREATE TABLE IF NOT EXISTS Cita (\r\n"
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \r\n"
				+ "dni VARCHAR(10), \r\n"
				+ "nom_p VARCHAR(25),\r\n"
				+ "fyh DATE, \r\n"
				+ "tipo VARCHAR(25),\r\n"
				+ "nom_d  VARCHAR(25),\r\n"
				+ "FOREIGN KEY (dni) REFERENCES Paciente(dni) ON DELETE CASCADE, \r\n"
				+ "FOREIGN KEY (nom_p) REFERENCES Paciente(nom) ON DELETE CASCADE, \r\n"
				+ "FOREIGN KEY (nom_d) REFERENCES Dentista(nom) ON DELETE CASCADE)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void crearTablaHistorial(Connection con, int cod, String nom) {
		//mirar int mirar foreign producto
		String sql = "CREATE TABLE IF NOT EXISTS Historial (\r\n"
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \r\n"
				+ "dni VARCHAR(10) PRIMARY KEY, \r\n"
				+ "nom_p VARCHAR(25),\r\n"
				+ "tipo VARCHAR(25),\r\n"
				+ "desc VARCHAR(200),\r\n"
				+ "FOREIGN KEY (tipo) REFERENCES Cita(tipo) ON DELETE CASCADE, \r\n"
				+ "FOREIGN KEY (dni) REFERENCES Paciente(dni) ON DELETE CASCADE, \r\n"
				+ "FOREIGN KEY (nom_p) REFERENCES Paciente(nom) ON DELETE CASCADE)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void anadirPaciente(Connection con, String dni, String nom, String apellidos, Date fechaNacimiento, String dir, int telf, String gen ) {
		
		String sql = "INSERT INTO Paciente VALUES ('"+dni+"', '"+nom+"','"+apellidos+"','"+fechaNacimiento+"', '"+dir+"','"+telf+"', '"+gen+"')";
		
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void anadirDentista(Connection con, String dni, String nom, String apellidos, Date fechaNacimiento, String dir, int telf, String gen ) {
		
		String sql = "INSERT INTO Dentista VALUES ('"+dni+"', '"+nom+"','"+apellidos+"','"+fechaNacimiento+"', '"+dir+"','"+telf+"', '"+gen+"')";
		
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void anadirProducto(Connection con, int cod_p, String nom, String desc, int precio) {
		
		String sql = "INSERT INTO Producto VALUES ('"+cod_p+"', '"+nom+"','"+desc+"','"+precio+"')";
		
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void anadirInventario(Connection con, int id, int cod_p, String nom, int cantidad) {
		
		String sql = "INSERT INTO Inventario VALUES ('"+id+"', '"+cod_p+"','"+nom+"','"+cantidad+"')";
		
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void anadirCita(Connection con, int id, String dni, String nom_p, Date fyh, String tipo, String nom_d) {
		
		String sql = "INSERT INTO Cita VALUES ('"+id+"', '"+dni+"','"+nom_p+"','"+fyh+"', '"+tipo+"', '"+nom_d+"')";
		
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void anadirHistoria(Connection con, int id, String dni, String nom_p, String tipo, String desc) {
		
		String sql = "INSERT INTO Historial VALUES ('"+id+"', '"+dni+"','"+nom_p+"', '"+tipo+"', '"+desc+"')";
		
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void borrarTablaPaciente(Connection con) {
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM Paciente";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void borrarTablaDentista(Connection con) {
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM Dentista";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void borrarTablaCita(Connection con) {
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM Cita";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void borrarTablaProducto(Connection con) {
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM Producto";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void borrarTablaInventario(Connection con) {
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM Inventario";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void borrarTablaHistorial(Connection con) {
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM Historial";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}