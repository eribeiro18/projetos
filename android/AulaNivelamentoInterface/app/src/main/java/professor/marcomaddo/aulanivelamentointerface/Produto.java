package professor.marcomaddo.aulanivelamentointerface;

import android.util.Log;

public class Produto implements ICrud{

    private static final String TAG = "Crud";
    private String nome;
    private String fornecedor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public void incluir() {

    }

    @Override
    public void alterar() {

    }

    @Override
    public void deletar() {

        Log.i(TAG, "deletar: Produto");

    }

    @Override
    public void listar() {

        Log.i(TAG, "listar: Produtos");

    }
}
