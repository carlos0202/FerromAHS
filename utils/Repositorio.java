package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;

import modelos.Profesor;
import modelos.Usuario;

public final class Repositorio {
	public static List<String> escuelas;
	public static Usuario logeado;
	public static Profesor profesor;

	static {
		escuelas = new ArrayList<String>();
		escuelas.add("Informatica");
		escuelas.add("Matematica");
		escuelas.add("Fisica");
		escuelas.add("Artes");
		escuelas.add("Letras");
	}

	public static void clrSrc() {
		// String r = "\r\n\r\n\r\n\r\n\r\n\r\n" +
		// "\r\n\r\n\r\n\r\n\r\n\r\n" +
		// "\r\n\r\n\r\n\r\n\r\n\r\n" +
		// "\r\n\r\n\r\n\r\n\r\n\r\n";
		String r = "\r\n\r\n";
		System.out.println(r);
	}

	public static String valorDeLista(List<String> opciones,
			String mensajeSuperior, Scanner lector) {

		int opcion = 0;
		do {
			System.out.println(mensajeSuperior);

			for (int i = 0; i < opciones.size(); i++) {
				System.out.print("\n" + (i + 1) + ")" + opciones.get(i));
			}
			System.out.print("\n\nSeleccione una opcion [1-" + opciones.size()
					+ "]: ");
			opcion = lector.nextInt();
		} while (opcion < 1 || opcion > opciones.size());

		return opciones.get(opcion - 1);
	}

	public static Object valorDeLista(Map<Integer, String> opciones,
			String mensajeSuperior, Scanner lector) {
		Object[] vals = opciones.values().toArray();
		int opcion = 0;
		do {
			System.out.println(mensajeSuperior);

			for (int i = 0; i < opciones.size(); i++) {
				System.out.print("\n" + (i + 1) + ")" + vals[i]);
			}
			System.out.print("\n\nSeleccione una opcion [1-" + opciones.size()
					+ "]: ");
			opcion = lector.nextInt();
		} while (opcion < 1 || opcion > opciones.size());

		return getKeyByValue(opciones, (String) vals[opcion]);
	}

	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	public static <T> Object valorDeLista(List<T> opciones,
			String mensajeSuperior, String textoProp, Scanner lector)
			throws Exception {
		int opcion = 0;
		do {
			System.out.println(mensajeSuperior);

			for (int i = 0; i < opciones.size(); i++) {
				String prop = BeanUtils.getProperty(opciones.get(i), textoProp);
				System.out.print("\n" + ++i + ")" + prop);
			}
			System.out.print("\n\nSeleccione una opcion [1-" + opciones.size()
					+ "]: ");
			opcion = lector.nextInt();
			lector.nextLine();
		} while (opcion < 1 || opcion > opciones.size());

		return BeanUtils.getProperty(opciones.get(opcion - 1), "id");

	}

	private static final String[] dias = { "1) Lunes", "2) Martes", "3) Miercoles",
			"4) Jueves", "5) Viernes", "6) Sabado", "7) Domingo" };
	private static final String patronHora = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

	public static int LeerDia(Scanner lector) {
		int dia = 0;
		while (true) {
			try {
				System.out
						.println("\nSeleccione el dia en que imparte la clase\n");
				for (String s : dias) {
					System.out.println(s);
				}
				System.out.println("\nIntroduzca el dia: [1-7]");
				dia = lector.nextInt();
				if (dia < 1 || dia > 7)
					throw new Exception();
				return dia;
			} catch (Exception ex) {
				System.out.println("Dia invalido...");
			}
		}
	}
	
	public static String LeerHora(Scanner lector, String segmento) {
		String hora;
		while (true) {
			try {
				System.out.println("\nIntroduzca la hora " + segmento + ": [00:00 : 23:59]");
				hora = lector.next();
				Pattern pattern = Pattern.compile(patronHora);
				Matcher matcher = pattern.matcher(hora);
				if (!matcher.matches())
					throw new Exception();
				return hora;
			} catch (Exception ex) {
				System.out.println("Hora invalido...");
			}
		}
	}
}
