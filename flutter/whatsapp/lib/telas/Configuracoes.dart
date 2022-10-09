import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'dart:io';

class Configuracoes extends StatefulWidget {
  const Configuracoes({Key? key}) : super(key: key);

  @override
  State<Configuracoes> createState() => _ConfiguracoesState();
}

class _ConfiguracoesState extends State<Configuracoes> {

  TextEditingController _controllerNome = TextEditingController();
  File? _imagem;
  String? _idUsuarioLogado;
  bool _subindoImagem = false;
  String? _urlImagemRecuperada;

  Future _recuperarImagem(String origemImagem) async{
    XFile? imagemSelecionada;
    switch(origemImagem){
      case "camera":
        imagemSelecionada = await ImagePicker().pickImage(source: ImageSource.camera);
        break;
      case "galeria":
        imagemSelecionada = await ImagePicker().pickImage(source: ImageSource.gallery);
        break;
    }
    setState(() {
      _imagem = File(imagemSelecionada!.path);
      if(_imagem != null){
        _subindoImagem = true;
        _uploadImagem();
      }
    });
  }

  Future _uploadImagem() async {
    FirebaseStorage storage = FirebaseStorage.instance;
    Reference pastaRaiz = storage.ref();
    Reference arquivo = pastaRaiz
        .child("perfil")
        .child(_idUsuarioLogado! + ".jpg");

    UploadTask task = arquivo.putFile(_imagem!);
    task.snapshotEvents.listen((TaskSnapshot taskSnapshot) {
      if(taskSnapshot.state == TaskState.running){
        setState(() {
         _subindoImagem = true;
        });
      }else if(taskSnapshot.state == TaskState.success){
        _recuperarUrlImagem(taskSnapshot);
        setState(() {
          _subindoImagem = false;
        });
      }
    });
  }

  Future _recuperarUrlImagem(TaskSnapshot taskSnapshot) async{
    String url = await taskSnapshot.ref.getDownloadURL();
    _atualizarUrlImagemFirestore(url);
    setState(() {
      _urlImagemRecuperada = url;
    });
  }

  _atualizarUrlImagemFirestore(String url) async{
    FirebaseFirestore db = await FirebaseFirestore.instance;
    Map<String, dynamic> dadosAtualizar = {
      "urlImagem": url
    };
    db.collection("usuarios")
        .doc(_idUsuarioLogado)
        .update(dadosAtualizar);
  }

  _atualizarNomeFirestore() async{
    FirebaseFirestore db = await FirebaseFirestore.instance;
    Map<String, dynamic> dadosAtualizar = {
      "nome": _controllerNome.text,
    };
    db.collection("usuarios")
        .doc(_idUsuarioLogado)
        .update(dadosAtualizar);
  }

  @override
  void initState() {
    super.initState();
    _recuperaUsuario();
  }

  _recuperaUsuario() async{
      FirebaseAuth auth = FirebaseAuth.instance;
      User? usuarioLogado = await auth.currentUser;
      _idUsuarioLogado = usuarioLogado!.uid;

      FirebaseFirestore db = FirebaseFirestore.instance;
      DocumentSnapshot snapshot = await db.collection("usuarios")
      .doc(_idUsuarioLogado).get();

      dynamic dados = snapshot.data();
      _controllerNome.text = dados["nome"];
      if(dados["urlImagem"] != null){
        setState(() {
          _urlImagemRecuperada = dados["urlImagem"];
        });
      }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Configurações")),
      body: Container(
        padding: EdgeInsets.all(16),
        child: Center(
          child: SingleChildScrollView(
            child: Column(
              children: [
                Container(
                  padding: EdgeInsets.all(16),
                  child: _subindoImagem
                      ? CircularProgressIndicator()
                      : Container(),
                ),
                CircleAvatar(
                  radius: 100,
                  backgroundColor: Colors.grey,
                  backgroundImage:
                  _urlImagemRecuperada != null
                      ? NetworkImage(_urlImagemRecuperada!)
                      : null,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    TextButton(
                        onPressed: (){
                          _recuperarImagem("camera");
                        },
                        child: Text("Câmera")
                    ),
                    TextButton(
                        onPressed: (){
                          _recuperarImagem("galeria");
                        },
                        child: Text("Galeria")
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.only(bottom: 8),
                  child: TextField(
                    controller: _controllerNome,
                    autofocus: true,
                    keyboardType: TextInputType.text,
                    style: TextStyle(fontSize: 20),
                    decoration: InputDecoration(
                        contentPadding: EdgeInsets.fromLTRB(32, 16, 32, 16),
                        hintText: "Nome",
                        filled: true,
                        fillColor: Colors.white,
                        border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(32))),
                  ),
                ),
                Padding(
                  padding: EdgeInsets.only(top: 16, bottom: 10),
                  child: TextButton(
                      style: TextButton.styleFrom(
                          backgroundColor: Colors.green,
                          elevation: 15,
                          shadowColor: Colors.green,
                          padding: EdgeInsets.fromLTRB(32, 16, 32, 16),
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(32))),
                      onPressed: _atualizarNomeFirestore,
                      child: Text(
                        "Salvar",
                        style: TextStyle(color: Colors.white, fontSize: 20),
                      )),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
