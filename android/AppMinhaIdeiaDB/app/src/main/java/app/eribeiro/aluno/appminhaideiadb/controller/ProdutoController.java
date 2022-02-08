package app.eribeiro.aluno.appminhaideiadb.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

import app.eribeiro.aluno.appminhaideiadb.datamodel.ProdutoDataModel;
import app.eribeiro.aluno.appminhaideiadb.datasource.AppDataBase;
import app.eribeiro.aluno.appminhaideiadb.model.Produto;

public class ProdutoController extends AppDataBase implements ICrud<Produto>{

    ContentValues value;

    public ProdutoController(Context context) {
        super(context);
    }

    @Override
    public boolean incluir(Produto obj) {
        value = new ContentValues();
        value.put(ProdutoDataModel.NOME, obj.getNome());
        value.put(ProdutoDataModel.FORNECEDOR, obj.getFornecedor());
        return true;
    }

    @Override
    public boolean alterar(Produto obj) {
        value = new ContentValues();
        value.put(ProdutoDataModel.ID, obj.getId());
        value.put(ProdutoDataModel.NOME, obj.getNome());
        value.put(ProdutoDataModel.FORNECEDOR, obj.getFornecedor());
        return true;
    }

    @Override
    public boolean deletar(Produto obj) {
        value = new ContentValues();
        value.put(ProdutoDataModel.ID, obj.getId());
        return true;
    }

    @Override
    public List<Produto> listar() {
        return null;
    }

}
