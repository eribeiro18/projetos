import 'package:flutter/material.dart';
import 'package:navegacao_route/TelaSecundaria.dart';

//rotas nomeadas
void main(){
  runApp(MaterialApp(
    //inicilizando com barra / assume que a rota inicial é main.dart
    initialRoute: "/",
    routes: {
      //adotando rotas é um das opções pra navegação entre telas
      "/secundaria": (context) => TelaSecundaria(),
    },
    home: TelaPrincipal(),
  ));
}

class TelaPrincipal extends StatefulWidget {
  const TelaPrincipal({Key? key}) : super(key: key);

  @override
  State<TelaPrincipal> createState() => _TelaPrincipalState();
}

class _TelaPrincipalState extends State<TelaPrincipal> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Tela Princiapl"),
        backgroundColor: Colors.orange,
      ),
      body: Container(
        padding: EdgeInsets.all(32),
        child: Column(
          children: [
            ElevatedButton(
                onPressed: (){
                  Navigator.pushNamed(context, "/secundaria");
                },
                child: Text("Ir para a segunda tela"),
            )
          ],
        ),
      ),
    );
  }
}
