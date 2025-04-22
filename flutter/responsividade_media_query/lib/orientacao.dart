import 'package:flutter/material.dart';

class Orientacao extends StatefulWidget {
  const Orientacao({super.key});

  @override
  State<Orientacao> createState() => _OrientacaoState();
}

class _OrientacaoState extends State<Orientacao> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Orientação"), backgroundColor: Colors.lightBlue,),
      body: OrientationBuilder(
          builder: (context, orientacao){
/*            return Container(
              child: orientacao == Orientation.portrait
                ? Text("Portrait") : Text("Landscape"),
            );*/
          return GridView.count(
                crossAxisCount: orientacao == Orientation.portrait ? 2: 4,//quantos blocos serão exibidos na grid
                children: [
                    Container(color: Colors.red),
                    Container(color: Colors.green),
                    Container(color: Colors.orange),
                    Container(color: Colors.blue),
                    Container(color: Colors.purple),
                    Container(color: Colors.yellow),
                ],
              );
          },
      ),
    );
  }
}
