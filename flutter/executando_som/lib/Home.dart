import 'package:flutter/material.dart';
import 'package:audioplayers/audioplayers.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {

  AudioPlayer audioPlayer = AudioPlayer();
  AudioCache audioCache = AudioCache(prefix: "assets/");
  bool primeiraExecucao = true;
  double volume = 0.5;

  _executarAudioLocal() async {
    Uri url = await audioCache.fetchToMemory("audios/musica.mp3");
    audioPlayer.setVolume(volume);
    if(primeiraExecucao){
      audioPlayer.play(UrlSource(url.toString()));
      primeiraExecucao = false;
    }else{
      audioPlayer.resume();
    }
  }

  _pausar() async{
    audioPlayer.pause();
  }

  _parar() async {
    audioPlayer.stop();
  }

  _executarAudioURL() async {
    String url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
    audioPlayer.play(UrlSource(url));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Executando sons"),),
      body: Column(
        children: [
          Slider(
              value: volume,
              min: 0,
              max: 1,
              divisions: 10,
              onChanged: (novoVolume){
                setState(() {
                  volume = novoVolume;
                });
                audioPlayer.setVolume(novoVolume);
              }
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Padding(
                padding: EdgeInsets.all(8),
                child: GestureDetector(
                  child: Image.asset("assets/imagens/executar.png"),
                  onTap: (){
                    _executarAudioLocal();
                  },
                ),
              ),
              Padding(
                padding: EdgeInsets.all(8),
                child: GestureDetector(
                  child: Image.asset("assets/imagens/pausar.png"),
                  onTap: (){
                    _pausar();
                  },
                ),
              ),
              Padding(
                padding: EdgeInsets.all(8),
                child: GestureDetector(
                  child: Image.asset("assets/imagens/parar.png"),
                  onTap: (){
                    _parar();
                  },
                ),
              ),
            ],
          )
        ],
      ),
    );
  }
}
