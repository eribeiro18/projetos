package app.daazi.v3.appclientevip.controller;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.List;

import app.daazi.v3.appclientevip.api.AppDataBase;
import app.daazi.v3.appclientevip.datamodel.ClienteDataModel;
import app.daazi.v3.appclientevip.datamodel.ClientePFDataModel;
import app.daazi.v3.appclientevip.model.ClientePF;

public class ClientePFController extends AppDataBase {

    private static final String TABELA = ClientePFDataModel.TABELA;
    private ContentValues dados;

    public ClientePFController(@Nullable Context context) {
        super(context);
    }

    public boolean incluir(ClientePF obj){
        dados = new ContentValues();
        dados.put(ClientePFDataModel.FK, obj.getClienteID());
        dados.put(ClientePFDataModel.CPF, obj.getCpf());
        dados.put(ClientePFDataModel.NOME_COMPLETO, obj.getNomeCompleto());
        return insert(TABELA, dados);
    }

    public boolean alterar(ClientePF obj){
        dados = new ContentValues();
        dados.put(ClientePFDataModel.ID, obj.getId());
        dados.put(ClientePFDataModel.CPF, obj.getCpf());
        dados.put(ClientePFDataModel.NOME_COMPLETO, obj.getNomeCompleto());
        return update(TABELA, dados);
    }

    public boolean deletar(ClientePF obj){
        return delete(TABELA, obj.getId());
    }

    public List<ClientePF> listar(){

        return listClientesPessoaFisica(TABELA);
    }

    public int getUltimoID(){
        return getLastPk(ClientePFDataModel.TABELA);
    }

    public ClientePF getClientePFByIdFK(int fk){
        return getClientePFByFK(ClienteDataModel.TABELA, fk);
    }
}
