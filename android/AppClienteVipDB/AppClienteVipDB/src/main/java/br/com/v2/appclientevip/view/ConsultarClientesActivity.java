package br.com.v2.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.com.v2.appclientevip.R;
import br.com.v2.appclientevip.api.ClienteAdapter;
import br.com.v2.appclientevip.model.Cliente;

public class ConsultarClientesActivity extends AppCompatActivity {
    List<Cliente> clientes;
    ClienteAdapter adapter;
    Cliente obj;
    RecyclerView rvClientesVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_clientes);

        rvClientesVip = findViewById(R.id.rvClientesVip);
        clientes = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            obj = new Cliente();
            obj.setPrimeiroNome("Cliente "+i);
            obj.setPessoaFisica(i % 2==0);
            clientes.add(obj);
        }

        adapter = new ClienteAdapter(clientes, getApplicationContext());

        rvClientesVip.setAdapter(adapter);
        rvClientesVip.setLayoutManager(new LinearLayoutManager(this));
    }
}