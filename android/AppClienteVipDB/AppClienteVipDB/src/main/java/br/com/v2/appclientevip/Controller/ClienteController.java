package br.com.v2.appclientevip.Controller;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.List;

import br.com.v2.appclientevip.api.AppDataBase;
import br.com.v2.appclientevip.datamodel.ClienteDataModel;
import br.com.v2.appclientevip.model.Cliente;

public class ClienteController extends AppDataBase {

    private ContentValues dados;

    public ClienteController(@Nullable Context context) {
        super(context);
    }

    public boolean incluir(Cliente obj) {
        dados = new ContentValues();
        dados.put(ClienteDataModel.PRIMEIRO_NOME,obj.getPrimeiroNome());
        dados.put(ClienteDataModel.SOBRE_NOME,obj.getSobreNome());
        dados.put(ClienteDataModel.EMAIL,obj.getEmail());
        dados.put(ClienteDataModel.SENHA,obj.getSenha());
        dados.put(ClienteDataModel.PESSOA_FISICA,obj.isPessoaFisica());
        dados.put(ClienteDataModel.DATA_INC,obj.getDataInc());
        dados.put(ClienteDataModel.DATA_ALT,obj.getDataAlt());
        return this.insert(ClienteDataModel.TABELA, dados);
    }

    public boolean alterar(Cliente obj) {
        dados = new ContentValues();
        dados.put(ClienteDataModel.PRIMEIRO_NOME,obj.getPrimeiroNome());
        dados.put(ClienteDataModel.SOBRE_NOME,obj.getSobreNome());
        dados.put(ClienteDataModel.EMAIL,obj.getEmail());
        dados.put(ClienteDataModel.SENHA,obj.getSenha());
        dados.put(ClienteDataModel.PESSOA_FISICA,obj.isPessoaFisica());
        dados.put(ClienteDataModel.DATA_INC,obj.getDataInc());
        dados.put(ClienteDataModel.DATA_ALT,obj.getDataAlt());
        return this.update(ClienteDataModel.TABELA, dados, obj.getId());
    }

    public boolean deletar(Cliente obj) {
        return this.delete(ClienteDataModel.TABELA, obj.getId());
    }

    public List<Cliente> listar() {
        return this.list(ClienteDataModel.TABELA);
    }
}
