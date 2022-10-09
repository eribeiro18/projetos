import 'package:minhas_anotacoes/model/Anotacao.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

class AnotacaoHelper{

  static final String nomeTabela = "anotacao";
  static final String colunaId = "id";
  static final String colunaTitulo = "titulo";
  static final String colunaDescricao = "descricao";
  static final String colunaData = "Data";

  static final AnotacaoHelper _anotacaoHelper = AnotacaoHelper._internal();
  Database? _db;

  factory AnotacaoHelper(){
    return _anotacaoHelper;
  }

  AnotacaoHelper._internal(){
  }

  get db async{
    _db ??= await inicializarDB();
    return _db;
  }

  _onCreate(Database db, int version) async{
    String sql = "CREATE TABLE $nomeTabela ("
        "$colunaId INTEGER PRIMARY KEY AUTOINCREMENT, "
        "$colunaTitulo VARCHAR, "
        "$colunaDescricao TEXT, "
        "$colunaData DATETIME)";
    await db.execute(sql);
  }

  inicializarDB() async{
    final caminhBancoDados = await getDatabasesPath();
    final localBancoDados = join(caminhBancoDados, "banco_minhas_anotacoes.db");
    var db = await openDatabase(localBancoDados, version: 1, onCreate: _onCreate);
    return db;
  }

  Future<int> salvarAnotacao(Anotacao anotacao) async{
    var bancoDados = await db;
    int id = await bancoDados.insert(nomeTabela, anotacao.toMap());
    return id;
  }

  Future<int> atualizarAnotacao(Anotacao anotacao) async {
    var bancoDados = await db;
    int qtdeUpdate = await bancoDados.update(
      nomeTabela,
      anotacao.toMap(),
      where : "id = ?",
        whereArgs : [anotacao.id]
    );
    return qtdeUpdate;
  }

  recuperarAnotacoes () async {
    var bancoDados = await db;
    String sql = "SELECT * FROM $nomeTabela ORDER BY $colunaData DESC ";
    List anotacoes = await bancoDados.rawQuery(sql);
    return anotacoes;
  }

  Future<int> removerAnotacao(int id) async {
    var bancoDados = await db;
    return await bancoDados.delete(
      nomeTabela,
      where : "id = ?",
      whereArgs: [id]
    );
  }
}

/*

class Normal {

  Normal(){
  }

}

class Singleton {

  static final Singleton _singleton = Singleton._internal();

  	factory Singleton(){
      print("Singleton");
      return _singleton;
    }

    Singleton._internal(){
    	print("_internal");
  	}

}

void main() {

  var i1 = Singleton();
  print("***");
  var i2 = Singleton();

  print( i1 == i2 );

}
* */