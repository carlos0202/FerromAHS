package controladores;

import java.util.List;
import accesoADatos.ClearViewDAO;
import modelos.*;

public class AdminController {
	public boolean registrarProfesor(Profesor p) throws Exception{
		return ClearViewDAO.getInstance().registrarProfesor(p);
	}
	
	public List<Profesor> obtenerProfesores() throws Exception{
		
		return ClearViewDAO.getInstance().obtenerProfesores();
	}
}
