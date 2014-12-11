package modelos;

public class Seccion {
	private int id;
	private int idAsignatura;
	private int idProfesor;
	private int idAula;
	private String dias;
	private String horas;
	private boolean activa;
	private Asignatura asignatura;
	private Profesor profesor;
	private Aula aula;
	
	public Seccion(){
		
	}
	
	public Seccion(int idAsignatura, int idProfesor, int idAula, String dias,
			String horas, boolean activa) {
		this.idAsignatura = idAsignatura;
		this.idProfesor = idProfesor;
		this.idAula = idAula;
		this.dias = dias;
		this.horas = horas;
		this.activa = activa;
	}

	public Seccion(int id, int idAsignatura, int idProfesor, int idAula,
			String dias, String horas, boolean activa) {
		this.id = id;
		this.idAsignatura = idAsignatura;
		this.idProfesor = idProfesor;
		this.idAula = idAula;
		this.dias = dias;
		this.horas = horas;
		this.activa = activa;
	}

	public int getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(int idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public int getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}

	public int getIdAula() {
		return idAula;
	}

	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return id + "\t| " + horas + "\t| " + dias + "\t| "
				+ profesor.getNombre() + "\t| " + asignatura.getNombre() + "\t| " + aula.getNombre()
				+ "\n";
	}
}
