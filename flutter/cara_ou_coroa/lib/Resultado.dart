import 'package:flutter/material.dart';

class Resultado extends StatefulWidget {
  //caso não puder ser final somente remover
  //o const da classe resultado
  final int numero;

  const Resultado({Key? key, required this.numero}) : super(key: key);

  @override
  State<Resultado> createState() => _ResultadoState();
}

class _ResultadoState extends State<Resultado> {

  void _voltar(){
    //pop fecha a tela atual e exibi o empilhamento
    //anterior que no caso é a tela jogar
    Navigator.pop(context);
  }

  @override
  Widget build(BuildContext context) {
    var caminhoImagem = "imagens/moeda_cara.png";
    if(widget.numero == 0){
      caminhoImagem = "imagens/moeda_cara.png";
    }else{
      caminhoImagem = "imagens/moeda_coroa.png";
    }
    return Scaffold(
      backgroundColor: Color(0xff61bd86),
      body: Container(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Image.asset(caminhoImagem),
            GestureDetector(
              onTap: _voltar,
              child: Image.asset("imagens/botao_voltar.png"),
            )
          ],
        ),
      ),
    );
  }
}
