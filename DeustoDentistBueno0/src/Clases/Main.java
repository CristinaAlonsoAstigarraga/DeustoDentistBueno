package Clases;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;

import BD.BD;

public class Main {

	public static void main(String[] args) {
		
		Connection con = BD.initBD("BaseDeDatos.db");
		//BD.crearTablas(con);
		
		/*Esto luego se camba cuando esten los constructores bien hechos*/
		Paciente p=null;
		Cita c=null;
		Dentista d=null;
		
		/*---------insertar Paciente a bbdd--------------*/
		
		/*MIRAR COMO SE PASA LA FECHA */
		// p=new Paciente("83736514w","Maria","Gonzalez","15/10/2008",766835174,"mujer","direccion1");
		BD.anadirPaciente(con, p);
		
		/*---------insertar Cita a bbdd--------------*/
		
		//CONSTRUCTOREES DE FECHA CREO QUE MAL HECHO !!--> porque dos fechas dos horas y porque se pasa el formato de ellas
		// c=new Cita("83736514w", "Maria", "Alberto", "","","", "",TipoCita.BLANQUEAMIENTO);

		BD.anadirCita(con,c);
		
		
		/*---------insertar producto a bbdd--------------*/
		
		Producto prod=new Producto(23545,"Caja Gomas elasticas","Gomas elasticass ortodoncia",30);
		BD.anadirProducto(con,prod);
		
		/*---------insertar Inventario a bbdd--------------*/
		
		Inventario inv=new Inventario(23545,"Caja Gomas elasticas",130);
		BD.anadirInventario(con,inv);
		
		/*---------insertar Inventario a bbdd--------------*/
		
		//String dni, String nombre, String apellido, SimpleDateFormat sdfFecha, int telefono, String genero,float salario) {
		//Dentista d=new Dentista("54332356q","Alberto","Martinez","",345678456,"masculino",2330);
		
		BD.anadirDentista(con,d);
	
	}

}
