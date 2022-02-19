package app.modelo.meusclientes.datamodel;

public class ClienteDataModel {

    //nome da tabela
    public static final String TABELA = "Cliente";

    //campos que compoe a tabela
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";

    //query para criar a tabela
    public static String queryCriarTabela = "";

    //metodo que cria o script para criar a tabela
    public static String criarTabela(){
        queryCriarTabela += " CREATE TABLE "+TABELA+"(";
        queryCriarTabela += ID+" integer primary key autoincrement, ";
        queryCriarTabela += NOME + " text, ";
        queryCriarTabela += EMAIL + " text ";
        queryCriarTabela += ") ";
        return queryCriarTabela;
    }
}
