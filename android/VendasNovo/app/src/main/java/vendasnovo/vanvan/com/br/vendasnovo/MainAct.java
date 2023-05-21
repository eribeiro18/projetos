package vendasnovo.vanvan.com.br.vendasnovo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;

import vendasnovo.vanvan.com.br.vendasnovo.entidade.UtilXml;
import vendasnovo.vanvan.com.br.vendasnovo.entidade.Vendas;

public class MainAct extends AppCompatActivity implements Runnable {

    private UtilXml utilXml = new UtilXml();
    private final String USER_AGENT = "Mozilla/5.0";
    private ProgressBar pgb;
    private TextView txtStatusPgb;
    private int totalDb = 0;
    private int totalReplicado;
    private Cursor cursor;
    private SQLiteDatabase db;
    private String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainact);

        SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
        pgb = (ProgressBar) findViewById(R.id.pgb);
        this.txtStatusPgb = (TextView) findViewById(R.id.txtStatusPgb);
        /*this.txtStatusPgb.setVisibility(View.INVISIBLE);*/
        StringBuilder sqlProduto = new StringBuilder();
        sqlProduto.append("CREATE TABLE IF NOT EXISTS [produtos](")
                  .append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ")
                  .append("nome varchar(100), ")
                  .append("preco DOUBLE(10,2));");
        db.execSQL(sqlProduto.toString());

//        db.execSQL("INSERT INTO produtos (nome, preco) VALUES ('Coca Cola', '2.50')");
//        db.execSQL("INSERT INTO produtos (nome, preco) VALUES ('Agua', '1.50')");
//        db.execSQL("INSERT INTO produtos (nome, preco) VALUES ('Fanta Laranja', '2.30')");

        StringBuilder sqlVendas = new StringBuilder();
        sqlVendas.append("CREATE TABLE IF NOT EXISTS [vendas](")
                 .append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ")
                 .append("produto INTEGER, ")
                 .append("preco DOUBLE(10,2), ")
                 .append("la DOUBLE(10,9), ")
                 .append("lo DOUBLE(10,9)); ");
        db.execSQL(sqlVendas.toString());
        db.close();
    }

    public void novaVenda(View view){
        startActivity(new Intent(getBaseContext(), NovaVendaAct.class));
    }

    public void listarVenda(View view){
        startActivity(new Intent(getBaseContext(), ListaVendasAct.class));
    }

    public void iniciarReplicacao(View view){
        db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
        this.cursor = db.rawQuery(" SELECT * FROM vendas", null);
        pgb.setMax(cursor.getCount());
        pgb.setVisibility(View.VISIBLE);
        txtStatusPgb.setText("0/"+ String.valueOf(pgb.getMax()) + " | " + "0%");
        txtStatusPgb.setVisibility(View.VISIBLE);
        new Thread(MainAct.this).start();
//      chamando serviço para execução em background e informar a conclusão atraves de notification
/*        Intent serviceIntent = new Intent(getBaseContext(),ExportarVendasService.class);
        getBaseContext().startService(serviceIntent);*/
    }

    public void run() {

        String url = "http://192.168.1.103:10090/ManagementFastFoodWebService/webresources/VendasWS/vendas/replicacao";
        this.totalDb        = cursor.getCount();
        this.totalReplicado = 0;
        while (cursor.moveToNext()){
            try {
                Vendas venda = new Vendas();
                venda.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                venda.setProduto(cursor.getInt(cursor.getColumnIndex("produto")));
                venda.setPreco(cursor.getDouble(cursor.getColumnIndex("preco")));
                venda.setLa(cursor.getDouble(cursor.getColumnIndex("la")));
                venda.setLo(cursor.getDouble(cursor.getColumnIndex("lo")));
                String xmlVenda = utilXml.getObjectToXmlString(venda);
                String resposta = this.send(url, xmlVenda, "PUT");
                if(resposta.equals("Y")){
                    db.delete("vendas", "_id=?", new String[]{String.valueOf(cursor.getInt(0))});
                    totalReplicado++;
                    h1.sendEmptyMessage(0);
                }
            } catch (Exception e) {
                this.error = e.getMessage();
                h1.sendEmptyMessage(2);
            }
        }
        if(totalDb == totalReplicado){
            h1.sendEmptyMessage(1);
        }else{
            this.error = "Ocorreu erros ao replicar algumas vendas!";
            h1.sendEmptyMessage(2);
        }
        db.close();
    }

    public Handler h1 = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what == 0){
                pgb.setProgress(pgb.getProgress() + 1);
                txtStatusPgb.setText((pgb.getProgress() + 1) + "/" + totalDb + " | " + String.valueOf((((pgb.getProgress() + 1) * 100) / totalDb)) +"%");
            }else if(msg.what == 1){
                pgb.setVisibility(View.INVISIBLE);
                txtStatusPgb.setVisibility(View.INVISIBLE);
                txtStatusPgb.setText("");
                Toast.makeText(MainAct.this, "Sucesso na Replicação!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainAct.this, error, Toast.LENGTH_LONG).show();
                pgb.setVisibility(View.INVISIBLE);
                txtStatusPgb.setVisibility(View.INVISIBLE);
                txtStatusPgb.setText("");
            }
        }
    };

    public String send(String url, String urlParameters, String method) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/xml");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        //CONSOME O SERVIÇO E RECEBE O PARAMETRO
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        //ADD EM QUANTO O READLINE FOR DIFERENTE DE NULL
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response != null && !response.toString().equals("") ? "Y" : "N";
    }
}
