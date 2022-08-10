package android.curso.mediaescolarmvc.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.curso.mediaescolarmvc.controller.MediaEscolarController;
import android.curso.mediaescolarmvc.datamodel.MediaEscolarDataModel;
import android.curso.mediaescolarmvc.model.MediaEscolar;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

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

public class AlterarAsyncTask extends  AsyncTask<String, String, String>{

    ProgressDialog progressDialog;

    HttpURLConnection conn;
    URL url = null;
    Uri.Builder builder;

    private MediaEscolarController controller;

    Context context;


    public AlterarAsyncTask(MediaEscolar obj, Context context){
        this.builder = new Uri.Builder();

        this.context = context;

        builder.appendQueryParameter("app", "MediaEscolar");


        builder.appendQueryParameter(MediaEscolarDataModel.getIdPK(), String.valueOf(obj.getIdPK()));
        builder.appendQueryParameter(MediaEscolarDataModel.getBimestre(), obj.getBimestre());
        builder.appendQueryParameter(MediaEscolarDataModel.getSituacao(), obj.getSituacao());
        builder.appendQueryParameter(MediaEscolarDataModel.getMediaFinal(), String.valueOf(obj.getMediaFinal()));
        builder.appendQueryParameter(MediaEscolarDataModel.getNotaMateria(), String.valueOf(obj.getNotaTrabalho()));
        builder.appendQueryParameter(MediaEscolarDataModel.getNotaProva(), String.valueOf(obj.getNotaProva()));
        builder.appendQueryParameter(MediaEscolarDataModel.getMateria(), obj.getMateria());

    }

    @Override
    protected void onPreExecute() {
        Log.i("WebService", "IncluirAsyncTask()");

        progressDialog = new ProgressDialog(context);

        progressDialog.setMessage("Incluindo, por favor espere...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {

            url = new URL(UtilMediaEscolar.URL_WEB_SERVICE + "APIAlterarDados.php");

        } catch (MalformedURLException e) {

            Log.e("WebService", "MalformedURLException - " + e.getMessage());

        } catch (Exception e) {

            Log.e("WebService", "Exception - " + e.getMessage());

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

        } catch (IOException erro) {

            Log.e("WebService", "IOException - " + erro.getMessage());

        }

        try {

            int response_code = conn.getResponseCode();

            if (response_code == HttpURLConnection.HTTP_OK) {

                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                return (result.toString());

            } else {
                return ("Erro de conexão");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            conn.disconnect();
        }

    }

    @Override
    protected void onPostExecute(String result) {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }

}
