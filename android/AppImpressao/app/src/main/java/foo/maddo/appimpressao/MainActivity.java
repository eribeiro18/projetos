package foo.maddo.appimpressao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ApiBluetooth mService = null;
    BluetoothDevice con_dev = null;

    Button btnProcurarImpressora;
    Button btnSendNormal, btnSendPrioritario;
    Button btnClose;

    private static final int REQUEST_ENABLE_BT = 2;
    private static final int REQUEST_CONNECT_DEVICE = 1;

    private static final String SENHA_NORMAL = "N";
    private static final String SENHA_PRIORITARIO = "P";

    private static final String SALTAR_LINHAS = "\n\n";

    private static final String PICOTE = "_______________\n\n";

    LinearLayout llMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = new ApiBluetooth(this, mHandler);

        // Verifica se o hardware Bluetooth existe no dispositivo
        if (!mService.isAvailable()) {
            Toast.makeText(this, "Bluetooth não disponível...", Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        /*
         * Verifique se o serviço BLUETOOTH está ativado no celular/tablet      *
         */
        if (!mService.isBTopen()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }

        /*
         * Componentes de Layout
         */
        try {

            btnProcurarImpressora = this.findViewById(R.id.btnProcurarImpressora);
            btnProcurarImpressora.setOnClickListener(new ClickEvent());

            btnSendNormal = this.findViewById(R.id.btnSendNormal);
            btnSendNormal.setOnClickListener(new ClickEvent());
            btnSendPrioritario = this.findViewById(R.id.btnSendPrioridade);
            btnSendPrioritario.setOnClickListener(new ClickEvent());
            btnClose = this.findViewById(R.id.btnClose);
            btnClose.setOnClickListener(new ClickEvent());

            btnClose.setEnabled(true);

            llMensagem = findViewById(R.id.mensagem);


        } catch (Exception ex) {

            Log.e("AppImpressao", ex.getMessage());
        }
    }

    /*
     * Desativa a ApiBluetooth se a Activity for destruída
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mService != null)
            mService.stop();
        mService = null;
    }

    /*
     * Tratamento dos evendos para cada botão da tela
     * principal do aplicativo
     */
    class ClickEvent implements View.OnClickListener {
        public void onClick(View v) {

            if (v == btnProcurarImpressora) {
                Intent serverIntent = new Intent(MainActivity.this, PrinterListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);

            } else if (v == btnSendNormal) {

                String msg = SENHA_NORMAL + "010";

                if (msg.length() > 0) {

                    mService.write(ComandoEscPos.initPrinter());

                    mService.sendMessage("SENHA: " + msg + SALTAR_LINHAS + PICOTE, "GBK");

                }

            } else if (v == btnSendPrioritario) {

                String msg = SENHA_PRIORITARIO + "020";

                if (msg.length() > 0) {

                    mService.write(ComandoEscPos.initPrinter());

                    mService.sendMessage("SENHA: " + msg + SALTAR_LINHAS + PICOTE, "GBK");

                    /*
                     * Impressão do Código de Barras padrão EAN13
                     * No código de barras está sendo impresso os seguintes
                     * dados:
                     *
                     * O tamanho do código é: 13 digitos -> 9999999999999
                     *
                     * Número do Ticket - 99999 - sempre com 5 dígitos
                     * Número da Sequência - 99999 - sempre com 5 dígitos
                     *
                     * Exemplo:
                     *
                     * Número do Ticket - 2016
                     * Número da Caixa - 20
                     *
                     *                           9999999999999
                     * Código a ser gerado será: 0000201600020
                     *
                     *
                     */
                    mService.write(ComandoEscPos.setBarcodeHeight((byte) 80));
                    mService.write(ComandoEscPos.setAlinhamentoCenter());
                    mService.write(ComandoEscPos.setStringPosition((byte) 1));
                    mService.write(ComandoEscPos.getBarCode(ComandoEscPos.BarCode.EAN13, ComandoEscPos.putZeros2BarCode("010101", "0101")));
                    /*
                     * Impressão do Código de Barras padrão EAN13
                     */
                    mService.write(ComandoEscPos.initPrinter());
                    mService.sendMessage( SALTAR_LINHAS + PICOTE, "GBK");

                }

            } else if (v == btnClose) {

                mService.stop();
                finish();
            }
        }
    }

    /**
     * Handler ApiBluetooth
     */
    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ApiBluetooth.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case ApiBluetooth.STATE_CONNECTED:
                            Toast.makeText(getApplicationContext(), "Conectado com sucesso!",
                                    Toast.LENGTH_SHORT).show();
                            btnClose.setEnabled(true);
                            btnSendNormal.setEnabled(true);
                            btnSendPrioritario.setEnabled(true);

                            btnSendNormal.setVisibility(View.VISIBLE);
                            btnSendPrioritario.setVisibility(View.VISIBLE);
                            llMensagem.setVisibility(View.GONE);
                            break;
                        case ApiBluetooth.STATE_CONNECTING:
                            break;
                        case ApiBluetooth.STATE_LISTEN:
                        case ApiBluetooth.STATE_NONE:
                            break;
                    }
                    break;
                case ApiBluetooth.MESSAGE_CONNECTION_LOST:
                    Toast.makeText(getApplicationContext(), "Conexão pertida com a impressora!",
                            Toast.LENGTH_SHORT).show();
                    btnClose.setEnabled(false);
                    btnSendNormal.setEnabled(false);
                    btnSendPrioritario.setEnabled(false);

                    btnSendNormal.setVisibility(View.GONE);
                    btnSendPrioritario.setVisibility(View.GONE);
                    llMensagem.setVisibility(View.VISIBLE);
                    break;
                case ApiBluetooth.MESSAGE_UNABLE_CONNECT:
                    Toast.makeText(getApplicationContext(), "Não foi possível estabelecer uma conexão com a impressora",
                            Toast.LENGTH_SHORT).show();
                    btnSendNormal.setEnabled(false);
                    btnSendPrioritario.setEnabled(false);
                    break;
            }
        }

    };

    /**
     * Trata o status da conexão Bluetooth
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "Bluetooth ativa com sucesso", Toast.LENGTH_LONG).show();
                } else {
                    finish();
                }
                break;
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
                    String address = data.getExtras()
                            .getString(PrinterListActivity.EXTRA_DEVICE_ADDRESS);
                    con_dev = mService.getDevByMac(address);

                    mService.connect(con_dev);
                }
                break;
        }
    }

}
