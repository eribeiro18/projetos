import 'dart:async';

import 'package:flutter/material.dart';
import 'package:minhas_viagens_new/mapa.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {

/*
  final _controller = StreamController<QuerySnapshot>.broadcast();
  FirebaseFirestore _db = FirebaseFirestore.instance;
*/

  _abriMapa(String idViagem){
    Navigator.push(context,
        MaterialPageRoute(builder: (_) => Mapa(idViagem: idViagem)));
  }

  _excluirViagem(String idViagem){
/*    _db.collection("viagens")
        .doc(idViagem)
        .delete();*/
  }
  _adicionarLocal(){
    String value = "";
    Navigator.push(context,
        MaterialPageRoute(builder: (_) => Mapa(idViagem: value)));
  }

  _adicionarListenerViagens() async{
/*    final stream = _db.collection("viagens")
        .snapshots();
    stream.listen((dados) {
      _controller.add(dados);
    });*/
  }

  @override
  void initState() {
    super.initState();
    _adicionarListenerViagens();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Minhas Viagens")),
      floatingActionButton: FloatingActionButton(
          child: Icon(Icons.add),
          backgroundColor: Color(0xff0066cc),
          onPressed: (){
            _adicionarLocal();
          }
      ),
      body: Container()
    );
  }
}
