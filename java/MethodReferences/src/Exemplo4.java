//Functional interface
interface Figura3D {
	Quadrado desenha(Double largura, Double altura);
}

class Quadrado {
	
	public Quadrado(Double largura, Double altura) {
		System.out.println("Desenha Quadrado de Largura: " + largura + " e Altura: " + altura);
	}
}
public class Exemplo4 {

	/*Method reference 
	* Referência a um construtor
	* Lembrando neste contexto deve ser similar a quantidade 
	* e os tipos de parametros entre o objeto e interface funcional
	* */
	public static void main(String[] args) {

		Figura3D fig1 = Quadrado::new;
		
		fig1.desenha(10.5, 7.0);
	}

}
