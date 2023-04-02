import 'package:flutter/material.dart';
import 'package:mobx_aula2/Controller.dart';
import 'package:mobx_aula2/Home.dart';
import 'package:provider/provider.dart';

void main() {
  //pode ser usado MultiProvider nele conterá uma lista de
  //providers que segui igual ao implementado abaixo.
  //Provider existe para permitir que um controller ou uma class
  //fique disponivel em qualquer outra classe ou controller
  runApp(Provider<Controller>(
      create: (_) => Controller(),
      child: MaterialApp(
        home: Home(),
      ),
  ));
}