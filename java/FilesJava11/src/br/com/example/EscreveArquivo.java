package br.com.example;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class EscreveArquivo {

	public static void main(String[] args) {
		String caminho = "meutexto.txt";
		String meuTexto = "Curso Java Tópicos Avançados";
		/*
		 * Escreve fluxos de caracteres (String)  
		 * Maneira mais simples até então
		 * Indicado para poucas operações de escritas
		 */
		/*try (FileWriter myWriter = new FileWriter(caminho)){
			myWriter.write(meuTexto);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		/*
		 * Grava o texto em uma stream de saida, usando mecanismo de buffer
		 * para gravação mais eficiente de caracteres 
		 */
		
		/*try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(caminho))){
			bufferedWriter.write(meuTexto);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		/*
		 * 
		 * Forma nova writeString da classe file
		 */
		try {
			Files.writeString(new File(caminho).toPath(), meuTexto, StandardCharsets.ISO_8859_1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
