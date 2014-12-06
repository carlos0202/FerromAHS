package vistas;

import java.util.List;
import java.util.Scanner;

import controladores.AdminController;
import modelos.*;
import utils.Repositorio;

public class AdminAula {
	private static Scanner lector = new Scanner(System.in);
	private static AdminController c = new AdminController();

	public static void menuPrincipal() throws Exception {
		int opcion = 0;
		while (true) {
			lector = new Scanner(System.in);
			lector.useDelimiter("\r\n");
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

	public static void registrarAula() {
		lector = new Scanner(System.in);
		lector.useDelimiter("\r\n");
		Aula a = new Aula();
		Usuario u = new Usuario();
		u.setRol("Aula");
		u.setActivo(true);
		try {
			System.out.println("\nIntroduzca los datos del aula");
			System.out.print("\nNombre: ");
			a.setNombre(lector.next());
			System.out.print("\nUbicacion: ");
			a.setUbicacion(lector.next());
			if (!c.registrarAula(a)) {
				System.out.println("\nError al registrar los datos. Intente luego.");
			} else {
				System.out.println("Aula registrada satisfactoriamente...");
				System.out.println("Presione <ENTER> para continuar...");
				lector.next();
				return;
			}
		} catch (Exception ex) {
			System.out.println("\nError al registrar los datos. Intente luego.");
		}
	}

	public static void actualizarAula() {
		lector = new Scanner(System.in);
		lector.useDelimiter("\r\n");
		try {
			List<Aula> aula = c.obtenerAulas();
			System.out.println("\nAulas registrados:");
			System.out.println("ID\t| Nombre\t| Ubicacion\n|");
			for (Aula a : aula) {
				System.out.print(a);
			}
			System.out.println("\nSeleccione el aula (ID):");
			int id = lector.nextInt();
			Aula a = c.buscarAula(id);
			if(a == null){
				System.out.println("\nAula no encontrada...");
				System.out.println("Presione <ENTER> para continuar...");
				lector.next();
				return;
			}
			System.out.println("\nIntroduzca los nuevos datos del Aula");
			System.out.print("\nNombre: ");
			a.setNombre(lector.next());
			System.out.print("\nUbicacion: ");
			a.setUbicacion(lector.next());
			boolean r = c.actualizarAula(a);
			
			if(r){
				System.out.println("\nDatos actualizados correctamente.");
			} else{
				System.out.println("\nError al actualizar los datos. Intente luego.");
			}
			System.out.println("Presione <ENTER> para continuar...");
			lector.next();
			return;
		} catch (Exception ex) {
			System.out.println("\nError al actualizar los datos. Intente luego.");
		}
	}

	public static void eliminarAula() {
		lector = new Scanner(System.in);
		lector.useDelimiter("\r\n");
		try {
			List<Aula> aula = c.obtenerAulas();
			System.out.println("\nAulas registrados:");
			System.out.println("ID\t| Nombre\t| Ubicacion\t|");
			for (Aula a : aula) {
				System.out.print(a);
			}
			System.out.println("\nSeleccione el Aula (ID):");
			int id = lector.nextInt();
			Aula a = c.buscarAula(id);
			if(a == null){
				System.out.println("\nAula no encontrado...");
				System.out.println("Presione <ENTER> para continuar...");
				lector.next();
				return;
			}

			boolean r = c.eliminarAula(a);
			if(r){
				System.out.println("\nAula eliminada correctamente.");
			} else{
				System.out.println("\nError al eliminar los datos. Intente luego.");
			}
			System.out.println("Presione <ENTER> para continuar...");
			lector.next();
			return;
		} catch (Exception ex) {
			System.out.println("\nError al eliminar los datos. Intente luego.");
		}
	}

	public static void verAula() {
		try {
			List<Aula> aula = c.obtenerAulas();
			System.out.println("\nAulas registrados:");
			System.out.println("ID\t| Nombre\t| Ubicacion\t|");
			for (Aula a : aula) {
				System.out.print(a);
			}
			System.out.println("\nPresione <ENTER> para continuar...");
			lector.next();
		} catch (Exception ex) {

		}
	}
}