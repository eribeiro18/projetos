import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/abas/Contatos.dart';
import 'package:whatsapp/abas/Conversas.dart';
import 'dart:io';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> with SingleTickerProviderStateMixin{

  String? _emaiUsuario;
  TabController? _tabController;
  List<String> itensMenu = ["Configurações","Deslogar"];

  Future _recurarDadosUsuario() async{
    FirebaseAuth auth = FirebaseAuth.instance;
    User? usuarioLogado = await auth.currentUser;
    setState(() {
      _emaiUsuario = usuarioLogado?.email;
    });
  }

  Future _verificaUsuarioLogado() async {
    FirebaseAuth auth = FirebaseAuth.instance;
    User? usuarioLogado = await auth.currentUser;
    if (usuarioLogado == null) {
      Navigator.pushReplacementNamed(context, "/login");
    }
  }

  @override
  void initState(){
    super.initState();
    _verificaUsuarioLogado();
    _recurarDadosUsuario();
    _tabController = TabController(length: 2, vsync: this);
  }

  @override
  void dispose() {
    super.dispose();
    _tabController!.dispose();
  }

  _escolhaMenuItem(String itemEscolhido){
    switch(itemEscolhido){
      case "Configurações":
        Navigator.pushNamed(context, "/configuracoes");
        break;
      case "Deslogar":
        _deslogarUsuario();
        break;
    }
  }

  _deslogarUsuario() async{
    FirebaseAuth auth = FirebaseAuth.instance;
    await auth.signOut();
    Navigator.pushReplacementNamed(context, "/login");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("WhatsApp"),
        elevation: Platform.isIOS ? 0 : 4,
        bottom: TabBar(
          indicatorWeight: 4,
          indicatorColor: Platform.isIOS ? Colors.grey[400] :Colors.white,
          labelStyle: TextStyle(
              fontSize: 18,
              fontWeight: FontWeight.bold
          ),
          controller: _tabController!,
          tabs: [
            Tab(text: "Conversas"),
            Tab(text: "Contatos")
          ],
        ),
        actions: [
          PopupMenuButton<String>(
            onSelected: _escolhaMenuItem,
            itemBuilder: (context){
              return itensMenu.map((e){
                return PopupMenuItem<String>(
                  value: e,
                  child: Text(e)
                );
              }).toList();
            },
          )
        ],
      ),
      body: TabBarView(
        controller: _tabController!,
        children: [
          Conversas(),
          Contatos()
        ],
      ),
    );
  }
}
