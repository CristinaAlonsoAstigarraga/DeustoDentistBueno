package Clases;

import java.sql.Connection;
import java.util.ArrayList;

import BD.BD;

public class Main {

	public static void main(String[] args) {

		Connection con = BD.initBD("BaseDatos.db");
		// BD.crearTablas(con);

		/*---------insertar Paciente a bbdd--------------*/

		// Paciente p=new
		// Paciente("83736714w","Maria","Gonzalez","15-10-2008",766835174,"mujer","direccion1");
		// BD.anadirPaciente(con, p);

		/*---------insertar Cita a bbdd--------------*/

		// Cita c=new Cita("83736714w", "Maria", "Alberto",
		// "13-04-2003","10:45",TipoCita.BLANQUEAMIENTO);

		// BD.anadirCita(con,c);

		/*---------insertar producto a bbdd--------------*/

		// Producto prod=new Producto(23045,"Caja Gomas elasticas","Gomas elasticass
		// ortodoncia",30);
		// BD.anadirProducto(con,prod);

		/*---------insertar Inventario a bbdd--------------*/

		// Inventario inv=new Inventario(23045,"Caja Gomas elasticas",130);
		// BD.anadirInventario(con,inv);

		/*---------insertar Dentista a bbdd--------------*/

//		Dentista d=new Dentista("66332386q","Jon","Martinez","04-12-1990",345678456,"masculino",2330);

//		BD.anadirDentista(con,d);

		/*---------insertar Historial a bbdd--------------*/

		// Historial h=new Historial("345546w", "nombre","Infeccion muela");

		// BD.anadirHistorial(con,h);

		/*---------Modificar direccion Paciente a bbdd--------------*/
		String dir = "nuevadireccion";
		String dni = "83736514w";
		// BD.modificarPaciente(con, dir, dni);

		/*---------Modificar telefono Dentista a bbdd--------------*/
		int telf = 111111;
		dni = "232222d";
		// BD.modificarDentista(con, telf, dni);

		/*---------Modificar cantidad inventario a bbdd--------------*/
		int cant = 100;
		int cod_p = 23045;
		// BD.modificarInventario(con, cant, cod_p);

		/*---------Modificar precio Producto a bbdd--------------*/
		float precio = 9;
		cod_p = 23045;
		// BD.modificarProducto(con, precio, cod_p);

		/*---------Modificar fecha Cita a bbdd--------------*/
		String fecha = "13-06-2023";
		int id = 1;
		BD.modificarCita(con, fecha, id);

		/*---------Eliminar Paciente por DNI a BBDD---------*/
		String dni2 = "345546w";
		BD.eliminarPacientePorDni(con, dni2);

		/*--------Eliminar Producto por ID a BBDD---------*/
		int id2 = 23045;
		BD.eliminarProductoPorId(con, id2);

		/* Eliminar Inventario por ID de Producto a BBDD----- */
		int id3 = 23045;
		BD.eliminarInventarioPorIddeProducto(con, id3);

		/*---------Borrar una cita de la bbdd--------------*/
		int idp = 1;
		// BD.eliminarCitaPorId(con, idp);

		/*---------Eliminar Dentista por DNI a BBDD---------*/
		String dniD = "54332386q";
		// BD.eliminarDentistaPorDni(con, dniD);

		/*---------Eliminar Historial por DNI a BBDD---------*/
		String dniP = "54332386q";
		// BD.eliminarHistorialPorDni(con, dniP);

		// Select Pacientes
		ArrayList<Paciente> aPersonas = BD.obtenerListaPaciente(con);
		for (Paciente p : aPersonas) {
			System.out.println(p);
		}

		// Select Dentistas
		ArrayList<Dentista> aDentistas = BD.obtenerListaDentista(con);
		for (Dentista d : aDentistas) {
			System.out.println(d);
		}

		// Select Historiales
		ArrayList<Historial> aHistoriales = BD.obtenerListaHistorial(con);
		for (Historial h : aHistoriales) {
			System.out.println(h);

		}
		
		// Select Productos
		ArrayList<Producto> aProducto = BD.obtenerListaProducto(con);

		for (Producto p : aProducto) {

			System.out.println(p);
		}
		
		//Select Inventario
		ArrayList<Inventario> aInventario = BD.obtenerListaInventario(con);
			   for(Inventario i: aInventario) {
			   System.out.println(i);
			   }

	}

}
