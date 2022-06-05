package app.daazi.v5.appclientevip.datamodel;

// MOR - Modelo Objeto Relacional - SQLserver, Oracle, Postgress

public class ClienteDataModel {

    /**
     *     private int id;
     *     private String primeiroNome;
     *     private String sobreNome;
     *     private String email;
     *     private String senha;
     *     private boolean pessoaFisica;
     */


    public static final String TABELA = "cliente";

    public static final String ID = "id";
    public static final String PRIMEIRO_NOME = "primeiroNome";
    public static final String SOBRE_NOME = "sobreNome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static final String PESSOA_FISICA = "pessoaFisica";
    private static final String DATA_INC = "datainc";
    private static final String DATA_ALT = "dataalt";

    private static String query;

    /**
     * CREATE TABLE cliente (
     *     id      INTEGER PRIMARY KEY AUTOINCREMENT,
     *     nome    TEXT,
     *     email   TEXT,
     *     status  INTEGER,
     *     datainc TEXT,
     *     dataalt TEXT
     * );
     */

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += PRIMEIRO_NOME+" TEXT, ";
        query += SOBRE_NOME+" TEXT, ";
        query += EMAIL+" TEXT, ";
        query += SENHA+" TEXT, ";
        query += PESSOA_FISICA+" INTEGER, ";
        query += DATA_INC+" datetime default current_timestamp, ";
        query += DATA_ALT+" datetime default current_timestamp ";

        // datetime default current_timestamp,

        query += ")";

        return query;
    }



}
