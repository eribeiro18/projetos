import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:image_picker/image_picker.dart';
import 'package:whatsapp/model/Conversa.dart';
import 'package:whatsapp/model/Mensagem.dart';
import 'package:whatsapp/model/Usuario.dart';
import 'dart:io';
import 'dart:async';
import 'package:firebase_storage/firebase_storage.dart';

class Mensagens extends StatefulWidget {
  Usuario? contato;

  Mensagens({Key? key, required this.contato}) : super(key: key);

  @override
  _MensagensState createState() => _MensagensState();
}

class _MensagensState extends State<Mensagens> {
  File? _imagem;
  String _idUsuarioLogado = "";
  String _nomeUsuarioLogado = "";
  String? _urlImagemUsuarioLogado = "";
  String _idUsuarioDestinatario = "";
  bool _subindoImagem = false;

  TextEditingController _controllerMensagem = TextEditingController();
  FirebaseFirestore db = FirebaseFirestore.instance;

  final _controller = StreamController<QuerySnapshot>.broadcast();
  ScrollController _scrollController = ScrollController();

  _enviarMensagemTexto() {
    String textoMensagem = _controllerMensagem.text;
    if (textoMensagem.isNotEmpty) {
      Mensagem msg = Mensagem();
      msg.idUsuario = _idUsuarioLogado;
      msg.mensagem = textoMensagem;
      msg.urlImagem = "";
      msg.dtHrMsg = DateTime.now();
      msg.tipo = "texto";

      //Salvar msg para remetente
      _salvarMensagem(_idUsuarioLogado, _idUsuarioDestinatario, msg);

      //Salvar msg para o destinatario
      _salvarMensagem(_idUsuarioDestinatario, _idUsuarioLogado, msg);

      _salvarConversa(msg);
    }
  }

  _salvarConversa(Mensagem msg) {
    //Salvar conversa remetente
    Conversa cRemetente = Conversa();
    cRemetente.idRemetente = _idUsuarioLogado;
    cRemetente.idDestinatario = _idUsuarioDestinatario;
    cRemetente.mensagem = msg.mensagem;
    cRemetente.nome = widget.contato!.nome;
    cRemetente.caminhoFoto = widget.contato!.urlImagem;
    cRemetente.tipoMensagem = msg.tipo;
    cRemetente.salvar();

    //Salvar conversa destinatario
    Conversa cDestinatario = Conversa();
    cDestinatario.idRemetente = _idUsuarioDestinatario;
    cDestinatario.idDestinatario = _idUsuarioLogado;
    cDestinatario.mensagem = msg.mensagem;
    cDestinatario.nome = _nomeUsuarioLogado;
    cDestinatario.caminhoFoto = _urlImagemUsuarioLogado;
    cDestinatario.tipoMensagem = msg.tipo;
    cDestinatario.salvar();
  }

  _salvarMensagem(
      String? idRemetente, String? idDestinatario, Mensagem msg) async {
    await db
        .collection("mensagens")
        .doc(idRemetente)
        .collection(idDestinatario!)
        .add(msg.toMap());
    _controllerMensagem.clear();
  }

  _enviarFoto() async {
    XFile? imagemSelecionada;
    imagemSelecionada =
        await ImagePicker().pickImage(source: ImageSource.gallery);
    _subindoImagem = true;
    String nomeImgem = DateTime.now().microsecondsSinceEpoch.toString();
    _imagem = File(imagemSelecionada!.path);
    if (_imagem != null) {
      FirebaseStorage storage = FirebaseStorage.instance;
      Reference pastaRaiz = storage.ref();
      Reference arquivo = pastaRaiz
          .child("mensagens")
          .child(_idUsuarioLogado)
          .child(nomeImgem + ".jpg");

      UploadTask task = arquivo.putFile(_imagem!);
      task.snapshotEvents.listen((TaskSnapshot taskSnapshot) {
        if (taskSnapshot.state == TaskState.running) {
          setState(() {
            _subindoImagem = true;
          });
        } else if (taskSnapshot.state == TaskState.success) {
          _recuperarUrlImagem(taskSnapshot);
          setState(() {
            _subindoImagem = false;
          });
        }
      });
    }
  }

  Future _recuperarUrlImagem(TaskSnapshot taskSnapshot) async {
    String url = await taskSnapshot.ref.getDownloadURL();
    Mensagem mensagem = Mensagem();
    mensagem.idUsuario = _idUsuarioLogado;
    mensagem.mensagem = "";
    mensagem.urlImagem = url;
    mensagem.dtHrMsg = DateTime.now();
    mensagem.tipo = "imagem";

    //Salvar mensagem para remetente
    _salvarMensagem(_idUsuarioLogado, _idUsuarioDestinatario, mensagem);

    //Salvar mensagem para o destinatario
    _salvarMensagem(_idUsuarioDestinatario, _idUsuarioLogado, mensagem);
  }

  _recuperarDadosUsuario() async {
    FirebaseAuth auth = FirebaseAuth.instance;
    User? usuarioLogado = await auth.currentUser;
    setState(() {
      _idUsuarioLogado = usuarioLogado!.uid;
      _idUsuarioDestinatario = widget.contato!.idUsuario!;
    });
    DocumentSnapshot item = await db.collection("usuarios").doc(_idUsuarioLogado).get();
    dynamic dados = item.data();
    setState(() {
      _nomeUsuarioLogado = dados["nome"];
      _urlImagemUsuarioLogado = dados["urlImagem"];
    });
    _adicionarListenerMensagens();
  }

  Stream<QuerySnapshot> _adicionarListenerMensagens(){
    final stream = db
        .collection("mensagens")
        .doc(_idUsuarioLogado)
        .collection(_idUsuarioDestinatario)
        .orderBy("dtHrMsg", descending: false)
        .snapshots();
    stream.listen((event) {
      _controller.add(event);
      //após um segundo a o scroll manda a lista lá para o final
      Timer(Duration(seconds: 1),() => _scrollController.jumpTo(_scrollController.position.maxScrollExtent));
    });
    return stream;
  }

  @override
  void dispose() {
    super.dispose();
    _controller.close();
  }

  @override
  void initState() {
    super.initState();
    _recuperarDadosUsuario();
  }

  @override
  Widget build(BuildContext context) {
    var caixaMensagem = Container(
      padding: EdgeInsets.all(8),
      child: Row(
        children: <Widget>[
          Expanded(
            child: Padding(
              padding: EdgeInsets.only(right: 8),
              child: TextField(
                controller: _controllerMensagem,
                autofocus: true,
                keyboardType: TextInputType.text,
                style: TextStyle(fontSize: 20),
                decoration: InputDecoration(
                    contentPadding: EdgeInsets.fromLTRB(32, 8, 32, 8),
                    hintText: "Digite uma mensagem...",
                    filled: true,
                    fillColor: Colors.white,
                    border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(32)),
                    prefixIcon: IconButton(
                        icon: _subindoImagem
                            ? CircularProgressIndicator()
                            : Icon(Icons.camera_alt),
                        onPressed: _enviarFoto)),
              ),
            ),
          ),
          Platform.isIOS
              ? CupertinoButton(
                  child: Text("Enviar"),
                  onPressed: _enviarMensagemTexto,
                )
              : FloatingActionButton(
                  backgroundColor: Color(0xff075E54),
                  child: Icon(
                    Icons.send,
                    color: Colors.white,
                  ),
                  mini: true,
                  onPressed: _enviarMensagemTexto,
                )
        ],
      ),
    );

    var stream = StreamBuilder<QuerySnapshot>(
      stream: _controller.stream,
      builder: (context, snapshot) {
        if (snapshot != null && snapshot.hasData) {
          switch (snapshot.connectionState) {
            case ConnectionState.none:
            case ConnectionState.waiting:
              return Center(
                child: Column(
                  children: <Widget>[
                    Text("Carregando mensagens"),
                    CircularProgressIndicator()
                  ],
                ),
              );
              break;
            case ConnectionState.active:
            case ConnectionState.done:
              QuerySnapshot? querySnapshot = snapshot.data as QuerySnapshot;

              if (snapshot.hasError) {
                return Text("Erro ao carregar os dados!");
              } else {
                return Expanded(
                  child: ListView.builder(
                      controller: _scrollController,
                      itemCount: querySnapshot.docs.length,
                      itemBuilder: (context, indice) {
                        //recupera mensagem
                        List<DocumentSnapshot> mensagens =
                            querySnapshot.docs.toList();
                        DocumentSnapshot item = mensagens[indice];

                        double larguraContainer =
                            MediaQuery.of(context).size.width * 0.8;

                        //Define cores e alinhamentos
                        Alignment alinhamento = Alignment.centerRight;
                        Color cor = Color(0xffd2ffa5);
                        if (_idUsuarioLogado != item["idUsuario"]) {
                          alinhamento = Alignment.centerLeft;
                          cor = Colors.white;
                        }

                        return Align(
                          alignment: alinhamento,
                          child: Padding(
                            padding: EdgeInsets.all(6),
                            child: Container(
                              width: larguraContainer,
                              padding: EdgeInsets.all(16),
                              decoration: BoxDecoration(
                                  color: cor,
                                  borderRadius:
                                      BorderRadius.all(Radius.circular(8))),
                              child: item["tipo"] == "texto"
                                  ? Text(
                                      item["mensagem"],
                                      style: TextStyle(fontSize: 18),
                                    )
                                  : Image.network(item["urlImagem"]),
                            ),
                          ),
                        );
                      }),
                );
              }
          }
        } else {
          return Container();
        }
      },
    );

    return Scaffold(
      appBar: AppBar(
        title: Row(
          children: <Widget>[
            CircleAvatar(
                maxRadius: 20,
                backgroundColor: Colors.grey,
                backgroundImage: widget.contato!.urlImagem != null
                    ? NetworkImage(widget.contato!.urlImagem!)
                    : null),
            Padding(
              padding: EdgeInsets.only(left: 8),
              child: Text(widget.contato!.nome!),
            )
          ],
        ),
      ),
      body: Container(
        width: MediaQuery.of(context).size.width,
        decoration: BoxDecoration(
            image: DecorationImage(
                image: AssetImage("imagens/bg.png"), fit: BoxFit.cover)),
        child: SafeArea(
            child: Container(
          padding: EdgeInsets.all(8),
          child: Column(
            children: <Widget>[
              stream,
              caixaMensagem,
            ],
          ),
        )),
      ),
    );
  }
}
