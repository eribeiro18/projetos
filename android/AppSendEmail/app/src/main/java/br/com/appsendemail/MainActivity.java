package br.com.appsendemail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editEmailDestinatario;
    EditText editAssunto;
    EditText editMensagem;
    Button btnEnviarEmail;
    String emailDestinatario, assunto, mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeObjects();
        btnEnviarEmail.setOnClickListener(view -> {
            if(validaFormulario()){
                processarFormulario();
                enviaEmail();
            }
        });
    }

    private void enviaEmail(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailDestinatario});
        intent.putExtra(Intent.EXTRA_SUBJECT, assunto);
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Selecione um aplicativo"));
    }

    private boolean validaFormulario(){
        boolean valida = true;
        if(TextUtils.isEmpty(editEmailDestinatario.getText().toString())){
            editEmailDestinatario.setError("* Obrigatório");
            editEmailDestinatario.requestFocus();
            valida = false;
        }
        if(TextUtils.isEmpty(editAssunto.getText().toString())){
            editAssunto.setError("* Obrigatório");
            editAssunto.requestFocus();
            valida = false;
        }
        if(TextUtils.isEmpty(editMensagem.getText().toString())){
            editMensagem.setError("* Obrigatório");
            editMensagem.requestFocus();
            valida = false;
        }
        return valida;
    }

    private void processarFormulario(){
        emailDestinatario = editEmailDestinatario.getText().toString();
        assunto = editAssunto.getText().toString();
        mensagem = editMensagem.getText().toString();
    }

    private void initializeObjects(){
        editEmailDestinatario = findViewById(R.id.editEmailDestinatario);
        editAssunto = findViewById(R.id.editAssunto);
        editMensagem = findViewById(R.id.editMensagem);
        btnEnviarEmail = findViewById(R.id.btnEnviarEmail);
    }
}