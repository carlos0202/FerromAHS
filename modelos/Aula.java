package modelos;

public class Aula {
	private int id;
	private String nombre;
	private String ubicacion;
	
	public String getNombre() {
		return nombre;
	}
	
	public Aula(String nombre, String ubicacion) {
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
	
}
