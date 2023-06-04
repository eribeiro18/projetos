package br.com.example;

import java.text.CompactNumberFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Example1 {

	public static void main(String[] args) {
		
		NumberFormat nf = CompactNumberFormat
			.getCompactNumberInstance(new Locale("pt", "BR"), NumberFormat.Style.SHORT);
		
		System.out.println(nf.format(50000));
	}

}
