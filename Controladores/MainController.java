package controladores;

import accesoADatos.ClearViewDAO;

public class MainController {
	public boolean conectarBD() {
		//Verificar si se puede realizar la conexión con la BD
		if (!ClearViewDAO.getInstance().canConnect) {
			//Crear Base de datos.
			ClearViewDAO.getInstance().crearBase();
			System.out.println(ClearViewDAO.getInstance().connResult);
		}

		return ClearViewDAO.getInstance().canConnect;
	}
}
