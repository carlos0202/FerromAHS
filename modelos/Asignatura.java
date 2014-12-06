package modelos;

public class Asignatura {
	private int id;
	private String nombre;
	private int cantCreditos;
	private String escuela;
	
	public Asignatura(){
		
	}
	
	public Asignatura(String nombre, int cantCreditos, String escuela) {
		this.nombre = nombre;
		this.cantCreditos = cantCreditos;
		this.escuela = escuela;
	}
	
	public Asignatura(int id, String nombre, int cantCreditos, String escuela) {
		this.id = id;
		this.nombre = nombre;
		this.cantCreditos = cantCreditos;
		this.escuela = escuela;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantCreditos() {
		return cantCreditos;
	}

	public void setCantCreditos(int cantCreditos) {
		this.cantCreditos = cantCreditos;
	}

	public String getEscuela() {
		return escuela;
	}

	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return id + "\t| " + nombre + "\t| "
				+ cantCreditos  + "\t| " + escuela
				+ "\n";
	}
}
