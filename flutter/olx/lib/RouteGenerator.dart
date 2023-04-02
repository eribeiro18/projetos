import 'package:flutter/material.dart';
import 'package:olx/models/Anuncio.dart';
import 'package:olx/views/Anuncios.dart';
import 'package:olx/views/DetalhesAnuncio.dart';
import 'package:olx/views/Login.dart';
import 'package:olx/views/MeusAnuncios.dart';
import 'package:olx/views/NovoAnuncios.dart';

class RouteGenerator {

  static Route<dynamic> generateRoute(RouteSettings settings){
    Route<dynamic> rota;
    final args = settings.arguments;

    switch (settings.name){
      case "/":
        rota = MaterialPageRoute(
            builder: (_) => Anuncios()
        );
        break;
      case "/login":
        rota = MaterialPageRoute(
            builder: (_) => Login()
        );
        break;
      case "/meus-anuncios":
        rota = MaterialPageRoute(
            builder: (_) => MeusAnuncios()
        );
        break;
      case "/novo-anuncios":
        rota = MaterialPageRoute(
            builder: (_) => NovoAnuncios()
        );
        break;
      case "/detalhes-anuncios":
        rota = MaterialPageRoute(
            builder: (_) => DetalhesAnuncio(anuncio: args as Anuncio)
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