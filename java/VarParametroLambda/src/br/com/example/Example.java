package br.com.example;

import java.util.function.Function;

public class Example {

	public static void main(String[] args) {
		System.out.println("teste");
		Function<String, String> concatena = (String s) -> s + ". ";
		
		Function<String, String> concatenaVar = (var s) -> s + ". ";
		
		System.out.println(concatenaVar.apply("Olá Mundo"));
	}
}