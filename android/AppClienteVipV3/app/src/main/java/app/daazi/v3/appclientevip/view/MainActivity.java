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
import app.daazi.v3.appclientevip.controller.ClienteController;
import app.daazi.v3.appclientevip.controller.ClientePFController;
import app.daazi.v3.appclientevip.controller.ClientePJController;
import app.daazi.v3.appclientevip.model.Cliente;
import app.daazi.v3.appclientevip.model.ClientePF;
import app.daazi.v3.appclientevip.model.ClientePJ;

public class MainActivity extends AppCompatActivity {

    Cliente cliente;

    TextView txtNomeCliente;
    List<Cliente> clientes;
    List<String> cidades;
    private SharedPreferences preferences;

    private ClienteController clienteController;
    private ClientePFController clientePFController;
    private ClientePJController clientePJController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFormulario();
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
            cliente.setPrimeiroNome("Cliente nº " + i);
            clientes.add(cliente);
        }
        for (String obj : cidades) {
            Log.i(AppUtil.LOG_APP, "Obj: " + obj);
        }
    }

    private void initFormulario() {
        cliente = new Cliente();
        txtNomeCliente = findViewById(R.id.txtNomeCliente);
        restaurarSharedPreferences();
        txtNomeCliente.setText("Bem vindo, " + cliente.getPrimeiroNome());
        this.clienteController = new ClienteController(this);
        this.clientePFController = new ClientePFController(this);
        this.clientePJController = new ClientePJController(this);
    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        cliente.setPrimeiroNome(preferences.getString("primeiroNome", "NULO"));
        cliente.setSobreNome(preferences.getString("sobreNome", "NULO"));
        cliente.setEmail(preferences.getString("email", "NULO"));
        cliente.setSenha(preferences.getString("senha", "NULO"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica", true));
        cliente.setId(preferences.getInt("clienteID", -1));
    }

    public void meusDados(View view) {
        Intent intent = new Intent(MainActivity.this, MeusDadosActivity.class);
        startActivity(intent);
    }

    public void atualizarMeusDados(View view) {
        Intent intent = new Intent(MainActivity.this, AtualizarMeusDadosActivity.class);
        startActivity(intent);
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
                        cliente.setClientePF(clientePFController.getClientePFByIdFK(cliente.getId()));
                        if(!cliente.isPessoaFisica()){
                            cliente.setClientePJ(clientePJController.getClientePJByIdFK(cliente.getClientePF().getId()));
                            if(cliente.getClientePJ() != null && cliente.getClientePJ().getId() != 0){
                                clientePJController.deletar(cliente.getClientePJ());
                            }
                        }
                        clientePFController.deletar(cliente.getClientePF());
                        clienteController.deletar(cliente);
                        Toast.makeText(getApplicationContext(), cliente.getPrimeiroNome() + ", sua conta foi excluída, esperamos que retorne em breve...", Toast.LENGTH_SHORT).show();
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
