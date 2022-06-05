package app.daazi.v3.appclientevip.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import app.daazi.v3.appclientevip.R;
import app.daazi.v3.appclientevip.api.AppUtil;
import app.daazi.v3.appclientevip.controller.ClienteController;
import app.daazi.v3.appclientevip.controller.ClientePFController;
import app.daazi.v3.appclientevip.controller.ClientePJController;
import app.daazi.v3.appclientevip.model.Cliente;
import app.daazi.v3.appclientevip.model.ClientePF;

public class MeusDadosActivity extends AppCompatActivity {

    private EditText editSenhaA;
    private EditText editEmail;
    private EditText editDataAberturaPJ;
    private EditText editRazaoSocial;
    private EditText editCNPJ;
    private EditText editNomeCompleto;
    private EditText editCPF;
    private EditText editSobreNome;
    private EditText editPrimeiroNome;

    private CheckBox chMEI;
    private CheckBox chSimplesNacional;
    private CheckBox chPessoaFisica;

    private Button btnVoltar;

    private ClienteController controller;
    private ClientePFController controllerPF;
    private ClientePJController controllerPJ;
    private Cliente cliente;
    private SharedPreferences preferences;

    int clienteID;
    boolean isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_dados);
        this.restaurarSharedPreferences();
        this.initFormulario();
        this.popularFormulario();
    }

    private void popularFormulario() {
        if(clienteID>=1){
            cliente = this.controller.getClienteById(cliente);
            editPrimeiroNome.setText(cliente.getPrimeiroNome());
            editSobreNome.setText(cliente.getSobreNome());
            editEmail.setText(cliente.getEmail());
            editSenhaA.setText(cliente.getSenha());
            chPessoaFisica.setChecked(cliente.getClientePF().isPessoaFisica());
            //###################### PF
            cliente.setClientePF(this.controllerPF.getClientePFByIdFK(cliente.getId()));
            editNomeCompleto.setText(cliente.getClientePF().getNomeCompleto());
            editCPF.setText(cliente.getClientePF().getCpf());
            //###################### PJ
            if(!cliente.isPessoaFisica()){
                cliente.setClientePJ(this.controllerPJ.getClientePJByIdFK(cliente.getClientePF().getId()));
                editCNPJ.setText(cliente.getClientePJ().getCnpj());
                editRazaoSocial.setText(cliente.getClientePJ().getRazaoSocial());
                editDataAberturaPJ.setText(cliente.getClientePJ().getDataAbertura());
                chSimplesNacional.setChecked(cliente.getClientePJ().isSimplesNacional());
                chMEI.setChecked(cliente.getClientePJ().isMei());
            }
        }else{
            new FancyAlertDialog.Builder(MeusDadosActivity.this)
                    .setTitle("Atenção")
                    .setBackgroundColor(Color.parseColor("#303F9F"))
                    .setMessage("Não foi possivel recuperar os dados do cliente!")
                    .setNegativeBtnText("Retornar")
                    .setNegativeBtnBackground(Color.parseColor("#FF4081"))
                    .isCancellable(true)
                    .setIcon(R.mipmap.ic_launcher_round, Icon.Visible)
                    .OnNegativeClicked(new FancyAlertDialogListener() {
                        @Override
                        public void OnClick() {
                            Intent intent = new Intent(MeusDadosActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .build();
        }
    }

    private void initFormulario() {
        editSenhaA = findViewById(R.id.editSenhaA);
        editEmail = findViewById(R.id.editEmail);
        editDataAberturaPJ = findViewById(R.id.editDataAberturaPJ);
        editRazaoSocial = findViewById(R.id.editRazaoSocial);
        editCNPJ = findViewById(R.id.editCNPJ);
        editNomeCompleto = findViewById(R.id.editNomeCompleto);
        editCPF = findViewById(R.id.editCPF);
        editSobreNome = findViewById(R.id.editSobreNome);
        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        chMEI = findViewById(R.id.chMEI);
        chSimplesNacional = findViewById(R.id.chSimplesNacional);
        chPessoaFisica = findViewById(R.id.chPessoaFisica);
        cliente = new Cliente();
        cliente.setId(clienteID);
        controller = new ClienteController(this);
        controllerPF = new ClientePFController(this);
        controllerPJ = new ClientePJController(this);
    }

    public void voltar(View view) {
        Intent intent = new Intent(MeusDadosActivity.this, MainActivity.class);
        startActivity(intent);
    }


    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);
        clienteID = preferences.getInt("clienteID", -1);
    }
}