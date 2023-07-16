package br.com.example;

class Cat {
	
	private String nome;
	
	public String miar() {
		return "miau";
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}

class Dog {

	private String nome;
	
	public String latir() {
		return "Auau";
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}

public class ExampleInstanceof {

	public static void main(String[] args) {
		
		Object animal = new Cat();
		
		//antes o cast do objeto ocorria dentro da condição, ou seja após o instanceof
		//agora já entrega um objeto feito o cast após a verificação desta forma é somente usar o objeto
		if(animal instanceof Cat cat) {
			String acao = cat.miar();
			System.out.println(acao);
		}else if(animal instanceof Dog dog) {
			String acao = dog.latir();
			System.out.println(acao);
		}
		
	}
}
