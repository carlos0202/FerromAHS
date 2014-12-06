package vistas;

import java.util.List;
import java.util.Scanner;

import controladores.AdminController;
import modelos.*;
import utils.Repositorio;

public class AdminAulas {
	private static Scanner lector = new Scanner(System.in);
	private static AdminController c = new AdminController();

	public static void menuPrincipal() throws Exception {
		lector.useDelimiter("\r\n");
		int opcion = 0;
		while (true) {
			Repositorio.clrSrc();
			System.out.println("\r\nAdministracion de Aulas");
			System.out.println("Seleccione la opcion deseada:");

			System.out.println("\n1)Registrar Aula");
			System.out.println("\n2)Actualizar datos de Aula");
			System.out.println("\n3)Eliminar Aula");
			System.out.println("\n4)Ver Aulas Registrados");
			System.out.println("\n5)Regresar");
			System.out.println("\n\nSeleccione una opcion[1-5]");
			opcion = lector.nextInt();

			switch (opcion) {
			case 1:
				registrarAula();
				break;
			case 2:
				actualizarAula();
				break;
			case 3:
				eliminarAula();
				break;
			case 4:
				verAula();
				break;
			case 5: {
				System.out.println("\nPresione enter para continuar...");
				System.in.read();
				System.exit(0);
			}
				break;

			default: {
				System.out.println("\nOpcion invalida...");
			}
				break;
			}
		}
	}

	public static void registrarAula() {
		Aula a = new Aula();
		Usuario u = new Usuario();
		u.setRol("Aula");
		u.setActivo(true);
		try {
			System.out.println("\nIntroduzca los datos del Aula");
			System.out.print("\nNombre: ");
			a.setNombre(lector.next());
			System.out.print("\nUbicacion: ");
			a.setUbicacion(lector.next());

			System.out.println("\n\nDatos para el inicio de sesion:");
			System.out.print("\nUsurio: ");
			u.setUsuario(lector.next());
			System.out.print("\nPassword: ");
			u.setPass(lector.next());
			if (!c.registrarAula()) {
				System.out.println("\nError al registrar los datos. Intente luego.");
			} else {
				System.out.println("Profesor registrado satisfactoriamente...");
				System.out.println("Presione <ENTER> para continuar...");
				System.in.read();
				return;
			}
		} catch (Exception ex) {
			System.out.println("\nError al registrar los datos. Intente luego.");
		}
	}

	public static void actualizarAula() {

	}

	public static void eliminarAula() {

	}

	public static void verAula() {
		try {
			List<Profesor> profesores = c.obtenerProfesores();
			System.out.println("\nProfesores registrados:");
			System.out.println("ID\t| Nombre\t| Ubicacion\t|");
			for (Aula a : aula) {
				System.out.print(a);
			}
			System.out.println("\nPresione <ENTER> para continuar...");
			System.in.read();
		} catch (Exception ex) {

		}
	}
}
