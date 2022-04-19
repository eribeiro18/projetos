package app.br.com.listadecompras.model;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Categoria extends RealmObject {

    @PrimaryKey
    @Index
    private Long id;

    @Required
    private String nome;

    private Float totalDeProdutos;

    private byte[] imagem;

    @Ignore
    private List<String> tituloCategoriaSpinner;

    @Ignore
    private List<Categoria> categorias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getTotalDeProdutos() {
        return totalDeProdutos;
    }

    public void setTotalDeProdutos(Float totalDeProdutos) {
        this.totalDeProdutos = totalDeProdutos;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public List<String> getTituloCategoriaSpinner() {
        return tituloCategoriaSpinner;
    }

    public void setTituloCategoriaSpinner(List<String> tituloCategoriaSpinner) {
        this.tituloCategoriaSpinner = tituloCategoriaSpinner;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
