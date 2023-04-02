import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:uber/model/Usuario.dart';

class UsuarioFirebase{

  static Future<User?> getUsuarioAtual () async {
    FirebaseAuth auth = FirebaseAuth.instance;
    return await auth.currentUser;
  }

  static Future<Usuario?> getDadosUsuarioLogado () async {
    User? user = await getUsuarioAtual();
    String idUsuario = user!.uid;
    FirebaseFirestore db = FirebaseFirestore.instance;
    DocumentSnapshot snapshot = await db.collection("usuarios")
    .doc(idUsuario)
    .get();
    dynamic dados = snapshot.data();
    String tipoUsuario = dados["tipoUsuario"];
    String email = dados["email"];
    String nome = dados["nome"];
    Usuario usu = Usuario();
    usu.idUsuario = idUsuario;
    usu.tipoUsuario = tipoUsuario;
    usu.nome = nome;
    usu.email = email;
    return usu;
  }

  static atualizarDadosLocalizacao(String idRequisicao, double lat, double lon, String tipo) async{
    FirebaseFirestore db = FirebaseFirestore.instance;
    Usuario? usuario = await getDadosUsuarioLogado();
    usuario?.latitude = lat;
    usuario?.longitude = lon;

    db.collection("requisicoes")
    .doc(idRequisicao)
    .update({
      "${tipo}" : usuario?.toMap()
    });
  }

}