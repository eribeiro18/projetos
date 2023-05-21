package vendasnovo.vanvan.com.br.vendasnovo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import vendasnovo.vanvan.com.br.vendasnovo.entidade.UtilXml;
import vendasnovo.vanvan.com.br.vendasnovo.entidade.Vendas;

/**
 * Created by evandro on 15/10/17.
 */

public class ExportarVendasService extends Service implements Runnable {

    private UtilXml utilXml = new UtilXml();
    private final String USER_AGENT = "Mozilla/5.0";

    public void onCreate() {
        new Thread(ExportarVendasService.this).start();
    }
    //Implementação original informando o fim atraves de notfications
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void run() {
        SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery(" SELECT * FROM vendas", null);
        String url = "http://192.168.0.103:10090/ManagementFastFoodWebService/webresources/VendasWS/vendas/replicacao";
        int totalDb        = cursor.getCount();
        int totalReplicado = 0;
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
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = null;
        Intent resultIntent = new Intent(this, MainAct.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainAct.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        if(totalDb == totalReplicado){
            mBuilder = new NotificationCompat.Builder(this)
                            .setVibrate(new long[]{100, 2000, 1000, 2000})
                            .setAutoCancel(true)
                            .setVisibility(Notification.VISIBILITY_PUBLIC)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("Status da Replicação")
                            .setContentText("Replicação finalizada com sucesso! Total: " + totalReplicado);
        }else{
            mBuilder = new NotificationCompat.Builder(this)
                            .setVibrate(new long[]{100, 2000, 1000, 2000})
                            .setAutoCancel(true)
                            .setVisibility(Notification.VISIBILITY_PUBLIC)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("Status da Replicação")
                            .setContentText("Replicação não foi finalizada com sucesso! Total: " + totalReplicado + " de " + totalDb);
        }
        mBuilder.setContentIntent(resultPendingIntent);
        notificationManager.notify((int) Math.round(Math.random()), mBuilder.build());
        db.close();
    }

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
