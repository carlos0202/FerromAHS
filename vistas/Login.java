package vistas;

import java.util.Scanner;

import utils.Repositorio;
import controladores.LoginController;

public class Login {
	public static Scanner lector = new Scanner(System.in);
	private static LoginController c = new LoginController();

	public static boolean logUser() {
		boolean valido = false;
		String usuario;
		String pass;
		int intentos = 0;
		lector.useDelimiter("\r\n");

		do {
			try {
				Repositorio.clrSrc();
				System.out.println("\nIniciar Sesion");
				System.out.println("-----------------------------------\n");
				System.out
						.println("\nIntroduzca sus datos de inicio de sesion");
				System.out.println("\nIntroduzca su usuario");
				usuario = lector.next();
				System.out.println("\nIntroduzca su password");
				pass = lector.next();

				valido = c.logUser(usuario, pass);
				if(!valido){
					System.out.println("\nIntentos fallidos: " + ++intentos);
				}
			} catch (Exception ex) {
				System.out.println("\n\nError en los datos, por favor introduzcalos de nuevo.");
				System.out.println("\nIntentos fallidos: " + ++intentos);
				System.out.println("\nPresione Enter para continuar...");
				lector.next();
			}

		} while (!valido && intentos < 3);
		
		return valido;
	}
}
