package utils;

import java.util.ArrayList;
import java.util.List;
import modelos.*;

public final class Repositorio {
	public static List<String> escuelas;
	public static Usuario logeado;
	public static Profesor profesor;
	
	private Repositorio(){
		escuelas = new ArrayList<String>();
		escuelas.add("Informatica");
		escuelas.add("Matematica");
		escuelas.add("Fisica");
		escuelas.add("Artes");
		escuelas.add("Letras");
	}
	
	
	public static void clrSrc(){
		//String r = "\r\n\r\n\r\n\r\n\r\n\r\n" +
		//		"\r\n\r\n\r\n\r\n\r\n\r\n" +
		//		"\r\n\r\n\r\n\r\n\r\n\r\n" +
		//		"\r\n\r\n\r\n\r\n\r\n\r\n";
		String r = "\r\n\b\r\n\b";
		System.out.println(r);
	}
}
