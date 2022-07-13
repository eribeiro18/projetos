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
      home: HomeStateful(),
  ));
}

class HomeStateful extends StatefulWidget {
  const HomeStateful({Key? key}) : super(key: key);

  @override
  State<HomeStateful> createState() => _HomeStatefulState();
  //trexo abaixo tem o mesmo funcionamento da linha acima
  /*State<HomeStateful> createState() {
      return _HomeStatefulState();
  };*/
}

class _HomeStatefulState extends State<HomeStateful> {

  var _texto = "Evandro Ribeiro";

  @override
  Widget build(BuildContext context) {
    print("Build Chamado");
    return  Scaffold(
      appBar: AppBar(
        title: Text(_texto),
        backgroundColor: Colors.green,
      ),
      body: Container(
        child: Column(
          children: [
            RaisedButton(
                onPressed: (){
                  setState((){
                    _texto = "Curso Flutter";
                  });
                  print("Clicado");
                },
              color: Colors.amber,
              child: Text("Clique aqui"),
            ),
            Text("Nome: $_texto")
          ],
        ),
      ),
    );
  }
}
