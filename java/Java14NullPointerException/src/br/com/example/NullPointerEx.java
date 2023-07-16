package br.com.example;

class Aluno{
	
	private String nome = null;
	
	public Aluno (){}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

public class NullPointerEx {

	public static void main(String[] args) {
		
		Aluno a = new Aluno();
		//a partir da versão 14 é discriminado melhor o erro quando ocorre um nullpointer, indicado o motivo da falha
		a.getNome().substring(0, 1);
	}

}
