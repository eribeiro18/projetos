import 'package:flutter/material.dart';

class TamanhoProporcional extends StatefulWidget {
  const TamanhoProporcional({super.key});

  @override
  State<TamanhoProporcional> createState() => _TamanhoProporcionalState();
}

class _TamanhoProporcionalState extends State<TamanhoProporcional> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
        title: Text("Tamanho textos"),
    ),
    body: Container(
      color: Colors.orange,
      child: FractionallySizedBox( //não pode ser usado em conjunto com column ou row, nestes caso pode ser usado o Flexible
        widthFactor: 0.50, //corresponde a 50%
        heightFactor: 0.50, //corresponde a 50%
        alignment: Alignment.topLeft,
        child: Text("Tamanho proporcional"),
      ),
    ),
    );
  }
}
