
interface Figura2D{
	void desenha(Double largura, Double altura);
}

class Retangulo {
	public void desenhaRetangulo(Double largura, Double altura) {
		System.out.println("Desenha retângulo de Largura: " + largura + " e Altura: " + altura);
	}
}

public class Exemplo01 {

	//Method reference 
	//Exemplo fazendo referencia a um metodo de intancia de um objeto particular
	public static void main(String[] args) {
		//Lambda expression
		Figura2D fig1 = (l, a) -> System.out.println("Desenha figura de Largura: " + l + " e Altura: " + a);
		fig1.desenha(8.0, 1.5);
		
		//substituindo expressão lambda acima por method Reference
		//method Reference é representado pela expressão :: (dois pontos dois pontos)
		//onde - a direita dele é a classes ou objeto e a esquerda o metodo referenciado exemplo comentado linha abaixo
		//classe ou objeto :: metodo referenciado
		//desta forma seguindo o exemplo acima precisamos de um objeto que contenha 
		//a mesma quantidade de parametros semelhante ao metodo abstrato da interface funcional figura2d
		//neste contexto o objeto será Retangulo e o metodo referenciado será desenhaRetangulo
		Retangulo ret = new Retangulo();
		//sendo assim isso ret::desenhaRetangulo será referencia do metodo abstrato da interface Figura2D.desenha
		//Method reference ficando da forma abaixo(exemplo referência de um metodo de um objeto particular)
		Figura2D fig2 = ret::desenhaRetangulo;
		fig2.desenha(10.0, 2.5);
	}

}
