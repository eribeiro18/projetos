package vendasnovo.vanvan.com.br.vendasnovo;

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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

/**
 * Created by evandro on 19/09/17.
 */

public class NovaVendaAct extends AppCompatActivity implements LocationListener {

    private double la;
    private double lo;
    private LocationManager locationManager;
    private Location location;
    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;
    private boolean canGetLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novavendaact);

        Spinner spProduto = (Spinner) findViewById(R.id.spProduto);

        SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("SELECT * FROM produtos order by nome asc", null);

        String[] from = {"_id", "nome", "preco"};
        int[] to = {R.id.txtId, R.id.txtId, R.id.txtPreco};
        SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.spinner, cursor, from , to);
        spProduto.setAdapter(ad);
        db.close();
    }

    public void salvarVenda(View view){
/*        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria,false);*/
        Location loc = this.getLocation();
        la = location.getLatitude();
        lo = location.getLongitude();

        SQLiteDatabase db = openOrCreateDatabase("vendas.db", Context.MODE_PRIVATE, null);

        Spinner spProduto = (Spinner) findViewById(R.id.spProduto);

        SQLiteCursor dados = (SQLiteCursor) spProduto.getAdapter().getItem(spProduto.getSelectedItemPosition());

        ContentValues contentValues =  new ContentValues();

        contentValues.put("produto", dados.getInt(0));
        contentValues.put("preco",dados.getDouble(2));
        contentValues.put("la", la);
        contentValues.put("lo", lo);

        db.insert("vendas", "_id", contentValues);
        Toast.makeText(getBaseContext(), "Sucesso", Toast.LENGTH_LONG).show();
    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                    Log.d("Network", "Network Enabled");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            la = location.getLatitude();
                            lo = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                la = location.getLatitude();
                                lo = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
