package com.demo;

public class Exemplo01 {

	public static void main(String[] args) {
		
		//classe interna anonima no java 8 ou anteriores é obrigado a informar a tipagem no final da expressão
		//nova feature informa que não há necessidade de informar o tipo exemplo "new SomaTudo<String>()"
		//ficando igual abaixo, onde a tipagem é informada no incio da expressão
		SomaTudo<String> somaString = new SomaTudo<>() {
			
			@Override
			public String soma(String a, String b) {
				return a + b;
			}
		};
		
		System.out.println(somaString.soma("Olá", " mundo!"));
	}

}
