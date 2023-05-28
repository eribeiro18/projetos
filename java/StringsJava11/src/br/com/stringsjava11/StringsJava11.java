package br.com.stringsjava11;import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StringsJava11 {

	public static void main(String[] args) {
		//isBlank retorna true mesmo se estiver somente espaço conforme exemplo abaixo
		String str1 = "";
		System.out.println(str1.isBlank());
		String str2 = "            ";
		System.out.println(str2.isBlank());
		String str3 = "Olá mundo";
		System.out.println(str3.isBlank());
		
		//lines remove todos os \n da string
		String str4 = "Java\n Tópicos\n Avançados";
		System.out.println(str4);
		System.out.println(str4.lines().collect(Collectors.toList()));
		
		//repeat repete e comcatena a string
		String str5 = " Java Tópicos Avançados";
		System.out.println(str5.repeat(3));
	}

}
