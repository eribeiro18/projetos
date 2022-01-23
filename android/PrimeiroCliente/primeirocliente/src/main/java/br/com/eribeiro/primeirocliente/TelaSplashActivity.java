package br.com.eribeiro.primeirocliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class TelaSplashActivity extends AppCompatActivity {

    int timer = 1000 * 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);
        this.carregaTelaPromocao();
    }

    private void carregaTelaPromocao() {
        new Handler().postDelayed(() -> {
            Intent trocarDeTela = new Intent(TelaSplashActivity.this, PromocaoActivity.class);
            startActivity(trocarDeTela);
            finish();
        }, timer);
    }
}