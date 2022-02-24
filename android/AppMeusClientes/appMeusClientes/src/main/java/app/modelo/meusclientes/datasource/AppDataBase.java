package app.modelo.meusclientes.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.modelo.meusclientes.datamodel.ClienteDataModel;

public class AppDataBase extends SQLiteOpenHelper {

    public static final String DB_NAME = "appMeusClientes.sqllite";
    public static final int DB_VERSION = 1;

    SQLiteDatabase db;

    public AppDataBase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClienteDataModel.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert(String tb, ContentValues value) throws Exception{
        db = getWritableDatabase();
        return (db.insert(tb, null, value) > 0);
    }

    public boolean update(String tb, ContentValues values) throws Exception{
        db = getWritableDatabase();
        return (db.update(tb, values, "id=?", new String[] {String.valueOf(values.get("id"))}) > 0);
    }

    public boolean delete (String tb, int id) throws Exception{
        db = getWritableDatabase();
        return (db.delete(tb, "id = ?", new String[]{String.valueOf(id)}) > 0);
    }

    public List<Map<String, String>> getAll(String tabela){
        db = getWritableDatabase();
        List<Map<String, String>> list = new ArrayList<>();
        String sql = new String();
        sql = "SELECT * FROM " + tabela;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            String[] names = cursor.getColumnNames();
            do {
                Map<String, String> obj = new HashMap<>();
                int count = 0;
                for (String s: names) {
                    obj.put(s, cursor.getString(count));
                    count++;
                }
                list.add(obj);
            }while (cursor.moveToNext());
        }
        return list;
    }
}
