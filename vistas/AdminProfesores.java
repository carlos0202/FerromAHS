package vistas;

import java.util.List;
import java.util.Scanner;

import controladores.AdminController;
import modelos.*;
import utils.Repositorio;

public class AdminProfesores {
	private static Scanner lector = new Scanner(System.in);
	private static AdminController c = new AdminController();

	public static void menuPrincipal() throws Exception {
		int opcion = 0;
		while (true) {
			lector = new Scanner(System.in);
			lector.useDelimiter("\r\n");
			Repositorio.clrSrc();
			System.out.println("\r\nAdministracion de profesores");
			System.out.println("Seleccione la opcion deseada:");

			System.out.println("\n1)Registrar Profesor");
			System.out.println("\n2)Actualizar datos de Profesor");
			System.out.println("\n3)Eliminar Profesor");
			System.out.println("\n4)Ver Profesores Registrados");
			System.out.println("\n5)Regresar");
			System.out.println("\n\nSeleccione una opcion[1-5]");
			opcion = lector.nextInt();

			switch (opcion) {
			case 1:
				registrarProfesor();
				break;
			case 2:
				actualizarProfesor();
				break;
			case 3:
				eliminarProfesor();
				break;
			case 4:
				verProfesores();
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

	public static void registrarProfesor() {
		lector = new Scanner(System.in);
		lector.useDelimiter("\r\n");
		Profesor p = new Profesor();
		Usuario u = new Usuario();
		u.setRol("Profesor");
		u.setActivo(true);
		try {
			System.out.println("\nIntroduzca los datos del profesor");
			System.out.print("\nNombre: ");
			p.setNombre(lector.next());
			System.out.print("\nApellido: ");
			p.setApellido(lector.next());
			System.out.print("\nCedula: ");
			p.setCedula(lector.next());
			System.out.print("\nEscuela: ");
			String escuela = Repositorio.valorDeLista(Repositorio.escuelas,
					"\nSeleccione la escuela:", lector);
			p.setEscuela(escuela);

			System.out.println("\n\nDatos para el inicio de sesion:");
			System.out.print("\nUsurio: ");
			u.setUsuario(lector.next());
			System.out.print("\nPassword: ");
			u.setPass(lector.next());
			p.setUsuario(u);
			if (!c.registrarProfesor(p)) {
				System.out
						.println("\nError al registrar los datos. Intente luego.");
			} else {
				System.out.println("Profesor registrado satisfactoriamente...");
				System.out.println("Presione <ENTER> para continuar...");
				lector.next();
				return;
			}
		} catch (Exception ex) {
			System.out
					.println("\nError al registrar los datos. Intente luego.");
		}
	}

	public static void actualizarProfesor() {
		lector = new Scanner(System.in);
		lector.useDelimiter("\r\n");
		try {
			List<Profesor> profesores = c.obtenerProfesores();
			System.out.println("\nProfesores registrados:");
			System.out
					.println("ID\t| Nombre\t| Apellido\t| Cedula\t| Escuela\n");
			for (Profesor p : profesores) {
				System.out.print(p);
			}
			System.out.println("\nSeleccione el profesor (ID):");
			int id = lector.nextInt();
			Profesor p = c.buscarProfesor(id);
			if(p == null){
				System.out.println("\nProfesor no encontrado...");
				System.out.println("Presione <ENTER> para continuar...");
				lector.next();
				return;
			}
			System.out.println("\nIntroduzca los nuevos datos del profesor");
			System.out.print("\nNombre: ");
			p.setNombre(lector.next());
			System.out.print("\nApellido: ");
			p.setApellido(lector.next());
			System.out.print("\nCedula: ");
			p.setCedula(lector.next());
			System.out.print("\nEscuela: ");
			String escuela = Repositorio.valorDeLista(Repositorio.escuelas,
					"\nSeleccione la escuela:", lector);
			p.setEscuela(escuela);

			System.out.println("\n\nDatos para el inicio de sesion:");
			System.out.print("\nUsurio: ");
			p.getUsuario().setUsuario(lector.next());
			System.out.print("\nPassword: ");
			p.getUsuario().setPass(lector.next());
			boolean r = c.actualizarProfesor(p);
			
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

	public static void eliminarProfesor() {

	}

	public static void verProfesores() {
		try {
			List<Profesor> profesores = c.obtenerProfesores();
			System.out.println("\nProfesores registrados:");
			System.out
					.println("ID\t| Nombre\t| Apellido\t| Cedula\t| Escuela\n");
			for (Profesor p : profesores) {
				System.out.print(p);
			}
			System.out.println("\nPresione <ENTER> para continuar...");
			lector.next();
		} catch (Exception ex) {

		}
	}
}
