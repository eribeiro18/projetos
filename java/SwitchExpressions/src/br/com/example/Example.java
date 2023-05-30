package br.com.example;

import java.util.Scanner;

public class Example {

	public static void main(String[] args) {
		System.out.println("Digite o dia da semana");
		Scanner inpuScanner = new Scanner(System.in);
		String dia = inpuScanner.nextLine();
		inpuScanner.close();
		String resultado = "";
		/*switch (dia) {
			case "segunda":
				resultado = "dia útil";
				break;
			case "terça":
				resultado = "dia útil";
				break;
			case "quarta":
				resultado = "dia útil";
				break;
			case "quinta":
				resultado = "dia útil";
				break;
			case "sexta":
				resultado = "dia útil";
				break;
			case "sabado":
				resultado = "final de semana";
				break;
			case "domingo":
				resultado = "final de semana";
				break;
		default:
			resultado = "dia invalido";
		}*/
		System.out.println(resultado);
		//switch expressions
		resultado = switch (dia) {
			case "segunda" -> "dia útil";
			case "terça" -> "dia útil";
			case "quarta" -> "dia útil";
			case "quinta" -> "dia útil";
			case "sexta" -> "dia útil";
			case "sabado" -> "dia útil";
			case "domingo" -> "dia útil";
			default -> "dia invalido";
		};
		//generalizando as condições
		resultado = switch (dia) {
			case "segunda", "terça", "quarta", "quinta", "sexta" -> "dia útil";
			case "sabado", "domingo" -> "dia útil";
			default -> "dia invalido";
		};
		System.out.println(resultado);
	}
}