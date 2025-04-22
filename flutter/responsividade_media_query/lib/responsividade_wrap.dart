import 'package:flutter/material.dart';

class ResponsividadeWrap extends StatefulWidget {
  const ResponsividadeWrap({super.key});

  @override
  State<ResponsividadeWrap> createState() => _ResponsividadeWrapState();
}

class _ResponsividadeWrapState extends State<ResponsividadeWrap> {
  @override
  Widget build(BuildContext context) {

    double altura = 200;
    double largura = 200;

    return Scaffold(
      appBar: AppBar(title: Text("Wrap")),
      body: Container(
        color: Colors.black26,
        width: MediaQuery.of(context).size.width,
        //child: Row( //substitui melhor com o wrap pois faz caber o conteudo completo
        child: Wrap(
          alignment: WrapAlignment.center,//end, center, start, spaceBetween dentre outros isso para melhorar a orginazção dos conteudos
          spacing: 10,//espaçamento entre os objetos conforme as colunas
          runSpacing: 10, //espaçamento entre os objetos conforme as linhas
          children: [
            Container(
              width: largura,
              height: altura,
              color: Colors.orange,
            ),
            Container(
              width: largura,
              height: altura,
              color: Colors.green,
            ),
            Container(
              width: largura,
              height: altura,
              color: Colors.purple,
            ),
            Container(
              width: largura,
              height: altura,
              color: Colors.black,
            ),
            Container(
              width: largura,
              height: altura,
              color: Colors.yellow,
            ),
          ],
        ),
      ),
    );
  }
}
