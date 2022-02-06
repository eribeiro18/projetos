package app.eribeiro.aluno.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "APP_SHARED_PREFERENCE";
    //este será o nome do arquivo com extensao .xml
    //para ver o arquivo canto inferior esquerdo da IDE -> Device File Explorer
    //caminho /data/data/<package_app>/shared_prefs
    //package_app exemplo app.eribeiro.aluno.sharedpreferences
    private static final String PREF_NOME = "APP_PREFERENCE";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor dados;

    String nomeProduto;
    int codigoProduto;
    float precoProduto;
    boolean estoque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate: Rodando");

        sharedPreferences = getSharedPreferences(PREF_NOME, Context.MODE_PRIVATE);

        Log.i(TAG, "onCreate: Pasta Shared criada!");

        dados = sharedPreferences.edit();
        nomeProduto = "Laptop";
        codigoProduto = 123456;
        precoProduto = 997.97f;
        estoque = true;

        Log.i(TAG, "onCreate: Dados para ser salvo nome => " + nomeProduto + " codigo => " +codigoProduto);
        dados.putString("nomeProduto", nomeProduto);
        dados.putInt("codigoProduto", codigoProduto);
        dados.putFloat("precoProduto", precoProduto);
        dados.putBoolean("estoque" , estoque);
        dados.apply();
        Log.i(TAG, "onCreate: Dados salvo no arquivo");

        //trexo apenas se quiser limpar o arquivo antes criado com linhas acima
        /*Log.i(TAG, "onCreate: Limpando/removendo dados do arquivo!");
        dados.clear();
        dados.apply();
        Log.i(TAG, "onCreate: Remocao/limpeza do arquivo concluida!");*/

        //limpar uma tag do arquivo
        /*Log.i(TAG, "onCreate: Limpando/removendo a tag estoque do arquivo!");
        dados.remove("estoque");
        dados.apply();
        Log.i(TAG, "onCreate: Remocao/limpeza da tag estoque do arquivo concluida!");*/

        //recuperar os dados de um arquivo
        Log.d(TAG, "onCreate: Recuperando daods de um arquivo!");
        Log.d(TAG, "onCreate: nomeProduto => " + sharedPreferences.getString("nomeProduto", "fora do estoque"));
        Log.d(TAG, "onCreate: codigoProduto => " + sharedPreferences.getInt("codigoProduto", -1));
        Log.d(TAG, "onCreate: precoProduto => " + sharedPreferences.getFloat("precoProduto", -1.0f));
        Log.d(TAG, "onCreate: estoque => " + sharedPreferences.getBoolean("estoque", false));
    }
}