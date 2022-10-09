import 'package:flutter/material.dart';
import 'package:audioplayers/audioplayers.dart';

class Numeros extends StatefulWidget {
  const Numeros({Key? key}) : super(key: key);

  @override
  State<Numeros> createState() => _NumerosState();
}

class _NumerosState extends State<Numeros> {

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
      "1.mp3","2.mp3","3.mp3",
      "4.mp3","5.mp3","6.mp3"
    ]);
  }

  @override
  Widget build(BuildContext context) {
    return GridView.count(
      crossAxisCount: 2,
      //abaixo default é 1, aspectRatio explicação acima
      childAspectRatio: MediaQuery.of(context).size.aspectRatio * 2,
      //default vertical
      //scrollDirection: Axis.horizontal,
      children: <Widget>[
        GestureDetector(
          onTap: (){
            _executarAudioLocal("1");
          },
          child: Image.asset("assets/imagens/1.png"),
        ),
        GestureDetector(
          onTap: (){
            _executarAudioLocal("2");
          },
          child: Image.asset("assets/imagens/2.png"),
        ),
        GestureDetector(
          onTap: (){
            _executarAudioLocal("3");
          },
          child: Image.asset("assets/imagens/3.png"),
        ),
        GestureDetector(
          onTap: (){
            _executarAudioLocal("4");
          },
          child: Image.asset("assets/imagens/4.png"),
        ),
        GestureDetector(
          onTap: (){
            _executarAudioLocal("5");
          },
          child: Image.asset("assets/imagens/5.png"),
        ),
        GestureDetector(
          onTap: (){
            _executarAudioLocal("6");
          },
          child: Image.asset("assets/imagens/6.png"),
        ),
      ],
    );
  }
}
