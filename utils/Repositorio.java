package utils;

import java.util.ArrayList;
import java.util.List;

import modelos.Usuario;

public final class Repositorio {
	public static List<String> escuelas;
	public static Usuario logeado;
	
	private Repositorio(){
		escuelas = new ArrayList<String>();
		escuelas.add("Informatica");
		escuelas.add("Matematica");
		escuelas.add("Fisica");
		escuelas.add("Artes");
		escuelas.add("Letras");
	}
	
	
	public static void clrSrc(){
		String r = "\r\n\r\n\r\n\r\n\r\n\r\n" +
				"\r\n\r\n\r\n\r\n\r\n\r\n" +
				"\r\n\r\n\r\n\r\n\r\n\r\n" +
				"\r\n\r\n\r\n\r\n\r\n\r\n";
		
		System.out.println(r);
	}
}
