import 'package:flutter/material.dart';
import 'dart:math';

class Jogo extends StatefulWidget {
  const Jogo({Key? key}) : super(key: key);

  @override
  State<Jogo> createState() => _JogoState();
}

class _JogoState extends State<Jogo> {

  var _opcoes = [
    "papel",
    "pedra",
    "tesoura",
  ];

  var _mensagem = "Escolha uma opção abaixo";
  var _imagemApp = AssetImage("images/padrao.png");

  void opcaoSelectionada(String escolhaUsuario){
    var numero = Random().nextInt(_opcoes.length);
    var escolhaApp = _opcoes[numero];
    switch(escolhaApp){
      case "pedra":
        setState((){
          _imagemApp = AssetImage("images/pedra.png");
        });        
        break;
      case "papel":
        setState((){
          _imagemApp = AssetImage("images/papel.png");
        });
        break;
      case "tesoura":
        setState((){
          _imagemApp = AssetImage("images/tesoura.png");
        });
        break;
    }
    if((escolhaUsuario == "pedra" && escolhaApp == "tesoura") ||
        (escolhaUsuario == "tesoura" && escolhaApp == "papel") ||
        (escolhaUsuario == "papel" && escolhaApp == "pedra")){
      setState((){
        this._mensagem = "Parabens!!! Vocês ganhou :)";
      });
    }else if((escolhaUsuario == "tesoura" && escolhaApp == "pedra") ||
        (escolhaUsuario == "papel" && escolhaApp == "tesoura") ||
        (escolhaUsuario == "pedra" && escolhaApp == "papel")){
      setState((){
        this._mensagem = "Você perdeu :(";
      });
    }else{
      setState(() {
        this._mensagem = "Empatamos ;)";
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("JokenPo"),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Padding(
            padding: EdgeInsets.only(top: 32, bottom: 16),
            child: Text("Escolha do App:",
                textAlign: TextAlign.center,
                style: TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.bold
                )),
          ),
          Image(image: _imagemApp),
          Padding(
            padding: EdgeInsets.only(top: 32, bottom: 16),
            child: Text(_mensagem,
                textAlign: TextAlign.center,
                style: TextStyle(
                    fontSize: 20,
                    fontWeight: FontWeight.bold
                )),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              GestureDetector(
                  onTap: () => opcaoSelectionada("pedra"),
                  child: Image.asset("images/pedra.png", height: 100,),
              ),
              GestureDetector(
                  onTap: () => opcaoSelectionada("papel"),
                  child: Image.asset("images/papel.png", height: 100,),
              ),
              GestureDetector(
                  onTap: () => opcaoSelectionada("tesoura"),
                  child:Image.asset("images/tesoura.png", height: 100,),
              ),
            ],
          )
        ],
      ),
    );
  }
}
