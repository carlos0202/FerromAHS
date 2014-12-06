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

	public boolean registrarAsignatura(Asignatura a) throws Exception {
		String query = "INSERT INTO Asignaturas(Nombre,CantCreditos,Escuela) "
				+ "VALUES(?,?,?)";
		try {
			pStm.clearParameters();
			pStm = conn.prepareStatement(query);
			pStm.setString(1, a.getNombre());
			pStm.setInt(2, a.getCantCreditos());
			pStm.setString(3, a.getEscuela());
			pStm.executeUpdate();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean registrarAula(Aula a) throws Exception{
		String query = "INSERT INTO Aulas(Nombre,Ubicacion) "
				+ "VALUES(?,?)";
		try {
			pStm.clearParameters();
			pStm = conn.prepareStatement(query);
			pStm.setString(1, a.getNombre());
			pStm.setString(2, a.getUbicacion());
			pStm.executeUpdate();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean registrarProfesor(Profesor p) throws Exception {
		conn.setAutoCommit(false);
		Savepoint s = conn.setSavepoint();

		try {
			String query = "Insert into Usuarios(Usuario,Pass,Rol,Activo) "
					+ "Values(?,?,?,?)";
			pStm = conn
					.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			Usuario u = p.getUsuario();
			pStm.setString(1, u.getUsuario());
			pStm.setString(2, u.getPass());
			pStm.setString(3, u.getRol());
			pStm.setBoolean(4, u.isActivo());
			pStm.executeUpdate();
			ResultSet rs = pStm.getGeneratedKeys();
			if (rs.next()) {
				p.setIdUsuario(rs.getInt(1));
			}

			query = "Insert into Profesores(Nombre, Apellido, Cedula, Escuela, IdUsuario) "
					+ " Values(?,?,?,?,?)";
			pStm = conn.prepareStatement(query);
			pStm.clearParameters();
			pStm.setString(1, p.getNombre());
			pStm.setString(2, p.getApellido());
			pStm.setString(3, p.getCedula());
			pStm.setString(4, p.getEscuela());
			pStm.setInt(5, p.getIdUsuario());
			pStm.executeUpdate();
			conn.commit();

			return true;
		} catch (Exception ex) {
			conn.rollback(s);
			return false;
		} finally {
			conn.setAutoCommit(true);
		}
	}
	
	public boolean registrarSeccion(Seccion s) throws Exception{
		String query = "INSERT INTO Secciones(IdAsignatura,IdProfesor,IdAula,Dias,Horas,Activa) "
				+ "VALUES(?,?,?,?,?,?)";
		try {
			pStm.clearParameters();
			pStm = conn.prepareStatement(query);
			pStm.setInt(1, s.getIdAsignatura());
			pStm.setInt(2, s.getIdProfesor());
			pStm.setInt(3, s.getIdAula());
			pStm.setString(4, s.getDias());
			pStm.setString(5, s.getHoras());
			pStm.setBoolean(6, true);
			pStm.executeUpdate();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public List<Asignatura> obtenerAsignaturas() throws Exception {
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		String query = "SELECT * FROM Asignaturas";
		pStm = conn.prepareStatement(query);
		ResultSet rs = pStm.executeQuery();

		while (rs.next()) {
			asignaturas.add(new Asignatura(rs.getInt("Id"), rs
					.getString("Nombre"), rs.getInt("CantCreditos"), rs
					.getString("Escuela")));
		}

		return asignaturas;
	}

	public List<Profesor> obtenerProfesores() throws Exception {
		List<Profesor> profesores = new ArrayList<Profesor>();
		String query = "Select * From Profesores";
		pStm = conn.prepareStatement(query);
		ResultSet rs = pStm.executeQuery();

		while (rs.next()) {
			profesores.add(new Profesor(rs.getInt("Id"),
					rs.getString("Nombre"), rs.getString("Apellido"), rs
							.getString("Cedula"), rs.getString("Escuela"), rs
							.getInt("IdUsuario"), null));
		}

		return profesores;
	}
	
	public List<Aula> obtenerAulas() throws Exception{
		List<Aula> aulas = new ArrayList<Aula>();
		String query = "SELECT * FROM Aulas";
		pStm = conn.prepareStatement(query);
		pStm.clearParameters();
		ResultSet rs = pStm.executeQuery();
		
		while (rs.next()) {
			aulas.add(new Aula(rs.getInt("Id"),
					rs.getString("Nombre"),
					rs.getString("Ubicacion")));
		}
		
		return aulas;
	}

	public Asignatura buscarAsignatura(int id) throws Exception {
		Asignatura a = null;
		String query = "SELECT * FROM Asignaturas Where Id = ?";
		pStm = conn.prepareStatement(query);
		pStm.clearParameters();
		pStm.setInt(1, id);
		ResultSet rs = pStm.executeQuery();

		while (rs.next()) {
			a = new Asignatura(rs.getInt("Id"), rs.getString("Nombre"),
					rs.getInt("CantCreditos"), rs.getString("Escuela"));
		}

		return a;
	}
	
	public Aula buscarAula(int id) throws Exception{
		Aula a = null;
		String query = "SELECT * FROM Aulas Where Id = ?";
		pStm = conn.prepareStatement(query);
		pStm.clearParameters();
		pStm.setInt(1, id);
		ResultSet rs = pStm.executeQuery();

		while (rs.next()) {
			a = new Aula(rs.getInt("Id"), rs.getString("Nombre"),
					rs.getString("Ubicacion"));
		}

		return a;
	}

	public Profesor boscarProfesor(int id) throws Exception {
		Profesor p = null;
		String query = "Select p.*, u.Usuario, u.Pass, u.Activo, u.Rol From Profesores p inner join Usuarios u"
				+ " on p.idUsuario = u.Id Where p.Id = ?";
		pStm = conn.prepareStatement(query);
		pStm.clearParameters();
		pStm.setInt(1, id);
		ResultSet rs = pStm.executeQuery();

		while (rs.next()) {
			p = new Profesor(rs.getInt("Id"), rs.getString("Nombre"),
					rs.getString("Apellido"), rs.getString("Cedula"),
					rs.getString("Escuela"), rs.getInt("IdUsuario"),
					new Usuario(rs.getInt("IdUsuario"),
							rs.getString("Usuario"), rs.getString("Pass"), rs
									.getString("Rol"), rs.getBoolean("Activo")));
		}

		return p;
	}

	public boolean actualizarAsignatura(Asignatura a) throws Exception {
		String query = "UPDATE Asignaturas SET Nombre = ?, CantCreditos = ?, "
				+ "Escuela = ? WHERE Id = ?";

		try {
			pStm.clearParameters();
			pStm = conn.prepareStatement(query);
			pStm.setString(1, a.getNombre());
			pStm.setInt(2, a.getCantCreditos());
			pStm.setString(3, a.getEscuela());
			pStm.setInt(4, a.getId());
			pStm.executeUpdate();

			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean actualizarAula(Aula a) throws Exception{
		String query = "UPDATE Aulas SET Nombre = ?, Ubicacion = ? "
				+ "WHERE Id = ?";

		try {
			pStm.clearParameters();
			pStm = conn.prepareStatement(query);
			pStm.setString(1, a.getNombre());
			pStm.setString(2, a.getUbicacion());
			pStm.setInt(3, a.getId());
			pStm.executeUpdate();

			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean actualizarProfesor(Profesor p) throws Exception {
		String query = "UPDATE Profesores SET Nombre = ?, Apellido = ?, "
				+ "Cedula = ?, Escuela = ? Where Id = ?";
		conn.setAutoCommit(false);
		Savepoint s = conn.setSavepoint();

		try {
			pStm.clearParameters();
			pStm = conn.prepareStatement(query);
			pStm.setString(1, p.getNombre());
			pStm.setString(2, p.getApellido());
			pStm.setString(3, p.getCedula());
			pStm.setString(4, p.getEscuela());
			pStm.setInt(5, p.getId());
			pStm.executeUpdate();

			query = "UPDATE Usuarios SET Usuario = ?, Pass = ?, "
					+ "Activo = ? WHERE Id = ?";
			pStm.clearParameters();
			pStm = conn.prepareStatement(query);
			Usuario u = p.getUsuario();

			pStm.setString(1, u.getUsuario());
			pStm.setString(2, u.getPass());
			pStm.setBoolean(3, u.isActivo());
			pStm.setInt(4, u.getId());
			pStm.executeUpdate();
			conn.commit();

			return true;
		} catch (Exception ex) {
			conn.rollback(s);
			return false;
		} finally {
			conn.setAutoCommit(true);
		}
	}

	public boolean eliminarAsignatura(Asignatura a) throws Exception {
		String query = "DELETE FROM Asignaturas WHERE Id = ?";
		try{
			pStm.clearParameters();
			pStm = conn.prepareStatement(query);
			pStm.setInt(1, a.getId());
			pStm.executeUpdate();
			
			return true;
		} catch(Exception ex){
			return false;
		}
	}
	
	public boolean eliminarAula(Aula a) throws Exception{
		String query = "DELETE FROM Aulas WHERE Id = ?";
		try{
			pStm.clearParameters();
			pStm = conn.prepareStatement(query);
			pStm.setInt(1, a.getId());
			pStm.executeUpdate();
			
			return true;
		} catch(Exception ex){
			return false;
		}
	}

	public boolean eliminarProfesor(Profesor p) throws Exception {
		String query = "DELETE FROM Profesores Where Id = ?";
		conn.setAutoCommit(false);
		Savepoint s = conn.setSavepoint();

		try {
			pStm.clearParameters();
			pStm = conn.prepareStatement(query);
			pStm.setInt(1, p.getId());
			pStm.executeUpdate();

			query = "DELETE FROM Usuarios WHERE Id = ?";
			pStm.clearParameters();
			pStm = conn.prepareStatement(query);

			pStm.setInt(1, p.getUsuario().getId());
			pStm.executeUpdate();
			conn.commit();

			return true;
		} catch (Exception ex) {
			conn.rollback(s);
			return false;
		} finally {
			conn.setAutoCommit(true);
		}
	}
}
