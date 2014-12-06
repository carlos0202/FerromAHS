package vistas;

import java.util.List;
import java.util.Scanner;
import modelos.*;
import utils.Repositorio;
import controladores.AdminController;

public class AdminAsignaturas {
	private static Scanner lector = new Scanner(System.in);
	private static AdminController c = new AdminController();

	public static void menuPrincipal() throws Exception {
		int opcion = 0;
		while (true) {
			lector = new Scanner(System.in);
			lector.useDelimiter("\r\n");
			Repositorio.clrSrc();
			System.out.println("\r\nAdministracion de Asignaturas");
			System.out.println("Seleccione la opcion deseada:");

			System.out.println("\n1)Registrar Asignatura");
			System.out.println("\n2)Actualizar datos de Asignatura");
			System.out.println("\n3)Eliminar Asignatura");
			System.out.println("\n4)Ver Asignaturas Registradas");
			System.out.println("\n5)Regresar");
			System.out.println("\n\nSeleccione una opcion[1-5]");
			opcion = lector.nextInt();

			switch (opcion) {
			case 1:
				registrarAsignatura();
				break;
			case 2:
				actualizarAsignatura();
				break;
			case 3:
				eliminarAsignatura();
				break;
			case 4:
				verAsignaturas();
				break;
			case 5: {
				System.out.println("\nPresione enter para continuar...");
				lector.next();
				return;
			}

			default: {
				System.out.println("\nOpcion invalida...");
			}
				break;
			}
		}
	}

	public static void registrarAsignatura() {
		lector = new Scanner(System.in);
		lector.useDelimiter("\r\n");
		Asignatura a = new Asignatura();
		try {
			System.out.println("\nIntroduzca los datos de la asignatura");
			System.out.print("\nNombre: ");
			a.setNombre(lector.next());
			System.out.print("\nCantidad de Creditos: ");
			a.setCantCreditos(lector.nextInt());
			System.out.print("\nEscuela: ");
			String escuela = Repositorio.valorDeLista(Repositorio.escuelas,
					"\nSeleccione la escuela:", lector);
			a.setEscuela(escuela);

			if (!c.registrarAsignatura(a)) {
				System.out
						.println("\nError al registrar los datos. Intente luego.");
			} else {
				System.out.println("Asignatura registrada satisfactoriamente...");
				System.out.println("Presione <ENTER> para continuar...");
				lector.next();
				return;
			}
		} catch (Exception ex) {
			System.out
					.println("\nError al registrar los datos. Intente luego.");
		}
	}

	public static void actualizarAsignatura() {
		lector = new Scanner(System.in);
		lector.useDelimiter("\r\n");
		try {
			List<Asignatura> asignaturas = c.obtenerAsignaturas();
			System.out.println("\nAsignaturas registradas:");
			System.out
					.println("ID\t| Nombre\t| Creditos\t| Escuela\n");
			for (Asignatura a : asignaturas) {
				System.out.print(a);
			}
			System.out.println("\nSeleccione la asignatura (ID):");
			int id = lector.nextInt();
			Asignatura a = c.buscarAsignatura(id);
			if(a == null){
				System.out.println("\nAsignatura no encontrada...");
				System.out.println("Presione <ENTER> para continuar...");
				lector.next();
				return;
			}
			System.out.println("\nIntroduzca los nuevos datos de la asignatura");
			System.out.print("\nNombre: ");
			a.setNombre(lector.next());
			System.out.print("\nCantidad de Creditos: ");
			a.setCantCreditos(lector.nextInt());
			System.out.print("\nEscuela: ");
			String escuela = Repositorio.valorDeLista(Repositorio.escuelas,
					"\nSeleccione la escuela:", lector);
			a.setEscuela(escuela);
			boolean r = c.actualizarAsignatura(a);
			
			if(r){
				System.out.println("\nDatos actualizados correctamente.");
			} else{
				System.out
				.println("\nError al actualizar los datos. Intente luego.");
			}
			System.out.println("Presione <ENTER> para continuar...");
			lector.next();
			return;
		} catch (Exception ex) {
			System.out
					.println("\nError al actualizar los datos. Intente luego.");
		}
	}

	public static void eliminarAsignatura() {
		lector = new Scanner(System.in);
		lector.useDelimiter("\r\n");
		try {
			List<Asignatura> asignaturas = c.obtenerAsignaturas();
			System.out.println("\nAsignaturas registradas:");
			System.out
					.println("ID\t| Nombre\t| Creditos\t| Escuela\n");
			for (Asignatura a : asignaturas) {
				System.out.print(a);
			}
			System.out.println("\nSeleccione la asignatura (ID):");
			int id = lector.nextInt();
			Asignatura a = c.buscarAsignatura(id);
			if(a == null){
				System.out.println("\nAsignatura no encontrada...");
				System.out.println("Presione <ENTER> para continuar...");
				lector.next();
				return;
			}

			boolean r = c.eliminarAsignatura(a);
			if(r){
				System.out.println("\nAsignatura eliminada correctamente.");
			} else{
				System.out
				.println("\nError al eliminar los datos. Intente luego.");
			}
			System.out.println("Presione <ENTER> para continuar...");
			lector.next();
			return;
		} catch (Exception ex) {
			System.out
					.println("\nError al eliminar los datos. Intente luego.");
		}
	}

	public static void verAsignaturas() {
		try {
			List<Asignatura> asignaturas = c.obtenerAsignaturas();
			System.out.println("\nAsignaturas registradas:");
			System.out
					.println("ID\t| Nombre\t| Creditos\t| Escuela\n");
			for (Asignatura a : asignaturas) {
				System.out.print(a);
			}
			System.out.println("\nPresione <ENTER> para continuar...");
			lector.next();
		} catch (Exception ex) {

		}
	}
}
