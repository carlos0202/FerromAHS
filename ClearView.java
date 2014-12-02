//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.DriverManager;
//import java.io.*;
//import java.util.Scanner;
//
//class BaseDeDatos{
//	public void AbrirOCrear(){
//		if ( conn.exist() ){
//			conectarABase();
//		}
//		else{
//			crearBase();
//		}
//	}
//	
//	public void conectarABase(){
//		String nombreDB = "ClearViewDB";
//		String URL = "jdbc:derby:" + nombreDB + ";create=false";
//		Connection conn = null;
//		try {
//			conn = DriverManager.getConnection(URL);
//			System.out.println("\n* Conexion exitosa con la base de datos " +  nombreDB);
//		} catch(Exception ex){
//			System.out.println("\nError al conectar con la base de datos");
//		}
//		finally {}
//	}
//	
//	public void crearBase(){
//	System.out.println("Creando la Base de Datos, por favor Espere.");
//		String nombreDB = "ClearViewDB";
//		String URL = "jdbc:derby:" + nombreDB + ";create=true";
//		Connection conn = null;
//		try  {    
//			conn = DriverManager.getConnection(URL);
//			System.out.println("\n* Driver jdbc:derby cargado para la base de datos " +  nombreDB);
//			Statement stat = conn.createStatement();
//						
//			stat.execute( "CREATE TABLE Credenciales" +
//                                "( numCed BIGINT NOT NULL PRIMARY KEY," +
//                                "  primerNombre VARCHAR(20) NOT NULL, " +
//                                "  primerApellido VARCHAR(20) NOT NULL, " +
//                                "  pena INTEGER NOT NULL)" 
//                              );
//		
//			conn.close();
//
//			System.out.println("\n* Creacion exitosa de base datos " + nombreDB );
//		}
//	
//		finally  {     }
//	}
//}
//
//class Usuario{
//	Scanner EntradaTeclado = new Scanner(System.in);
//	private String tenerUsuario;
//	private String nuevoUsuario = "Ninguno";
//	private String nuevaContrasena = "";
//	public void tenerUsuario(){
//		System.out.println("Tiene un usuario ? Si(S) o No(N)");
//		tenerUsuario = EntradaTeclado.next();
//		if ( (tenerUsuario.equals("N") || tenerUsuario.equals("n"))) {
//			System.out.println("Necesita Crear un Usuario para acceder al sistema:");
//			System.out.println("Ingrese Su Nuevo Usuario:");
//			nuevoUsuario = EntradaTeclado.next();
//			System.out.println("Ingrese su Nueva Contrasena:");
//			nuevoUsuario = EntradaTeclado.next();
//		}
//	}
//}
//
//class Materia extends Usuario{
//	private int Seccion1;
//	private String Ubicacion1;
//	private int Seccion2;
//	private String Ubicacion2;
//	private int Seccion3 = 03;
//}
//
//class Profesor extends Materia{
//	private String Nombre;
//	private int CodigoDocente;
//	private String Escuela;
//	private int cantDeSecciones;
//	
//	Profesor(String No, int CoDo, String Es, int CanSec){
//		Nombre = No;
//		CodigoDocente = CoDo;
//		Escuela = Es;
//		cantDeSecciones = CanSec;
//	}
//	Profesor(){
//		Nombre = "Ninguno";
//		CodigoDocente = 00;
//		Escuela = "Por Asignar";
//		cantDeSecciones = 00;
//	}
//	
//	public void verEstado(){
//		System.out.println("El nombre del docente es: " + Nombre + 
//		                   "\nSu Codigo Docente es: " + CodigoDocente + 
//						   "\nSu escuela es: " + Escuela + 
//						   "\nLa cantidad de secciones son: " + cantDeSecciones);
//	}
//	
//}
//
//class Aula{
//	
//}
//
//class ClearView{
//	public static void main(String [] fofi) throws Exception {
//		BaseDeDatos.AbrirOCrear();
//	}
//}