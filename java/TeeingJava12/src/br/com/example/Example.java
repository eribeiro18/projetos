package br.com.example;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example {
	
	public static void main(String[] args) {
		
		//Collectors.teeing(soma, qtde, media);
		
		//Nesta caso faz a media para os valores contidos na coleção
		//pode ser criado outras iteração como por exemplo pegar os repedidos, 
		//ou até mesmo o maio ou menor existente na coleção
		double resultado = Stream.of(50, 10, 3, 19, 11, 22, 19).collect(
				Collectors.teeing(Collectors.summarizingDouble(i -> i), 
				Collectors.counting(), (soma, qtde) -> soma.getSum() / qtde));
		
		System.out.println(resultado);
				
		
	}

}
