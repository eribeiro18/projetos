package app.daazi.v3.appclientevip.api;

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

import app.daazi.v3.appclientevip.datamodel.ClienteDataModel;
import app.daazi.v3.appclientevip.datamodel.ClientePFDataModel;
import app.daazi.v3.appclientevip.datamodel.ClientePJDataModel;
import app.daazi.v3.appclientevip.model.Cliente;
import app.daazi.v3.appclientevip.model.ClientePF;
import app.daazi.v3.appclientevip.model.ClientePJ;

public class AppDataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "clienteDB.sqlite";
    private static final int DB_VERSION = 1;

    Cursor cursor;

    SQLiteDatabase db;

    public AppDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criar as tabelas

        try {

            // Executar o que desejamos
            db.execSQL(ClienteDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB Cliente: " + ClienteDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar o erro
            Log.e(AppUtil.LOG_APP, "Erro TB Cliente: " + e.getMessage());
            Log.e(AppUtil.LOG_APP, "Erro TB Cliente: " + ClienteDataModel.gerarTabela());

        }

        try {

            // Executar o que desejamos
            db.execSQL(ClientePFDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB ClientePF: " + ClientePFDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar o erro
            Log.e(AppUtil.LOG_APP, "Erro TB ClientePF: " + e.getMessage());

        }

        try {

            // Executar o que desejamos
            db.execSQL(ClientePJDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB ClientePJ: " + ClientePJDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar o erro
            Log.e(AppUtil.LOG_APP, "Erro TB ClientePJ: " + e.getMessage());

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualizar o banco de dados
        // tabelas
    }


    /**
     * Incluir dados no banco de dados
     *
     * @return
     */
    public boolean insert(String tabela, ContentValues dados) {

        boolean sucesso = true;

        try {

            Log.i(AppUtil.LOG_APP, tabela + " insert() executado com sucesso. ");

            sucesso = db.insert(tabela, null, dados) > 0;

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, tabela + " falhou ao executar o insert(): " + e.getMessage());
        }

        return sucesso;
    }

    /**
     * Deletar dados no banco de dados
     *
     * @return
     */
    public boolean delete(String tabela, int id) {

        boolean sucesso = true;

        try {

            Log.i(AppUtil.LOG_APP, tabela + " delete() executado com sucesso. ");

            sucesso = db.delete(tabela, "id=?", new String[]{Integer.toString(id)}) > 0;

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, tabela + " falhou ao executar o delete(): " + e.getMessage());
        }

        return sucesso;
    }

    /**
     * Atualizar dados no banco de dados
     *
     * @return
     */
    public boolean update(String tabela, ContentValues dados) {

        boolean sucesso = true;

        try {

            int id = dados.getAsInteger("id");

            Log.i(AppUtil.LOG_APP, tabela + " update() executado com sucesso. ");

            sucesso = db.update(tabela, dados, "id=?", new String[]{Integer.toString(id)}) > 0;

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, tabela + " falhou ao executar o update(): " + e.getMessage());
        }

        return sucesso;
    }

    public List<Cliente> listClientes(String tabela) {
        List<Cliente> list = new ArrayList<>();
        Cliente cliente;
        String sql = "SELECT * FROM " + tabela;
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    cliente = new Cliente();
                    cliente.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
                    cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.PRIMEIRO_NOME)));
                    cliente.setSobreNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SOBRE_NOME)));
                    cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
                    cliente.setSenha(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SENHA)));
                    cliente.setPessoaFisica(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.PESSOA_FISICA)) == 1);
                    list.add(cliente);
                } while (cursor.moveToNext());
                Log.i(AppUtil.LOG_APP, tabela + " lista gerada com sucesso.");
            }
        } catch (SQLException e) {
            Log.e(AppUtil.LOG_APP, "Erro ao listar os dados: " + tabela);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }
        return list;
    }

    public List<ClientePF> listClientesPessoaFisica(String tabela) {
        List<ClientePF> list = new ArrayList<>();
        ClientePF clientePf;
        String sql = "SELECT * FROM " + tabela;
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    clientePf = new ClientePF();
                    clientePf.setId(cursor.getInt(cursor.getColumnIndex(ClientePFDataModel.ID)));
                    clientePf.setClienteID(cursor.getInt(cursor.getColumnIndex(ClientePFDataModel.FK)));
                    clientePf.setPrimeiroNome(cursor.getString(cursor.getColumnIndex(ClientePFDataModel.NOME_COMPLETO)));
                    clientePf.setCpf(cursor.getString(cursor.getColumnIndex(ClientePFDataModel.CPF)));
                    list.add(clientePf);
                } while (cursor.moveToNext());
                Log.i(AppUtil.LOG_APP, tabela + " lista gerada com sucesso.");
            }
        } catch (SQLException e) {
            Log.e(AppUtil.LOG_APP, "Erro ao listar os dados: " + tabela);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }
        return list;
    }

    public List<ClientePJ> listClientesPessoaJuridica(String tabela) {
        List<ClientePJ> list = new ArrayList<>();
        ClientePJ clientePj;
        String sql = "SELECT * FROM " + tabela;
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    clientePj = new ClientePJ();
                    clientePj.setId(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.ID)));
                    clientePj.setClientePFID(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.FK)));
                    clientePj.setRazaoSocial(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.RAZAO_SOCIAL)));
                    clientePj.setCnpj(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.CNPJ)));
                    clientePj.setDataAbertura(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.DATA_ABERTURA)));
                    clientePj.setSimplesNacional(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.SIMPLES_NACIONAL)) == 1 );
                    clientePj.setMei(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.MEI)) == 1);
                    list.add(clientePj);
                } while (cursor.moveToNext());
                Log.i(AppUtil.LOG_APP, tabela + " lista gerada com sucesso.");
            }
        } catch (SQLException e) {
            Log.e(AppUtil.LOG_APP, "Erro ao listar os dados: " + tabela);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }
        return list;
    }

    public int getLastPk(String tabela) {
        String sql = "SELECT seq FROM sqlite_sequence WHERE name = '" + tabela + "'";
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    return cursor.getInt(cursor.getColumnIndex("seq"));
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            Log.e(AppUtil.LOG_APP, "Erro ao listar os dados: " + tabela);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }
        return -1;
    }

    public Cliente getClienteById(String tabela, Cliente obj){
        Cliente c = new Cliente();;
        String sql = "SELECT * FROM " + tabela + " WHERE id = " + obj.getId();
        try {
            cursor = db.rawQuery(sql, null);
            if(cursor.moveToNext()){
                c.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
                c.setPrimeiroNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.PRIMEIRO_NOME)));
                c.setSobreNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SOBRE_NOME)));
                c.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
                c.setSenha(cursor.getString(cursor.getColumnIndex(ClienteDataModel.SENHA)));
                c.setPessoaFisica(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.PESSOA_FISICA)) == 1);
            }
        }catch (SQLException e){
            Log.e(AppUtil.LOG_APP, "erro ao consultar cliente por id(getClienteById) id=> " + obj.getId() +" Erro => " + e.getMessage());
        }
        return c;
    }

    public ClientePF getClientePFByFK(String tabela, int fk){
        ClientePF cPF = new ClientePF();;
        String sql = "SELECT * FROM " + tabela + " WHERE clienteID = " + fk;
        try {
            cursor = db.rawQuery(sql, null);
            if(cursor.moveToNext()){
                cPF.setId(cursor.getInt(cursor.getColumnIndex(ClientePFDataModel.ID)));
                cPF.setClienteID(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.FK)));
                cPF.setNomeCompleto(cursor.getString(cursor.getColumnIndex(ClientePFDataModel.NOME_COMPLETO)));
                cPF.setCpf(cursor.getString(cursor.getColumnIndex(ClientePFDataModel.CPF)));
            }
        }catch (SQLException e){
            Log.e(AppUtil.LOG_APP, "erro ao consultar clientePF por fk(getClientePFByFK) fk=> " + fk +" Erro => " + e.getMessage());
        }
        return cPF;
    }

    public ClientePJ getClientePJByFK(String tabela, int fk){
        ClientePJ cPJ = new ClientePJ();;
        String sql = "SELECT * FROM " + tabela + " WHERE clientePFID = " + fk;
        try {
            cursor = db.rawQuery(sql, null);
            if(cursor.moveToNext()){
                cPJ.setId(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.ID)));
                cPJ.setClientePFID(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.FK)));
                cPJ.setRazaoSocial(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.RAZAO_SOCIAL)));
                cPJ.setCnpj(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.CNPJ)));
                cPJ.setDataAbertura(cursor.getString(cursor.getColumnIndex(ClientePJDataModel.DATA_ABERTURA)));
                cPJ.setSimplesNacional(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.SIMPLES_NACIONAL)) == 1);
                cPJ.setMei(cursor.getInt(cursor.getColumnIndex(ClientePJDataModel.MEI)) == 1);
            }
        }catch (SQLException e){
            Log.e(AppUtil.LOG_APP, "erro ao consultar clientePJ por fk(getClientePFByFK) fk=> " + fk +" Erro => " + e.getMessage());
        }
        return cPJ;
    }
}
