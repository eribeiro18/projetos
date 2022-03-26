package app.eribeiro.aluno.appgeraqrcode;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class MainActivity extends AppCompatActivity {

    EditText editConteudoQrCode;
    Button btnGerarQRCode;
    ImageView imgQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarComponentes();
        btnGerarQRCode.setOnClickListener(view -> {
            if(TextUtils.isEmpty(editConteudoQrCode.getText().toString())){
                editConteudoQrCode.setError("*");
                editConteudoQrCode.requestFocus();
            }else{
                geraQrCode(editConteudoQrCode.getText().toString());
            }
        });
    }

    private void geraQrCode(String conteudo) {
        QRCodeWriter qrCode = new QRCodeWriter();
        try {
            BitMatrix bit = qrCode.encode(conteudo, BarcodeFormat.QR_CODE, 200, 200);
            int largura = bit.getWidth();
            int altura = bit.getHeight();
            Bitmap bitMap = Bitmap.createBitmap(largura, altura, Bitmap.Config.RGB_565);
            for (int x = 0; x < largura; x++){
                for (int y = 0; y < altura ; y++){
                    bitMap.setPixel(x,y, bit.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            imgQrCode.setImageBitmap(bitMap);
        }catch (WriterException ex){
            ex.printStackTrace();
        }
    }

    private void iniciarComponentes() {
        editConteudoQrCode = findViewById(R.id.editConteudoQrCode);
        btnGerarQRCode = findViewById(R.id.btnGerarQRCode);
        imgQrCode = findViewById(R.id.imgQrCode);
    }
}