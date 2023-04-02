import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//annotations existe desde antes do java 8, mas com a nova feature 
//tem a possibilidade de incluir mas de uma vez a anotação
//(com valores diferentes igual exempo abaixo) em campos, metodos e etc.
//sem contar que agora a anotação pode ser incluindo em qualquer lugar no codigo java
@Repeatable(Voltagens.class)
@interface Voltagem{
	String tensao();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Voltagens{
	Voltagem[] value();
}

@Voltagem(tensao = "110")
@Voltagem(tensao = "220")
class Produto {
	
	private Integer codigo;
	private String nome;
	private Double preco;
	
	public Produto(Integer codigo, String nome, Double preco) {
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
}

public class Exemplo01 {

	public static void main(String[] args) {
		Voltagem[] volts = Produto.class.getAnnotationsByType(Voltagem.class);
		
		for (Voltagem voltagem : volts) {
			System.out.println(voltagem.tensao());
		}
	}
}
