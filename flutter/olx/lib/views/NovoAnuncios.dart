import 'dart:io';

import 'package:brasil_fields/brasil_fields.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:image_picker/image_picker.dart';
import 'package:olx/models/Anuncio.dart';
import 'package:olx/util/Configuracoes.dart';
import 'package:olx/views/widgets/BotaoCustomizado.dart';
import 'package:olx/views/widgets/InputCustomizado.dart';
import 'package:validadores/validadores.dart';

class NovoAnuncios extends StatefulWidget {
  const NovoAnuncios({Key? key}) : super(key: key);

  @override
  State<NovoAnuncios> createState() => _NovoAnunciosState();
}

class _NovoAnunciosState extends State<NovoAnuncios> {

  List<File> _listaImagens = [];
  List<DropdownMenuItem<String>> _listaItensDropEstados = [];
  List<DropdownMenuItem<String>> _listaItensDropCategorias = [];
  String _itemSelecionadoEstado = "";
  String _itemSelecionadoCategoria = "";
  Anuncio? _anuncio;
  BuildContext? _dialogContext;

  final _formKey = GlobalKey<FormState>();

  _selecionarImagemGaleria() async {
      XFile? imagemsSelecionada = await ImagePicker().pickImage(source: ImageSource.gallery);
      if(imagemsSelecionada != null){
        _listaImagens.add(File(imagemsSelecionada!.path));
      }
  }

  _abrirDialog(BuildContext context){
      showDialog(
          context: context,
          barrierDismissible: false,
          builder: (BuildContext context){
            return AlertDialog(
              content: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  CircularProgressIndicator(),
                  SizedBox(height: 20,),
                  Text("Salvando anúncio...")
                ],
              ),
            );
          }
      );
  }

  _salvarAnuncio() async{
    _abrirDialog(_dialogContext!);
    await _uploadImagens();
    FirebaseAuth auth = FirebaseAuth.instance;
    User? usuarioLogado = await auth.currentUser;
    FirebaseFirestore db = await FirebaseFirestore.instance;
    db.collection("meus_anuncios")
        .doc(usuarioLogado!.uid)
        .collection("anuncios")
        .doc(_anuncio!.id)
        .set(_anuncio!.toMap()).then((_) {
            db.collection("anuncios")
              .doc(_anuncio!.id)
              .set(_anuncio!.toMap()).then((_){
                  Navigator.pop(_dialogContext!);
                  Navigator.pop(context);
            });
        });
  }

  Future _uploadImagens() async {
    FirebaseStorage storage = FirebaseStorage.instance;
    Reference pastaRaiz = storage.ref();
    for (var imagem in _listaImagens){
      String nomeImagem = DateTime.now().microsecondsSinceEpoch.toString();
      Reference arquivo = pastaRaiz
          .child("meus_anuncios")
          .child(_anuncio!.id!)
          .child(nomeImagem);
      UploadTask task = arquivo.putFile(imagem!);
      task.snapshotEvents.listen((TaskSnapshot taskSnapshot) async {
        if(taskSnapshot.state == TaskState.success){
          String url = await taskSnapshot.ref.getDownloadURL();
          _anuncio!.fotos!.add(url);
        }
      });
    }
  }

  @override
  void initState() {
    super.initState();
    _carregarItensDropdown();
    _anuncio = Anuncio.gerarId();
  }

  _carregarItensDropdown(){
    _listaItensDropEstados = Configuracoes.getEstados();
    _listaItensDropCategorias = Configuracoes.getCateghorias();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Novo Anúncios"),
      ),
      body: SingleChildScrollView(
        child: Container(
          padding: EdgeInsets.all(16),
          child: Form(
            key: _formKey,
            child: Column(
              children: [
                FormField<List>(
                    builder: ( state ){
                      return Column( children: [
                        Container(
                          height: 100,
                          child: ListView.builder(
                              scrollDirection:  Axis.horizontal,
                              itemCount: _listaImagens.length + 1,
                              itemBuilder: (context, indice){
                                  if(indice == _listaImagens.length){
                                      return Padding(
                                          padding: EdgeInsets.symmetric(horizontal: 8),
                                          child: GestureDetector(
                                            onTap: (){
                                              _selecionarImagemGaleria();
                                            },
                                            child: CircleAvatar(
                                              backgroundColor: Colors.grey[400],
                                              radius: 50,
                                              child: Column(
                                                mainAxisAlignment: MainAxisAlignment.center,
                                                children: [
                                                    Icon(
                                                        Icons.add_a_photo,
                                                      size: 40,
                                                      color: Colors.grey[100],
                                                    ),
                                                    Text(
                                                        "Adicionar",
                                                        style: TextStyle(
                                                          color: Colors.grey[100]
                                                        ),
                                                    )
                                                ],),
                                            ),
                                          ),
                                      );
                                  }
                                  if(_listaImagens.length > 0){
                                    return Padding(
                                        padding: EdgeInsets.symmetric(horizontal: 8),
                                      child: GestureDetector(
                                        onTap: (){
                                          showDialog(
                                              context: context,
                                              builder: (context) => Dialog(
                                                child: Column(
                                                  mainAxisSize: MainAxisSize.min,
                                                  children: [
                                                    Image.file(_listaImagens[indice]),
                                                    TextButton(
                                                        onPressed: (){
                                                          setState(() {
                                                            _listaImagens.removeAt(indice);
                                                            Navigator.of(context).pop();
                                                          });
                                                        },
                                                        child: Text(
                                                          "Excluir",
                                                          style: TextStyle(color: Colors.red)
                                                        )
                                                    )
                                                  ],
                                                ),
                                              )
                                          );
                                        },
                                        child: CircleAvatar(
                                          radius: 50,
                                          backgroundImage: FileImage(_listaImagens[indice]),
                                          child:Container(
                                            color: Color.fromRGBO(255, 255, 255, 0.4),
                                            alignment: Alignment.center,
                                            child: Icon(
                                              Icons.delete, color: Colors.red,
                                            ),
                                          ) ,
                                        ),
                                      ),
                                    );
                                  }
                                  return Container();
                              }
                          ),
                        ),
                        if(state.hasError)
                          Container(
                            child: Text(
                              "[${state.hasError}]",
                              style: TextStyle(
                                color: Colors.red,
                                fontSize: 14
                              ),
                            )
                          )
                      ],);
                    },
                    initialValue: _listaImagens,
                    validator: ( imagens ){
                      if(imagens!.length == 0){
                        return "Necessário selecionar uma imagem!";
                      }
                      return null;
                    },
                ),
                Row(
                  children: [
                    Expanded(
                        child: Padding(
                          padding: EdgeInsets.all(8),
                          child: DropdownButtonFormField(
                              items: _listaItensDropEstados,
                              validator: (valor){
                                return Validador()
                                    .add(Validar.OBRIGATORIO, msg: "Campo obrigatório")
                                    .valido(valor!.toString());
                              },
                              onChanged: (valor){
                                setState(() {
                                  _itemSelecionadoEstado = valor!.toString();
                                });
                              },
                            value: _itemSelecionadoEstado,
                              hint: Text("Estados"),
                              onSaved: (estado){
                                _anuncio!.estado = estado.toString();
                              },
                              style: TextStyle(
                                  color: Colors.black,
                                  fontSize: 20
                              ),
                          ),
                        )
                    ),
                    Expanded(
                        child: Padding(
                          padding: EdgeInsets.all(8),
                          child: DropdownButtonFormField(
                            items: _listaItensDropCategorias,
                            validator: (valor){
                              return Validador()
                                  .add(Validar.OBRIGATORIO, msg: "Campo obrigatório")
                                  .valido(valor!.toString());
                            },
                            onChanged: (valor){
                              setState(() {
                                _itemSelecionadoCategoria = valor!.toString();
                              });
                            },
                            value: _itemSelecionadoCategoria,
                            hint: Text("Categorias"),
                            onSaved: (categoria){
                              _anuncio!.categoria = categoria.toString();
                            },
                            style: TextStyle(
                                color: Colors.black,
                                fontSize: 20
                            ),
                          ),
                        )
                    ),
                  ],
                ),
                Padding(
                    padding: EdgeInsets.only(bottom: 15, top: 15),
                    child: InputCustomizado(
                      hint: "Título",
                      onSaved: (titulo){
                        _anuncio!.titulo = titulo.toString();
                      },
                      validator: (valor){
                        return Validador()
                            .add(Validar.OBRIGATORIO, msg: "Campo obrigatório")
                            .valido(valor!.toString());
                      },
                    ),
                ),

                Padding(
                  padding: EdgeInsets.only(bottom: 15),
                  child: InputCustomizado(
                    type: TextInputType.number,
                    hint: "Preço",
                    onSaved: (preco){
                      _anuncio!.preco = preco.toString();
                    },
                    inputFormatters: [
                      FilteringTextInputFormatter.digitsOnly,
                      RealInputFormatter(moeda: true)
                    ],
                    validator: (valor){
                      return Validador()
                          .add(Validar.OBRIGATORIO, msg: "Campo obrigatório")
                          .valido(valor!.toString());
                    },
                  ),
                ),

                Padding(
                  padding: EdgeInsets.only(bottom: 15),
                  child: InputCustomizado(
                    type: TextInputType.phone,
                    hint: "Telefone",
                    onSaved: (telefone){
                      _anuncio!.telefone = telefone.toString();
                    },
                    inputFormatters: [
                      FilteringTextInputFormatter.digitsOnly,
                      TelefoneInputFormatter()
                    ],
                    validator: (valor){
                      return Validador()
                          .add(Validar.OBRIGATORIO, msg: "Campo obrigatório")
                          .valido(valor!.toString());
                    },
                  ),
                ),

                Padding(
                  padding: EdgeInsets.only(bottom: 15),
                  child: InputCustomizado(
                    type: TextInputType.phone,
                    hint: "Descrição (200 caracteres)",
                    onSaved: (descricao){
                      _anuncio!.descricao = descricao.toString();
                    },
                    maxLines: null,
                    validator: (valor){
                      return Validador()
                          .add(Validar.OBRIGATORIO, msg: "Campo obrigatório")
                          .maxLength(200, msg: "Máximo de 200 caracteres")
                          .valido(valor!.toString());
                    },
                  ),
                ),
                BotaoCustomizado(
                    texto: "Cadastra Anúnico",
                    corTexto: Colors.white,
                    onPressed: (){
                      if(_formKey.currentState!.validate()){
                        _formKey.currentState!.save();
                        _dialogContext = context;
                        _salvarAnuncio();
                      }
                    }
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
