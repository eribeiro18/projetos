package br.com.v2.appclientevip.api;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.v2.appclientevip.datamodel.ClienteDataModel;
import br.com.v2.appclientevip.datamodel.ClientePFDataModel;
import br.com.v2.appclientevip.datamodel.ClientePJDataModel;
import br.com.v2.appclientevip.model.Cliente;

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

    public boolean insert(String tabela, ContentValues values){
        try {
            return db.insert(tabela, null, values) > 0;
        }catch (SQLException e){
            Log.e(AppUtil.LOG_APP, "Erro insert CLIENTE: " + e.getMessage());
            return false;
        }
    }

    public boolean update(String tabela, ContentValues values, Integer id){
        try {
            return db.update(tabela, values, "id=?", new String[]{Integer.toString(id)}) > 0;
        }catch (SQLException e){
            Log.e(AppUtil.LOG_APP, "Erro update CLIENTE: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String tabela, Integer id){
        try {
            return db.delete(tabela, "id=?", new String[]{Integer.toString(id)}) > 0;
        }catch (SQLException e){
            Log.e(AppUtil.LOG_APP, "Erro delete CLIENTE: " + e.getMessage());
            return false;
        }
    }

    @SuppressLint("Range")
    public List<Cliente> list(String tabela){
        List<Cliente> list = new ArrayList<>();
        String sql = "SELECT * FROM " + tabela;
        try (Cursor cursor = db.rawQuery(sql, null)){
            if(cursor.moveToFirst()){
                do {
                    Cliente c = new Cliente();
                    c.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
                    c.setPrimeiroNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.PRIMEIRO_NOME)));
                    c.setSobreNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SOBRE_NOME)));
                    c.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
                    c.setSenha(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SENHA)));
                    c.setPessoaFisica(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.PESSOA_FISICA))==1);
                    c.setDataInc(cursor.getString(cursor.getColumnIndex(ClienteDataModel.DATA_INC)));
                    c.setDataAlt(cursor.getString(cursor.getColumnIndex(ClienteDataModel.DATA_ALT)));
                    list.add(c);
                }while (cursor.moveToNext());
            }
        }catch (SQLException e){
            Log.e(AppUtil.LOG_APP, "Erro delete CLIENTE: " + e.getMessage());
        }
        return list;
    }
}
