package Controladores;

import AccesoADatos.ClearViewDAO;

public class MainController {
	public boolean conectarBD() {
		ClearViewDAO.getInstance();

		System.out.println(ClearViewDAO.getInstance().connResult);
		if (!ClearViewDAO.getInstance().canConnect) {
			ClearViewDAO.getInstance().crearBase();
			System.out.println(ClearViewDAO.getInstance().connResult);
		}

		return ClearViewDAO.getInstance().canConnect;
	}
}
