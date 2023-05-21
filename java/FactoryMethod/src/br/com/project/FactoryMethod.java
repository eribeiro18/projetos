package br.com.project;

import java.util.List;

public class FactoryMethod {

	public static void main(String[] args) {
	//para set e map não pode existir chaves duplicadas
		List<String> lista = List.of("Banana", "Maça", "Pera");
		System.out.println(lista);
	}

}
