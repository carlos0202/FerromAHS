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
		try {
			profesores = c.obtenerProfesores();
			aulas = c.obtenerAulas();
			asignaturas = c.obtenerAsignaturas();

			System.out.println("\nIntroduzca los datos de la seccion");
			System.out.print("\nAula: ");
			int aula = (int) Repositorio.valorDeLista(aulas,
					"Seleccione el aula:\n", "nombre", lector);
			s.setIdAula(aula);
			System.out.print("\nProfesor: ");
			int prof = (int) Repositorio.valorDeLista(profesores,
					"Seleccione el profesor:\n", "nombre", lector);
			s.setIdProfesor(prof);
			System.out.print("\nAsignatura: ");
			int asig = (int) Repositorio.valorDeLista(asignaturas,
					"Seleccione el asignatura:\n", "nombre", lector);
			s.setIdAsignatura(asig);
			System.out
					.print("\nDias de clase: (ej: 1,2,3 {lunes,martes,miercoles}");
			s.setDias(lector.next());
			System.out
					.print("\nHors asignadas (6-22): (ej: 18-20,18-20,18-21) {l:6-8pm,m:6-8pm,mi:6-9pm} ");
			s.setHoras(lector.next());

			if (!c.registrarSeccion(s)) {
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
		
	}

	public static void eliminarSeccion() {
		
	}

	public static void verSeccions() {
		
	}
}
