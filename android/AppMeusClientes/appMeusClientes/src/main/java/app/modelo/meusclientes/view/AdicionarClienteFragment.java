package app.modelo.meusclientes.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import app.modelo.meusclientes.R;
import app.modelo.meusclientes.controller.ClienteController;
import app.modelo.meusclientes.model.Cliente;


public class AdicionarClienteFragment extends Fragment {

    // Fragment - Classe responsável pela camada de VISÃO (Layout)
    View view;
    TextView txtTitulo;
    EditText editNomeCompleto;
    EditText editTelefone;
    EditText editEmail;
    EditText editCep;
    EditText editLogradouro;
    EditText editNumero;
    EditText editBairro;
    EditText editCidade;
    EditText editEstado;
    CheckBox chkTermoDeUso;
    Button btnCancelar, btnSalvar;

    Cliente novoCliente;
    ClienteController clienteController;

    public AdicionarClienteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_adicionar_cliente, container, false);
        iniciarComponenteDeLayouts();
        eventBtns();
        return view;
    }

    private void eventBtns() {
        btnCancelar.setOnClickListener(view -> {

        });
        btnSalvar.setOnClickListener(view -> {
            try {
                if(validarCampos()){
                    popularCliente();
                    clienteController.incluir(novoCliente);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void popularCliente() {
        novoCliente.setNome(this.editNomeCompleto.getText().toString());
        novoCliente.setTelefone(this.editTelefone.getText().toString());
        novoCliente.setEmail(this.editEmail.getText().toString());
        novoCliente.setCep(Integer.parseInt(this.editCep.getText().toString()));
        novoCliente.setLogradouro(this.editLogradouro.getText().toString());
        novoCliente.setNumero(Integer.parseInt(this.editNumero.getText().toString()));
        novoCliente.setBairro(this.editBairro.getText().toString());
        novoCliente.setCidade(this.editCidade.getText().toString());
        novoCliente.setEstado(this.editEstado.getText().toString());
        novoCliente.setTermoUso(this.chkTermoDeUso.isChecked());
    }

    private boolean validarCampos(){
        boolean valid = true;
        if(TextUtils.isEmpty(editNomeCompleto.getText())){
            valid = false;
            editNomeCompleto.setError("Digite o nome completo...");
            editNomeCompleto.requestFocus();
        }else if(TextUtils.isEmpty(editTelefone.getText())){
            valid = false;
            editTelefone.setError("Digite o telefone...");
            editTelefone.requestFocus();
        }else if(TextUtils.isEmpty(editEmail.getText())){
            valid = false;
            editEmail.setError("Digite o e-mail...");
            editEmail.requestFocus();
        }else if(TextUtils.isEmpty(editCep.getText())){
            valid = false;
            editCep.setError("Digite o CEP...");
            editCep.requestFocus();
        }else if(TextUtils.isEmpty(editLogradouro.getText())){
            valid = false;
            editLogradouro.setError("Digite o Logradouro...");
            editLogradouro.requestFocus();
        }else if(TextUtils.isEmpty(editNumero.getText())){
            valid = false;
            editNumero.setError("Digite o numero...");
            editNumero.requestFocus();
        }else if(TextUtils.isEmpty(editBairro.getText())){
            valid = false;
            editBairro.setError("Digite o bairro...");
            editBairro.requestFocus();
        }else if(TextUtils.isEmpty(editCidade.getText())){
            valid = false;
            editCidade.setError("Digite o cidade...");
            editCidade.requestFocus();
        }else if(TextUtils.isEmpty(editEstado.getText())){
            valid = false;
            editEstado.setError("Digite o estado...");
            editEstado.requestFocus();
        }
        return valid;
    }



    private void iniciarComponenteDeLayouts() {
        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtTitulo.setText(R.string.novoCliente);
        editNomeCompleto = view.findViewById(R.id.editNomeCompleto);
        editTelefone = view.findViewById(R.id.editTelefone);
        editEmail = view.findViewById(R.id.editEmail);
        editCep = view.findViewById(R.id.editCep);
        editLogradouro = view.findViewById(R.id.editLogradouro);
        editNumero = view.findViewById(R.id.editNumero);
        editBairro = view.findViewById(R.id.editBairro);
        editCidade = view.findViewById(R.id.editCidade);
        editEstado = view.findViewById(R.id.editEstado);
        chkTermoDeUso = view.findViewById(R.id.chkTermoDeUso);
        btnCancelar = view.findViewById(R.id.btnCancelar);
        btnSalvar = view.findViewById(R.id.btnSalvar);
        novoCliente = new Cliente();
        clienteController = new ClienteController(getContext());
    }
}
