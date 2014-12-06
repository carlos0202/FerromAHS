package controladores;

import java.util.List;

import accesoADatos.ClearViewDAO;
import modelos.*;

public class AdminController {
	public boolean registrarProfesor(Profesor p) throws Exception{
		return ClearViewDAO.getInstance().registrarProfesor(p);
	}
	
	public boolean registrarAsignatura(Asignatura a) throws Exception{
		return ClearViewDAO.getInstance().registrarAsignatura(a);
	}
	
	public List<Profesor> obtenerProfesores() throws Exception{
		
		return ClearViewDAO.getInstance().obtenerProfesores();
	}
	
	public List<Asignatura> obtenerAsignaturas() throws Exception{
		
		return ClearViewDAO.getInstance().obtenerAsignaturas();
	}
	
	public Asignatura buscarAsignatura(int id) throws Exception{
		
		return ClearViewDAO.getInstance().buscarAsignatura(id);
	}
	
	public Profesor buscarProfesor(int id) throws Exception{
		
		return ClearViewDAO.getInstance().boscarProfesor(id);
	}
	
	public boolean actualizarProfesor(Profesor p) throws Exception{
		
		return ClearViewDAO.getInstance().actualizarProfesor(p);
	}
	
	public boolean actualizarAsignatura(Asignatura a) throws Exception{
		
		return ClearViewDAO.getInstance().actualizarAsignatura(a);
	}
	
	public boolean eliminarProfesor(Profesor p) throws Exception{
		
		return ClearViewDAO.getInstance().eliminarProfesor(p);
	}
	
	public boolean eliminarAsignatura(Asignatura a) throws Exception{
		
		return ClearViewDAO.getInstance().eliminarAsignatura(a);
	}
}
