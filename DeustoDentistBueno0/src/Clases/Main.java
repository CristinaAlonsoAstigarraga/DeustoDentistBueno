package Clases;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;

import BD.BD;

public class Main {

	public static void main(String[] args) {
		
		Connection con = BD.initBD("BaseDeDatos.db");
		//BD.crearTablas(con);
		
		/*---------insertar Paciente a bbdd--------------*/
		 
		Paciente p=new Paciente("83736514w","Maria","Gonzalez","15-10-2008",766835174,"mujer","direccion1");
		BD.anadirPaciente(con, p);
		
		/*---------insertar Cita a bbdd--------------*/
		
		Cita c=new Cita("83736514w", "Maria", "Alberto", "13-04-2003","10:45",TipoCita.BLANQUEAMIENTO);

		BD.anadirCita(con,c);
		
		
		/*---------insertar producto a bbdd--------------*/
		
		Producto prod=new Producto(23545,"Caja Gomas elasticas","Gomas elasticass ortodoncia",30);
		BD.anadirProducto(con,prod);
		
		/*---------insertar Inventario a bbdd--------------*/
		
		Inventario inv=new Inventario(23545,"Caja Gomas elasticas",130);
		BD.anadirInventario(con,inv);
		
		/*---------insertar Dentista a bbdd--------------*/
		
		Dentista d=new Dentista("54332356q","Alberto","Martinez","04-12-1990",345678456,"masculino",2330);
		
		BD.anadirDentista(con,d);
	
	}

}
