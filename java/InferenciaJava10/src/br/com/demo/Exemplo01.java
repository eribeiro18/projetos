package br.com.demo;

import java.util.List;

public class Exemplo01 {

	public static void main(String[] args) {
		//para o java 10 não a necessidade de informar uma tipagem, onde a tipagem será identificada atraves do valor recebido
		//exemplo var i = 0 neste quesito a variavel i será do tipo inteira, já a variavel s sera do tipo string
		var i = 0;
		var s = "teste";
		//regra deve ser uma variavel local
		//regra variavel deve ser sempre inicializada quando declarada
		//pode ser utilizada em for conforme abaixo
		//Enhaced For
		var listaFruta = List.of("Banana", "Maça", "Abacaxi");
		for(var fruta : listaFruta) {
			System.out.println(fruta);
		}
		//Indice laço for
		for(var j = 0; j < listaFruta.size(); j ++) {
			System.out.println(listaFruta.get(j));
		}
		
		//pq fizeram tal mudança já que neste casos pode ser usado a tipagem correspontende, foi feito para tornar o código mais limpo e mais legivel, conforme exemplo abaixo
		ArrayIndexOutOfBoundsException e = new ArrayIndexOutOfBoundsException();
		//neste caso poderia ser conforme abaixo
		var v = new ArrayIndexOutOfBoundsException();
	}

}
