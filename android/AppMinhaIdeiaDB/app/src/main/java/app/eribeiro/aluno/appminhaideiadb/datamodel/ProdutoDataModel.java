package app.eribeiro.aluno.appminhaideiadb.datamodel;

public class ProdutoDataModel {

    //nome da tabela
    public static final String TABELA = "Produto";
    private int id;
    private String nome;
    private String fornecedor;

    //campos que compoe a tabela
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String FORNECEDOR = "fornecedor";

    //query para criar a tabela
    public static String queryCriarTabela = "";

    //metodo que cria o script para criar a tabela
    public static String criarTabela(){
        queryCriarTabela += " CREATE TABLE "+TABELA+"(";
        queryCriarTabela += ID+" integer primary key autoincrement, ";
        queryCriarTabela += NOME + " text, ";
        queryCriarTabela += FORNECEDOR + " text ";
        queryCriarTabela += ") ";
        return queryCriarTabela;
    }

}
