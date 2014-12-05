package modelos;

import java.util.ArrayList;
import java.util.List;

public class Profesor {
	private int id;
	private String nombre;
	private String apellido;
	private String cedula;
	private String escuela;
	private int idUsuario;
	private Usuario usuario;
	private List<Seccion> secciones;
	
	public Profesor(String nombre, String apellido, String cedula,
			String escuela, int idUsuario) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.escuela = escuela;
		this.idUsuario = idUsuario;
		this.secciones = new ArrayList<Seccion>();
	}

	public Profesor(int id, String nombre, String apellido, String cedula,
			String escuela, int idUsuario, Usuario usuario) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.escuela = escuela;
		this.idUsuario = idUsuario;
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEscuela() {
		return escuela;
	}

	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}
	
	public Seccion getSeccion(int id){
		Seccion s = null;
		
		for(Seccion sec: secciones){
			if(sec.getId() == id){
				s = sec;
			}
		}
		
		return s;
	}

}
