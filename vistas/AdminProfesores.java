package vistas;

import java.util.Scanner;

import modelos.*;
import utils.Repositorio;

public class AdminProfesores {
	private static Scanner lector = new Scanner(System.in);
	
	public static void menuPrincipal() throws Exception{
		int opcion = 0;
		while (true) {
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
					System.in.read();
					System.exit(0);
				} break;
				
				default: {
					System.out.println("\nOpcion invalida...");
				} break;
			}
		}
	}
	
	public static void registrarProfesor(){
		Profesor p = new Profesor();
		Usuario u = new Usuario();
		u.setRol("Profesor");
		u.setActivo(true);
		try{
			System.out.println("\nIntroduzca los datos del profesor");
			System.out.print("\nNombre: ");
			p.setNombre(lector.nextLine());
			System.out.print("\nApellido: ");
			p.setApellido(lector.nextLine());
			System.out.print("\nCedula: ");
			p.setCedula(lector.nextLine());
			System.out.print("\nEscuela: ");
			String escuela = Repositorio.valorDeLista(Repositorio.escuelas, "\nSeleccione la escuela:", lector);
			p.setEscuela(escuela);
			
			System.out.println("\n\nDatos para el inicio de sesion:");
			System.out.print("\nUsurio: ");
			u.setUsuario(lector.nextLine());
			System.out.print("\nPassword: ");
			u.setPass(lector.nextLine());
			p.setUsuario(u);
		} catch(Exception ex){
			
		}
	}
	
	public static void actualizarProfesor(){
		
	}
	
	public static void eliminarProfesor(){
		
	}
	
	public static void verProfesores(){
		
	}
}