package modulo_datas;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;


public class DatasEmJava8 {

	public static void main(String[] args) throws ParseException, InterruptedException {
		
		LocalDate dataAntiga = LocalDate.parse("2019-10-05");
		
		LocalDate dataNova= LocalDate.parse("2021-10-10");
		
		System.out.println("Data antiga é maior que data nova: " + dataAntiga.isAfter(dataNova));
		
		
		System.out.println("Data antiga é anterior a nova: " + dataAntiga.isBefore(dataNova));
		
		System.out.println("Datas são iguais : " + dataAntiga.isEqual(dataNova));
		
		Period periodo = Period.between(dataAntiga, dataNova);
		
		System.out.println("Quantos dias : " + periodo.getDays());
		
		
		System.out.println("Quantos meses : " + periodo.getMonths());
		
		System.out.println("Quantos Anos : " + periodo.getYears());
		
		System.out.println(" Somente meses: " + periodo.toTotalMonths());
		
		System.out.println("Periodo é : " + periodo.getYears() + " anos " + periodo.getMonths() + " meses " + periodo.getDays() + " dias.");

	}

}
