package br.com.eribeiro.appminhaideia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    private final static String TAG = "APP_MINHA_IDEIA";
    int timer = 1000 * 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(TAG, "Projeto iniciado => ONCREATE SPLASH");
        trocarTela();
    }

    private void trocarTela() {
        Log.d(TAG, "TELA SPLASH METODO TROCARTELA. AGUARDANDO...EXECUTANDO FONTES... ");
        new Handler().postDelayed(() -> {
            Log.d(TAG, "TELA SPLASH METODO TROCARTELA ABRINDO MAIN...");
            Intent trocarDeTela = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(trocarDeTela);
            finish();
        }, timer);
    }
}