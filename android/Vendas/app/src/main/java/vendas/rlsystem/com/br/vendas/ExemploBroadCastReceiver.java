package vendas.rlsystem.com.br.vendas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class ExemploBroadCastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		
		if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){	
			Bundle bundle = intent.getExtras();

			if (bundle != null) {
				Object[] pdus = (Object[]) bundle.get("pdus");
				
				final SmsMessage[] messages = new SmsMessage[pdus.length];
				
				for (int i = 0; i < pdus.length; i++) {
					messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				}

				if (messages.length > -1) {
					if (messages[0].getMessageBody().equals("replicar")) {
						Toast.makeText(context, "SMS Chegou e replica��o ser� inicializada!", Toast.LENGTH_LONG).show();
						Intent it = new Intent("rlsystem.iniciar_servico");
						context.startService(it);
					}
				}
			}
		}
	}

}
