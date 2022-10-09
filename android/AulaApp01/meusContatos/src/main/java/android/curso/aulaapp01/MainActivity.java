package android.curso.aulaapp01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtTitulo;
    EditText primeiroNome, segundoNome, telefone, email;
    Button btnLimpar, btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initFormulario();
        btnLimpar.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCampos();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMensagem();
            }
        });
    }

    private void initCampos() {
        primeiroNome.setText("");
        segundoNome.setText("");
        telefone.setText("");
        email.setText("");
    }

    private void showMensagem() {
        Toast.makeText(getApplicationContext(),"Primeiro nome: "+primeiroNome.getText(),Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"Segundo nome: "+segundoNome.getText(),Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"Telefone: "+telefone.getText(),Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"Email: "+email.getText(),Toast.LENGTH_LONG).show();
    }

    private void initFormulario() {
        primeiroNome = findViewById(R.id.primeiroNome);
        segundoNome = findViewById(R.id.segundoNome);
        telefone = findViewById(R.id.telefone);
        email = findViewById(R.id.email);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
    }
}
