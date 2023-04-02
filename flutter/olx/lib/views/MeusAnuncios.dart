import 'dart:async';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:olx/models/Anuncio.dart';
import 'package:olx/views/widgets/ItemAnuncio.dart';

class MeusAnuncios extends StatefulWidget {
  const MeusAnuncios({Key? key}) : super(key: key);

  @override
  State<MeusAnuncios> createState() => _MeusAnunciosState();
}

class _MeusAnunciosState extends State<MeusAnuncios> {

  ScrollController _scrollController = ScrollController();
  final _controller = StreamController<QuerySnapshot>.broadcast();
  FirebaseFirestore db = FirebaseFirestore.instance;
  String _idUsuario = "";

  _recuperaDadosUsuarioLogado() async {
    FirebaseAuth auth = FirebaseAuth.instance;
    User? usuarioLogado = await auth.currentUser;
    _idUsuario = usuarioLogado!.uid;
  }

  FutureOr<Stream<QuerySnapshot?>?> _adicionarListernerAnuncios() async{
    await _recuperaDadosUsuarioLogado();
    Stream<QuerySnapshot> stream = db
        .collection("meus_anuncios")
        .doc(_idUsuario)
        .collection("anuncios")
        .snapshots();
    stream.listen((event) {
      _controller.add(event);
    });
  }

  _removerAnuncio(String idAnuncio){
    db.collection("meus_anuncios")
        .doc(_idUsuario)
        .collection("anuncios")
        .doc(idAnuncio)
        .delete().then((_){
            db.collection("anuncios")
            .doc(idAnuncio)
            .delete();
        });
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _controller.close();
    _adicionarListernerAnuncios();
  }

  @override
  Widget build(BuildContext context) {

    var carregandoDados = Center(
      child: Column(
        children: [
          Text("Carregando anúncios..."),
          CircularProgressIndicator()
        ],
      ),
    );

    return Scaffold(
      appBar: AppBar(
        title: Text("Meus Anúncios"),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
      floatingActionButton: FloatingActionButton.extended(
        foregroundColor: Colors.white,
          icon: Icon(Icons.add),
          label: Text("Adicionar"),
          onPressed: (){
            Navigator.pushNamed(context, "/novo-anuncio");
          }
      ),
      body: StreamBuilder(
          builder: (context, snapshot){

            switch(snapshot.connectionState){
              case ConnectionState.none:
              case ConnectionState.waiting:
                return carregandoDados;
                break;
              case ConnectionState.active:
              case ConnectionState.done:
                QuerySnapshot? querySnapshot = snapshot.data as QuerySnapshot;
                if(snapshot.hasError) {
                  return Text("Erro ao carregar os dados!");
                }else{
                  return ListView.builder(
                      controller: _scrollController,
                      itemCount: querySnapshot.docs.length,
                      itemBuilder: (context, indice){
                        List<DocumentSnapshot> anuncios =
                        querySnapshot.docs.toList();
                        DocumentSnapshot item = anuncios[indice];
                        Anuncio anuncio = Anuncio.fromDocumentSnapshot(item);
                        return ItemAnuncio(
                            anuncio: anuncio,
                            onPressedRemover: (){
                              showDialog(
                                  context: context,
                                  builder: (context){
                                    return AlertDialog(
                                      title: Text("Confirmar"),
                                      content: Text("Deseja realmente excluir o anúncio?"),
                                      actions: [
                                        TextButton(
                                            onPressed: (){
                                              Navigator.of(context).pop();
                                            },
                                            child: Text(
                                              "Cancelar",
                                              style: TextStyle(
                                                color: Colors.grey
                                              ),
                                            )
                                        ),
                                        TextButton(
                                            style: TextButton.styleFrom(
                                              backgroundColor: Colors.red
                                            ),
                                            onPressed: (){
                                              _removerAnuncio(anuncio.id!);
                                              Navigator.of(context).pop();
                                            },
                                            child: Text(
                                              "Remover",
                                              style: TextStyle(
                                                  color: Colors.white
                                              ),
                                            )
                                        ),
                                      ],
                                    );
                                  }
                              );
                            },
                        );
                      }
                  );
                }
            }
          },
          stream: _controller.stream,
      )
    );
  }
}
