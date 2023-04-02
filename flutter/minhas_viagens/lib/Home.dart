import 'dart:async';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:minhas_viagens/mapa.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {

  final _controller = StreamController<QuerySnapshot>.broadcast();
  FirebaseFirestore _db = FirebaseFirestore.instance;

  _abriMapa(String idViagem){
    Navigator.push(context,
        MaterialPageRoute(builder: (_) => Mapa(idViagem: idViagem)));
  }

  _excluirViagem(String idViagem){
    _db.collection("viagens")
        .doc(idViagem)
        .delete();
  }
  _adicionarLocal(){
    String value = "";
    Navigator.push(context,
        MaterialPageRoute(builder: (_) => Mapa(idViagem: value)));
  }

  _adicionarListenerViagens() async{
    final stream = _db.collection("viagens")
        .snapshots();
    stream.listen((dados) {
      _controller.add(dados);
    });
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
      body: StreamBuilder<QuerySnapshot>(
        stream: _controller.stream,
        builder: (context, snapshot){
          switch(snapshot.connectionState){
            case ConnectionState.none:
            case ConnectionState.waiting:
              return Center(
                child: Column(
                  children: <Widget>[
                    Text("Carregando Locais!!"),
                    CircularProgressIndicator()
                  ],
                ),
              );
            case ConnectionState.active:
            case ConnectionState.done:
              if(snapshot.hasError){
                return Text("Erro ao carregar os dados");
              }else{
                QuerySnapshot<Object?> querySnapshot = snapshot.data  as QuerySnapshot<Object?>;
                List<DocumentSnapshot> viagens = querySnapshot.docs.toList();
                return Column(
                  children: [
                    Expanded(
                        child: ListView.builder(
                            itemCount: viagens.length,
                            itemBuilder: (context, idx){
                              DocumentSnapshot item = viagens[idx];
                              String titulo = item["titulo"];
                              String idViagem = item.id;
                              return GestureDetector(
                                onTap: (){
                                  _abriMapa(idViagem);
                                },
                                child: Card(
                                    child: ListTile(
                                      title: Text(titulo),
                                      trailing: Row(
                                        mainAxisSize: MainAxisSize.min,
                                        children: [
                                          GestureDetector(
                                            onTap: (){
                                              _excluirViagem(idViagem);
                                            },
                                            child: Padding(
                                              padding: EdgeInsets.all(8),
                                              child: Icon(
                                                Icons.remove_circle,
                                                color: Colors.red,
                                              ),
                                            ),
                                          )
                                        ],
                                      ),
                                    )
                                ),
                              );
                            }
                        )
                    )
                  ],
                );
              }
          }
        },
      ),
    );
  }
}
