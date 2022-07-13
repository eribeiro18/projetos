import 'package:flutter/material.dart';

void main(){
  runApp(MaterialApp(
    // abaixo remove o debug
    debugShowCheckedModeBanner: false,
    //deste jeito não funciona como esperado
    /*title: "Frases do dia",*/
    home: Center(
      child: Container(
        padding: EdgeInsets.all(16),
        //o width abaixo faz o conteiner ocupar
        //toda a largura da tela tipo ocupar 100%
        //Uma outra alternativa ao width abaixo é o center que
        //engloba o conteiner conforme acima após o home
        width: double.infinity,
        //decoração apenas pra ver o contorno na area do conteiner
        decoration: BoxDecoration(
            border: Border.all(width: 3, color: Colors.amber)
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Image.asset("image/logo.png"),
            Text(
              "Clique abaixo para gerar uma frase!",
              textAlign: TextAlign.justify,
              style: TextStyle(
                  fontSize: 17,
                  fontStyle: FontStyle.italic,
                  color: Colors.black
              ),
            ),
            RaisedButton(
              onPressed: (){},
              child: Text(
                "Nova Frase",
                style: TextStyle(
                    fontSize: 25,
                    color: Colors.white,
                    fontWeight: FontWeight.bold
                ),
              ),
              color: Colors.green,
            )
          ],
        ),
      ),
    )
  ));
}