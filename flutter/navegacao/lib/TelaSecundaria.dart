import 'package:flutter/material.dart';

class TelaSecundaria extends StatefulWidget {
  //const TelaSecundaria({Key? key}) : super(key: key);
  String valor;
  //pode passar qualquer tipo valor Integer, bool, String
  TelaSecundaria(this.valor);
  //para variavel opcional seria igual o abaixo e
  //do outro lado ficaria TelaSecundaria(valor: "passagem")
  //TelaSecundaria({this.valor});

  @override
  State<TelaSecundaria> createState() => _TelaSecundariaState();
}

class _TelaSecundariaState extends State<TelaSecundaria> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Tela Secundaria"),
        backgroundColor: Colors.blue,
      ),
      body: Container(
        padding: EdgeInsets.all(32),
        child: Column(
          children: [
            //atributo valor não tem acesso devido a esta em outra classe pra acessar igual abaixo
            Text("Segunda Tela! valor passado: ${widget.valor}"),
          ],
        ),
      ),
    );
  }
}
