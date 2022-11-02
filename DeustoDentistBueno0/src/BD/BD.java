package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Clases.Cita;
import Clases.Dentista;
import Clases.Historial;
import Clases.Inventario;
import Clases.Paciente;
import Clases.Producto;

public class BD {

	/**
	 * Método que realiza la conexión con la base de datos
	 * 
	 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
	 * @return Devuelve la conexión a la base de datos
	 */
	public static Connection initBD(String nombreBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);

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
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void crearTablaPaciente(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Paciente(\r\n" + "dni VARCHAR(10) PRIMARY KEY, \r\n"
				+ "nom VARCHAR(25),\r\n" + "apellidos VARCHAR(30), \r\n" + "fechaNacimiento VARCHAR(25), \r\n"
				+ "dir VARCHAR(30), \r\n" + "telf INTEGER, \r\n" + "gen VARCHAR(10)\r\n" + ")";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Crea la tabla paciente--------------*/
	public static void crearTablaDentista(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Dentista (\r\n" + "dni  VARCHAR(10) PRIMARY KEY, \r\n"
				+ "nom  VARCHAR(25),\r\n" + "apellidos  VARCHAR(25), \r\n" + "fechaNacimiento VARCHAR(25), \r\n"
				+ "telf INTEGER, \r\n" + "gen VARCHAR(10)\r\n" + ")\r\n" + "";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Crea la tabla producto--------------*/
	public static void crearTablaProducto(Connection con) {

		String sql = "CREATE TABLE IF NOT EXISTS Producto (\r\n" + "cod_p INTEGER PRIMARY KEY, \r\n"
				+ "nom VARCHAR(20), \r\n" + "desc VARCHAR(40), \r\n" + "precio FLOAT)";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Crea la tabla inventario--------------*/
	public static void crearTablaInventario(Connection con, int cod, String nom) {

		String sql = "CREATE TABLE IF NOT EXISTS Inventario (\r\n"
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \r\n" + "cod_p INTEGER, \r\n"
				+ "nom VARCHAR(20), \r\n" + "cantidad INTEGER, \r\n"
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

	/*---------Crea la tabla cita--------------*/
	public static void crearTablaCita(Connection con, int cod, String nom) {

		String sql = "CREATE TABLE IF NOT EXISTS Cita (\r\n" + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \r\n"
				+ "dni VARCHAR(10), \r\n" + "nom_p VARCHAR(25),\r\n" + "fyh VARCHAR(25), \r\n" + "tipo VARCHAR(25),\r\n"
				+ "nom_d  VARCHAR(25),\r\n" + "FOREIGN KEY (dni) REFERENCES Paciente(dni) ON DELETE CASCADE, \r\n"
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

	/*---------Crea la tabla historial--------------*/
	public static void crearTablaHistorial(Connection con, int cod, String nom) {

		String sql = "CREATE TABLE IF NOT EXISTS Historial (\r\n" + "dni VARCHAR(10)PRIMARY KEY, \r\n"
				+ "nom_p VARCHAR(25),\r\n" + "desc VARCHAR(200),\r\n"
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

	/*---------Añade un paciente a la tabla--------------*/
	public static void anadirPaciente(Connection con, Paciente p) {

		String sql = "INSERT INTO Paciente VALUES ('" + p.getDni() + "', '" + p.getNombre() + "','" + p.getApellido()
				+ "','" + p.getFechaNacimiento() + "', '" + p.getDireccion() + "'," + p.getTelefono() + ", '"
				+ p.getGenero() + "')";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Añade un dentista a la tabla--------------*/
	public static void anadirDentista(Connection con, Dentista d) {

		String sql = "INSERT INTO Dentista VALUES ('" + d.getDni() + "','" + d.getNombre() + "', '" + d.getApellido()
				+ "','" + d.getFechaNacimiento() + "'," + d.getTelefono() + ", '" + d.getGenero() + "', "
				+ d.getSalario() + ")";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Añade un producto a la tabla--------------*/
	public static void anadirProducto(Connection con, Producto prod) {

		String sql = "INSERT INTO Producto VALUES (" + prod.getCodigo() + ", '" + prod.getNombre() + "','"
				+ prod.getDescripcion() + "'," + prod.getPrecio() + ")";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void anadirInventario(Connection con, Inventario inv) {

		// EL ID DEL INVENTARIO ES AUTOINCREMENT
		String sql = "INSERT INTO Inventario (cod_p,nom,cantidad) VALUES (" + inv.getCodigoProducto() + ",'"
				+ inv.getNombreProducto() + "'," + inv.getCantidad() + ")";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Añade una cita a la tabla--------------*/
	public static void anadirCita(Connection con, Cita c) {

		String sql = "INSERT INTO Cita (dni,nom_p,fyh,tipo,nom_d) VALUES ('" + c.getDniPaciente() + "','"
				+ c.getNombrePaciente() + "','" + c.getFecha() + "', '" + c.getTipo() + "','" + c.getNombreDentista()
				+ "')";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Añade un historial a la tabla--------------*/
	public static void anadirHistorial(Connection con, Historial h) {

		String sql = "INSERT INTO Historial VALUES ('" + h.getDni() + "', '" + h.getNombre() + "','" + h.getDesc()
				+ "')";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Borra la tabla paciente--------------*/
	public static void borrarTablaPaciente(Connection con) {
		try {
			Statement st = con.createStatement();
			String sql = "DROP TABLE Paciente";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Borra la tabla dentista--------------*/
	public static void borrarTablaDentista(Connection con) {
		try {
			Statement st = con.createStatement();
			String sql = "DROP TABLE Dentista";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Borra la tabla cita--------------*/
	public static void borrarTablaCita(Connection con) {
		try {
			Statement st = con.createStatement();
			String sql = "DROP TABLE Cita";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Borra la tabla producto--------------*/
	public static void borrarTablaProducto(Connection con) {
		try {
			Statement st = con.createStatement();
			String sql = "DROP TABLE Producto";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Borra la tabla inventario--------------*/
	public static void borrarTablaInventario(Connection con) {
		try {
			Statement st = con.createStatement();
			String sql = "DROP TABLE Inventario";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Borra la tabla historial--------------*/
	public static void borrarTablaHistorial(Connection con) {
		try {
			Statement st = con.createStatement();
			String sql = "DROP TABLE Historial";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*---------Modifica la dirección de una tupla de la tabla paciente--------------*/
	public static void modificarPaciente(Connection con, String dir, String dni) {

		String sentSQL = "UPDATE  paciente set dir='" + dir + "' WHERE dni ='" + dni + "' ";

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}

	}

	/*---------Modifica el teléfono de una tupla de la tabla paciente--------------*/
	public static void modificarDentista(Connection con, int telf, String dni) {

		String sentSQL = "UPDATE  dentista set telf=" + telf + " WHERE dni ='" + dni + "' ";

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}

	}

	/*---------Modifica la cantidad de una tupla de la tabla paciente--------------*/
	public static void modificarInventario(Connection con, int cant, int cod_p) {

		String sentSQL = "UPDATE  inventario set cantidad=" + cant + " WHERE cod_p =" + cod_p + " ";

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}

	}

	/*---------Modifica el precio de una tupla de la tabla paciente--------------*/
	public static void modificarProducto(Connection con, float precio, int cod_p) {
		String sentSQL = "UPDATE producto set precio=" + precio + " WHERE cod_p =" + cod_p + " ";

		Statement stmt = null;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}

	}

	/*---------Modifica la fecha de una tupla de la tabla paciente--------------*/
	public static void modificarCita(Connection con, String fecha, int id) {
		String sentSQL = "UPDATE cita set fyh='" + fecha + "' WHERE id =" + id + "";

		Statement stmt = null;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}

	}

	/*---------Elimina un paciente por DNI--------------*/
	public static void eliminarPacientePorDni(Connection con, String dni) {

		String sentSQL = "DELETE FROM Paciente WHERE dni ='" + dni + "'";

		Statement stmt = null;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}
	}

	/*---------Elimina un producto por CÓDIGO--------------*/
	public static void eliminarProductoPorId(Connection con, int cod_p) {

		String sentSQL = "DELETE FROM Producto WHERE cod_p =" + cod_p + "";

		Statement stmt = null;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}
	}

	/*---------Elimina un inventario por CÓDIGO--------------*/
	public static void eliminarInventarioPorIddeProducto(Connection con, int cod_p) {

		String sentSQL = "DELETE FROM Inventario WHERE cod_p =" + cod_p + "";

		Statement stmt = null;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}
	}

	/*---------Elimina una cita por ID--------------*/
	public static void eliminarCitaPorId(Connection con, int id) {
		String sql = "DELETE FROM Cita WHERE id = " + id + "";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}

	}

	/*---------Elimina un dentista por DNI--------------*/
	public static void eliminarDentistaPorDni(Connection con, String dni) {
		String sentSQL = "DELETE FROM Dentista WHERE dni ='" + dni + "'";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sentSQL);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}
	}

	/*---------Borrar un historial de la bbdd--------------*/
	public static void eliminarHistorialPorDni(Connection con, String dni) {
		String sentSQL = "DELETE FROM Historial WHERE dni ='" + dni + "'";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sentSQL);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}
	}

	/*---------Obtiene la lista de pacientes--------------*/
	public static ArrayList<Paciente> obtenerListaPaciente(Connection con) {
		ArrayList<Paciente> lista = new ArrayList<>();

		try {

			Statement st = con.createStatement();
			String sql = "SELECT * FROM paciente";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String dni = rs.getString("dni");
				String nom = rs.getString("nom");
				String apellidos = rs.getString("apellidos");
				String fechaNacimiento = rs.getString("fechaNacimiento");
				String dir = rs.getString("dir");
				int telf = rs.getInt("telf");
				String gen = rs.getString("gen");
				Paciente p = new Paciente(dni, nom, apellidos, fechaNacimiento, telf, gen, dir);
				lista.add(p);

			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	/*---------Obtiene la lista de dentistas--------------*/
	public static ArrayList<Dentista> obtenerListaDentista(Connection con) {
		ArrayList<Dentista> lista = new ArrayList<>();

		try {

			Statement st = con.createStatement();
			String sql = "SELECT * FROM dentista";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String dni = rs.getString("dni");
				String nom = rs.getString("nom");
				String apellidos = rs.getString("apellidos");
				String fechaNacimiento = rs.getString("fechaNacimiento");
				int telf = rs.getInt("telf");
				String gen = rs.getString("gen");
				int sal = rs.getInt("sal");
				Dentista d = new Dentista(dni, nom, apellidos, fechaNacimiento, telf, gen, sal);
				lista.add(d);

			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	/*---------Obtiene la lista de historiales--------------*/
	public static ArrayList<Historial> obtenerListaHistorial(Connection con) {
		ArrayList<Historial> lista = new ArrayList<>();

		try {

			Statement st = con.createStatement();
			String sql = "SELECT * FROM historial";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String dni = rs.getString("dni");
				String nom = rs.getString("nom");
				String des = rs.getString("des");
				Historial h = new Historial(dni, nom, des);
				lista.add(h);

			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	/*---------Obtiene la lista de productos--------------*/
	public static ArrayList<Producto> obtenerListaProducto(Connection con) {
		ArrayList<Producto> lista = new ArrayList<>();

		try {

			Statement st = con.createStatement();
			String sql = "SELECT * FROM producto";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int cod_p = rs.getInt("cod_p");
				String nom = rs.getString("nom");
				String desc = rs.getString("desc");
				float precio = rs.getFloat("precio");

				Producto p = new Producto(cod_p, nom, desc, precio);
				lista.add(p);

			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

}