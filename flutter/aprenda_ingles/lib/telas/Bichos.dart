import 'package:flutter/material.dart';
import 'package:audioplayers/audioplayers.dart';

class Bichos extends StatefulWidget {
  const Bichos({Key? key}) : super(key: key);

  @override
  State<Bichos> createState() => _BichosState();
}

class _BichosState extends State<Bichos> {

  AudioPlayer audioPlayer = AudioPlayer();
  AudioCache audioCache = AudioCache(prefix: "assets/audios/");

  _executarAudioLocal(String nomeAudio) async {
    Uri url = await audioCache.fetchToMemory(nomeAudio + ".mp3");
    audioPlayer.play(UrlSource(url.toString()));
  }

  @override
  void initState() {
    super.initState();
    audioCache.loadAll([
      "cao.mp3","gato.mp3","leao.mp3",
      "macaco.mp3","ovelha.mp3","vaca.mp3"
    ]);
  }

  @override
  Widget build(BuildContext context) {

    //aspectRatio é a altura dividido pelo largura(ou ao contrario)
    //e o MediaQuery devolve o tamanho do dispositivo e
    //tambem o aspectRatio conforme abaixo
    double largura = MediaQuery.of(context).size.width;
    double altura = MediaQuery.of(context).size.height;

    //gridview já vem com scrollview ativado
    return GridView.count(
      crossAxisCount: 2,
      //abaixo default é 1, aspectRatio explicação acima
      childAspectRatio: MediaQuery.of(context).size.aspectRatio * 2,
      //default vertical
      //scrollDirection: Axis.horizontal,
      children: <Widget>[
        GestureDetector(
          onTap: (){
            _executarAudioLocal("cao");
          },
          child: Image.asset("assets/imagens/cao.png"),
        ),
        GestureDetector(
          onTap: (){
            _executarAudioLocal("gato");
          },
          child: Image.asset("assets/imagens/gato.png"),
        ),
        GestureDetector(
          onTap: (){
            _executarAudioLocal("leao");
          },
          child: Image.asset("assets/imagens/leao.png"),
        ),
        GestureDetector(
          onTap: (){
            _executarAudioLocal("macaco");
          },
          child: Image.asset("assets/imagens/macaco.png"),
        ),
        GestureDetector(
          onTap: (){
            _executarAudioLocal("ovelha");
          },
          child: Image.asset("assets/imagens/ovelha.png"),
        ),
        GestureDetector(
          onTap: (){
            _executarAudioLocal("vaca");
          },
          child: Image.asset("assets/imagens/vaca.png"),
        ),
      ],
    );
  }
}
