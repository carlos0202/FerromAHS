package vistas;

import utils.Repositorio;
import controladores.MainController;

public class Main {
	private static MainController c = new MainController();

	/**
	 * Clase de punto de inicio de la aplicacion
	 */
	public static void main(String[] args) {
		try {
			// Prueba de la conexion con la base de datos...
			boolean result = c.conectarBD();
			boolean valido = false;
			System.out.println("\n\nPresione enter para continuar");
			System.in.read();
			//limpiar consola
			Repositorio.clrSrc();
			if (result) {
				// Mostrar login.
				valido = Login.logUser();
				
				if(valido){
					//Mostrar menu de acuerdo al rol
				}
				else{
					System.out.println("Ha alcanzado el limite de intentos permitidos. \nLa aplicacion se cerrara...");
					System.in.read();
					System.exit(0);
				}
			} else {
				System.out.println("\n\nNo se pudo obtener conexion con la BD. Saliendo...");
				System.exit(0);
			}
		} catch (Exception ex) {
			System.out.println("\n\nNo se pudo obtener conexion con la BD. Saliendo...");
			System.exit(0);
		}
	}

}
