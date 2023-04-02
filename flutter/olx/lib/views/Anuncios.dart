import 'dart:async';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:olx/models/Anuncio.dart';
import 'package:olx/util/Configuracoes.dart';
import 'package:olx/views/widgets/ItemAnuncio.dart';

class Anuncios extends StatefulWidget {
  const Anuncios({Key? key}) : super(key: key);

  @override
  State<Anuncios> createState() => _AnunciosState();
}

class _AnunciosState extends State<Anuncios> {

  List<String> itensMenu = [];
  String _itemSelecionadoEstado = "";
  String _itemSelecionadoCategoria = "";
  List<DropdownMenuItem<String>> _listaItensDropEstados = [];
  List<DropdownMenuItem<String>> _listaItensDropCategorias = [];
  FirebaseFirestore db = FirebaseFirestore.instance;

  final _controler = StreamController<QuerySnapshot>.broadcast();

  _escolhaMenuItem(String itemEscolhido){
    switch (itemEscolhido){
      case "Menu anúncios":
        Navigator.pushNamed(context, "/meus-anuncios");
        break;
      case "Entrar / Cadastrar":
        Navigator.pushNamed(context, "/login");
        break;
      case "Deslogar":
        _deslogar();
        break;
    }
  }

  _carregarItensDropdown(){
    _listaItensDropEstados = Configuracoes.getEstados();
    _listaItensDropCategorias = Configuracoes.getCateghorias();
  }


  _deslogar() async{
    FirebaseAuth auth = FirebaseAuth.instance;
    await auth.signOut();
    Navigator.pushNamed(context, "/login");
  }

  Future _verificarUsuarioLogal() async{
    FirebaseAuth auth = FirebaseAuth.instance;
    User? user = await auth.currentUser;
    if(user == null){
      itensMenu = ["Entrar / Cadastrar"];
    }else{
      itensMenu = ["Meus anúncios", "Deslogados"];
    }
  }

  @override
  void initState() {
    super.initState();
    _carregarItensDropdown();
    _verificarUsuarioLogal();
    _adicionarListenerAnuncios();
  }

  Future<Stream<QuerySnapshot?>?> _filtrarAnuncios() async{
    Query query = db.collection("anuncios");
    if(_itemSelecionadoEstado != null){
      query = query.where("estado", isEqualTo: _itemSelecionadoEstado);
    }
    if(_itemSelecionadoCategoria != null){
      query = query.where("categoria", isEqualTo: _itemSelecionadoCategoria);
    }
    Stream<QuerySnapshot> stream = query.snapshots();
    stream.listen((event) {
      _controler.add(event);
    });
  }

  Future<Stream<QuerySnapshot?>?> _adicionarListenerAnuncios() async{
    Stream<QuerySnapshot> stream = db
        .collection("anuncios")
        .snapshots();

    stream.listen((event) {
      _controler.add(event);
    });
  }

  @override
  Widget build(BuildContext context) {

    var carregandoDados = Center(
      child: Column(
        children: [
          Text("Carregando Anúncios..."),
          CircularProgressIndicator()
        ],
      ),
    );

    return Scaffold(
      appBar: AppBar(
        title: Text("OLX"),
        elevation: 0,
        actions: [
          PopupMenuButton(
              onSelected: _escolhaMenuItem,
              itemBuilder: (context) {
                return itensMenu.map((String item){
                  return PopupMenuItem<String>(
                      value: item,
                      child: Text(item)
                  );
                }).toList();
              }
          )
        ],
      ),
      body: Container(
        child: Column(
          children: [
            Row(
              children: [

                Expanded(
                  child: DropdownButtonHideUnderline(
                      child: Center(
                        child: DropdownButton(
                            iconEnabledColor: Color(0xff9c27b0),
                            value: _itemSelecionadoEstado,
                            items: _listaItensDropEstados,
                            style: TextStyle(
                              fontSize:  22,
                              color: Colors.black
                            ),
                            onChanged: (estado){
                                setState(() {
                                  _itemSelecionadoEstado = estado.toString();
                                  _filtrarAnuncios();
                                });
                            }
                        ),
                      )
                  )
                ),
                Container(
                  color: Colors.grey[200],
                  width: 2,
                  height: 60,
                ),
                Expanded(
                    child: DropdownButtonHideUnderline(
                        child: Center(
                          child: DropdownButton(
                              iconEnabledColor: Color(0xff9c27b0),
                              value: _itemSelecionadoCategoria,
                              items: _listaItensDropCategorias,
                              style: TextStyle(
                                  fontSize:  22,
                                  color: Colors.black
                              ),
                              onChanged: (categoria){
                                setState(() {
                                  _itemSelecionadoCategoria = categoria.toString();
                                  _filtrarAnuncios();
                                });
                              }
                          ),
                        )
                    )
                )
              ],),
              StreamBuilder(
                  stream: _controler.stream,
                  builder: (context, snapshot){
                    switch(snapshot.connectionState){
                      case ConnectionState.none:
                      case ConnectionState.waiting:
                        return carregandoDados;
                        break;
                      case ConnectionState.active:
                      case ConnectionState.done:
                        QuerySnapshot querySnapshot = snapshot.data as QuerySnapshot;
                        if(querySnapshot.docs.length == 0){
                          return Container(
                            padding: EdgeInsets.all(25),
                            child: Text(
                              "Nenhum anúncio! :( ",
                              style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),),
                          );
                        }
                        return Expanded(
                            child:  ListView.builder(
                                //controller: _scrollController,
                                itemCount: querySnapshot.docs.length,
                                itemBuilder: (_, indice){
                                  List<DocumentSnapshot> anuncios =
                                  querySnapshot.docs.toList();
                                  DocumentSnapshot item = anuncios[indice];
                                  Anuncio anuncio = Anuncio.fromDocumentSnapshot(item);
                                  return ItemAnuncio(
                                    anuncio: anuncio,
                                    onTapItem: (){
                                      Navigator.pushNamed(context, "/detalhes-anuncios", arguments: anuncio);
                                    },
                                  );
                                }
                            )
                        );
                    }
                  }
              )
          ],
        )
      ),
    );
  }
}

