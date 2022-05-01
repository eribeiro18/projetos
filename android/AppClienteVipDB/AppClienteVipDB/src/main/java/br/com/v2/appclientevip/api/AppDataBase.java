package br.com.v2.appclientevip.api;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import br.com.v2.appclientevip.datamodel.ClienteDataModel;
import br.com.v2.appclientevip.datamodel.ClientePFDataModel;
import br.com.v2.appclientevip.datamodel.ClientePJDataModel;

public class AppDataBase extends SQLiteOpenHelper {

    public static final String DB_NAME = "clienteDB.sqlite";
    public static final int DB_VERSION = 1;

    SQLiteDatabase db;

    public AppDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(ClienteDataModel.gerarTabela());
            Log.i(AppUtil.LOG_APP, "TB CLIENTE CRIADA => " + ClienteDataModel.gerarTabela());
        }catch (SQLException e){
            Log.e(AppUtil.LOG_APP, "Erro sql cliente: " + e.getMessage());
        }
        try {
            db.execSQL(ClientePFDataModel.gerarTabela());
            Log.i(AppUtil.LOG_APP, "TB CLIENTEPF CRIADA => " + ClientePFDataModel.gerarTabela());
        }catch (SQLException e){
            Log.e(AppUtil.LOG_APP, "Erro CLIENTEPF sql: " + e.getMessage());
        }
        try {
            db.execSQL(ClientePJDataModel.gerarTabela());
            Log.i(AppUtil.LOG_APP, "TB CLIENTEPJ CRIADA => " + ClientePJDataModel.gerarTabela());
        }catch (SQLException e){
            Log.e(AppUtil.LOG_APP, "Erro CLIENTEPJ sql: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
