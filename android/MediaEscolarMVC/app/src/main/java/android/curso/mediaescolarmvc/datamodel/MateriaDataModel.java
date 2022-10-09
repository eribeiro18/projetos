package android.curso.mediaescolarmvc.datamodel;

public class MateriaDataModel {

    private final static String TABELA = "materia";

    private final static String id = "id";
    private final static String codigoDaMateria = "codigoDaMateria";
    private final static String nomeDaMateria = "nomeDaMateria";

    private static String queryCriarTabela = "";


    public static String criarTabela() {
        queryCriarTabela = "CREATE TABLE " + TABELA;
        queryCriarTabela += "(";
        queryCriarTabela += id + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        queryCriarTabela += codigoDaMateria + " TEXT, ";
        queryCriarTabela += nomeDaMateria + " TEXT ";
        queryCriarTabela += ")";
        return queryCriarTabela;
    }

    public static String getTABELA() {
        return TABELA;
    }

    public static String getCodigoDaMateria() {
        return codigoDaMateria;
    }

    public static String getId() {
        return id;
    }

    public static String getNomeDaMateria() {
        return nomeDaMateria;
    }

    public static String getQueryCriarTabela() {
        return queryCriarTabela;
    }

    public static void setQueryCriarTabela(String queryCriarTabela) {
        MateriaDataModel.queryCriarTabela = queryCriarTabela;
    }
}
