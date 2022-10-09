import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';


class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {

  _recuperarBancoDados() async {
    final caminhoBancoDados = await getDatabasesPath();
    final localBancoDados = join(caminhoBancoDados, "banco.db");
    var db  = await openDatabase(
      localBancoDados,
      version: 1,
      onCreate: (db, novaVersao){
        String sql = "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INTEGER)";
        db.execute(sql);
      }
    );
    return db;
  }

  _salvar() async {
    Database db = await _recuperarBancoDados();
    Map<String, dynamic> dadosUsuario = {
      "nome": "Evandro Ribeiro",
      "idade" : 32
    };
    int id = await db.insert("usuarios", dadosUsuario);
    print("Salvo: $id");
  }

  _listaUsuarios() async {
    Database db = await _recuperarBancoDados();
    String sql = "SELECT * FROM usuarios ";
    List usuarios = await db.rawQuery(sql);
    //print("usuarios: " + usuarios.toString());
    for(var usuario in usuarios){
      print(
          "item id: " + usuario['id'].toString()+
          " nome: " + usuario['nome']+
          " idade: " + usuario['idade'].toString()
      );
    }
  }

  _recuperarUsuarioPorId(int id) async {
    Database db = await _recuperarBancoDados();
    List usuarios = await db.query(
        "usuarios",
        where : "id = ? ", //conteudo da string pode ser -> "id = ? AND idade = ?" no whereArgs vai na sequencia,
        whereArgs: [id],
        columns: ["id", "nome", "idade"]);
    for(var usuario in usuarios){
      print(
          "item id: " + usuario['id'].toString()+
              " nome: " + usuario['nome']+
              " idade: " + usuario['idade'].toString()
      );
    }
  }

  _excluirUsuario(int id) async {
    Database db = await _recuperarBancoDados();
    int qtde = await db.delete(
        "usuarios",
        where: "id = ?",
        whereArgs: [id]
    );
    print("quantidade de exclusão: $qtde");
  }

  _atualizarUsuario(int id) async {
    Database db = await _recuperarBancoDados();
    Map<String, dynamic> dadosUsuario = {
      "nome": "Evandro Ribeiro alterado",
      "idade" : 33
    };
    int qtde = await db.update(
        "usuarios",
        dadosUsuario,
        where: "id = ?",
        whereArgs: [id]
    );
    print("quantidade de atualizações: $qtde");
  }

  @override
  Widget build(BuildContext context) {
    //_salvar();
    //_listaUsuarios();
    //_recuperarUsuarioPorId(5);
    //_excluirUsuario(5);
    _atualizarUsuario(1);
    return Container();
  }
}
