package android.curso.mediaescolarmvc.util;

import android.content.Context;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by marcomaddo on 25/03/2018.
 */

public class UtilMediaEscolar {

    // URL do Servidor Apache - PHP
    // Informe o endereço IP se estiver rodando em seu computador
    // Informe URL real se estiver rodando em uma hospedagem
    public static final String URL_WEB_SERVICE = "http://192.168.0.15/mediaescolar/";

    // Tempo máximo para considerar TIMEOUT para conectar ao Apache
    public static final int CONNECTION_TIMEOUT = 10000; // 10 segundos

    // Tempo máximo para considerar erro de resposta do Apache
    public static final int READ_TIMEOUT = 15000; // 15 segundos


    public static String formatarValorDecimal(Double valor)
    {
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        return df.format(valor);
    }

    public static void showMensagem(Context context, String mensagem){

        Toast.makeText(context,mensagem,Toast.LENGTH_LONG).show();
    }
}
