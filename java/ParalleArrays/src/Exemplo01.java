import java.util.Arrays;

public class Exemplo01 {

	public static void main(String[] args) {
		
		int[] meuArray = {5, 8, 1, 0, 6, 9, 50, -3};
		
		for(int i: meuArray) {
			System.out.print(i+ " ");
		}
		
		Arrays.parallelSort(meuArray);
		
		System.out.println();
		
		for(int i: meuArray) {
			System.out.print(i + " ");
		}
	}

}
