package br.com.emailproject.emailproject.util;

public class LogUtil {
	
	private LogUtil() {}
	
	public static void info(String message) {
		System.out.println("INFO: " + message);
	}

	public static void erro(String message) {
		System.out.println("ERRO: " + message);
	}
	
	public static void warn(String message) {
		System.out.println("WARN: " + message);
	}
	
}
