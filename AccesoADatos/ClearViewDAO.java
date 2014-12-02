package AccesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ClearViewDAO {
	private Connection conn;
	private PreparedStatement pStm;
	private Statement stm;
	private static ClearViewDAO _instance;
	public String nombreDB;
	private String URL;
	public String connResult;
	public boolean canConnect;
	private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";

	private ClearViewDAO() {
		nombreDB = "ClearViewDB";
		instanciate();
	}

	private void instanciate() {
		try {
			Class.forName(driver).newInstance();
			URL = String.format("jdbc:derby:%1$s;create=false",
					new Object[] { nombreDB });
			conn = DriverManager.getConnection(URL);
			connResult = "\n* Conexion exitosa con la base de datos "
					+ nombreDB;
			canConnect = true;
		} catch (Exception ex) {
			connResult = "\nError al conectar con la base de datos";
			canConnect = false;
		}
	}

	public static ClearViewDAO getInstance() {
		if (_instance == null) {
			_instance = new ClearViewDAO();
		}

		return _instance;
	}

	public void crearBase() {
		System.out.println("Creando la Base de Datos, por favor Espere.");
		this.conn = null;
		try {
			String cURL = String.format("jdbc:derby:%1$s;create=true",
					new Object[] { nombreDB });
			this.conn = DriverManager.getConnection(cURL);
			System.out
					.println("\n* Driver jdbc:derby cargado para la base de datos "
							+ nombreDB);
			this.stm = conn.createStatement();

			this.stm.execute("CREATE TABLE Credenciales"
					+ "( numCed BIGINT NOT NULL PRIMARY KEY,"
					+ "  primerNombre VARCHAR(20) NOT NULL, "
					+ "  primerApellido VARCHAR(20) NOT NULL, "
					+ "  pena INTEGER NOT NULL)");

			this.conn.close();
			canConnect = true;
			connResult = "\n* Conexion exitosa con la base de datos "
					+ nombreDB;
			System.out
					.println("\n* Creacion exitosa de base datos " + nombreDB);
		} catch (Exception ex) {
			System.out.println("\nError al Crear la Base de datos...");
		}

		finally {
		}
	}
}
