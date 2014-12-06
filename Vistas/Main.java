package vistas;

import java.util.Scanner;

import utils.Repositorio;
import controladores.MainController;

public class Main {
	private static MainController c = new MainController();
	private static Scanner lector = new Scanner(System.in);

	/**
	 * Clase de punto de inicio de la aplicacion
	 */
	public static void main(String[] args) {
		lector.useDelimiter("\r\n");
		try {
			// Prueba de la conexion con la base de datos...
			boolean result = c.conectarBD();
			boolean valido = false;
			System.out.println("\nSistema de seleccion de Asignaturas");
			if (result) {
				// Mostrar login.
				valido = Login.logUser();

				if (valido) {
					// Mostrar menu de acuerdo al rol
					mostrarMenu();
				} else {
					System.out
							.println("Ha alcanzado el limite de intentos permitidos. \nLa aplicacion se cerrara...");
					lector.next();
					System.exit(0);
				}
			} else {
				System.out
						.println("\n\nNo se pudo obtener conexion con la BD. Saliendo...");
				System.exit(0);
			}
		} catch (Exception ex) {
			System.out
					.println("\n\nNo se pudo obtener conexion con la BD. Saliendo...");
			System.exit(0);
		}
	}

	public static void mostrarMenu() throws Exception {
		int opcion = 0;
		// Administrador
		if (Repositorio.logeado.getRol().equals("Administrador")) {
			while (true) {
				Repositorio.clrSrc();
				System.out.println("\r\nBienvenido "
						+ Repositorio.logeado.getUsuario());
				System.out.println("Seleccione la opcion deseada:");
				
				System.out.println("\n1)Administrar Profesores");
				System.out.println("\n2)Administrar Asignaturas");
				System.out.println("\n3)Administrar Secciones");
				System.out.println("\n4)Administrar Aulas");
				System.out.println("\n5)Administrar Usuarios");
				System.out.println("\n6)Salir de la aplicacion");
				System.out.println("\n\nSeleccione una opcion[1-6]");
				opcion = lector.nextInt();

				switch (opcion) {
					case 1: {// Administrar Profesores
						AdminProfesores.menuPrincipal();
					} break;
					case 2: // Administrar Asignaturas
						AdminAsignaturas.menuPrincipal();
						break;
					case 3: // Administrar Secciones
						AdminSecciones.menuPrincipal();
						break;
					case 4: // Administrar Aulas
						break;
					case 5: // Administrar Usuarios
						break;
					case 6: {
						System.out.println("\nPresione enter para continuar...");
						lector.next();
						System.exit(0);
					} break;
					
					default: {
						System.out.println("\nOpcion invalida...");
					} break;
				}
			}
		}
		// Profesor
		else {
			while (true) {
				Repositorio.clrSrc();
				System.out.println("\r\nBienvenido "
						+ Repositorio.logeado.getUsuario());
				System.out.println("Seleccione la opcion deseada:");
				
				System.out.println("\n1)Inscribir Seccion");
				System.out.println("\n2)Eliminar Seccion");
				System.out.println("\n3)Ver Secciones");
				System.out.println("\n4)Salir");
				System.out.println("\n\nSeleccione una opcion[1-4]");
				opcion = lector.nextInt();

				switch (opcion) {
					case 1:
						break;
					case 2: 
						break;
					case 3: 
						break;
					case 4: {
						System.out.println("\nPresione enter para continuar...");
						lector.next();
						System.exit(0);
					} break;
					
					default: {
						System.out.println("\nOpcion invalida...");
					} break;
				}
			}
		}
	}

}
