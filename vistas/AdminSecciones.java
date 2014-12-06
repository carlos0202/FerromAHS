package vistas;

import java.util.List;
import java.util.Scanner;
import modelos.*;
import utils.Repositorio;
import controladores.AdminController;

public class AdminSecciones {
	private static Scanner lector = new Scanner(System.in);
	private static AdminController c = new AdminController();
	private static List<Profesor> profesores;
	private static List<Asignatura> asignaturas;
	private static List<Aula> aulas;

	public static void menuPrincipal() throws Exception {
		int opcion = 0;
		while (true) {
			lector = new Scanner(System.in);
			lector.useDelimiter("\r\n");
			Repositorio.clrSrc();
			System.out.println("\r\nAdministracion de Secciones");
			System.out.println("Seleccione la opcion deseada:");

			System.out.println("\n1)Registrar Seccion");
			System.out.println("\n2)Actualizar datos de Seccion");
			System.out.println("\n3)Eliminar Seccion");
			System.out.println("\n4)Ver Secciones Registradas");
			System.out.println("\n5)Regresar");
			System.out.println("\n\nSeleccione una opcion[1-5]");
			opcion = lector.nextInt();

			switch (opcion) {
			case 1:
				registrarSeccion();
				break;
			case 2:
				actualizarSeccion();
				break;
			case 3:
				eliminarSeccion();
				break;
			case 4:
				verSeccions();
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

	public static void registrarSeccion() {
		lector = new Scanner(System.in);
		lector.useDelimiter("\r\n");
		Seccion s = new Seccion();
		profesores = c.obtenerProfesores();
		aulas = c.obtenerAulas();
		asignaturas = c.obtenerAsignaturas();
		try {
			System.out.println("\nIntroduzca los datos de la seccion");
			System.out.print("\nAula: ");
			int aula = (int)Repositorio.valorDeLista(aulas, "Seleccione el aula:\n", "nombre");
			s.setIdAula(aula);
			System.out.print("\nProfesor: ");
			int prof = (int)Repositorio.valorDeLista(profesores, "Seleccione el profesor:\n", "nombre");
			s.setIdProfesor(prof);
			System.out.print("\nAsignatura: ");
			int asig = (int)Repositorio.valorDeLista(asignaturas, "Seleccione el asignatura:\n", "nombre");
			s.setIdAsignatura(asig);
			System.out.print("\nDias de clase: (ej: 1,2,3 {lunes,martes,miercoles}");
			s.setDias(lector.next());
			System.out.print("\nHors asignadas (6-22): (ej: 18-20,18-20,18-21) {l:6-8pm,m:6-8pm,mi:6-9pm} ");
			s.setHoras(lector.next());

			if (!c.registrarSeccion(s);) {
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

	public static void actualizarSeccion() {
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

	public static void eliminarSeccion() {
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

			boolean r = c.eliminarProfesor(p);
			if(r){
				System.out.println("\nProfesor eliminado correctamente.");
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

	public static void verSeccions() {
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
