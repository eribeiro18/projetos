package app.modelo.meusclientes.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.modelo.meusclientes.datamodel.ClienteDataModel;
import app.modelo.meusclientes.datasource.AppDataBase;
import app.modelo.meusclientes.model.Cliente;

public class ClienteController extends AppDataBase implements ICrud<Cliente> {

    ContentValues value;

    public ClienteController(Context context) {
        super(context);
    }

    @Override
    public boolean incluir(Cliente obj) throws Exception {
        value = new ContentValues();
        value.put(ClienteDataModel.NOME, obj.getNome());
        value.put(ClienteDataModel.EMAIL, obj.getEmail());
        return insert(ClienteDataModel.TABELA, value);
    }

    @Override
    public boolean alterar(Cliente obj) throws Exception {
        value = new ContentValues();
        value.put(ClienteDataModel.ID, obj.getId());
        value.put(ClienteDataModel.NOME, obj.getNome());
        value.put(ClienteDataModel.EMAIL, obj.getEmail());
        return update(ClienteDataModel.TABELA, value);
    }

    @Override
    public boolean deletar(int id) throws Exception {
        return delete(ClienteDataModel.TABELA, id);
    }

    @Override
    public List<Cliente> listar() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        List<Map<String, String>> list = getAll(ClienteDataModel.TABELA);
        for (Map<String, String> map : list) {
            Cliente c = new Cliente();
            for (Map.Entry<String, String> e : map.entrySet()) {
                if (e.getKey().equals("id")) {
                    c.setId(Integer.parseInt(e.getValue()));
                }
                if (e.getKey().equals("nome")) {
                    c.setNome(e.getValue());
                }
                if (e.getKey().equals("email")) {
                    c.setEmail(e.getValue());
                }
            }
            clientes.add(c);
        }
        return clientes;
    }
}
