package Controladores;

import AccesoADatos.ClearViewDAO;

public class MainController {
	public boolean conectarBD() {
		ClearViewDAO.getInstance(null);

		System.out.println(ClearViewDAO.getInstance(null).connResult);
		if (!ClearViewDAO.getInstance(null).canConnect) {
			ClearViewDAO.getInstance(null).crearBase();
			System.out.println(ClearViewDAO.getInstance(null).connResult);
		}

		return ClearViewDAO.getInstance(null).canConnect;
	}
}
