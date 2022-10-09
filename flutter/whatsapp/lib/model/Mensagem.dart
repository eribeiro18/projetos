class Mensagem{

  String? _idUsuario;
  String? _mensagem;
  String? _urlImagem;
  DateTime? _dtHrMsg;
  //tipo da mensagem texto ou imagem
  String? _tipo;

  Mensagem();

  Map<String, dynamic> toMap(){
    Map<String, dynamic> map = {
      "idUsuario": this.idUsuario,
      "mensagem": this.mensagem,
      "urlImagem": this.urlImagem,
      "dtHrMsg": this.dtHrMsg,
      "tipo": this.tipo
    };
    return map;
  }

  String? get tipo => _tipo;

  set tipo(String? value) {
    _tipo = value;
  }

  String? get urlImagem => _urlImagem;

  set urlImagem(String? value) {
    _urlImagem = value;
  }

  String? get mensagem => _mensagem;

  set mensagem(String? value) {
    _mensagem = value;
  }

  String? get idUsuario => _idUsuario;

  set idUsuario(String? value) {
    _idUsuario = value;
  }

  DateTime? get dtHrMsg => _dtHrMsg;

  set dtHrMsg(DateTime? value) {
    _dtHrMsg = value;
  }
}