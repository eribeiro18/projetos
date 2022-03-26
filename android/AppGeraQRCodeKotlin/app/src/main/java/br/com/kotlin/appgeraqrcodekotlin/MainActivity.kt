package br.com.kotlin.appgeraqrcodekotlin

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter


class MainActivity : AppCompatActivity() {

    var editConteudoQrCode : EditText? = null;
    var btnGerarQRCode : Button? = null;
    var imgQrCode : ImageView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarComponets();
        btnGerarQRCode!!.setOnClickListener {
            if(TextUtils.isEmpty(editConteudoQrCode!!.text.toString())){
                editConteudoQrCode!!.error = "*"
                editConteudoQrCode!!.requestFocus()
            }else{
                geraQrCode(editConteudoQrCode!!.text.toString())
            }
        }
    }

    private fun geraQrCode(conteudo: String) {
        val qrCode = QRCodeWriter()
        try {
            val bit = qrCode.encode(conteudo, BarcodeFormat.QR_CODE, 200, 200)
            val largura = bit.width
            val altura = bit.height
            val bitMap = Bitmap.createBitmap(largura, altura, Bitmap.Config.RGB_565)
            for (x in 0 until largura) {
                for (y in 0 until altura) {
                    bitMap.setPixel(x, y, if (bit[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            imgQrCode!!.setImageBitmap(bitMap)
        } catch (ex: WriterException) {
            ex.printStackTrace()
        }
    }

    private fun iniciarComponets() {
        editConteudoQrCode = findViewById(R.id.editConteudoQrCode)
        btnGerarQRCode = findViewById(R.id.btnGerarQRCode)
        imgQrCode = findViewById(R.id.imgQrCode)
    }
}