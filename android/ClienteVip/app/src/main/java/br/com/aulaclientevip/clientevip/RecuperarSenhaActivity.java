package br.com.aulaclientevip.clientevip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;

public class RecuperarSenhaActivity extends AppCompatActivity {

    private EditText editEmail;
    private Button btnRecuperarSenha;
    private Button btnCancelar;
    private TextView txtTermos;
    private boolean isLembrarSenha;

    private SharedPreferences sharedPreferences;
    public static final String PREF_APP = "SHARE_CLIENTE_VIP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);
        initFormulario();
        restaurarSharedPreferences();
        btnRecuperarSenha.setOnClickListener(view -> {
            if (validarFormulario()) {
                Toast.makeText(getApplicationContext(), "Sua senha foi enviada para o e-mail informado... ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RecuperarSenhaActivity.this, CadastroPessoaJuridicaActivity.class);
                startActivity(intent);
            }
        });
        btnCancelar.setOnClickListener(view -> {
            /**
             *
             * dialog pode ser generalizado em uma classe commons
             */
            FancyAlertDialog.Builder
                    .with(RecuperarSenhaActivity.this)
                    .setTitle("Confirmação de Cancelamento")
                    .setBackgroundColor(Color.parseColor("#303F9F"))
                    .setMessage("Deseja realmente cancelar?")
                    .setNegativeBtnText("Não")
                    .setPositiveBtnBackground(Color.parseColor("#FF4081"))
                    .setPositiveBtnText("Sim")
                    .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))
                    .setAnimation(Animation.SIDE)
                    .isCancellable(true)
                    .setIcon(R.drawable.ic_launcher_foreground, View.VISIBLE)
                    .onPositiveClicked(dialog -> {
                        Intent intent = new Intent(RecuperarSenhaActivity.this, CadastroPessoaJuridicaActivity.class);
                        startActivity(intent);
                        Toast.makeText(RecuperarSenhaActivity.this, "Recuperação de senha cancelada!", Toast.LENGTH_SHORT).show();
                    })
                    .onNegativeClicked(dialog -> Toast.makeText(RecuperarSenhaActivity.this, "Ok. Continue com a recuperação de senha!", Toast.LENGTH_SHORT).show())
                    .build()
                    .show();
        });

        txtTermos.setOnClickListener(view -> FancyAlertDialog.Builder
                .with(RecuperarSenhaActivity.this)
                .setTitle("Política de Privacidade & Termos de Uso")
                .setBackgroundColor(Color.parseColor("#303F9F"))
                .setMessage("Texto Texto Texto Texto Texto Texto Texto Texto Texto Texto Texto Texto Texto Texto Texto Texto")
                .setNegativeBtnText("Discordo")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))
                .setPositiveBtnText("Concordo")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))
                .setAnimation(Animation.SLIDE)
                .isCancellable(true)
                .setIcon(R.drawable.ic_launcher_foreground, View.VISIBLE)
                .onPositiveClicked(dialog -> Toast.makeText(RecuperarSenhaActivity.this, "Obrigado, seja bem vindo e conclua seu cadastro!", Toast.LENGTH_SHORT).show())
                .onNegativeClicked(dialog -> {
                    Toast.makeText(RecuperarSenhaActivity.this, "Lamentamos, mas é necessario concordar com os termos!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .build()
                .show());
    }

    private void initFormulario() {
        editEmail = findViewById(R.id.editEmail);
        btnRecuperarSenha = findViewById(R.id.btnRecuperarSenha);
        btnCancelar = findViewById(R.id.btnCancelar);
        txtTermos = findViewById(R.id.txtTermos);
    }

    private boolean validarFormulario() {
        boolean retorno = true;
        if (TextUtils.isEmpty(editEmail.getText())) {
            editEmail.setError("Informe um e-mail*");
            editEmail.requestFocus();
            retorno = false;
        }
        return retorno;
    }

    private void restaurarSharedPreferences(){
        sharedPreferences = getSharedPreferences(PREF_APP, MODE_PRIVATE);
        isLembrarSenha = sharedPreferences.getBoolean("loginAutomatico", false);
    }

    private void salvarSharedPreferences(){
        sharedPreferences = getSharedPreferences(PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = sharedPreferences.edit();
        dados.putString("email", editEmail.getText().toString());
        dados.apply();
    }

}