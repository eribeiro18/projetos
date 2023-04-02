import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Exemplo01 {

	public static void main(String[] args) {
		
		String nomes = " João, Pedro, Maria, Ana, Paulo";
		
		StringTokenizer st = new StringTokenizer(nomes, ",");
		while (st.hasMoreElements()) {
			System.out.println(st.nextElement());
		}
		
		//Esta implementação abaixo faz basicamente o oposto da implementação acima
		StringJoiner sj = new StringJoiner(", ");
		
		sj.add("João");
		sj.add("Pedro");
		sj.add("Maria");
		sj.add("Ana");
		sj.add("Paulo");
		
		System.out.println(sj);
	}

}
