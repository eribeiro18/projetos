package vendas.rlsystem.com.br.vendas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import vendas.rlsystem.com.br.vendas.R;

public class ListarVendasActivity extends Activity{

	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.listar_vendas);
	        
	        SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
	        
	        ListView ltwVendas = (ListView)findViewById(R.id.ltwVendas);
	        
	        
	        Cursor cursor = db.rawQuery("SELECT vendas._id, vendas.preco, vendas.la, vendas.lo, produtos.nome FROM vendas INNER JOIN produtos on produtos._id = vendas.produto", null);
	        
	        String[] from = { "_id", "preco", "nome", "la", "lo"};
	        int[] to = {R.id.txvID, R.id.txvPreco, R.id.txvNome, R.id.txvLa, R.id.txvLo};
	        
	        
	        SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.model_listar, cursor, from, to);
	        
	        ltwVendas.setAdapter(ad);
	        
	        ltwVendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				public void onItemClick(AdapterView ad, View v,
						int position, long id) {
					
					SQLiteCursor c = (SQLiteCursor)ad.getAdapter().getItem(position);
					Intent it = new Intent(getBaseContext(), MapShowActivity.class);
					it.putExtra("latitude", c.getDouble(c.getColumnIndex("la")));
					it.putExtra("longitude", c.getDouble(c.getColumnIndex("lo")));
					startActivity(it);
				}
	        });
	        
	        
	        
	  }
}
