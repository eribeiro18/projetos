package app.daazi.v5.appclientevip.view;

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

import app.daazi.v5.appclientevip.R;
import app.daazi.v5.appclientevip.api.AppUtil;
import app.daazi.v5.appclientevip.controller.ClienteController;
import app.daazi.v5.appclientevip.controller.ClientePFController;
import app.daazi.v5.appclientevip.controller.ClientePJController;
import app.daazi.v5.appclientevip.model.Cliente;

public class AtualizarMeusActivity extends AppCompatActivity {

    // Card Cliente
    EditText editPrimeiroNome, editSobreNome;
    CheckBox chPessoaFisica;
    Button btnSalvarCardCliente, btnEditarCardCliente;

    // Card ClientePF
    EditText editCPF, editNomeCompleto;
    Button btnSalvarCardPF, btnEditarCardPF;

    // Card ClientePJ
    EditText editCNPJ,editRazaoSocial, editDataAberturaPJ;
    CheckBox chSimplesNacional, chMEI ;
    Button btnSalvarCardPJ, btnEditarCardPJ;

    // Card Credenciais
    EditText editEmail,editSenhaA;
    Button btnSalvarCardCredenciais, btnEditarCardCredenciais;

    Button btnVoltar;

    Cliente cliente;

    ClienteController controller;
    ClientePFController controllerPF;
    ClientePJController controllerPJ;

    SharedPreferences preferences;

    int clienteID;
    boolean isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_meus);

        restaurarSharedPreferences();

        initFormulario();

        polularFormulario();
    }

    private void polularFormulario() {

        if(clienteID>=1){

            cliente = controller.getClienteByID(cliente);
            cliente.setClientePF(controllerPF.getClientePFByFK(cliente.getId()));

            if(!cliente.isPessoaFisica())
                cliente.setClientePJ(controllerPJ.getClientePJByFK(cliente.getClientePF().getId()));


            // Dados Obj Cliente
            editPrimeiroNome.setText(cliente.getPrimeiroNome());
            editSobreNome.setText(cliente.getSobreNome());
            editEmail.setText(cliente.getEmail());
            editSenhaA.setText(cliente.getSenha());
            chPessoaFisica.setChecked(cliente.isPessoaFisica());

            // Dados Pessoa Física Obj Cliente
            editCPF.setText(cliente.getClientePF().getCpf());
            editNomeCompleto.setText(cliente.getClientePF().getNomeCompleto());

            // Dados Pessoa Júridica Obj Cliente
            if(!cliente.isPessoaFisica()){


                editCNPJ.setText(cliente.getClientePJ().getCnpj());
                editRazaoSocial.setText(cliente.getClientePJ().getRazaoSocial());
                editDataAberturaPJ.setText(cliente.getClientePJ().getDataAbertura());
                chSimplesNacional.setChecked(cliente.getClientePJ().isSimplesNacional());
                chMEI.setChecked(cliente.getClientePJ().isMei());

            }


        }else{

            new FancyAlertDialog.Builder(this)
                    .setTitle("ATENÇÃO")
                    .setBackgroundColor(Color.parseColor("#303F9F"))
                    .setMessage("Não foi possível, recuperar os dados do cliente?")
                    .setNegativeBtnText("RETORNAR")
                    .setNegativeBtnBackground(Color.parseColor("#FF4081"))
                    .isCancellable(true)
                    .setIcon(R.mipmap.ic_launcher_round, Icon.Visible)
                    .OnNegativeClicked(new FancyAlertDialogListener() {
                        @Override
                        public void OnClick() {

                            Intent intent = new Intent(AtualizarMeusActivity.this, MainActivity.class);
                            startActivity(intent);

                        }
                    })
                    .build();

        }

    }

    private void initFormulario() {


        // Card Cliente
        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobreNome = findViewById(R.id.editSobreNome);
        chPessoaFisica = findViewById(R.id.chPessoaFisica);
        btnEditarCardCliente = findViewById(R.id.btnEditarCardCliente);
        btnSalvarCardCliente = findViewById(R.id.btnSalvarCardCliente);

        // Card ClientePF
        editCPF = findViewById(R.id.editCPF);
        editNomeCompleto = findViewById(R.id.editNomeCompleto);
        btnEditarCardPF = findViewById(R.id.btnEditarCardPF);
        btnSalvarCardPF = findViewById(R.id.btnSalvarCardPF);

        // Card ClientePJ
        editCNPJ = findViewById(R.id.editCNPJ);
        editRazaoSocial = findViewById(R.id.editRazaoSocial);
        editDataAberturaPJ = findViewById(R.id.editDataAberturaPJ);
        btnEditarCardPJ = findViewById(R.id.btnEditarCardPJ);
        btnSalvarCardPJ = findViewById(R.id.btnSalvarCardPJ);

        chSimplesNacional = findViewById(R.id.chSimplesNacional);
        chMEI = findViewById(R.id.chMEI);

        // Card Credenciais
        editEmail = findViewById(R.id.editEmail);
        editSenhaA = findViewById(R.id.editSenhaA);
        btnEditarCardCredenciais = findViewById(R.id.btnEditarCardCredenciais);
        btnSalvarCardCredenciais = findViewById(R.id.btnSalvarCardCredenciais);


        cliente = new Cliente();
        cliente.setId(clienteID);

        controller = new ClienteController(this);
        controllerPF = new ClientePFController(this);
        controllerPJ = new ClientePJController(this);

        if(!cliente.isPessoaFisica()){
            // busco os dados
        }



    }

    public void editarCardCliente(View view){

        // Ativar o botão Salva
        // Desativar o botão Editar

        btnEditarCardCliente.setEnabled(false);
        btnSalvarCardCliente.setEnabled(true);

        editPrimeiroNome.setEnabled(true);
        editSobreNome.setEnabled(true);
        chPessoaFisica.setEnabled(true);
    }

    public void salvarCardCliente(View view){


        if(validarFormularioCardCliente()){

            // alterar os dados salvando no banco dados
            // controller
        }



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

    public void editarCardPF(View view){

        btnEditarCardPF.setEnabled(false);
        btnSalvarCardPF.setEnabled(true);

        editNomeCompleto.setEnabled(true);
        editCPF.setEnabled(true);

    }

    public void salvarCardPF(View view){

        if(validarFormularioCardPF()){

        }

    }

    private boolean validarFormularioCardPF() {

        boolean retorno = true;

        String cpf = editCPF.getText().toString();

        if (TextUtils.isEmpty(cpf)) {
            editCPF.setError("*");
            editCPF.requestFocus();
            retorno = false;
        }

        if(!AppUtil.isCPF(cpf)){

            editCPF.setError("*");
            editCPF.requestFocus();

            retorno =  false;

            Toast.makeText(this,"CPF Inválido, tente novamente...",Toast.LENGTH_LONG).show();
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

    public void editarCardPJ(View view){

        btnEditarCardPJ.setEnabled(false);
        btnSalvarCardPJ.setEnabled(true);

        editRazaoSocial.setEnabled(true);
        editDataAberturaPJ.setEnabled(true);
        editCNPJ.setEnabled(true);

    }

    public void salvarCardPJ(View view){

        if(validarFormularioCardPJ()){

        }

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

            Toast.makeText(this,"CNPJ Inválido, tente novamente...",Toast.LENGTH_LONG).show();
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

    public void editarCardCredenciais(View view){

        btnEditarCardCredenciais.setEnabled(false);
        btnSalvarCardCredenciais.setEnabled(true);

        editEmail.setEnabled(true);
        editSenhaA.setEnabled(true);
    }

    public void vontar(View view) {

        Intent intent = new Intent(AtualizarMeusActivity.this, MainActivity.class);
        startActivity(intent);

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);
        clienteID = preferences.getInt("clienteID", -1);

    }

}
