package app.daazi.v3.appclientevip.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.ArrayList;
import java.util.List;

import app.daazi.v3.appclientevip.R;
import app.daazi.v3.appclientevip.api.AppUtil;
import app.daazi.v3.appclientevip.model.Cliente;
import app.daazi.v3.appclientevip.model.ClientePF;
import app.daazi.v3.appclientevip.model.ClientePJ;

public class MainActivity extends AppCompatActivity {


    Cliente cliente;
    ClientePF clientePF;
    ClientePJ clientePJ;

    TextView txtNomeCliente;

    private SharedPreferences preferences;

    List<Cliente> clientes;
    List<String> cidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFormulario();

     buscarListaDeClientes();


    }


    private void buscarListaDeClientes() {

        cidades = new ArrayList<>();


        clientes = new ArrayList<>();


        cidades.add("Brasília");
        cidades.add("Campo Grande");
        cidades.add("São Paulo");
        cidades.add("Curitiba");

        for (int i = 0; i < 10; i++) {


            cliente = new Cliente();
            cliente.setPrimeiroNome("Cliente nº "+i);

            clientes.add(cliente);

        }


        for (String obj: cidades) {
            Log.i(AppUtil.LOG_APP,"Obj: "+obj);


        }


    }

    private void initFormulario() {


        cliente = new Cliente();
        clientePF = new ClientePF();
        clientePJ = new ClientePJ();

        txtNomeCliente = findViewById(R.id.txtNomeCliente);

        restaurarSharedPreferences();

        txtNomeCliente.setText("Bem vindo, "+ cliente.getPrimeiroNome());
    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();


    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

        cliente.setPrimeiroNome(preferences.getString("primeiroNome", "NULO"));
        cliente.setSobreNome(preferences.getString("sobreNome", "NULO"));
        cliente.setEmail(preferences.getString("email", "NULO"));
        cliente.setSenha(preferences.getString("senha", "NULO"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica", true));

        clientePF.setCpf(preferences.getString("cpf", "NULO"));
        clientePF.setNomeCompleto(preferences.getString("nomeCompleto", "NULO"));

        clientePJ.setCnpj(preferences.getString("cnpf", "NULO"));
        clientePJ.setRazaoSocial(preferences.getString("razaoSocial", "NULO"));
        clientePJ.setSimplesNacional(preferences.getBoolean("simplesNacional", false));
        clientePJ.setMei(preferences.getBoolean("mei", false));
        clientePJ.setDataAbertura(preferences.getString("dataAbertura", "NULO"));

    }

    public void meusDados(View view) {

        /**
         *     <string name="primeiroNome">Maddo</string>
         *     <string name="emailCliente">maddo@teste.com</string>
         *     <boolean name="pessoaFisica" value="false" />
         *     <string name="email">maddo@teste.com</string>
         *     <string name="dataAbertura">11/01/2020</string>
         *     <string name="nomeCompleto">Marco A Oliveira</string>
         *     <string name="sobreNome">Marco</string>
         *     <string name="cpf">11122233300</string>
         *     <string name="cnpf">11222333000155</string>
         *     <string name="senha">12345</string>
         *     <boolean name="mei" value="false" />
         *     <boolean name="simplesNacional" value="true" />
         *     <string name="razaoSocial">Marco Maddo ME</string>
         *     <boolean name="loginAutomatico" value="true" />
         */

        Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE ****");
        Log.i(AppUtil.LOG_APP, "ID " + cliente.getId());
        Log.i(AppUtil.LOG_APP, "Primeiro Nome: " + cliente.getPrimeiroNome());
        Log.i(AppUtil.LOG_APP, "Sobre Nome: " + cliente.getSobreNome());
        Log.i(AppUtil.LOG_APP, "Email: " + cliente.getEmail());
        Log.i(AppUtil.LOG_APP, "Senha: " + cliente.getSenha());
        Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE PF ****");
        Log.i(AppUtil.LOG_APP, "CPF: " + clientePF.getCpf());
        Log.i(AppUtil.LOG_APP, "Nome Completo: " + clientePF.getNomeCompleto());

        if (!cliente.isPessoaFisica()) {
            Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE PJ ****");
            Log.i(AppUtil.LOG_APP, "CNPJ: " + clientePJ.getCnpj());
            Log.i(AppUtil.LOG_APP, "Razão Social: " + clientePJ.getRazaoSocial());
            Log.i(AppUtil.LOG_APP, "Data Abertura: " + clientePJ.getDataAbertura());
            Log.i(AppUtil.LOG_APP, "Simples Nacional: " + clientePJ.isSimplesNacional());
            Log.i(AppUtil.LOG_APP, "MEI: " + clientePJ.isMei());
        }

    }

    public void atualizarMeusDados(View view) {

        if(cliente.isPessoaFisica()){

            cliente.setPrimeiroNome("Marco A");
            cliente.setSobreNome("Oliveira");

            clientePF.setNomeCompleto("Marco A D Oliveira");

            // salvarSharedPreferences();

            Log.i(AppUtil.LOG_APP, "*** ALTERANDO DADOS CLIENTE ****");
            Log.i(AppUtil.LOG_APP, "Primeiro Nome: " + cliente.getPrimeiroNome());
            Log.i(AppUtil.LOG_APP, "Sobre Nome: " + cliente.getSobreNome());
            Log.i(AppUtil.LOG_APP, "*** ALTERANDO DADOS CLIENTE PF ****");
            Log.i(AppUtil.LOG_APP, "Nome Completo: " + clientePF.getNomeCompleto());



        }else{

            clientePJ.setRazaoSocial("MADDO ME");

            Log.i(AppUtil.LOG_APP, "*** ALTERANDO DADOS CLIENTE PJ ****");
            Log.i(AppUtil.LOG_APP, "Razao Social: " + clientePJ.getRazaoSocial());

        }

    }

    public void excluirMinhaConta(View view) {

        new FancyAlertDialog.Builder(MainActivity.this)
                .setTitle("EXCLUIR SUA CONTA")
                .setBackgroundColor(Color.parseColor("#303F9F"))
                .setMessage("Confirma EXCLUSÃO definitiva da sua conta do aplicativo?")
                .setNegativeBtnText("RETORNAR")
                .setNegativeBtnBackground(Color.parseColor("#FF4081"))
                .setPositiveBtnText("SIM")
                .setPositiveBtnBackground(Color.parseColor("#4ECA25"))
                .isCancellable(true)
                .setIcon(R.mipmap.ic_launcher_round, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(), cliente.getPrimeiroNome() + ", sua conta foi excluída, esperamos que retorne em breve...", Toast.LENGTH_SHORT).show();

                        cliente = new Cliente();
                        clientePF = new ClientePF();
                        clientePJ = new ClientePJ();

                        // Lembrar Senha para Automático, tem que resetado

                        // salvarSharedPreferences();

                        finish();
                        return;
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(), cliente.getPrimeiroNome() + ", divirta-se com as opções do aplicativo...", Toast.LENGTH_SHORT).show();

                    }
                })
                .build();
    }

    public void consultarClientesVip(View view) {

        Intent intent = new Intent(MainActivity.this, ConsultarClientesActivity.class);
        startActivity(intent);
    }

    public void sairDoAplicativo(View view) {

        new FancyAlertDialog.Builder(MainActivity.this)
                .setTitle("SAIR DO APLICATIVO")
                .setBackgroundColor(Color.parseColor("#303F9F"))
                .setMessage("Confirma sua saída do aplicativo?")
                .setNegativeBtnText("RETORNAR")
                .setNegativeBtnBackground(Color.parseColor("#FF4081"))
                .setPositiveBtnText("SIM")
                .setPositiveBtnBackground(Color.parseColor("#4ECA25"))
                .isCancellable(true)
                .setIcon(R.mipmap.ic_launcher_round, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(), cliente.getPrimeiroNome() + ", volte sempre e obrigado...", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(), cliente.getPrimeiroNome() + ", divirta-se com as opções do aplicativo...", Toast.LENGTH_SHORT).show();

                    }
                })
                .build();

    }
}
