import 'package:flutter/material.dart';

class ResponsividadeRowCol extends StatefulWidget {
  const ResponsividadeRowCol({super.key});

  @override
  State<ResponsividadeRowCol> createState() => _ResponsividadeRowColState();
}

class _ResponsividadeRowColState extends State<ResponsividadeRowCol> {
  @override
  Widget build(BuildContext context) {

    var largura = MediaQuery.of(context).size.width;
    var altura = MediaQuery.of(context).size.height;

    return Scaffold(
      appBar: AppBar(
        title: Text("Responsividade"),
        backgroundColor: Colors.blueGrey,
      ),
      body: Column(//poderia usar uma row desta forma dividiria em duas colunas
        children: [
          Expanded(//Expanded toma o restante do espaço disponivel
            //o flex pode ser em porcentagem exemplo em um total de 100 poderia ser 20
            flex: 1, // atributo contempla valor 4 neste caso usando 1
            child: Container(
              color: Colors.red,
            ),
          ),
          Expanded(//Expanded toma o restante do espaço disponivel
            //o flex pode ser em porcentagem exemplo em um total de 100 poderia ser 60
            flex: 3, // atributo contempla valor 4 neste caso usando 3
            child: Row(
              children: [
                Expanded(
                    flex: 1,
                    child: Container(
                      color: Colors.pink,
                    )
                ),
                Expanded(
                    flex: 1,
                    child: Container(
                      color: Colors.purple,
                    )
                ),
                Expanded(
                    flex: 1,
                    child: Container(
                      color: Colors.green,
                    )
                )
              ],
            )
          ),
          Expanded(//Expanded toma o restante do espaço disponivel
            //o flex pode ser em porcentagem exemplo em um total de 100 poderia ser 20
            flex: 1, // atributo contempla valor 4 neste caso usando 1
            child: Container(
              color: Colors.orange,
            ),
          ),
        ],
      ),
    );
  }
}
