import 'package:flutter/material.dart';
import 'package:whatsapp/model/Usuario.dart';
import 'package:whatsapp/telas/Cadastro.dart';
import 'package:whatsapp/telas/Configuracoes.dart';
import 'package:whatsapp/telas/Home.dart';
import 'package:whatsapp/telas/Login.dart';
import 'package:whatsapp/telas/mensagens.dart';

class RouteGenerator{

  static Route<dynamic> generateRoute(RouteSettings settings){
    Route<dynamic> rota;
    final args = settings.arguments;
    switch(settings.name){
      case "/":
        rota = MaterialPageRoute(
            builder: (_) => Login()
        );
        break;
      case "/login":
        rota = MaterialPageRoute(
            builder: (_) => Login()
        );
        break;
      case "/cadastro":
        rota = MaterialPageRoute(
            builder: (_) => Cadastro()
        );
        break;
      case "/home":
        rota = MaterialPageRoute(
            builder: (_) => Home()
        );
        break;
      case "/configuracoes":
        rota = MaterialPageRoute(
            builder: (_) => Configuracoes()
        );
        break;
      case "/mensagens":
        rota = MaterialPageRoute(
            builder: (_) => Mensagens(contato: args as Usuario)
        );
        break;
      default:
        rota = _erroRota();
        break;
    }
    return rota;
  }

  static Route<dynamic> _erroRota(){
    return MaterialPageRoute(
        builder: (_){
          return Scaffold(
            appBar: AppBar(
              title: Text("Tela não encontrada!"),
            ),
            body: Center(
              child:  Text("Tela não encontrada!"),
            ),
          );
        }
    );
  }
}