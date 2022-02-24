package app.modelo.meusclientes.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.modelo.meusclientes.R;
import app.modelo.meusclientes.controller.ClienteController;
import app.modelo.meusclientes.model.Cliente;


public class ListarClientesFragment extends Fragment {

    // Fragment - Classe responsável pela camada de VISÃO (Layout)
    View view;
    TextView txtTitulo;
    EditText editPesquisarNome;
    ListView listViewCliente;
    List<Cliente> clienteList;
    List<String> clientes;
    ClienteController clienteController;
    Cliente objCliente;
    ArrayAdapter clienteAdapter;
    ArrayList<HashMap<String, String>> filtroClientes;

    public ListarClientesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_listar_clientes, container, false);
        iniciarComponenteDeLayouts();
        popularListas();
        actions();
        return view;
    }

    private void actions() {
        editPesquisarNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence filtro, int i, int i1, int i2) {
                ListarClientesFragment.this.clienteAdapter.getFilter().filter(filtro);
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void popularListas() {
        try {
            clientes.clear();
            clientes.addAll(clienteController.listarClintesView());
            clienteAdapter = new ArrayAdapter<>(getContext(), R.layout.listar_cliente_item, R.id.txtItemLista, clientes);
            listViewCliente.setAdapter(clienteAdapter);
        }catch (Exception e){
            Toast.makeText(getContext(), "Falha ao Listar Clientes, ERRO " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void iniciarComponenteDeLayouts() {
        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtTitulo.setText(R.string.fragmento_lista_clientes);
        editPesquisarNome = view.findViewById(R.id.editPesquisarNome);
        listViewCliente = view.findViewById(R.id.listViewCliente);
        clienteList = new ArrayList<>();
        clientes = new ArrayList<>();
        clienteController = new ClienteController(getContext());
        objCliente = new Cliente();
    }
}
