package vendasnovo.vanvan.com.br.vendasnovo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by evandro on 21/09/17.
 */

public class ListaVendasAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listarvendasact);

        ListView ltwVendas = (ListView) findViewById(R.id.ltwVendas);

        SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("SELECT v._id, v.preco, p.nome, v.la, v.lo " +
                                    "FROM vendas v " +
                                    "INNER JOIN produtos p on p._id = v.produto", null);

        String[] from = {"_id", "preco", "nome", "la", "lo"};
        int[] to = {R.id.txtId, R.id.txtPreco, R.id.txtNome, R.id.txtLa, R.id.txtLo};
        SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.modellistar, cursor, from , to);
        ltwVendas.setAdapter(ad);

        ltwVendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> ad, View v, int position, long id) {
                SQLiteCursor c = (SQLiteCursor) ad.getAdapter().getItem(position);
                Intent it = new Intent(getBaseContext(), MapsShowAct.class);
                it.putExtra("latitude", c.getDouble(c.getColumnIndex("la")));
                it.putExtra("longitude", c.getDouble(c.getColumnIndex("lo")));
                startActivity(it);
            }
        });

        db.close();
    }
}
