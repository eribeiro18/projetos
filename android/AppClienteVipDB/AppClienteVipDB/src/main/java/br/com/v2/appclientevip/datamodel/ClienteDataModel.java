package br.com.v2.appclientevip.datamodel;

public class ClienteDataModel {

    public static final String TABELA = "cliente";

    public static final String ID = "id";
    public static final String PRIMEIRO_NOME = "primeiroNome";
    public static final String SOBRE_NOME = "sobreNome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static final String PESSOA_FISICA = "pessoaFisica";
    public static final String DATA_INC = "dataInc";
    public static final String DATA_ALT = "DataAlt";

    private static String query;

    public static String gerarTabela(){
        query = "CREATE TABLE " + TABELA + " ( ";
        query += ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += PRIMEIRO_NOME + " TEXT, ";
        query += SOBRE_NOME + " TEXT, ";
        query += EMAIL + " TEXT, ";
        query += SENHA + " TEXT, ";
        query += PESSOA_FISICA + " INTEGER, ";
        query += DATA_INC + " TEXT, ";
        query += DATA_ALT + " TEXT ";
        query += " )";
        return query;
    }
}
