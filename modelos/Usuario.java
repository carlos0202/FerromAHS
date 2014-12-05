package modelos;

public class Usuario {
	private int id;
	private String usuario;
	private String pass;
	private String rol;
	private boolean activo;
	
	public Usuario(String usuario, String pass, String rol, boolean activo) {
		this.usuario = usuario;
		this.pass = pass;
		this.rol = rol;
		this.activo = activo;
	}

	public Usuario(int id, String usuario, String pass, String rol,
			boolean activo) {
		this.id = id;
		this.usuario = usuario;
		this.pass = pass;
		this.rol = rol;
		this.activo = activo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getId() {
		return id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
}
