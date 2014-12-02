package AccesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ClearViewDAO {
	Connection Conn;
	PreparedStatement Stm;
	private static ClearViewDAO _instance;
	public Object nombreDB;
	private String URL;
	
	private ClearViewDAO(String nombreDB){
		this.nombreDB = nombreDB;
		this.instanciate();
	}
	
	private ClearViewDAO(){
		this.nombreDB = "ClearViewDB";
		this.instanciate();
	}
	
	private void instanciate(){
		try{
			URL = String.format("jdbc:derby:%1$s;create=false", new Object[]{nombreDB});
			Conn = DriverManager.getConnection(URL);
			System.out.println("\n* Conexion exitosa con la base de datos " +  nombreDB);
		} catch(Exception ex){
			System.out.println("\nError al conectar con la base de datos");
		}
	}
	
	public static ClearViewDAO getInstance(String nombreDB){
		if(_instance == null){
			if(nombreDB == null){
				_instance = new ClearViewDAO();
			}
			_instance = new ClearViewDAO(nombreDB);
		}
		
		return _instance;
	}
}
