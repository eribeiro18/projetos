package app.modelo.meusclientesrealm.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import app.modelo.meusclientesrealm.R;
import app.modelo.meusclientesrealm.api.AppUtil;
import app.modelo.meusclientesrealm.controller.ClienteORMController;
import app.modelo.meusclientesrealm.model.ClienteORM;

public class MainActivity extends AppCompatActivity {

    ClienteORMController clienteORMController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clienteORMController = new ClienteORMController();

        ClienteORM orm;
/*        for (int i = 0; i < 30; i++){
            orm = new ClienteORM();
            orm.setId(i);
            orm.setNome("Nome Cliente " + i);
            orm.setBairro("Bairro do cliente " + i);
            orm.setCep(100*i);
            orm.setCidade("Cidade do cliente " + i);
            orm.setEmail("Email do cliente " + i);
            orm.setEstado("Estado do cliente " + i);
            orm.setLogradouro("Logradouro do cliente " + i);
            orm.setNumero(i);
            orm.setTelefone("Telefone do cliente " + i);
            orm.setTermoUso(true);

            clienteORMController.insert(orm);
        }*/

/*        List<ClienteORM> result = clienteORMController.list();
        for (ClienteORM c : result) {
            Log.d(AppUtil.TAG, "CLIENTE [ID e NOME] " + c.getId() + " " + c.getNome());
        }*/

        ClienteORM cliente = clienteORMController.getById(1);
        Log.d(AppUtil.TAG, "CLIENTE [ID e NOME] " + cliente.getId() + " " + cliente.getNome());


    }
}