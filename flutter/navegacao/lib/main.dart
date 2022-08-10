import 'package:flutter/material.dart';
import 'package:navegacao/TelaSecundaria.dart';

void main(){
  runApp(MaterialApp(
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
                  //push abre uma tela que foi definido
                  //pop fecha a atual e abre uma nova qualquer um pode ser usado
                  Navigator.push(
                      context, 
                      MaterialPageRoute(
                          builder: (context) => TelaSecundaria("Evandro Ribeiro")
                      )
                  );
                },
                child: Text("Ir para a segunda tela"),

            )
          ],
        ),
      ),
    );
  }
}
