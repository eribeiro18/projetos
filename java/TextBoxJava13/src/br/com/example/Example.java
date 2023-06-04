package br.com.example;

public class Example {

	public static void main(String[] args) {

		//Forma convencional
		String text = "Olá Mundo!\n" + 
					  "Este é o módulo\n" + 
				      "JAVA 13";
		
		System.out.println(text);
		
		//Nova forma
		String text2 = """
					   Olá Mundo!  
					   Este é o módulo 
					   JAVA 13
					   """;
	
		System.out.println(text2);
	}

}
