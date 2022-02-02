package br.com.eribeiro.appminhaideia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import br.com.eribeiro.appminhaideia.R;
import br.com.eribeiro.appminhaideia.core.AppUtil;
import br.com.eribeiro.appminhaideia.model.Cliente;

public class SplashActivity extends AppCompatActivity {

    int timer = 1000 * 3;
    Cliente objCliente;
    TextView txtVersionApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(AppUtil.TAG, "Projeto iniciado => ONCREATE SPLASH");
        txtVersionApp = findViewById(R.id.txtVersionApp);
        txtVersionApp.setText(AppUtil.versaoDoAplicativo());
        trocarTela();
    }

    private void trocarTela() {
        Log.d(AppUtil.TAG, "TELA SPLASH METODO TROCARTELA. AGUARDANDO...EXECUTANDO FONTES... ");
        new Handler().postDelayed(() -> {
            Log.d(AppUtil.TAG, "TELA SPLASH METODO TROCARTELA ABRINDO MAIN...");
            objCliente = new Cliente("Evandro",
                    "teste@teste.com.br",
                    "16999999999",
                    31,true);
            Intent trocarDeTela = new Intent(SplashActivity.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("nome", objCliente.getNome());
            bundle.putString("email", objCliente.getEmail());
            trocarDeTela.putExtras(bundle);
            startActivity(trocarDeTela);
            finish();
        }, timer);
    }
}