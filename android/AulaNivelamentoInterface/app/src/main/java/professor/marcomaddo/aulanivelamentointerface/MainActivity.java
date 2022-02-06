package professor.marcomaddo.aulanivelamentointerface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Cliente objCliente;
    Produto objProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objCliente = new Cliente();
        objProduto = new Produto();

        objProduto.setNome("HD SSD 1TB");
        objProduto.setFornecedor("DELL");

        objCliente.setNome("Maddo");
        objCliente.setEmail("teste@teste.com.br");

        objCliente.incluir();
        objCliente.alterar();

        objProduto.deletar();
        objProduto.listar();
    }
}