package accesoADatos;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelos.*;
import utils.Repositorio;
import utils.SqlRunner;

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
			InputStream scriptInputStream = ClearViewDAO.class
					.getResourceAsStream("/Sql/createDB.sql");
			SqlRunner.runScript(conn, scriptInputStream);

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

	public boolean logUser(String user, String pass) throws Exception {
		boolean valido = false;
		String query = "Select * from Usuarios Where Usuario = ? and Pass = ?";
		pStm = conn.prepareStatement(query);
		pStm.setString(1, user);
		pStm.setString(2, pass);
		ResultSet rs = pStm.executeQuery();

		Usuario usr = null;
		while (rs.next()) {
			usr = new Usuario(rs.getInt("Id"), rs.getString("Usuario"),
					rs.getString("Pass"), rs.getString("Rol"),
					rs.getBoolean("Activo"));
		}
		valido = (usr != null && usr.getUsuario().equals(user));

		if (valido && usr.getRol().equals("Profesor")) {
			query = "Select * from Profesores Where IdUsuario = ?";
			pStm = conn.prepareStatement(query);
			pStm.clearParameters();
			pStm.setInt(1, usr.getId());
			rs = pStm.executeQuery();

			while (rs.next()) {
				Profesor p = new Profesor(rs.getInt("Id"),
						rs.getString("Nombre"), rs.getString("Apellido"),
						rs.getString("Cedula"), rs.getString("Escuela"),
						usr.getId(), usr);
				Repositorio.profesor = p;
			}

		}
		Repositorio.logeado = usr;

		return valido;
	}

	public boolean registrarProfesor(Profesor p) throws Exception {
		conn.setAutoCommit(false);
		Savepoint s = conn.setSavepoint();
		
		try {
			String query = "Insert into Usuarios(Usuario,Pass,Rol,Activo) "
					+ "Values(?,?,?,?)";
			pStm = conn.prepareStatement(query);
			Usuario u = p.getUsuario();
			pStm.setString(1, u.getUsuario());
			pStm.setString(2, u.getPass());
			pStm.setString(3, u.getRol());
			pStm.setBoolean(4, u.isActivo());
			pStm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = pStm.getGeneratedKeys();
			if (rs.next()) {
				p.setIdUsuario(rs.getInt(1));
			}

			query = "Insert into Profesores(Nombre, Apellido, Cedula, Escuela, IdUsuario) "
					+ " Values(?,?,?,?,?)";
			pStm.clearParameters();
			pStm.setString(1, p.getNombre());
			pStm.setString(2, p.getApellido());
			pStm.setString(3, p.getCedula());
			pStm.setString(4, p.getEscuela());
			pStm.setInt(5, p.getIdUsuario());
			pStm.executeQuery();
			conn.commit();

			return true;
		} catch (Exception ex) {
			conn.rollback(s);
			return false;
		} finally {
			conn.setAutoCommit(true);
		}
	}
	
	public List<Profesor> obtenerProfesores() throws Exception{
		List<Profesor> profesores = new ArrayList<Profesor>();
		String query = "Select * From Profesores";
		pStm = conn.prepareStatement(query);
		ResultSet rs = pStm.executeQuery();
		
		while(rs.next()){
			profesores.add(new Profesor(
					rs.getInt("Id"),
					rs.getString("Nombre"),
					rs.getString("Apellido"),
					rs.getString("Cedula"),
					rs.getString("Escuela"),
					rs.getInt("IdUsuario"),
					null
					));
		}
		
		return profesores;
	}
}
