import 'package:cara_ou_coroa/Resultado.dart';
import 'package:flutter/material.dart';
import 'dart:math';

class Jogar extends StatefulWidget {
  const Jogar({Key? key}) : super(key: key);

  @override
  State<Jogar> createState() => _JogarState();
}

class _JogarState extends State<Jogar> {

  void _exibirResultado(){
    final int numero = Random().nextInt(2);
    Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => Resultado(numero: numero)));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color(0xff61bd86),
      //cor exemplo -> ffcc80 antes dele deve ser incluido 0xff
      //backgroundColor: Color(0xffffcc80),
      //cores em rgb com opacity(trasparencia)
      //backgroundColor: Color.fromRGBO(255, 204, 128, 1),
      body: Container(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Image.asset("imagens/logo.png"),
            GestureDetector(
              onTap: _exibirResultado,
              child: Image.asset("imagens/botao_jogar.png"),
            )
          ],
        ),
      ),
    );
  }
}
