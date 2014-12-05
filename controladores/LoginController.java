package controladores;

import accesoADatos.ClearViewDAO;

public class LoginController {
	
	public boolean logUser(String user, String pass) throws Exception{
		
		return ClearViewDAO.getInstance().logUser(user, pass);
	}
}
