package verificaconexao.vanvan.com.br.verificaconexao;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActVerifica extends AppCompatActivity {

    private TextView txtVerificaStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_act_verifica);
        txtVerificaStatus = (TextView) findViewById(R.id.txtStatusConexao);
        txtVerificaStatus.setText("Status da conexão: Verificando...");
        this.verificaStatusConexão();
    }

    @Override
    protected void onResume(){
        super.onResume();
        this.verificaStatusConexão();
    }

    public void verificaStatusConexão(){
        ConnectivityManager conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //forma antiga
/*        if(conn.getNetworkInfo(0).isConnected()){
            txtVerificaStatus.setText("Status da conexão: 3G");
        }else if (conn.getNetworkInfo(1).isConnected()){
            txtVerificaStatus.setText("Status da conexão: WIFI");
        }else{
            txtVerificaStatus.setText("Status da conexão: Desconectado");
        }*/
        //forma Atual
        NetworkInfo activeNetwork = conn.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                txtVerificaStatus.setText("Status da conexão: WIFI");
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                txtVerificaStatus.setText("Status da conexão: 3G");
            }
        } else {
            txtVerificaStatus.setText("Status da conexão: Desconectado");
        }
    }
}
