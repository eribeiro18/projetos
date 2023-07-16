package br.com.example;

interface Figura2D{}

//não permiti extends em records ou estender
//não permiti records abstratos
//não pode haver variaveis de instancia na estrutura, ou seja somente variaveis estaticas
//não permiti modificadores de acesso set, pois as variaveis são final e estaticas
//permite implementar interfaces
//permite metodos estaticos

//a partir do java 15 novas regras foram aplicadas
//metodos nativos não podem ser declarados em records
//atributos declarados implicitamente são final e não de ser modificados por reflexão = illegalAccessException
record Retangulo(double largura, double altura) implements Figura2D {
	
	static String saudacao = "Olá eu sou um retangulo!";
	
	public static void imprimeNome() {
		System.out.println(saudacao);
	}
	
}

public class RecordsEx {

	public static void main(String[] args) {
		//records a jvm já entrega uma classe com contrutor padrão 
		//e com get conforme pode ser visto com o exemplo criado acima
		//e a exibição abaixo da utilização do construtor e o get padrão dos atributos
		Retangulo r1 = new Retangulo(300.0, 200.0);
		System.out.println(r1.altura());
		System.out.println(r1.largura());
		
		r1.imprimeNome();
	}

}
