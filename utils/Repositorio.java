package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

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

		return opciones.get(opcion);
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
			String mensajeSuperior, String textoProp, Scanner lector) throws Exception {
		int opcion = 0;
		do {
			System.out.println(mensajeSuperior);

			for(int i = 0; i < opciones.size(); i++){
				String prop = BeanUtils.getProperty(opciones.get(i), textoProp);
				System.out.print("\n" + ++i + ")" + prop);
			}
			System.out.print("\n\nSeleccione una opcion [1-" + opciones.size()
					+ "]: ");
			opcion = lector.nextInt();
		} while (opcion < 1 || opcion > opciones.size());
		
		return  BeanUtils.getProperty(opciones.get(opcion-1), "id");

	}
}
