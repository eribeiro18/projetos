package br.com.example;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class LerArquivo {

	public static void main(String[] args) {
		String caminho = "meutexto.txt";
		/*
		 * Leitura do fluxos de caracteres (String)  
		 * Maneira mais simples até então
		 * Indicado para poucas operações de leitura
		 */
		/*try (FileReader myReader = new FileReader(caminho)){
			int i ;
			while((i=myReader.read()) != -1) {
				System.out.println((char)i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		/*
		 * Lê o texto em uma stream de saida, usando mecanismo de buffer
		 * para leitura mais eficiente de caracteres 
		 */
		
		/*try (BufferedReader bufferedReader = new BufferedReader(new FileReader(caminho, StandardCharsets.ISO_8859_1))){
			String s;
			while((s=bufferedReader.readLine()) != null) {
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		/*
		 * 
		 * Forma nova readString da classe file
		 */
		try {
			String s = Files.readString(new File(caminho).toPath(),StandardCharsets.ISO_8859_1);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
