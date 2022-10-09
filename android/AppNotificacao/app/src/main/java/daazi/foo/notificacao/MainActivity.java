package daazi.foo.notificacao;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(daazi.foo.notificacao.R.layout.activity_main);

        String mensagem = "50% de desconto.";
        String titulo = "Promoção";

        notificarUsuario(mensagem, titulo);

    }

    private void notificarUsuario(String mensagem,
                                  String titulo) {

        NotificationCompat.Builder notificacao =
                new NotificationCompat.Builder(getBaseContext());

        notificacao.setContentTitle(titulo);
        notificacao.setContentText(mensagem);
        notificacao.setPriority(Notification.PRIORITY_HIGH);


        notificacao.setLargeIcon(
                BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.mipmap.ic_notificacao));

        notificacao.setSmallIcon(R.drawable.ic_email_notificacao);

        Intent intent =
                new Intent(getBaseContext(), NotificacaoActivity.class);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(getBaseContext(), 100,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

        notificacao.setAutoCancel(true);
        notificacao.setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager)
                        getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(9000, notificacao.build());
    }


}
