package br.com.example;

import java.lang.reflect.Field;

class Produto {
	
	private final Integer codigo = 123456;
	
	public Integer getCodigo() {
		return codigo;
	}
}

public class ReflexaoExample {

	public static void main(String[] args) {
		Produto geladeira = new Produto();
		System.out.println(geladeira.getCodigo());
		try {
			//Obter o atributo (field) da classe
			Field atributo = geladeira.getClass().getDeclaredField("codigo");
			
			//Alterar o modificador de acesso para o atributo e torna-lo acessível
			atributo.setAccessible(true);
			
			//Atribuir um novo valor ao atributo
			atributo.set(geladeira, 654321);
			
			//imprimindo o novo valor
			System.out.println(geladeira.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
