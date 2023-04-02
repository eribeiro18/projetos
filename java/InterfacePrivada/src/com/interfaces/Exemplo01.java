package com.interfaces;

public class Exemplo01 {

	public static void main(String[] args) {
		LogsEventosI log = new LogsEventosI() {};
		log.logError("Erro na execução do sistema");
	}

}
