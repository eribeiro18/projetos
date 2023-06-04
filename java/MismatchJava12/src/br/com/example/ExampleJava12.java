package br.com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ExampleJava12 {

	public static void main(String[] args) {
		File f1 = new File("texto1.txt");
		File f2 = new File("texto2.txt");
		try {
			//mismatch nova feature que devolve o primiero byte que diverge entre os arquivos
			//caso forem identicos devolve -1
			long resultado = Files.mismatch(f1.toPath(), f2.toPath());
			System.out.println(resultado);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
