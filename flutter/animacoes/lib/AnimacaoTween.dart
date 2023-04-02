import 'package:flutter/material.dart';

class AnimacaoTween extends StatefulWidget {
  const AnimacaoTween({Key? key}) : super(key: key);

  @override
  State<AnimacaoTween> createState() => _AnimacaoTweenState();
}

class _AnimacaoTweenState extends State<AnimacaoTween> {

  final colorTween = ColorTween(begin: Colors.white, end: Colors.orange);

  @override
  Widget build(BuildContext context) {
    return Center(
      //com o tween consegui animar qualquer widget
      //exemplo 1
     /* child: TweenAnimationBuilder(
        duration: Duration(seconds: 2),
        curve: Curves.linear,
        tween: Tween<double>(begin: 0, end: 6.28),
        builder: (BuildContext context, double angulo, Widget? widget){
          return Container(
            color: Colors.green,
            width: angulo,
            height: 60,
          );
        },
      ),*/
     //exemplo 2
     /*child: TweenAnimationBuilder(
       duration: Duration(seconds: 2),
       curve: Curves.linear,
       tween: Tween<double>(begin: 0, end: 6.28),
       builder: (BuildContext context, double angulo, Widget? widget){
          return Transform.rotate(
            angle: angulo,
            child: Image.asset("imagens/logos.png"),
          );
       },
     ),*/
      //exemplo 3
      child: TweenAnimationBuilder(
        duration: Duration(seconds: 2),
        curve: Curves.linear,
        tween: colorTween,
        child: Image.asset("imagens/estrelas.jpg"), //esta imagem é passada no widget na linha abaixo
        builder: (BuildContext context, Color? cor, Widget? widget){
          return ColorFiltered(
              colorFilter: ColorFilter.mode(cor!, BlendMode.overlay),
              child: widget,
          );
        },
      ),
    );
  }
}

