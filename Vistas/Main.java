package Vistas;

import Controladores.MainController;

public class Main {
	private static MainController c = new MainController();

	/**
	 * Clase de punto de inicio de la aplicacion
	 */
	public static void main(String[] args) {
		try {
			// Prueba de la conexion con la base de datos...
			boolean result = c.conectarBD();
			System.out.println("\n\nPresione enter para continuar");
			System.in.read();
			if (result) {
				// Mostrar menu principal.
				System.out.println("Bienvenido!");
				System.in.read();
				System.exit(0);
			} else {
				System.out.println("\n\nNo se puede continuar sin BD. xD.");
				System.exit(1);
			}
		} catch (Exception ex) {

		}
	}

}
