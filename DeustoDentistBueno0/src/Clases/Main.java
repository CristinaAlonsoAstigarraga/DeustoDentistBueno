package Clases;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import BD.BD;

public class Main {

	public static void main(String[] args) {
		
/*
 * HEMOS DEJADO COMENTADOS LOS METODOS DE BORRAR Y MODIFICAR PARA QUE AL EJECUTAR NO DE ERROR
 * CUANDO SE EJECUTA POR PRIMERA VEZ, SE AÑADEN LOS DATOS
 * PARA PROBAR QUE FUNCIONA LOS METODOS DE BORRAR Y MODIFICAR, HACE FALTA DESCOMENTAR LAS LLAMADAS A ESTOS METODOS 
 * SI SE EJECUTA DOS VECES SIN COMENTAR LOS METODOS DE AÑADIR, DARA ERROR POR LAS PRIMARY KEYS, PARA QUE NO SE REPITAN LOS DATOS
 */
		/*Por derfecto en sqqlite la clave externa no están activas de forma predeterminada 
		 * EJECUTAR POR CADA INICIOL DE CONEXION
		 * PRAGMA foreign_keys = ON;
		 */
		
		Connection con = BD.initBD("BaseDatos.db");
		// BD.crearTablas(con);

//		/*---------insertar Paciente a bbdd--------------*/
//		 
//		Paciente p=new Paciente("83736714w","Maria","Gonzalez","15-10-2008","direccion1", 766835174,"mujer");
//		BD.anadirPaciente(con, p);
//		
//		/*---------insertar Cita a bbdd--------------*/
//		
//		
//		Cita c=new Cita("83736714w", "Maria", "Alberto", "13-12-2022",TipoCita.BLANQUEAMIENTO);
//		BD.anadirCita(con,c);
//		
//		
//		/*---------insertar producto a bbdd--------------*/
//		
//		Producto prod=new Producto(23045,"Caja Gomas elasticas","Gomas elasticass ortodoncia",30);
//		BD.anadirProducto(con,prod);
//		
//		/*---------insertar Inventario a bbdd--------------*/
//		
//		Inventario inv=new Inventario(23045,"Caja Gomas elasticas",130);
//		BD.anadirInventario(con,inv);
//		
//		/*---------insertar Dentista a bbdd--------------*/
//		
//		Dentista d=new Dentista("66332386q","Alberto","Martinez","04-12-1990",645678456,"masculino",2330);
//		BD.anadirDentista(con,d);
//		
//		/*---------insertar Historial a bbdd--------------*/
//
//		Historial h=new Historial("83736714w", "Maria", "Tratamiento de blanqueamiento");
//		BD.anadirHistorial(con,h);
//		
//		
//		
//		/*---------Modificar direccion Paciente a bbdd--------------*/
//		String dir="nuevadireccion";
//		String dni="83736514w";
//		//BD.modificarPaciente(con, dir, dni);
//		
//		
//		/*---------Modificar telefono Dentista a bbdd--------------*/
//		int telf=111111;
//	    dni="66332386q";
//	   // BD.modificarDentista(con, telf, dni);
//	    
//	    /*---------Modificar cantidad inventario a bbdd--------------*/
//		int cant=100;
//	    int cod_p=23045;
//	    //BD.modificarInventario(con, cant, cod_p);
//	    
//	    /*---------Modificar precio Producto a bbdd--------------*/
//	  	float precio=20;
//	  	cod_p=23045;
//	  	//BD.modificarProducto(con, precio, cod_p);
//	  	
//	  	/*---------Modificar fecha Cita a bbdd--------------*/
//	  	String fecha="13-12-2022";
//	  	int id=1;
//	  	//BD.modificarCita(con, fecha, id);
//	  	
//		/*---------Eliminar Paciente por DNI a BBDD---------*/
//	  	String dni2 = "83736714w";
//	  	//BD.eliminarPacientePorDni(con, dni2);
//	  	
//	  	/*--------Eliminar Producto por ID a BBDD---------*/
//	  	int id2 = 23045;
//	  	//BD.eliminarProductoPorId(con, id2);
//	  	
//	  	/*Eliminar Inventario por ID de Producto a BBDD-----*/
//	  	int id3 = 23045;
//	  	//BD.eliminarInventarioPorIddeProducto(con, id3);
//	  	
//	  	/*---------Borrar una cita de la bbdd--------------*/	
//	  	int idp = 1;
//	  //BD.eliminarCitaPorId(con, idp);
//	  	
//	  	/*---------Eliminar Dentista por DNI a BBDD---------*/
//	  	String dniD = "66332386q";
//	  //BD.eliminarDentistaPorDni(con, dniD);
//	  	
//	  	/*---------Eliminar Historial por DNI a BBDD---------*/
//	  	String dniP = "83736714w";
//	  //BD.eliminarHistorialPorDni(con, dniP);
//	  	
	  	
		// Select Pacientes
		ArrayList<Paciente> aPersonas = BD.obtenerListaPaciente(con);
		for (Paciente pa : aPersonas) {
			System.out.println(pa);
		}

		// Select Dentistas
		ArrayList<Dentista> aDentistas = BD.obtenerListaDentista(con);
		for (Dentista de : aDentistas) {
			System.out.println(de);
		}

		// Select Historiales
		ArrayList<Historial> aHistoriales = BD.obtenerListaHistorial(con);
		for (Historial hi : aHistoriales) {
			System.out.println(hi);

		}
		
		// Select Productos
		ArrayList<Producto> aProducto = BD.obtenerListaProducto(con);

		for (Producto pro : aProducto) {

			System.out.println(pro);
		}
		
		//Select Inventario
		ArrayList<Inventario> aInventario = BD.obtenerListaInventario(con);
			   for(Inventario i: aInventario) {
			   System.out.println(i);
			   }
			   
		//Select Citas
		ArrayList<Cita> aCitas = BD.obtenerListaCitas(con); 
		for(Cita ci: aCitas) { 
			System.out.println(ci);
			}
		
		
		//Prueba combinacionesProductos
		ArrayList<Producto> productos = BD.obtenerListaProducto(con);
		double dineroDisponible = 2d;
		double dineroSobrante = 1d;
		int repetidos = 0;
		
		List<List<Producto>> resultado = Producto.combinacionesProductos(productos, dineroDisponible, dineroSobrante, repetidos);
		System.out.println(String.format("Combinacions de menos de %.2f€ y sobrante máximo de %.2f€", dineroDisponible, dineroSobrante));
		resultado.forEach(p -> System.out.println(p));
		}

}
