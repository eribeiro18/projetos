package vendas.rlsystem.com.br.vendas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import vendas.rlsystem.com.br.vendas.R;

public class MainActivity extends Activity implements Runnable{
    
	ProgressDialog pgd;
	Cursor cursor;
	SQLiteDatabase db;
	String error = "";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Exemplo AlarmManager
        AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
       
        Intent intent = new Intent(this, ExportarVendasService.class);
        
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
        
        Calendar time = Calendar.getInstance();
        
        time.setTimeInMillis(System.currentTimeMillis());

        time.add(Calendar.SECOND, 30);

        //alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), 5000, pendingIntent);
        
        SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
        
        StringBuilder sqlProdutos = new StringBuilder();
        sqlProdutos.append("CREATE TABLE IF NOT EXISTS [produtos](");
        sqlProdutos.append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlProdutos.append("nome varchar(100), ");
        sqlProdutos.append("preco DOUBLE(10,2));");
        db.execSQL(sqlProdutos.toString());
        
        //db.execSQL("INSERT INTO produtos(nome, preco) VALUES('Coca Cola', '2.50')");
        //db.execSQL("INSERT INTO produtos(nome, preco) VALUES('Redd Bull', '6.50')");
        
        for(int i = 0; i < 100; i++){
        	db.execSQL("INSERT INTO vendas(produto, preco, la, lo) VALUES(1, '6.50', '223', '23232')");
        }
        
        
        StringBuilder sqlVendas = new StringBuilder();
        sqlVendas.append("CREATE TABLE IF NOT EXISTS [vendas](");
        sqlVendas.append("[_id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlVendas.append("produto INTEGER, ");
        sqlVendas.append("preco DOUBLE(10,2), ");
        sqlVendas.append("la DOUBLE(10,9), ");
        sqlVendas.append("lo DOUBLE(10,9)); ");
        db.execSQL(sqlVendas.toString());
        
        db.close();
    }
    
    public void onResume(){
    	super.onResume();
    	
    	  TextView txvStatusConexao = (TextView)findViewById(R.id.txvStatusConexao);
          
          ConnectivityManager conn = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
          
          if(conn.getNetworkInfo(0).isConnected()){
          	txvStatusConexao.setText("Status da conex�o: 3G");
          } else if(conn.getNetworkInfo(1).isConnected()){
          	txvStatusConexao.setText("Status da conex�o: Wifi");
          } else {
        	 ((Button)findViewById(R.id.btnReplicar)).setEnabled(false); 
          	txvStatusConexao.setText("Status da conex�o: desconectado");
          }
    }
    
    public void NovaVenda_Click(View v){
    	startActivity(new Intent(getBaseContext(), NovaVendaActivity.class));
    }
    
    public void ListarVendas_Click(View v){
    	startActivity(new Intent(getBaseContext(), ListarVendasActivity.class));
    }
    
    public void Replicacao_Click(View v){
    	//startService(new Intent("rlsystem.iniciar_servico"));
    	
        db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
 	    
		cursor = db.rawQuery("SELECT * FROM vendas", null);
    	
    	pgd = new ProgressDialog(MainActivity.this);
    	pgd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    	pgd.setCancelable(false);
    	pgd.setTitle("Replicando dados.");
    	pgd.setMax(cursor.getCount());
    	pgd.show();
    	new Thread(MainActivity.this).start();
    }

	public void run() {
		 int totalDB = cursor.getCount();
		 int totalReplicado = 0;
		 
		 while(cursor.moveToNext()){
			 StringBuilder strURL = new StringBuilder();
			 strURL.append("http://192.168.1.100:50/vendas/inserir.php?produto=");
			 strURL.append(cursor.getInt(cursor.getColumnIndex("produto")));
			 strURL.append("&preco=");
			 strURL.append(cursor.getDouble(cursor.getColumnIndex("preco")));
			 strURL.append("&latitude=");
			 strURL.append(cursor.getDouble(cursor.getColumnIndex("la")));
			 strURL.append("&longitude=");
			 strURL.append(cursor.getDouble(cursor.getColumnIndex("lo")));

			 
			 try{
				 URL url = new URL(strURL.toString());
				 HttpURLConnection http = (HttpURLConnection) url.openConnection();
				 InputStreamReader ips = new InputStreamReader(http.getInputStream());
				 BufferedReader line = new BufferedReader(ips);
				 
				 String linhaRetorno = line.readLine();

				 if(linhaRetorno.equals("Y")){
					 db.delete("vendas", "_id=?", new String[]{String.valueOf(cursor.getInt(0))});
					 totalReplicado++;
					 hl.sendEmptyMessage(0);
				 }
			 } catch(Exception ex){
				 error = ex.getMessage();
				 hl.sendEmptyMessage(2);
			 }
		 }
		 
		 if(totalDB == totalReplicado){
			 hl.sendEmptyMessage(1);
		 } else {
			 error = "Ocorreu algum erro no sistema.";
			 hl.sendEmptyMessage(2);
		 }
	}
	
	public Handler hl = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == 0){
				pgd.setProgress(pgd.getProgress() + 1);
			} else if(msg.what == 1){
				 pgd.dismiss();
				 Toast.makeText(MainActivity.this, "Sucesso na replica��o!", Toast.LENGTH_LONG).show();
			} else if(msg.what == 2){
				pgd.dismiss();
				Toast.makeText(MainActivity.this, "Erro na replica��o: " + error, Toast.LENGTH_LONG).show();
			}
		}
	};
}