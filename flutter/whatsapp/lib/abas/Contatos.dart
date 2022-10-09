import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/model/Usuario.dart';

class Contatos extends StatefulWidget {
  const Contatos({Key? key}) : super(key: key);

  @override
  State<Contatos> createState() => _ContatosState();
}

class _ContatosState extends State<Contatos> {
  String _idUsuarioLogado = "";
  String _emailUsuarioLogado = "";

  Future<List<Usuario>> _recuperarContatos() async {
    FirebaseFirestore db = await FirebaseFirestore.instance;
    QuerySnapshot snapshot = await db.collection("usuarios").get();
    List<Usuario> listaUsuarios = [];
    for (DocumentSnapshot item in snapshot.docs) {
      dynamic dados = item.data();
      if (dados["email"] == _emailUsuarioLogado) continue;
      Usuario u = Usuario();
      u.idUsuario = item.id;
      u.email = dados["email"];
      u.nome = dados["nome"];
      u.urlImagem = dados["urlImagem"];
      listaUsuarios.add(u);
    }
    return listaUsuarios;
  }

  _recuperaUsuario() async {
    FirebaseAuth auth = FirebaseAuth.instance;
    User? usuarioLogado = await auth.currentUser;
    _idUsuarioLogado = usuarioLogado!.uid;
    _emailUsuarioLogado = usuarioLogado.email!;
  }

  @override
  void initState() {
    super.initState();
    _recuperaUsuario();
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<List<Usuario>>(
      future: _recuperarContatos(),
      builder: (context, snapshot) {
        switch (snapshot.connectionState) {
          case ConnectionState.none:
          case ConnectionState.waiting:
            return Center(
              child: Column(
                children: [
                  Text("Carregando contatos"),
                  CircularProgressIndicator()
                ],
              ),
            );
          case ConnectionState.active:
          case ConnectionState.done:
            return ListView.builder(
                itemCount: snapshot.data?.length,
                itemBuilder: (_, i) {
                  List<Usuario>? listaItem = snapshot.data;
                  Usuario u = listaItem![i];
                  return ListTile(
                      onTap: () {
                        Navigator.pushNamed(context, "/mensagens", arguments: u);
                      },
                      contentPadding: EdgeInsets.fromLTRB(16, 8, 16, 8),
                      leading: CircleAvatar(
                        maxRadius: 30,
                        backgroundColor: Colors.grey,
                        backgroundImage: u.urlImagem != null
                            ? NetworkImage(u.urlImagem!)
                            : null,
                      ),
                      title: Text(
                        u.nome!,
                        style: TextStyle(
                            fontWeight: FontWeight.bold, fontSize: 16),
                      ));
                });
        }
      },
    );
  }
}
