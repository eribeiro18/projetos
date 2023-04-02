import 'package:flutter/material.dart';


class ResponsividadeRowCol extends StatefulWidget {
  const ResponsividadeRowCol({Key? key}) : super(key: key);

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
      ),
      body: Column(
        children: [
      /*    Container(
            color: Colors.red,
            height: 200,
          ),*/
          //expanded ocupa o espaço disponivel
          //propriedade flex facilita a contagem onde conforme abaixo
          //o primeiro ocupara uma parte e o de baixo
          //ocupara 3x o que o primeiro ocupa
          Expanded(
            flex: 1,
            child: Container(
              color: Colors.red,
            ),
          ),
          Expanded(
            flex: 3,
            child: Container(
              color: Colors.orange,
            ),
          ),
          Expanded(
            flex: 1,
              child: Container(
                color: Colors.cyanAccent,
              ),
          ),
        ],
      ),
    );
  }
}
