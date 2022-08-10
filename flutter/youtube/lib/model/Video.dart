class Video {

  String? id;
  String? imagem;
  String? descricao;
  String? titulo;
  String? canal;

  Video({this.id, this.imagem, this.descricao, this.titulo, this.canal});
  //construtor abaixo campo obrigatorios acima campos não obrigatorios
  //Video(this.id, this.imagem, this.descricao, this.titulo, this.canal);

  /*static converterJson(Map<String, dynamic> json){
    return Video(
        json["id"]["videoId"],
        json["snippet"]["thumbnails"]["high"]["url"],
        "",
        json["snippet"]["title"],
        json["snippet"]["channelId"],
    );
  }*/

  //ideal usar factory ao envés de static pois usa apenas uma instancia do video para todas as iterações apenas os valores são sobrescritos
  factory Video.fromJson(Map<String, dynamic> json) {
    return Video(
      id: json["id"]["videoId"],
      imagem: json["snippet"]["thumbnails"]["high"]["url"],
      titulo: json["snippet"]["title"],
      descricao: json["snippet"]["description"],
      canal: json["snippet"]["channelTitle"],
    );
  }
}