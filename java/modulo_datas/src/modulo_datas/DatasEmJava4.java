package modulo_datas;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DatasEmJava4 {
	
	public static void main(String[] args) throws ParseException {
	
	
	long dias = ChronoUnit.DAYS.between(LocalDate.parse("2019-01-01"), LocalDate.now()); /* total de dias de uma até até hoje*/
	
	System.out.println("Possuí " + dias + " dias entra a  faixa de data");
		
		
	}

}
