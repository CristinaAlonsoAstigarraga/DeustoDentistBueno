package BD;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JOptionPane;

import Clases.Cita;
import Clases.Dentista;
import Clases.Historial;
import Clases.Inventario;
import Clases.Paciente;
import Clases.Producto;
import Clases.TipoCita;
import Clases.Usuario;

public class BD { 
	/**
	 * Método que realiza la conexión con la base de datos
	 * 
	 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
	 * @return Devuelve la conexión a la base de datos
	 */
	private static Logger logger;
	public static Connection initBD(String nombreBD) {
		String name = null;
		//leer fichero properrties
		Properties p=new Properties();
		try {
			p.load(new FileReader("config.properties"));
			 name=p.getProperty("namesqlite");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//realizar conexion
		Connection con = null;
		try {
			Class.forName(name);
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
	/**
	 * metodo crear la tabla Paciente de las bases de datos
	 * @param con
	 */
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
	/**
	 * metodo crear la tabla dentista de las bases de datos
	 * @param con
	 */
	public static void crearTablaDentista(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Dentista (\r\n" + "dni  VARCHAR(10) PRIMARY KEY, \r\n"
				+ "nom  VARCHAR(25),\r\n" + "apellidos  VARCHAR(25), \r\n" + "fechaNacimiento VARCHAR(25), \r\n"
				+ "telf INTEGER, \r\n" + "gen VARCHAR(10)\r\n" + "salario INTEGER, \r\n" + ")";

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
	/**
	 * metodo crear la tabla producto de las bases de datos
	 * @param con
	 */
	public static void crearTablaProducto(Connection con) {

		String sql = "CREATE TABLE IF NOT EXISTS Producto (\r\n" + "cod_p INTEGER PRIMARY KEY, \r\n"
				+ "nom VARCHAR(20), \r\n" + "desc VARCHAR(40), \r\n" + "precio FLOAT, \r\n" + "cantidad INTEGER)";

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
	/**
	 * metodod para crear tabla imnventario
	 * @param con
	 * @param cod
	 * @param nom
	 */
	
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
	/**
	 * metodo crear la tabla Paciente de las bases de datos
	 * @param con
	 * @param cod
	 * @param nom
	 */
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
	/**
	 * Metodo para crear la tabla historial
	 * @param con
	 * @param cod
	 * @param nom
	 */
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
	/**
	 * Metodo para añadir un paciente a la bbdd
	 * @param con
	 * @param p
	 */
	public static void anadirPaciente(Connection con, Paciente p) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("fecha:" + sdf1.format(p.getFechaNacimiento()));
		String sql = "INSERT INTO Paciente VALUES ('"+p.getDni()+ "','"+p.getNombre()+"','"+p.getApellido()+"','"+sdf1.format(p.getFechaNacimiento())+"','"+p.getDireccion()+"', " +p.getTelefono()+ ", '"+p.getGenero()+"')";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "anadirPaciente: error al añadir un paciente", e );
		}
	}

	/*---------Añade un dentista a la tabla--------------*/
	/**
	 * añadir un nuevo dentista a la bbdd
	 * @param con conexion de la base de datos
	 * @param d objento de tipo dentista 
	 */
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
			log( Level.SEVERE, "anadirDentista: error al añadir un dentista", e );
		}
	}

	/*---------Añade un producto a la tabla--------------*/
	/**
	 * metodo para añadir un producto
	 * @param con concexion de la bbdd
	 * @param prod objeto de tipo producto
	 */
//	public static void anadirProducto(Connection con, Producto prod) {
//
//		String sql = "INSERT INTO Producto (cod_p,nom,desc,precio,cantidad) VALUES (" + prod.getCodigo() + ", '" + prod.getNombre() + "','"
//				+ prod.getDescripcion() + "'," + prod.getPrecio() + "'," + prod.getCantidad() + ")";
//
//		try {
//			Statement st = con.createStatement();
//			st.executeUpdate(sql);
//			st.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static void anadirProducto(Connection con, int cod_p, String nom, String desc, float precio, int cantidad) {

		String sql = "INSERT INTO Producto (cod_p,nom,desc,precio,cantidad) VALUES ('" + cod_p + "', '" + nom + "','" + desc + "','" + precio + "','" + cantidad + "')";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "anadirProducto: error al añadir un producto", e );
		}
	}
	/**
	 * metodo para añadir un nuevo inventario a la bbdd
	 * @param con conexion de la bbdd
	 * @param inv objeto de tipo inventario
	 */
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
			log( Level.SEVERE, "anadirInventario: error al añadir un inventario", e );
		}
	}

	/*---------Añade una cita a la tabla--------------*/
	/**
	 * metodo ppara añadir cita
	 * @param con conexion de bbdd
	 * @param c objeto de tipo cita
	 */
	public static void anadirCita(Connection con, Cita c) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		System.out.println("fecha:" + sdf.format(c.getFecha()));
		String sql = "INSERT INTO Cita (dni,nom_p,fyh,tipo,nom_d) VALUES ('" + c.getDniPaciente() + "','"
				+ c.getNombrePaciente() + "','" + sdf.format(c.getFecha()) + "', '" + c.getTipo().toString() + "','"
				+ c.getNombreDentista() + "')";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "anadirCita: error al añadir una cita", e );
		}
	}

	/*---------Añade un historial a la tabla--------------*/
	/**
	 * metodo para añadir un nuevo historial
	 * @param con conexion de bbdd
	 * @param h objeto de tipo historial
	 */
	public static void anadirHistorial(Connection con, Historial h) {

		String sql = "INSERT INTO Historial VALUES ('" + h.getDni() + "', '" + h.getNombre() + "','" + h.getDesc()
				+ "')";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "anadirHistorial: error al añadir un", e );
		}
	}

	/*---------Borra la tabla paciente--------------*/
	/**
	 * metodo para borrar la tabla paciente
	 * @param con
	 */
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
	/**
	 * metodo para borrar la tabla dentista
	 * @param con
	 */
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
	/**
	 * metodo para borrar la tabla cita
	 * @param con
	 */
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
	/**
	 * metodo para borrar la tabla producto
	 * @param con
	 */
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
	/**
	 * metodo para borrar la tabla inventario
	 * @param con
	 */
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
	/**
	 * metodo para borrar la tabla historial
	 * @param con
	 */
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

	/**
	 * metodo para borrar una cita 
	 * @param con concexion de bbdd
	 * @param c objeto de tipo cita
	 */
	public static void borrarCita(Connection con,Cita c) {
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM  Cita WHERE id="+c.getId()+"";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			log( Level.INFO, "Cita borrada" , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo para borrar un producto 
	 * @param con concexion de bbdd
	 * @param p objeto de tipo produtco
	 */
	public static void borrarProducto(Connection con, Producto p) {
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM Producto WHERE cod_p="+p.getCodigo()+"";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			log( Level.INFO, "Producto con codigo:"+p.getCodigo()+" elimiando" , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo para borrar un historial
	 * @param con conexion con la bbdd
	 * @param h objeto de tipo historial
	 */
	public static void borrarHistorial(Connection con, Historial h) {
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM Historial WHERE dni='"+h.getDni()+"'";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	/*----------Modificacion de alguna tupla de la tabla de pacientes------------*/
	/**
	 * Metodo para modificar tupla de  un paciente
	 * @param con
	 * @param dni
	 * @param nom
	 * @param ape
	 * @param fechaNacimiento
	 * @param dir
	 * @param telf
	 * @param gen
	 */
	public static void modificarTuplaPaciente(Connection con, String dni, String nom, String ape, String fechaNacimiento, String dir, int telf, String gen) {

		String sql = "UPDATE Paciente SET nom = '" + nom + "', apellidos = '" + ape + "', fechaNacimiento = '" + fechaNacimiento + "', dir = '" + dir + "', telf = "
				+ telf + ", gen = '" + gen + "' WHERE dni ='" + dni + "'";
		try {

			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
			log( Level.INFO, "Paciente modificado" , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}
	}

	/*---------Modifica la dirección de una tupla de la tabla paciente--------------*/
	/**
	 * metodo para modificar solo la direcion del paciente
	 * @param con conexion de bbdd
	 * @param dir direccion del paciente
	 * @param dni dni del paciente
	 */
	public static void modificarPaciente(Connection con, String dir, String dni) {

		String sentSQL = "UPDATE  paciente set dir='" + dir + "' WHERE dni ='" + dni + "' ";

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
			log( Level.INFO, "Paciente modificado" , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}

	}

	/*---------Modifica el teléfono de una tupla de la tabla paciente--------------*/
	/**
	 * metodo para modificar un dentista
	 * @param con conexion de bbdd
	 * @param telf telefono del dentista
	 * @param dni dni del dentista
	 */
	public static void modificarDentista(Connection con, int telf, String dni) {

		String sentSQL = "UPDATE  dentista set telf=" + telf + " WHERE dni ='" + dni + "' ";

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
			log( Level.INFO, "Dentista modificado" , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}

	}

	/*---------Modifica la cantidad de una tupla de la tabla paciente--------------*/
	/**
	 * metodo para modificar inventario
	 * @param con concexion de bbdd
	 * @param cant cantidad del producto
	 * @param cod_p codigo del producto
	 */
	public static void modificarInventario(Connection con, int cant, int cod_p) {

		String sentSQL = "UPDATE  inventario set cantidad=" + cant + " WHERE cod_p =" + cod_p + " ";

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
			log( Level.INFO, "Inventario modificado" , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}

	}

	/*---------Modifica el precio de una tupla de la tabla paciente--------------*/
	
//	public static void modificarProducto(Connection con, float precio, int cod_p) {
//		String sentSQL = "UPDATE producto set precio=" + precio + " WHERE cod_p =" + cod_p + " ";
//
//		Statement stmt = null;
//
//		try {
//			stmt = con.createStatement();
//			stmt.executeUpdate(sentSQL);
//			stmt.close();
//			log( Level.INFO, "Producto modificado" , null );
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
//		}
//
//	}
	
	public static void modificarTuplaProducto(Connection con, int cod_p, String nom, String desc, float precio, int cantidad) {

		String sql = "UPDATE Producto SET nom = '" + nom + "', desc = '" + desc + "', precio = " + precio + ", cantidad = " + cantidad + " WHERE cod_p = "+ cod_p +" ";
		try {

			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
			log( Level.INFO, "Producto modificado" , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}
	}

	/*---------Modifica la fecha de una tupla de la tabla paciente--------------*/
	/**
	 * metodo para modificar unca cita
	 * @param con conexion de bbdd
	 * @param fecha fecha de la cita
	 * @param id id de la cita
	 */
	public static void modificarCita(Connection con, String fecha, int id) {
		String sentSQL = "UPDATE cita set fyh='" + fecha + "' WHERE id =" + id + "";

		Statement stmt = null;
		System.out.println(sentSQL);
		try {
			
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
			log( Level.INFO, "Cita modificada" , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}

	}
	
	/**
	 * metodo para modificar el historial de un paciente
	 * @param con conecxion con la bbdd
	 * @param desc descripcion del historial
	 * @param dni dni del paciente
	 */
	public static void modificarHistorial(Connection con, String desc, String dni) {
		String sentSQL = "UPDATE historial SET des ='" +desc +"' WHERE dni ='" + dni +"'";
		Statement stmt = null;
		System.out.println(sentSQL);
		
		try {
			
			stmt = con.createStatement();
			stmt.executeUpdate(sentSQL);
			stmt.close();
			log( Level.INFO, "Historial modificado" , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
		}
	}
	

	// obtener paciente segun dni
	/**
	 * metodo para buscar un paciente segun su dni
	 * @param con conexion de bbdd
	 * @param dni dni del paciente
	 * @return
	 */
	public static String buscarPacientePorDni(Connection con, String dni) {
		String sql = "SELECT * FROM Paciente WHERE dni='" + dni + "'";
		String nombre = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				nombre = rs.getString("nom");
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "buscarPacientePorDni: error en la busqueda del paciente con dni:"+dni, e );
		}
		return nombre;
	}

	/**
	 * Obtener citas segun dni del dentista
	 * @param con conexion de bbdd
	 * @param nombre del dentista a buscar
	 * @return 
	 */
	public static ArrayList<Cita>  buscarCitaPorDentista(Connection con, String nombre) {
		ArrayList<Cita> lista=new ArrayList<>();
		
		Date fechaDate = new Date();

		try {
			TipoCita tipo;
			Statement st = con.createStatement();
			String sql = "SELECT dni, nom_p, fyh, tipo, nom_d FROM cita WHERE nom_d='"+nombre+"'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				String dni = rs.getString("dni");
				String nom_p = rs.getString("nom_p");
				try {
					SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy hh:mm");
					String fyh = rs.getString("fyh");
					fechaDate = formato.parse(fyh);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String t = rs.getString("tipo");
				tipo = TipoCita.valueOf(t);
				String nom_d = rs.getString("nom_d");

				Cita c = new Cita(dni, nom_p, nom_d, fechaDate, tipo);
				lista.add(c);

			}
			rs.close();
			st.close();
			log( Level.INFO, "Citas encontradas con el dentista: "+nombre, null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "buscarCitaPorDentista: error en la busqueda de citas segun dentista con nombre:"+nombre, e );
		}
		return lista;
	}

	
	/**
	 * Obtener productos segun codigo
	 * @param con conexion de bbdd
	 * @param codigo del producto a buscar
	 * @return 
	 */
	public static String buscarProducto(Connection con, int cod_p) {
		String sql = "SELECT * FROM Producto WHERE cod_p='"+cod_p+"'";
		boolean productoEncontrado = false;
		String nombre = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				productoEncontrado = true;
				nombre = rs.getString("nom");
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "buscarProducto: error en la busqeuda del producto con codigo:"+cod_p, e );
		}
//		return productoEncontrado;
		return nombre;
	}
	
	
	public static ArrayList<Historial>  buscarHistorialPorDNI(Connection con, String dni) {
		String sql = "SELECT * FROM Historial WHERE dni ='"+dni+"'";
		ArrayList<Historial> lista = new ArrayList<>();

		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String nom = rs.getString("nom");
				String des = rs.getString("des");
				Historial h = new Historial(dni, nom, des);
				lista.add(h);

			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "obtenerListaHistorial: error en la obtencion de la lista", e );
		}
		return lista;

		
	}
	
	/**
	 * Metodo para borrar un paciente mediante su DNI
	 * @param con
	 * @param paciente
	 */
	public static void borrarPaciente(Connection con, Paciente paciente) {
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM Paciente WHERE dni='"+paciente.getDni()+"'";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			log( Level.INFO, "Paciente con dni: "+paciente.getDni()+" eliminado" , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
			log( Level.SEVERE, "eliminarPacientePorDni: error en el borrado del paciente con dni:"+paciente.getDni(), e );
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
			log( Level.SEVERE, "eliminarProducto: error en el borrado del producto con codigo:"+cod_p, e );
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
			log( Level.INFO, "Producto eliminado con codigo: "+cod_p , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
			log( Level.SEVERE, "eliminarProducto: error en el borrado del producto con codigo:"+cod_p, e );
		}
	}

	/*---------Elimina una cita por ID--------------*/
	public static void eliminarCitaPorId(Connection con, int id) {
		String sql = "DELETE FROM Cita WHERE id = " + id + "";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
			log( Level.INFO, "Cita eliminada " , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
			log( Level.SEVERE, "eliminarCitaPorId: error en el borrado de cita", e );
		}

	}

	/*---------Elimina un dentista por DNI--------------*/
	public static void eliminarDentistaPorDni(Connection con, String dni) {
		String sentSQL = "DELETE FROM Dentista WHERE dni ='" + dni + "'";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sentSQL);
			st.close();
			log( Level.INFO, "Dentista con dni: "+dni+" eliminado" , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERROR");
			log( Level.SEVERE, "eliminarDentistaPorDni: error en el borrado del dentista con dni:"+dni, e );
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
			log( Level.SEVERE, "eliminarHistorialPorDni: error en el borrado del historial con dni:"+dni, e );
		}
	}

	/*---------Obtiene la lista de pacientes--------------*/
	/**
	 * Metodo para obtener todos los pacientes
	 * @param con
	 * @return
	 */
	public static ArrayList<Paciente> obtenerListaPaciente(Connection con) {

		ArrayList<Paciente> lista = new ArrayList<>();

		Date fechaDate = new Date();

		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM paciente";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String dni = rs.getString("dni");
				String nom = rs.getString("nom");
				String apellidos = rs.getString("apellidos");
				try {
					SimpleDateFormat formato1 = new SimpleDateFormat("dd-MM-yyyy");
					String fechaNacimiento = rs.getString("fechaNacimiento");
					fechaDate = formato1.parse(fechaNacimiento);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String dir = rs.getString("dir");
				int telf = rs.getInt("telf");
				String gen = rs.getString("gen");
				Paciente p = new Paciente(dni, nom, apellidos, fechaDate, telf, gen, dir);
				lista.add(p);

			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "ObtenerListaPacientes: error en la lista de citas", e );
		}
		return lista;
	}

	/*---------Obtiene la lista de dentistas--------------*/
	public static ArrayList<Dentista> obtenerListaDentista(Connection con) {
		ArrayList<Dentista> lista = new ArrayList<>();

		Date fechaDate = new Date();

		try {

			Statement st = con.createStatement();
			String sql = "SELECT * FROM dentista";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String dni = rs.getString("dni");
				String nom = rs.getString("nom");
				String apellidos = rs.getString("apellidos");
				try {
					SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
					String fechaNacimiento = rs.getString("fechaNacimiento");
					// fechaDate = formato.parse(fechaNacimiento);
				} catch (Exception e) {
					e.printStackTrace();
				}
				int telf = rs.getInt("telf");
				String gen = rs.getString("gen");
				int sal = rs.getInt("sal");
				Dentista d = new Dentista(dni, nom, apellidos, fechaDate, telf, gen, sal);
				lista.add(d);

			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "obtenerListaDentista: error en la obtencion de la lista", e );
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
			log( Level.SEVERE, "obtenerListaHistorial: error en la obtencion de la lista", e );
		}
		return lista;
	}

	/*---------Obtiene la lista de productos--------------*/
	public static ArrayList<Producto> obtenerListaProducto(Connection con) {
		ArrayList<Producto> lista = new ArrayList<>();

		try {

			Statement st = con.createStatement();
			String sql = "SELECT * FROM producto ORDER BY cantidad ASC";	//Ordenamos la tabla, arriba los que menos cantidad tengan.
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int cod_p = rs.getInt("cod_p");
				String nom = rs.getString("nom");
				String desc = rs.getString("desc");
				float precio = rs.getFloat("precio");
				int cantidad = rs.getInt("cantidad");

				Producto p = new Producto(cod_p, nom, desc, precio, cantidad);
				lista.add(p);

			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "obtenerListaProducto: error en la obtencion de la lista", e );
		}
		return lista;
	}

	/* Obtiene lista de inventario */
	public static ArrayList<Inventario> obtenerListaInventario(Connection con) {
		ArrayList<Inventario> lista = new ArrayList<>();

		try {

			Statement st = con.createStatement();
			String sql = "SELECT cod_p, nom, cantidad FROM inventario";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int cod_p = rs.getInt("cod_p");
				String nom = rs.getString("nom");
				int cantidad = rs.getInt("cantidad");
				Inventario i = new Inventario(cod_p, nom, cantidad);
				lista.add(i);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "ObtenerListaHistorial:error en la obtencion de la lista", e );
		}
		return lista;
	}

	/* Obtiene lista de citas */
	public static ArrayList<Cita> obtenerListaCitas(Connection con) {
		ArrayList<Cita> lista = new ArrayList<>();

		Date fechaDate = new Date();

		try {
			TipoCita tipo;
			Statement st = con.createStatement();
			String sql = "SELECT * FROM cita";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int id=rs.getInt("id");
				String dni = rs.getString("dni");
				String nom_p = rs.getString("nom_p");
				//boolean atendido=rs.getBoolean("atendido");
				try {
					SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy hh:mm");
					String fyh = rs.getString("fyh");
					fechaDate = formato.parse(fyh);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String t = rs.getString("tipo");
				tipo = TipoCita.valueOf(t);
				String nom_d = rs.getString("nom_d");

				Cita c = new Cita(id,dni, nom_p, nom_d, fechaDate, tipo);
				lista.add(c);

			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "ObtenerListaCitas: error en la lista de citas", e );
		}
		return lista;
	}
	/**
	 * metodo para buscar la descripcion delgistorial de un paciente
	 * @param con conexion de bbdd
	 * @param dni dni del paciente
	 * @return
	 */
	public static String buscarHistorial(Connection con, String dni) {
		String sql = "SELECT * FROM Historial WHERE dni='" + dni + "'";
		String descripcion = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				descripcion = rs.getString("des");
			}
			rs.close();
			st.close();
			log( Level.INFO, "Historial encontrado con exito: " , null );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "BuscarHistorial: error en la busqueda de  historial con dni:"+dni, e );
		}
		return descripcion;
	}
	/**
	 * metodo para comprobar la existencia del usuario en la bbdd
	 * @param con conexion bddd
	 * @param user usuario 
	 * @param password contraseña
	 * @return variable rol de tipo String 
	 */
	public static String comprobarUsuario(Connection con,String user, String password) {
		// TODO Auto-generated method stub
		String rol="";
		String sql = "SELECT * FROM usuario WHERE nick = '" + user +"' AND password='"+password+"'";
		String descripcion = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				rol = rs.getString("Rol");
			}
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log( Level.SEVERE, "ComprobarUsuario: error en la busqueda del usuario", e );
		}
		log( Level.INFO, "Usuario encontrado" , null );
		return rol;
	}

	/**
	 * Metodo de inserccion de un usuario
	 * @param con 
	 * 
	 * @param u objeto de tipo usuario
	 */
	public static void insertarUsuario(Connection con, Usuario u) {

		String sql = "INSERT INTO Usuario( nombre,nick,password,Rol) VALUES ('" + u.getNombre()+ "', '" +u.getNick()+ "','" + u.getContrasenia()+"', '"+u.getRol()+"')";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			//logger.log(Level.SEVERE, "No se pudo crear un nuevo usuario", e);
		}
	}
	
	private static void log( Level level, String msg, Throwable excepcion ) {
		if (logger==null) {  // Logger por defecto local:
			logger = Logger.getLogger( "ficheros-log" );  // Nombre del logger
			logger.setLevel( Level.ALL );  // Loguea todos los niveles
			try {
				
				logger.addHandler( new FileHandler( "ficheros-log.xml", true ) );  // Y saca el log a fichero xml
			} catch (Exception e) {
				logger.log( Level.SEVERE, "No se pudo crear fichero de log", e );
			}
		}
		if (excepcion==null)
			logger.log( level, msg );
		else
			logger.log( level, msg, excepcion );
	}


}