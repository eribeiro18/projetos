package app.daazi.v3.appclientevip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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
import app.daazi.v3.appclientevip.model.ClientePJ;

public class AtualizarMeusDadosActivity extends AppCompatActivity {

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

    private Button btnEditarCardCliente;
    private Button btnSalvarCardCliente;
    private Button btnEditarCardPF;
    private Button btnSalvarCardPF;
    private Button btnEditarCardPJ;
    private Button btnSalvarCardPJ;
    private Button btnEditarCardSenha;
    private Button btnSalvarCardSenha;

    private ClienteController controller;
    private ClientePFController controllerPF;
    private ClientePJController controllerPJ;
    private Cliente cliente;
    private Cliente novoVip;
    private ClientePF novoClientePF;
    private ClientePJ novoClientePJ;
    private SharedPreferences preferences;

    private ClienteController clienteController;
    private ClientePFController clientePFController;
    private ClientePJController clientePJController;

    private int clienteID;
    private int ultimoID;
    private int ultimoIDClientePessooaPF;
    boolean isPessoaFisica, isSimplesNacional, isMEI;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_meus_dados);
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
            new FancyAlertDialog.Builder(AtualizarMeusDadosActivity.this)
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
                            Intent intent = new Intent(AtualizarMeusDadosActivity.this, MainActivity.class);
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

        btnEditarCardCliente = findViewById(R.id.btnEditarCardCliente);
        btnSalvarCardCliente = findViewById(R.id.btnSalvarCardCliente);
        btnEditarCardPF = findViewById(R.id.btnEditarCardPF);
        btnSalvarCardPF = findViewById(R.id.btnSalvarCardPF);
        btnEditarCardPJ = findViewById(R.id.btnEditarCardPJ);
        btnSalvarCardPJ = findViewById(R.id.btnSalvarCardPJ);
        btnEditarCardSenha = findViewById(R.id.btnEditarCardSenha);
        btnSalvarCardSenha = findViewById(R.id.btnSalvarCardSenha);

        cliente = new Cliente();
        cliente.setId(clienteID);
        controller = new ClienteController(this);
        controllerPF = new ClientePFController(this);
        controllerPJ = new ClientePJController(this);

        clienteController = new ClienteController(this);
        clientePFController = new ClientePFController(this);
        clientePJController = new ClientePJController(this);
    }

    public void voltar(View view) {
        Intent intent = new Intent(AtualizarMeusDadosActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void editarCardCliente(View view){
        btnEditarCardCliente.setEnabled(false);
        btnSalvarCardCliente.setEnabled(true);
        editPrimeiroNome.setEnabled(true);
        editSobreNome.setEnabled(true);
        chPessoaFisica.setEnabled(true);
    }

    public void salvarCardCliente(View view){
        if(validarFormularioCardCliente()){
            novoVip.setPrimeiroNome(editPrimeiroNome.getText().toString());
            novoVip.setSobreNome(editSobreNome.getText().toString());
            novoVip.setPessoaFisica(isPessoaFisica);
            clienteController.alterar(novoVip);
            ultimoID = clienteController.getUltimoID();
            salvarSharedPreferencesCliente();
        }
        btnEditarCardCliente.setEnabled(true);
        btnSalvarCardCliente.setEnabled(false);
        editPrimeiroNome.setEnabled(false);
        editSobreNome.setEnabled(false);
        chPessoaFisica.setEnabled(false);
    }

    public void editarCardPF(View view){
        if(validarFormularioCardPF()){
            novoClientePF.setCpf(editCPF.getText().toString());
            novoClientePF.setNomeCompleto(editNomeCompleto.getText().toString());
            novoClientePF.setClienteID(clienteID);
            controller.alterar(novoClientePF);
            ultimoIDClientePessooaPF = controller.getUltimoID();
            salvarSharedPreferencesPF();
        }
        btnEditarCardCliente.setEnabled(false);
        btnSalvarCardCliente.setEnabled(true);
        editCPF.setEnabled(true);
        editNomeCompleto.setEnabled(true);
    }

    public void salvarCardPF(View view){
        btnEditarCardCliente.setEnabled(true);
        btnSalvarCardCliente.setEnabled(false);
        editCPF.setEnabled(false);
        editNomeCompleto.setEnabled(false);
    }

    public void editarCardPJ(View view){
        btnEditarCardCliente.setEnabled(false);
        btnSalvarCardCliente.setEnabled(true);
        editCNPJ.setEnabled(true);
        editRazaoSocial.setEnabled(true);
        editDataAberturaPJ.setEnabled(true);
        chMEI.setEnabled(true);
        chSimplesNacional.setEnabled(true);
    }

    public void salvarCardPJ(View view){
        if(validarFormularioCardPJ()){
            novoClientePJ.setClientePFID(ultimoIDClientePessooaPF);
            novoClientePJ.setCnpj(editCNPJ.getText().toString());
            novoClientePJ.setRazaoSocial(editRazaoSocial.getText().toString());
            novoClientePJ.setDataAbertura(editDataAberturaPJ.getText().toString());
            novoClientePJ.setSimplesNacional(isSimplesNacional);
            novoClientePJ.setMei(isMEI);
            controller.alterar(novoClientePJ);
            salvarSharedPreferencesPJ();
        }
        btnEditarCardCliente.setEnabled(true);
        btnSalvarCardCliente.setEnabled(false);
        editCNPJ.setEnabled(false);
        editRazaoSocial.setEnabled(false);
        editDataAberturaPJ.setEnabled(false);
        chMEI.setEnabled(false);
        chSimplesNacional.setEnabled(false);
    }

    public void editarCardSenha(View view){
        btnEditarCardCliente.setEnabled(false);
        btnSalvarCardCliente.setEnabled(true);
        editEmail.setEnabled(true);
        editSenhaA.setEnabled(true);
    }

    public void salvarCardSenha(View view){
        btnEditarCardCliente.setEnabled(true);
        btnSalvarCardCliente.setEnabled(false);
        editEmail.setEnabled(false);
        editSenhaA.setEnabled(false );
    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);
        clienteID = preferences.getInt("clienteID", -1);
    }

    private void salvarSharedPreferencesCliente() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putString("primeiroNome", novoVip.getPrimeiroNome());
        dados.putString("sobreNome", novoVip.getSobreNome());
        dados.putBoolean("pessoaFisica", novoVip.isPessoaFisica());
        dados.putInt("ultimoID", ultimoID);
        dados.apply();
    }

    private void salvarSharedPreferencesPF() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putString("cpf", editCPF.getText().toString());
        dados.putString("nomeCompleto", editNomeCompleto.getText().toString());
        dados.putInt("ultimoIDClientePessooaPF", ultimoIDClientePessooaPF);
        dados.apply();
    }

    private void salvarSharedPreferencesPJ() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putString("cnpf", editCNPJ.getText().toString());
        dados.putString("razaoSocial", editRazaoSocial.getText().toString());
        dados.putBoolean("simplesNacional", isSimplesNacional);
        dados.putString("dataAbertura", editDataAberturaPJ.getText().toString());
        dados.putBoolean("mei", isMEI);
        dados.putInt("ultimoIDClientePessoaPF", ultimoIDClientePessooaPF);
        dados.apply();
    }

    private boolean validarFormularioCardCliente() {
        boolean retorno = true;
        if (TextUtils.isEmpty(editPrimeiroNome.getText().toString())) {
            editPrimeiroNome.setError("*");
            editPrimeiroNome.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editSobreNome.getText().toString())) {
            editSobreNome.setError("*");
            editSobreNome.requestFocus();
            retorno = false;
        }
        return retorno;
    }

    private boolean validarFormularioCardPF() {
        boolean retorno = true;
        if (TextUtils.isEmpty(editCPF.getText().toString())) {
            editCPF.setError("*");
            editCPF.requestFocus();
            retorno = false;
        }
        if (!AppUtil.isCPF(editCPF.getText().toString())) {
            editCPF.setError("*");
            editCPF.requestFocus();
            retorno = false;
            Toast.makeText(this, "CPF Inválido, tente novamente...", Toast.LENGTH_LONG);
        }else{
            editCPF.setText(AppUtil.mascaraCPF(editCPF.getText().toString()));
        }
        if (TextUtils.isEmpty(editNomeCompleto.getText().toString())) {
            editNomeCompleto.setError("*");
            editNomeCompleto.requestFocus();
            retorno = false;
        }
        return retorno;
    }

    private boolean validarFormularioCardPJ() {
        boolean retorno = true;
        if(TextUtils.isEmpty(editCNPJ.getText().toString())){
            editCNPJ.setError("*");
            editCNPJ.requestFocus();
            retorno =  false;
        }
        if(!AppUtil.isCNPJ(editCNPJ.getText().toString())){
            editCNPJ.setError("*");
            editCNPJ.requestFocus();
            retorno =  false;
            Toast.makeText(this, "CNPJ Inválido, tente novamente...", Toast.LENGTH_LONG);
        }else{
            editCNPJ.setText(AppUtil.mascaraCNPJ(editCNPJ.getText().toString()));
        }
        if(TextUtils.isEmpty(editRazaoSocial.getText().toString())){
            editRazaoSocial.setError("*");
            editRazaoSocial.requestFocus();
            retorno =  false;
        }
        if(TextUtils.isEmpty(editDataAberturaPJ.getText().toString())){
            editDataAberturaPJ.setError("*");
            editDataAberturaPJ.requestFocus();
            retorno =  false;
        }
        return  retorno;
    }
}