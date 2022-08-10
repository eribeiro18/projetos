package android.curso.mediaescolarmvc.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.curso.mediaescolarmvc.R;
import android.curso.mediaescolarmvc.controller.MediaEscolarController;
import android.curso.mediaescolarmvc.datamodel.MediaEscolarDataModel;
import android.curso.mediaescolarmvc.fragmets.BimestreAFragment;
import android.curso.mediaescolarmvc.fragmets.BimestreBFragment;
import android.curso.mediaescolarmvc.fragmets.BimestreCFragment;
import android.curso.mediaescolarmvc.fragmets.BimestreDFragment;
import android.curso.mediaescolarmvc.fragmets.ResultadoFinalFragment;
import android.curso.mediaescolarmvc.model.MediaEscolar;
import android.curso.mediaescolarmvc.util.UtilMediaEscolar;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    MediaEscolarController controller;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = getBaseContext();

        controller = new MediaEscolarController(context);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sincronização do sistema....", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                SincronizarSistema task = new SincronizarSistema();
                task.execute();

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.content_fragment, new ResultadoFinalFragment()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_sair) {

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_bimestre_a) {

            setTitle("Notas 1º Bimestre");

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new BimestreAFragment()).commit();

        } else if (id == R.id.nav_bimestre_b) {

            setTitle("Notas 2º Bimestre");

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new BimestreBFragment()).commit();

        } else if (id == R.id.nav_bimestre_c) {

            setTitle("Notas 3º Bimestre");

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new BimestreCFragment()).commit();

        } else if (id == R.id.nav_bimestre_d) {

            setTitle("Notas 4º Bimestre");

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new BimestreDFragment()).commit();

        } else if (id == R.id.nav_resultado_final) {

            setTitle("Resultado Final");

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ResultadoFinalFragment()).commit();

        } else if (id == R.id.nav_share) {

            String shareBody = getString(R.string.share_body);
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.share_subject));

            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_with)));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class SincronizarSistema extends
            AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        public SincronizarSistema() {
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("app", "MediaEscolar");
        }

        @Override
        protected void onPreExecute() {
            Log.i("WebService", "SincronizarSistema()");

            progressDialog.setMessage("Sincronizando Sistema, por espere...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                url = new URL(UtilMediaEscolar.URL_WEB_SERVICE + "APISincronizarSistema.php");

                /**
                 * FALHA NO FORMATO DO RETORNO DOS DADOS DO WEBSERVICE
                 * PROVALMENTE O SCRIPT PHP FOI MODIFICADO
                 */

            } catch (MalformedURLException e) {

                Log.e("WebService", "MalformedURLException - "+e.getMessage());

            } catch (Exception e){

                Log.e("WebService", "Exception - "+e.getMessage());

            }

            try {

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(UtilMediaEscolar.READ_TIMEOUT);
                conn.setConnectTimeout(UtilMediaEscolar.CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset", "utf-8");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.connect();

                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

                /**
                 * FALHA AO ACESSAR O SERVIDOR, WEBSERVICE
                 * FORA DO AR
                 */
            } catch (IOException erro) {


                Log.e("WebService", "IOException - "+erro.getMessage());

            }

            try {

                int response_code = conn.getResponseCode();

                // 200 ok
                // 403 forbirdden
                // 404 pg não encontrada
                // 500 erro interno no servidor

                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();

                    String linha;

                    while ((linha = reader.readLine()) != null) {
                        result.append(linha);
                    }

                    return (result.toString());

                } else {
                    return ("Erro de conexão");
                }

            } catch (IOException e) {

                Log.e("WebService","IOException - "+e.getMessage());

                return e.toString();
            } finally {

                conn.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String result) {

            try {

                JSONArray jArray = new JSONArray(result);

                if (jArray.length() != 0) {

                    controller.deletarTabela(MediaEscolarDataModel.getTABELA());
                    controller.criarTabela(MediaEscolarDataModel.criarTabela());

                    for (int i = 0; i < jArray.length(); i++) {

                        JSONObject jsonObject = jArray.getJSONObject(i);

                        MediaEscolar obj = new MediaEscolar();

                        obj.setIdPK(jsonObject.getInt(MediaEscolarDataModel.getId()));
                        obj.setMateria(jsonObject.getString(MediaEscolarDataModel.getMateria()));
                        obj.setBimestre(jsonObject.getString(MediaEscolarDataModel.getBimestre()));
                        obj.setNotaProva(jsonObject.getDouble(MediaEscolarDataModel.getNotaProva()));
                        obj.setNotaTrabalho(jsonObject.getDouble(MediaEscolarDataModel.getNotaMateria()));
                        obj.setSituacao(jsonObject.getString(MediaEscolarDataModel.getSituacao()));
                        obj.setMediaFinal(jsonObject.getDouble(MediaEscolarDataModel.getMediaFinal()));

                        controller.salvar(obj);

                    }

                } else {

                    UtilMediaEscolar.showMensagem(context,"Nenhum registro encontrado no momento...");

                }

            } catch (JSONException e) {

                Log.e("WebService", "Erro JSONException " + e.getMessage());

            } finally {

                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

        }

    }

}
