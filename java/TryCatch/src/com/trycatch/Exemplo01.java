package com.trycatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Exemplo01 {

	public static void main(String[] args) {
		try {
			Exemplo01.lerArquivoComBlocoTryCatchComRecursoJava9("file.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//forma que era feita antes do java 7 "modo tradicional"
	public static void lerArquivoComBlocoTryCatchFinally(String path) throws IOException {
		String linha = "";
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
			while((linha = br.readLine()) != null) {
				System.out.println(linha);
			}
		} catch (Exception e) {
			throw e;
		}finally {
			if(br != null) {
				br.close();
			}
		}
	}
	
	//forma que está disponivel desde o java 7
	public static void lerArquivoComBlocoTryCatchComRecurso(String path) throws IOException {
		String linha = "";
		try (BufferedReader br = new BufferedReader(new FileReader(path));) {
			while((linha = br.readLine()) != null) {
				System.out.println(linha);
			}
		}
	}
	
	//a partir do java 9 pode ser informado apenas a variavel que contem ou AutoCloseabçe no try
	public static void lerArquivoComBlocoTryCatchComRecursoJava9(String path) throws IOException {
		String linha = "";
		BufferedReader br = new BufferedReader(new FileReader(path));
		try (br) {
			while((linha = br.readLine()) != null) {
				System.out.println(linha);
			}
		}
	}
}
