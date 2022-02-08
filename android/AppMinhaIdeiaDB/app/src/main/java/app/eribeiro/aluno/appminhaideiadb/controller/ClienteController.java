package app.eribeiro.aluno.appminhaideiadb.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

import app.eribeiro.aluno.appminhaideiadb.datamodel.ClienteDataModel;
import app.eribeiro.aluno.appminhaideiadb.datasource.AppDataBase;
import app.eribeiro.aluno.appminhaideiadb.model.Cliente;

public class ClienteController extends AppDataBase implements ICrud<Cliente>{

    ContentValues value;

    public ClienteController(Context context) {
        super(context);
    }

    @Override
    public boolean incluir(Cliente obj) {
        value = new ContentValues();
        value.put(ClienteDataModel.NOME, obj.getNome());
        value.put(ClienteDataModel.EMAIL, obj.getEmail());
        return true;
    }

    @Override
    public boolean alterar(Cliente obj) {
        value = new ContentValues();
        value.put(ClienteDataModel.ID, obj.getId());
        value.put(ClienteDataModel.NOME, obj.getNome());
        value.put(ClienteDataModel.EMAIL, obj.getEmail());
        return true;
    }

    @Override
    public boolean deletar(Cliente obj) {
        value = new ContentValues();
        value.put(ClienteDataModel.ID, obj.getId());
        return true;
    }

    @Override
    public List<Cliente> listar() {
        return null;
    }
}
