package br.com.v2.appclientevip.datamodel;

public class ClientePJDataModel {

    public static final String TABELA = "clientePJ";

    public static final String ID = "id";
    public static final String FK = "clientePFID";
    public static final String CNPJ = "cnpj";
    public static final String RAZAO_SOCIAL = "razaoSocial";
    public static final String DATA_ABERTURA = "dataAbertura";
    public static final String SIMPLES_NACIONAL = "simplesNacional";
    public static final String MEI = "mei";
    public static final String DATA_INC = "dataInc";
    public static final String DATA_ALT = "DataAlt";

    private static String query;

    public static String gerarTabela(){
        query = "CREATE TABLE " + TABELA + " ( ";
        query += ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += FK + " INTEGER, ";
        query += CNPJ + " TEXT, ";
        query += RAZAO_SOCIAL + " TEXT, ";
        query += DATA_ABERTURA + " TEXT, ";
        query += SIMPLES_NACIONAL + " INTEGER, ";
        query += MEI + " INTEGER, ";
        query += DATA_INC + " TEXT, ";
        query += DATA_ALT + " TEXT, ";
        query += " FOREIGN KEY("+ FK +") REFERENCES clientePF(id) ";
        query += " )";
        return query;
    }
}
