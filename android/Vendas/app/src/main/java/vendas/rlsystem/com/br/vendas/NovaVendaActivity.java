package vendas.rlsystem.com.br.vendas;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.rlsystem.vendas2.R;

public class NovaVendaActivity extends Activity implements LocationListener {


	private double la;
	private double lo;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_venda);
        
        
        Spinner spProdutos = (Spinner)findViewById(R.id.spProdutos);
        
        SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
        
        
        Cursor cursor = db.rawQuery("SELECT * FROM produtos ORDER BY nome ASC", null);
        
        String[] from = { "_id", "nome", "preco"};
        int[] to = {R.id.txvID, R.id.txvNome, R.id.txvPreco};
        
        SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.spinner, cursor, from, to);
        
        spProdutos.setAdapter(ad);
        
        db.close();
    }
    
    public void Salvar_Click(View view){
    
    	LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	Criteria criteria = new Criteria();
    	String provider = locationManager.getBestProvider(criteria, false);
    	Location location = locationManager.getLastKnownLocation(provider);
    	la = location.getLatitude();
		lo = location.getLongitude();
		
		SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);
		
		Spinner spProdutos = (Spinner)findViewById(R.id.spProdutos);
		
		SQLiteCursor dados = (SQLiteCursor)spProdutos.getAdapter().getItem(spProdutos.getSelectedItemPosition());
		
		ContentValues ctv = new ContentValues();
		ctv.put("produto", dados.getInt(0));
		ctv.put("preco", dados.getDouble(2));
		ctv.put("la", la);
		ctv.put("lo", lo);
		
		if(db.insert("vendas", "_id", ctv) > 0){
			Toast.makeText(getBaseContext(), "Sucesso", Toast.LENGTH_LONG).show();
		}
    }

	public void onLocationChanged(Location location) {
		//la = location.getLatitude();
		//lo = location.getLongitude();
		
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}