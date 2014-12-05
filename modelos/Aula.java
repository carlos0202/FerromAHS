package modelos;

public class Aula {
	private int id;
	private String nombre;
	private String ubicacion;
	
	public Aula(){
		
	}
	
	public Aula(String nombre, String ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}

	public Aula(int id, String nombre, String ubicacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
}
