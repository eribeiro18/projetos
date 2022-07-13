import 'package:flutter/material.dart';

/*
Stateless -> Widgets que não pode ser alterados (corresponde a constatnes)
Stateful  -> Widgets que podem ser alterados (corresponde a variavies)
 */

void main(){
  runApp(MaterialApp(
    // abaixo remove o debug
      debugShowCheckedModeBanner: false,
      //deste jeito não funciona como esperado
      /*title: "Frases do dia",*/
      home: Home(),
  ));
}

// Neste conexto o build é chamado apenas uma vez.
class Home extends StatelessWidget {
  //const Home({Key? key}) : super(key: key);

  var _titulo = "Instagram";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(_titulo),
        backgroundColor: Colors.green,
      ),
      body: Padding(
        padding: EdgeInsets.all(16),
        child: Text("Conteúdo principal..."),
      ),
      bottomNavigationBar: BottomAppBar(
        color: Colors.lightGreen,
        child: Padding(
          padding: EdgeInsets.all(16),
          child: Row(
            children: [
              Text("Conteúdo principal 1"),
              Text("Conteúdo principal 2")
            ],
          ),
        ),
      ),
    );
  }
}
