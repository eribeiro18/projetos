package app.modelo.meusclientes.datamodel;

public class ClienteDataModel {

    //nome da tabela
    public static final String TABELA = "Cliente";

    //campos que compoe a tabela
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String TELEFONE = "telefone";
    public static final String CEP = "cep";
    public static final String LOGRADOURO = "logradouro";
    public static final String NUMERO = "numero";
    public static final String BAIRRO = "bairro";
    public static final String CIDADE = "cidade";
    public static final String ESTADO = "estado";
    public static final String TERMO_USO = "termoUso";

    //query para criar a tabela
    public static String queryCriarTabela = "";

    //metodo que cria o script para criar a tabela
    public static String criarTabela(){
        queryCriarTabela += " CREATE TABLE "+TABELA+"(";
        queryCriarTabela += ID+" integer primary key autoincrement, ";
        queryCriarTabela += NOME + " text, ";
        queryCriarTabela += EMAIL + " text, ";
        queryCriarTabela += TELEFONE + " text, ";
        queryCriarTabela += CEP + " integer, ";
        queryCriarTabela += LOGRADOURO + " text, ";
        queryCriarTabela += NUMERO + " text, ";
        queryCriarTabela += BAIRRO + " text, ";
        queryCriarTabela += CIDADE + " text, ";
        queryCriarTabela += ESTADO + " text, ";
        queryCriarTabela += TERMO_USO + " integer ";
        queryCriarTabela += ") ";
        return queryCriarTabela;
    }
}
