package app.eribeiro.aluno.appminhaideiadb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import app.eribeiro.aluno.appminhaideiadb.R;
import app.eribeiro.aluno.appminhaideiadb.controller.ClienteController;
import app.eribeiro.aluno.appminhaideiadb.model.Cliente;

public class MainActivity extends AppCompatActivity {

    ClienteController clienteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clienteController = new ClienteController(getApplicationContext());
        Cliente cliente = new Cliente();
        cliente.setNome("Mayra Ribeiro");
        cliente.setEmail("mayrahistoriadora@gmail.com");
        try {
            clienteController.listar();
            if(clienteController.incluir(cliente)){
                Toast.makeText(this, "Cliente " + cliente.getNome() + " incluído com sucesso...", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Cliente " + cliente.getNome() + " não incluído com sucesso...", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Falha na inclusão do Cliente " + cliente.getNome() + "erro => " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}