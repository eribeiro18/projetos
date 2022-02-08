package app.eribeiro.aluno.appminhaideiadb.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import app.eribeiro.aluno.appminhaideiadb.datamodel.ClienteDataModel;
import app.eribeiro.aluno.appminhaideiadb.datamodel.ProdutoDataModel;

public class AppDataBase extends SQLiteOpenHelper {

    public static final String DB_NAME = "appMinhaIdeia.sqllite";
    public static final int DB_VERSION = 1;

    public AppDataBase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClienteDataModel.criarTabela());
        db.execSQL(ProdutoDataModel.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
