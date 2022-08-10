import 'package:flutter/material.dart';
import 'package:youtube/CustomSearchDelegate.dart';
import 'package:youtube/telas/Biblioteca.dart';
import 'package:youtube/telas/EmAlta.dart';
import 'package:youtube/telas/Inicio.dart';
import 'package:youtube/telas/Inscricao.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {

  int _indiceAtual = 0;
  String _resultado = "";

  @override
  Widget build(BuildContext context) {

    List<Widget> telas = [
      Inicio(pesquisa: _resultado),
      EmAlta(),
      Inscricao(),
      Biblioteca(),
    ];

    return Scaffold(
      appBar: AppBar(
        iconTheme: IconThemeData(
          color: Colors.grey
        ),
        backgroundColor: Colors.white,
        title: Image.asset(
            "imagens/youtube.png",
          width: 98,
            height: 22,
        ),
        actions: [
          IconButton(
              onPressed: () async {
                String? res = await showSearch(context: context, delegate: CustomSearchDelegate());
                //setState((){
                  _resultado = res!;
                //});
              },
              icon: Icon(Icons.search)
          ),
          /*IconButton(
              onPressed: (){
                print("acao: videocam");
              },
              icon: Icon(Icons.videocam)
          ),
          IconButton(
              onPressed: (){
                print("acao: conta");
              },
              icon: Icon(Icons.account_circle)
          )*/
        ],
      ),
      body: Container(
        padding: EdgeInsets.all(16),
        child: telas[_indiceAtual],
      ),
      bottomNavigationBar: BottomNavigationBar(
          currentIndex: _indiceAtual,
          onTap: (indice){
            setState((){
              _indiceAtual = indice;
            });
          },
          //quando usado mais de 3 botões igual abaixo a parametro abaixo muda para
          //BottomNavigationBarType.shifting e fica com a cor branca sumindo com os icones
          //para melhor configuração usar o fixed igual abaixo, caso seja necessario usar o shifting
          //setar backgroundColor: Colors.red diretamente dentro do BottomNavigationBarItem
          //lembrando que a barra toda ficará com a cor do backgroundColor e mudará quando acionado o icone
          type: BottomNavigationBarType.fixed,
          fixedColor: Colors.red,
          items: [
            BottomNavigationBarItem(
                label: "Início",
                icon: Icon(Icons.home)
            ),
            BottomNavigationBarItem(
                label: "Em alta",
                icon: Icon(Icons.whatshot)
            ),
            BottomNavigationBarItem(
                label: "Inscrições",
                icon: Icon(Icons.subscriptions)
            ),
            BottomNavigationBarItem(
                label: "Biblioteca",
                icon: Icon(Icons.folder)
            ),
          ]
      ),
    );
  }
}
