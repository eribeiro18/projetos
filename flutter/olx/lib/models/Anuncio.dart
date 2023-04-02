import 'package:cloud_firestore/cloud_firestore.dart';

class Anuncio{

  String? _id;
  String? _estado;
  String? _categoria;
  String? _titulo;
  String? _preco;
  String? _telefone;
  String? _descricao;
  List<String>? _fotos;

  Anuncio();

  Anuncio.fromDocumentSnapshot(DocumentSnapshot doc){
    this.id = doc.id;
    this.estado = doc["estado"];
    this.categoria= doc["categoria"];
    this.titulo= doc["titulo"];
    this.preco= doc["preco"];
    this.telefone= doc["telefone"];
    this.descricao= doc["descricao"];
    this.fotos = List<String>.from(doc["fotos"]);
  }

  Anuncio.gerarId(){
    FirebaseFirestore db = FirebaseFirestore.instance;
    DocumentReference ref = db.collection("meus_anuncios").doc();
    this.id = ref.id;
    this.fotos = [];
  }

  Map<String, dynamic> toMap(){
    Map<String, dynamic> map = {
      "id" : this.id,
      "estado" : this.estado,
      "titulo" : this.titulo,
      "preco" : this.preco,
      "telefone" : this.telefone,
      "descricao" : this.descricao,
      "fotos" : this.fotos
    };
    return map;
  }

  String? get id => _id;

  set id(String? value) {
    _id = value;
  }

  String? get estado => _estado;

  List<String>? get fotos => _fotos;

  set fotos(List<String>? value) {
    _fotos = value;
  }

  String? get descricao => _descricao;

  set descricao(String? value) {
    _descricao = value;
  }

  String? get telefone => _telefone;

  set telefone(String? value) {
    _telefone = value;
  }

  String? get preco => _preco;

  set preco(String? value) {
    _preco = value;
  }

  String? get titulo => _titulo;

  set titulo(String? value) {
    _titulo = value;
  }

  String? get categoria => _categoria;

  set categoria(String? value) {
    _categoria = value;
  }

  set estado(String? value) {
    _estado = value;
  }
}