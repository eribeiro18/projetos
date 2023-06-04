package br.com.example;

public class StringEx {

	public static void main(String[] args) {

		String texto = "Olá mundo!\nEste é o módulo 12.";
		
		//adiciona 4 espaços em branco no inicio
		texto = texto.indent(4);
		System.out.println(texto);
		
		//Remove 1 espaço em branco do inicio
		texto = texto.indent(-1);
		System.out.println(texto);
		
		
		String texto2 = "Olá mundo!\nEste é o módulo 12.";
		texto2 = texto2.transform(s -> new StringBuilder(s).reverse().toString());
		System.out.println(texto2);
		
	}

}
