package app.daazi.api.v1.permissoes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by marcomaddo on 15/03/2020
 * <p>
 * Curso Desenvolvimento Android do Absoluto Zero para Iniciantes
 * Exemplo de como gerenciar as permissões normais e perigosas
 */
public class SplashActivity extends AppCompatActivity {

    // Use qualquer número
    public static final int APP_PERMISSOES = 2020;
    // Time Out para troca de tela
    private static final int SPLASH_TIME_OUT = 3000;
    // Array String com a lista de permissões necessárias
    String[] permissoesRequeridas = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // Verificar as permissões
        if (validarPermissoes()) {
            apresentarTelaSplash();
        } else {
            Toast.makeText(this, "Por favor, verifique as permissões.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    // Carregar a tela principal do aplicativo
    private void apresentarTelaSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Carregar normalmente a tela principal
                Intent aplicativo = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(aplicativo);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private boolean validarPermissoes() {
        int result;
        //array de permissões que forão autorizadas
        List<String> permissoesRequeridas = new ArrayList<>();

        //adiciona as permissões que o app necessita
        for (String permissao : this.permissoesRequeridas) {
            result = ContextCompat.checkSelfPermission(SplashActivity.this, permissao);
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissoesRequeridas.add(permissao);
            }
        }
        //Caso não vazio permissões pendentes de autorização
        if (!permissoesRequeridas.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    permissoesRequeridas.toArray(new String[permissoesRequeridas.size()]), APP_PERMISSOES);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == APP_PERMISSOES) {
            if (grantResults.length > 0) {
                StringBuilder permissoesNegadasPeloUsuario = new StringBuilder();
                for (String permissao : permissoesRequeridas) {
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        permissoesNegadasPeloUsuario.append("\n").append(permissao);
                    }
                }
                if (permissoesNegadasPeloUsuario.length() > 0) {
                    Toast.makeText(this, "Permissões negadas pelo usuario.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Todas as Permissões autorizadas pelo usuario.", Toast.LENGTH_SHORT).show();
                    apresentarTelaSplash();
                }
            }
        }
    }
}

