package br.com.example;

public class TextBlocksEx {

	public static void main(String[] args) {
		/**
		 * \ barra invertida é pra quando quer tirar as quebras de linhas conforme abaixo 
		 *  mostra tudo em uma linha só	
		 */
		String texto = """
				Olá mundo!\
				Este é o modulo java 14.\
				Curso Java - Topicos Anvançados
				""";
		System.out.println(texto);
		
		/**
		 * \s barra s serve pra adicionar espaços em branco unitarios conforme abaixo	
		 */
		String texto2 = """
				Olá\s\s\s\s mundo!\
				Este é o modulo java 14.\
				Curso Java - Topicos Anvançados
				""";
		System.out.println(texto2);
	}

}
