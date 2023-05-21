package vendas.rlsystem.com.br.vendas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import vendas.rlsystem.com.br.vendas.*;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ExportarVendasService extends Service implements Runnable {

	public void onCreate() {
		Log.d("InfoService", "InfoService");
		new Thread(ExportarVendasService.this).start();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void run() {
		 SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
	    
		 Cursor cursor = db.rawQuery("SELECT * FROM vendas", null);
		 int totalDB = cursor.getCount();
		 int totalReplicado = 0;
		 
		 while(cursor.moveToNext()){
			 StringBuilder strURL = new StringBuilder();
			 strURL.append("http://192.168.1.103/vendas/inserir.php?produto=");
			 strURL.append(cursor.getInt(cursor.getColumnIndex("produto")));
			 strURL.append("&preco=");
			 strURL.append(cursor.getDouble(cursor.getColumnIndex("preco")));
			 strURL.append("&latitude=");
			 strURL.append(cursor.getDouble(cursor.getColumnIndex("la")));
			 strURL.append("&longitude=");
			 strURL.append(cursor.getDouble(cursor.getColumnIndex("lo")));
			 Log.d("ExportarVendasService", strURL.toString());
			 try{
				 URL url = new URL(strURL.toString());
				 HttpURLConnection http = (HttpURLConnection) url.openConnection();
				 InputStreamReader ips = new InputStreamReader(http.getInputStream());
				 BufferedReader line = new BufferedReader(ips);
				 
				 String linhaRetorno = line.readLine();

				 if(linhaRetorno.equals("Y")){
					 db.delete("vendas", "_id=?", new String[]{String.valueOf(cursor.getInt(0))});
					 totalReplicado++;
					 Log.d("ExportarVendasService", "OK");
				 }
			 } catch(Exception ex){
				 Log.d("ExportarVendasService", ex.getMessage());
			 }
		 }
		 
		 
		 NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		 
		 Notification nt = null;
		 
		 if(totalDB == totalReplicado){
			 nt = new Notification(R.drawable.ic_launcher, "Status Replica��o", System.currentTimeMillis());
		 
			 nt.flags |= Notification.FLAG_AUTO_CANCEL;
			 
			 PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this.getApplicationContext(), MainActivity.class), 0);
			 
			 //nt.setLatestEventInfo(this, "Status Replica��o", "A replica��o foi feita com sucesso, total: " + totalReplicado, p);
		 } else {
			 nt = new Notification(R.drawable.ic_launcher, "Status Replica��o", System.currentTimeMillis());
			 
			 nt.flags |= Notification.FLAG_AUTO_CANCEL;
			 
			 PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this.getApplicationContext(), MainActivity.class), 0);
			 
			 //nt.setLatestEventInfo(this, "Status Replica��o", "A replica��o n�o foi feita com sucesso, total: " + totalReplicado + " de " + totalDB, p);
		 }
		 
		nt.vibrate = new long[]{100, 2000, 1000, 2000}; 
		
		notificationManager.notify((int)Math.round(Math.random()), nt);
		 
		db.close();
	}
}
