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
		//mudança agora no java 13 a utilização do yield para receber o valor dentro dos cases
		//yield veio tambem para subsitituir o break
		resultado = switch (dia) {
		case "segunda":
			yield "dia útil";
		case "terça":
			yield "dia útil";
		case "quarta":
			yield "dia útil";
		case "quinta":
			yield "dia útil";
		case "sexta":
			yield "dia útil";
		case "sabado":
			yield "final de semana";
		case "domingo":
			yield "final de semana";
		default:
			yield "dia invalido";
		};
		System.out.println(resultado);
	}
}