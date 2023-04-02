import 'package:flutter/material.dart';

class MaisSobreAnimacoes extends StatefulWidget {
  const MaisSobreAnimacoes({Key? key}) : super(key: key);

  @override
  State<MaisSobreAnimacoes> createState() => _MaisSobreAnimacoesState();
}

class _MaisSobreAnimacoesState extends State<MaisSobreAnimacoes> with SingleTickerProviderStateMixin{

  AnimationController? _animationController;
  Animation? _animation;

  @override
  void initState() {
    super.initState();
    _animationController = AnimationController(
        duration: Duration(seconds: 2),
        vsync: this
    );
    _animation = Tween<double>(
      begin: 0,
      end: 1
    ).animate(_animationController!);
  }

  @override
  void dispose() {
    _animationController!.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    _animationController!.forward();
    return Container(
      padding: EdgeInsets.all(20),
      color: Colors.white,
      child: AnimatedBuilder(
        animation: _animation!,
        child: Image.asset("imagens/logo.png"),//widget da linha abaixo corresponde a esta imagem
        builder: (context, widget){
          //exemplo com scale
          return Transform.scale(
            scale: _animation!.value,
            child: widget,
          );
          //rotate
/*          return Transform.rotate(
              angle: _animation!.value,
              child: widget,
          );*/
        },
      ),
    );
  }
}
