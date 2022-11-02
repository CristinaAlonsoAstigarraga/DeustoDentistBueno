package Clases;
import java.sql.Connection;


import BD.BD;

public class Main {

	public static void main(String[] args) {
		
		Connection con = BD.initBD("BaseDatos.db");
		//BD.crearTablas(con);
		
		/*---------insertar Paciente a bbdd--------------*/
		 
		//Paciente p=new Paciente("83736714w","Maria","Gonzalez","15-10-2008",766835174,"mujer","direccion1");
		//BD.anadirPaciente(con, p);
		
		/*---------insertar Cita a bbdd--------------*/
		
		
		//Cita c=new Cita("83736714w", "Maria", "Alberto", "13-04-2003","10:45",TipoCita.BLANQUEAMIENTO);

		//BD.anadirCita(con,c);
		
		
		/*---------insertar producto a bbdd--------------*/
		
		//Producto prod=new Producto(23045,"Caja Gomas elasticas","Gomas elasticass ortodoncia",30);
		//BD.anadirProducto(con,prod);
		
		/*---------insertar Inventario a bbdd--------------*/
		
		//Inventario inv=new Inventario(23045,"Caja Gomas elasticas",130);
		//BD.anadirInventario(con,inv);
		
		/*---------insertar Dentista a bbdd--------------*/
		
		//Dentista d=new Dentista("54332386q","Alberto","Martinez","04-12-1990",345678456,"masculino",2330);
		
		//BD.anadirDentista(con,d);
		
		/*---------insertar Historial a bbdd--------------*/

		//Historial h=new Historial("345546w", "nombre","Infeccion muela");
		
		//BD.anadirHistorial(con,h);
		
		
		
		/*---------Modificar direccion Paciente a bbdd--------------*/
		String dir="nuevadireccion";
		String dni="83736514w";
		//BD.modificarPaciente(con, dir, dni);
		
		
		/*---------Modificar telefono Dentista a bbdd--------------*/
		int telf=111111;
	    dni="232222d";
	   // BD.modificarDentista(con, telf, dni);
	    
	    /*---------Modificar cantidad inventario a bbdd--------------*/
		int cant=100;
	    int cod_p=23045;
	    //BD.modificarInventario(con, cant, cod_p);
	    
	
	
	}

}
