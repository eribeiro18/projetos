package app.eribeiro.aluno.appminhaideiadb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import app.eribeiro.aluno.appminhaideiadb.R;
import app.eribeiro.aluno.appminhaideiadb.controller.ClienteController;

public class MainActivity extends AppCompatActivity {

    ClienteController clienteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clienteController = new ClienteController(getApplicationContext());

    }
}