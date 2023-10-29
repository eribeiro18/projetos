package modulo_datas;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;

public class DatasEmJava7 {

	public static void main(String[] args) throws ParseException, InterruptedException {
		
		Instant inicio = Instant.now();
		
		
		Thread.sleep(2000);/*Pode um processo com tempo qualquer que não conhecemos*/
		
		Instant ifinal = Instant.now();
		
		Duration duracao = Duration.between(inicio, ifinal);
		
		System.out.println(" Duração em nano segundos : " + duracao.toNanos());
		
		
		System.out.println(" Duração em mininutos : " + duracao.toMinutes());
		
		
		System.out.println(" Duração em horas : " + duracao.toHours());
		
		
		System.out.println(" Duração em milisegundoa: " + duracao.toMillis());
		
		
		System.out.println(" Duração em dias: " + duracao.toDays());

	}

}
